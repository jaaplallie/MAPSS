package Backend;

import jade.wrapper.StaleProxyException;
import Agents.ProductAgent;
import Gui.MainWindow;

public class Simulations {	
	public static int finished_products;
	public static int unfinished_products;
	protected static String name_of_current_structure;
	
	protected static double product_lengths = 0;
	

	
	
	public static double productAgentsInRegularGridSimulation(String name){
		
		setGrid(name);
		Scenario S = ScenarioList.getScenario(name);
		
		System.out.println("Creating the nessesary files.....");
		System.out.println("Generating a set of products and giving them product agents.....");
		
		MainWindow.stringToOutput("Now running " +S.name);
		
		MainWindow.stringToOutput("That has: " + S.products.size() + " products");
		
		MapssFileHandler.createLogFile(name);
		MapssFileHandler.writeLogLn("***********************Configurations*************************************");
		MapssFileHandler.writeLogLn("Number of products: " + S.products.size());
		MapssFileHandler.logGrid(S);
		MapssFileHandler.logNeighbors(S);
		MapssFileHandler.writeLogLn("**************************************************************************");
		
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
		MapssFileHandler.writeLogLn("The average path for each product step is: " + average + " long");

		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		
		MapssFileHandler.closeLog();
		System.out.println("Simulation " + name + " is finished");
		MainWindow.stringToOutput("Simulation " + name + " is finished");
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
		product_lengths = 0;
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