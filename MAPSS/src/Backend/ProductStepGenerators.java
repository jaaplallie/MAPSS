package Backend;

import java.util.ArrayList;

import Gui.ChartPresenter;

/***************************************************************************
 * This class is inspired by "gridscenario.c" wich is part of "ToolkitLvM". 
 * 
 ***************************************************************************/

public class ProductStepGenerators {
	private static ArrayList<ArrayList<int[]>> product_batches = new ArrayList<ArrayList<int[]>>();
	private static ArrayList<String> batch_names = new ArrayList<String>();
	
	public static ArrayList<int[]> getBatch(String name){
		int counter = 0, found = 0;
		for(String s: batch_names){
			//System.out.println(s);
			if (s == name){
				found = counter;
				break;
			}
			counter++;
		}
		
		try {
			ArrayList<int[]> batch = product_batches.get(counter);
			return batch;
		} catch (IndexOutOfBoundsException i){
			return null;
		}
		
		//return null;
	}
	
	public static ArrayList<int[]> getProducts(String structure_name){
		try {
			return product_batches.get(Grid.getIndex(structure_name));
		} catch (IndexOutOfBoundsException i){
			
		}
		return null;
	}
	
	public static ArrayList<Integer> generateForbiddenList(Scenario S){
		ArrayList<Integer> forbidden_list = new ArrayList<Integer>();
		int count = 0;
		for (ArrayList<Integer> n: S.neighbors){
			if (n.size() <= 0){
				forbidden_list.add(count);
			}
			count++;
		}
		
		return forbidden_list;
		
	}
	
	
	public static void generateProductBatch(int product_count, int max_product_steps, String type, String structure_name){
			
		Scenario S = ScenarioList.getScenario(structure_name);
		//Grid.setGrid(structure_name);
		//gridsteps = Grid.getMaxvalue();
		//gridsteps = S.x*S.y;
		// Make sure that gridsteps has a value (setgridsteps) or you will get a list of 0's
		
		
		ArrayList<Integer> forbidden_list = generateForbiddenList(S);
		
		
		
		ArrayList<int[]> products = new ArrayList<int[]>();
		for (int i =0; i < product_count; i++){
			int amount = (int)(Math.random()*(max_product_steps));
			amount++;
			amount++;
			// we need a minimum of 2 product steps
			int[] steps = {};
			
			switch(type){
			case "random":
				steps = ProductStepGenerators.generateRandomSteps(amount, S.x*S.y, forbidden_list);
				break;
			case "increase":
				steps = ProductStepGenerators.generateIncreasedSteps(amount, S.x*S.y);
				break;
			default:
				steps = ProductStepGenerators.generateRandomSteps(amount, S.x*S.y, forbidden_list);
			};
			products.add(steps);
		}
		
		S.addProducts(products);
		
		batch_names.add(structure_name);
		product_batches.add(products);
		
		
//		if (products != null){
//		//ArrayList<String[]> products = ProductStepGenerators.getBatch(structure_name);
//		//System.out.println(products);
//		for (String[] productsteps : products) {
//			for (String s: productsteps){
//				structureWriter.print(s+" ");
//			}
//			structureWriter.println("");
//		}
//
//	}
		

		MapssFileWriter.saveProducts(structure_name, products);
		//MapssFileWriter.saveStructure(structure_name, Grid.getNeighbors(structure_name), products);

		System.out.println(type + " products created for " + structure_name);
		
		MapssFileReader.loadStructures();
		ChartPresenter.updateChartStructures();
	}
	
	public static int[] generateRandomSteps(int nps, int gridsteps, ArrayList<Integer> forbidden_list){
		/* flat distributed set */
		int[] steps = new int[nps];
		
		boolean safe = true;
		
    	for (int j = 0; j < nps; j++){      	
    	    Integer step = (int)(Math.random()*(gridsteps));
    	    for (int i: forbidden_list){
    	    	if (i == step){
    	    		safe = false;
    	    	}
    	    }
    	    
    	    if (safe == true){
    	    	steps[j] = step;
    	    } else {
    	    	j--;
    	    	safe = true;
    	    }
    	    
    	}
    	
		return steps;
	}
	
	public static int[] generateIncreasedSteps(int nps, int gridsteps){
		/* increasing set */
		int[]steps = new int[nps];
		
		for (int j = 0; j < nps; j++){     	
			Integer step = (int) (Math.random()*(gridsteps));
			Integer tmp = (int) (Math.random()*(gridsteps));
		    if(step >= tmp){
		    	steps[j] = step;
		    } else {
		    	j--; /* reject step and try again */
		    }
		}
		return steps;
	}
	
	
	public static void addProductBatch(String structure_name, ArrayList<int[]> product_list){
		batch_names.add(structure_name);
		product_batches.add(product_list);	
		
		System.out.println("Products created for " + structure_name);
	}

}