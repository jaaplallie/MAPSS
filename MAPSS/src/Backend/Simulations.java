package Backend;

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
	
	public static double productAgentsInRegularGridSimulation(
			int max_product_steps, 
			int products, 
			int gridx, 
			int gridy,
			String log_name,
			String type
			){
		
		// get a JADE runtime
		//Runtime rt = Runtime.instance();
		// create a default profile
		//Profile p = new ProfileImpl();
		// create the Main-container
		//ContainerController mainContainer = rt.createMainContainer(p);
		AgentEnvironmentCreator.start();
		
		System.out.println("Creating the nessesary files.....");
		
		MapssFileWriter.createLogFile(log_name);
		MapssFileWriter.writeLogLn("***********************Configurations*************************************");
		MapssFileWriter.writeLogLn("Number of products: " + products);
		MapssFileWriter.writeLogLn("Number of product steps per product: " + max_product_steps);

		Grid.createNormalGrid(gridx, gridy);
		Matrix.createMatrix(gridx, gridy);
		MapssFileWriter.writeLogLn("**************************************************************************");

		System.out.println("Generating a set of products and giving them product agents.....");
		ProductStepGenerators.setGridSize(gridx*gridy);
		for (int i =0; i < products; i++){
			Object[] arguments = {};
			switch(type){
			case "random":
				arguments = ProductStepGenerators.generateRandomSteps(max_product_steps);
				break;
			case "increase":
				arguments = ProductStepGenerators.generateIncreasedSteps(max_product_steps);
				break;
			case "+25%":
				arguments = ProductStepGenerators.generate25PercentPopularSteps(max_product_steps);
				break;
			default:
				arguments = ProductStepGenerators.generateRandomSteps(max_product_steps);
			}
			
			//Scenario new_scenario = new Scenario(log_name);
			
			//ProductAgent npa = new ProductAgent("Product" + i, arguments);
			//new_scenario.addProduct(npa);
			
			ProductAgent smith = new ProductAgent("Product" + i , arguments);
			try {
				AgentEnvironmentCreator.addProductAgent(smith);
				Thread.sleep(1);
			} catch (StaleProxyException | InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		while (finished_products < products){}
		
		//gc.printPaths();
		double a = Grid.getAverageProductStepPath();
		
		MapssFileWriter.writeLogLn("The average path for each product step is: " + Grid.getAverageProductStepPath() + " long");
		MapssFileWriter.writeLogLn("The average path for each product is: " + Grid.getAverageProductPath() + " long");
		MapssFileWriter.closeLog();
		clearProducts();
		AgentEnvironmentCreator.destroyMainContainer();
		Grid.clearProductStepPaths();
		Grid.clearProductPaths();
		System.out.println("Simulation " + log_name + " is finished");
		
		return a;

		
	}
	
}