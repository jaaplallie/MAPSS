package Backend;

import jade.wrapper.StaleProxyException;

import java.util.ArrayList;

import Agents.ProductAgent;

public class Simulations {	
	public static int finished_products;
	public static int unfinished_products;
	protected static String name_of_current_structure;
	
	protected static double product_lengths = 0;
	

	
	
	public static double productAgentsInRegularGridSimulation(String structure_name, String log_name){
		
		setGrid(structure_name);
		Scenario S = ScenarioList.getScenario(structure_name);
		
		System.out.println("Creating the nessesary files.....");
		System.out.println("Generating a set of products and giving them product agents.....");
		
		MapssFileWriter.createLogFile(log_name);
		MapssFileWriter.writeLogLn("***********************Configurations*************************************");
		MapssFileWriter.writeLogLn("Number of products: " + S.products.size());
		MapssFileWriter.logGrid(S);
		MapssFileWriter.logNeighbors(S);
		MapssFileWriter.writeLogLn("**************************************************************************");
		
		AgentEnvironmentCreator.start();
		for (int i =0; i < S.products.size(); i++){
			int[] arguments = S.products.get(i);

			ProductAgent smith = new ProductAgent("Product" + i , arguments);
			try {
				AgentEnvironmentCreator.addProductAgent(smith);
				Thread.sleep(1);
			} catch (StaleProxyException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while ((finished_products + unfinished_products) < S.products.size()){} //Wait until each product agent is finished

		double average = getTotalAverageProductSteps()/S.products.size();
		MapssFileWriter.writeLogLn("The average path for each product step is: " + average + " long");

		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		
		MapssFileWriter.closeLog();
		System.out.println("Simulation " + log_name + " is finished");
		return average;
	}
	
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
	
	public static void setGrid(String structure_name){
		name_of_current_structure = structure_name;
	}
	
	public static String getCurrentName(){
		return name_of_current_structure;
	}
	
	public static double getTotalAverageProductSteps(){
		return product_lengths;
	}
	
	public static void addPathLenght(double hops) {
		product_lengths += hops;
		
	}


}