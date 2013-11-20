package Backend;

import java.awt.List;
import java.util.ArrayList;

import Agents.EquipletAgent;

/*
 * This class is inspired by "gridscenario.c" wich is part of "ToolkitLvM". 
 * 
 */

public class ProductStepGenerators {
	private static int gridsize;
	//private static ArrayList<String[]> products;
	private static ArrayList<ArrayList> product_batches = new ArrayList<ArrayList>();
	private static ArrayList<String> batch_names = new ArrayList<String>();
	
	public static void setGridSize(int amount){
		gridsize = amount;
	}
	
	public static ArrayList getBatch(String name){
		int counter = 0, found = 0;
		for(String s: batch_names){
			if (s == name){
				found = counter;
				break;
			}
			counter++;
		}
		
		ArrayList<Object[]> batch = product_batches.get(found);
		
		return batch;
	}
	
	public static ArrayList<ArrayList> getProducts(){
		return product_batches;
	}
	
	public static void generateProductBatch(int product_count, int max_product_steps, String type, String structure_name){
		
		
		Grid.setGrid(structure_name);
		gridsize = Grid.getMaxvalue();
		
		ArrayList<Object[]> products = new ArrayList<Object[]>();
		for (int i =0; i < product_count; i++){
			Object[] steps = {};
			
			switch(type){
			case "random":
				steps = ProductStepGenerators.generateRandomSteps(max_product_steps);
				break;
			case "increase":
				steps = ProductStepGenerators.generateIncreasedSteps(max_product_steps);
				break;
			case "+25%":
				steps = ProductStepGenerators.generate25PercentPopularSteps(max_product_steps);
				break;
			default:
				steps = ProductStepGenerators.generateRandomSteps(max_product_steps);
			};
			products.add(steps);
		}

		
		batch_names.add(structure_name);
		product_batches.add(products);
		System.out.println("Products created for " + structure_name);
	}
	
	public static String[] generateRandomSteps(int amount){
		// Make sure that gridsize has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version randomness rules.
		String[] steps = new String[amount];
    	for (int j = 0; j < amount; j++){      	
    	    Integer step = (int)(Math.random()*(gridsize-1));
    	    System.out.println(step);
    	    steps[j] = step.toString();
    	}
    	

		return steps;
	}
	
	public static String[] generateIncreasedSteps(int amount){
		// Make sure that gridsize has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version likes higher numbers 
		// more then low numbers
		String[]steps = new String[amount];
		
		for (int j = 0; j < amount; j++){     	
			Integer step = (int) (Math.random()*(gridsize-1));
			Integer tmp = (int) (Math.random()*(gridsize-1));
		    if(step >= tmp){
		    	steps[j] = step.toString();
		    } else {
		    	j--; /* reject step and try again */
		    }
		}

		return steps;
	}
	
	public static String[] generate25PercentPopularSteps(int amount){
		// Make sure that gridsize has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version the 25% highest 
		// values are more likely to get chosen. (because they might offer multiple steps)
		String[]steps = new String[amount];

		for (int j = 0; j < amount; j++){    
			
			
			Integer range = gridsize+(gridsize/4);
			Integer step = (int) (Math.random()*(range-1));
    	    
    	    if (step >= gridsize){

    	    	//Integer tmp = (int) (step-(gridsteps*0.625));
    	    	Integer tmp = (int) (Math.random()*(gridsize-1));
    	    	steps[j] = tmp.toString();;
    	    } else {
    	    	steps[j] = step.toString();;
    	    }
		}

		return steps;
	}
}