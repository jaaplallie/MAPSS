package Backend;

import java.util.ArrayList;

import jade.wrapper.StaleProxyException;
import Agents.ProductAgent;

public class Simulations {	
	public static int finished_products;
	
	public static void addFinishedProduct() {
		finished_products ++;
	}
	
	public static void clearProducts(){
		finished_products = 0;
	}
	
	public static double productAgentsInRegularGridSimulation(String log_name){
		

		AgentEnvironmentCreator.start();
		
		System.out.println("Creating the nessesary files.....");

		System.out.println("Generating a set of products and giving them product agents.....");
		
		//ProductStepGenerators.setGridSize(gridx*gridy);
		
		//ProductStepGenerators.generateProductBatch(number_of_products, max_product_steps, type);
		
		ArrayList<ArrayList> A = ProductStepGenerators.getProducts();
		ArrayList<Object[]> products = A.get(0);
		
		
		Log.createLogFile(log_name);
		Log.writeln("***********************Configurations*************************************");
		Log.writeln("Number of products: " + products.size());

		Grid.logGrid();
		Grid.logNeighbors();
		Matrix.logMatrix();
		Log.writeln("**************************************************************************");
		
		
		for (int i =0; i < products.size(); i++){
			Object[] arguments = products.get(i);

			ProductAgent smith = new ProductAgent("Product" + i , arguments);
			try {
				AgentEnvironmentCreator.addProductAgent(smith);
				System.out.println("Agent created");
				Thread.sleep(1);
			} catch (StaleProxyException | InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		while (finished_products < products.size()){}
		
		//gc.printPaths();
		double a = Grid.getAverageProductStepPath();
		
		Log.writeln("The average path for each product step is: " + Grid.getAverageProductStepPath() + " long");
		Log.writeln("The average path for each product is: " + Grid.getAverageProductPath() + " long");
		Log.close();
		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		Grid.clearProductStepPaths();
		Grid.clearProductPaths();
		System.out.println("Simulation " + log_name + " is finished");
		
		return a;

		
	}
	
}