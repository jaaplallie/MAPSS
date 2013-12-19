package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jade.wrapper.StaleProxyException;
import Agents.EquipletAgent;
import Agents.ProductAgent;
import Gui.ChartPresenter;

public class Simulations {	
	public static int finished_products;
	
	public static void addFinishedProduct() {
		finished_products ++;
	}
	
	public static void clearProducts(){
		finished_products = 0;
	}
	
	public static double productAgentsInRegularGridSimulation(String structure_name, String log_name){
		AgentEnvironmentCreator.start();
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
		System.out.println(products.size());
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
		
		while (finished_products < products.size()){} //Wait until each product agent is finished

		double average = Grid.getAverageProductStepPath();
		MapssFileWriter.writeLogLn("The average path for each product step is: " + Grid.getAverageProductStepPath() + " long");
		MapssFileWriter.writeLogLn("The average path for each product is: " + Grid.getAverageProductPath() + " long");
//		
//		clearProducts();
//		AgentEnvironmentCreator.destroyMainContainer();
//		Grid.clearProductStepPaths();
//		Grid.clearProductPaths();
//		
//		
//
//		
//		AgentEnvironmentCreator.start();
//		Object[] args = new Object[0];
//		String s;
//		int counter = 0;
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("C:/Test.txt"));
//			while ((s = br.readLine()) != null) {
//				
//				
//				Pattern step_pattern = Pattern.compile("(<STEPS>)(.*)(</STEPS>)");
//				Matcher step_matcher = step_pattern.matcher(s);
//				while (step_matcher.find()) {
//					String temp = step_matcher.group(2);
//					args = temp.split(",");
//				}
//				
//				ProductAgent smith = new ProductAgent("Product" + counter , args);
//				try {
//					AgentEnvironmentCreator.addProductAgent(smith);
//					Thread.sleep(1);
//				} catch (StaleProxyException | InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				if (counter >= 1000){
//					break;
//				}
//				counter++;
//				
//			}
//			br.close();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		MapssFileWriter.writeLogLn("The average path for each product step is: " + Grid.getAverageProductStepPath() + " long");
//		MapssFileWriter.writeLogLn("The average path for each product is: " + Grid.getAverageProductPath() + " long");
//		
		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		Grid.clearProductStepPaths();
		Grid.clearProductPaths();
		
		
		MapssFileWriter.closeLog();

		System.out.println("Simulation " + log_name + " is finished");
		
		return average;
	}
}