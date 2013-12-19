package Backend;

import jade.wrapper.StaleProxyException;

import java.util.ArrayList;

import Agents.ProductAgent;

public class Simulations {	
	public static int finished_products;
	public static int unfinished_products;
	
	public static void addFinishedProduct() {
		finished_products ++;
	}
	
	public static void addUnfinishedProduct() {
		unfinished_products ++;
	}
	
	public static void clearProducts(){
		finished_products = 0;
		unfinished_products = 0;
	}
	
	public static double productAgentsInRegularGridSimulation(String structure_name, String log_name){
		
		Grid.setGrid(structure_name);
		Matrix.setMatrix(structure_name);
		ArrayList<String[]> products = ProductStepGenerators.getBatch(structure_name);
		
		System.out.println("Creating the nessesary files.....");
		System.out.println("Generating a set of products and giving them product agents.....");
		
		MapssFileWriter.createLogFile(log_name);
		MapssFileWriter.writeLogLn("***********************Configurations*************************************");
		MapssFileWriter.writeLogLn("Number of products: " + products.size());
		Grid.logGrid();
		Grid.logNeighbors();
		Matrix.logMatrix();
		MapssFileWriter.writeLogLn("**************************************************************************");
		
		AgentEnvironmentCreator.start();
		for (int i =0; i < products.size(); i++){
			Object[] arguments = products.get(i);

			ProductAgent smith = new ProductAgent("Product" + i , arguments);
			try {
				AgentEnvironmentCreator.addProductAgent(smith);
				Thread.sleep(1);
			} catch (StaleProxyException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while ((finished_products + unfinished_products) < products.size()){} //Wait until each product agent is finished

		double average = Grid.getTotalAverageProductSteps()/products.size();
		MapssFileWriter.writeLogLn("The average path for each product step is: " + average + " long");
		//MapssFileWriter.writeLogLn("The average path for each product is: " + Grid.getAverageProductPath() + " long");

		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		Grid.clearProductStepPaths();
		Grid.clearProductPaths();
		
		MapssFileWriter.closeLog();

		System.out.println("Simulation " + log_name + " is finished");
		
		return average;
	}


}