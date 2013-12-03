package Backend;

import java.awt.List;
import java.util.ArrayList;

import Agents.EquipletAgent;
import Gui.ChartPresenter;

/***************************************************************************
 * This class is inspired by "gridscenario.c" wich is part of "ToolkitLvM". 
 * 
 ***************************************************************************/

public class ProductStepGenerators {
	private static int gridsteps;
	//private static ArrayList<String[]> products;
	private static ArrayList<ArrayList<String[]>> product_batches = new ArrayList<ArrayList<String[]>>();
	private static ArrayList<String> batch_names = new ArrayList<String>();
	
	public static void setgridsteps(int amount){
		gridsteps = amount;
	}
	
	public static int randomInt(int range) {
		// Xorshift random number generator
		//System.out.println("test");
		long rand = range+1;
		rand ^= (rand << 21);
		rand ^= (rand >>> 35);
		rand ^= (rand << 4);
		
		if (rand < 0 || rand > range){
			rand = randomInt(range);
		}
		
		System.out.println(rand);
		
		return (int) rand;
	}
	
	public static ArrayList<String[]> getBatch(String name){
		int counter = 0, found = 0;
		for(String s: batch_names){
			System.out.println(s);
			if (s == name){
				found = counter;
				break;
			}
			counter++;
		}
		
		try {
			ArrayList<String[]> batch = product_batches.get(counter);
			return batch;
		} catch (IndexOutOfBoundsException i){
			return null;
		}
		
		//return null;
	}
	
	public static ArrayList<String[]> getProducts(String structure_name){
		try {
			return product_batches.get(Grid.getIndex(structure_name));
		} catch (IndexOutOfBoundsException i){
			
		}
		return null;
	}
	
	public static void generateProductBatch(int product_count, int max_product_steps, String type, String structure_name){
			
		Grid.setGrid(structure_name);
		gridsteps = Grid.getMaxvalue();
		// Make sure that gridsteps has a value (setgridsteps) or you will get a list of 0's
		
		
		ArrayList<String[]> products = new ArrayList<String[]>();
		for (int i =0; i < product_count; i++){
			int amount = (int)(Math.random()*(max_product_steps-1));
			amount++;
			String[] steps = {};
			
			switch(type){
			case "random":
				steps = ProductStepGenerators.generateRandomSteps(amount);
				break;
			case "increase":
				steps = ProductStepGenerators.generateIncreasedSteps(amount);
				break;
			case "+25%":
				steps = ProductStepGenerators.generate25PercentPopularSteps(amount);
				break;
			default:
				steps = ProductStepGenerators.generateRandomSteps(amount);
			};
			products.add(steps);
		}
		
		batch_names.add(structure_name);
		product_batches.add(products);
//		System.out.println("Products created for " + structure_name);
//		for (String[] A: products){
//			for (String p: A){
//				System.out.print(p);
//	
//				System.out.println("");
//			}
//		}
		
		
		MapssFileWriter.saveStructure(structure_name, Grid.getNeighbors(structure_name), products);

		System.out.println(type + " products created for " + structure_name);
		
		MapssFileReader.loadStructures();
		ChartPresenter.updateChartStructures();
	}
	
	public static String[] generateRandomSteps(int nps){
		/* flat distributed set */
		String[] steps = new String[nps];
		
    	for (int j = 0; j < nps; j++){      	
    	    Integer step = (int)(Math.random()*(gridsteps-1));
    	    steps[j] = step.toString();
    	}
    	
		return steps;
	}
	
	public static String[] generateIncreasedSteps(int nps){
		/* increasing set */
		String[]steps = new String[nps];
		
		for (int j = 0; j < nps; j++){     	
			Integer step = (int) (Math.random()*(gridsteps-1));
			Integer tmp = (int) (Math.random()*(gridsteps-1));
		    if(step >= tmp){
		    	steps[j] = step.toString();
		    } else {
		    	j--; /* reject step and try again */
		    }
		}
		return steps;
	}
	
	public static String[] generate25PercentPopularSteps(int amount){
		
		
		// This function generates a list of product steps. In this version 25% of the 
		// values are more likely to get chosen. (because they might offer multiple steps)
		String[]steps = new String[amount];
		
//		int doubles[] = new int[gridsteps/4];
//		for (int i = 0; i < gridsteps/4; i++){
//			doubles[i] = (int) (Math.random()*gridsteps);
//		}

		for (int j = 0; j < amount; j++){    
			
			
			Integer step = (int) (Math.random()*(gridsteps-1));
			
			//Integer range = gridsteps+(gridsteps/4);
			//Integer step = (int) (Math.random()*(range-1));
    	    
    	    //if (step >= gridsteps){
			if (step < 8){

    	    	//Integer tmp = doubles[step-gridsteps];

    	    	//Integer tmp = (int) (Math.random()*(gridsteps-1));
    	    	//steps[j] = tmp.toString();
				steps[j] = step.toString();
    	    } else {
    	    	j--; /* reject step and try again */
    	    	steps[j] = step.toString();
    	    }
		}

		return steps;
	}
	
	public static void addProductBatch(String structure_name, ArrayList<String[]> product_list){
		batch_names.add(structure_name);
		product_batches.add(product_list);	
		
		for (String[] p: product_list){
			for(String i: p){
				System.out.print(i);

			}
			System.out.println();
		}
		System.out.println("Products created for " + structure_name);
	}

}