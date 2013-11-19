package Backend;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is inspired by "gridscenario.c" wich is part of "ToolkitLvM". 
 * 
 */

public class ProductStepGenerators {
	private static int gridsteps;
	
	public static void setGridSize(int amount){
		gridsteps = amount;
	}
	
	public static String[] generateRandomSteps(int amount){
		// Make sure that gridsteps has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version randomness rules.
		String[] steps = new String[amount];
    	for (int j = 0; j < amount; j++){      	
    	    Integer step = (int)(Math.random()*(gridsteps-1));
    	    
    	    steps[j] = step.toString();
	}
		return steps;
	}
	
	public static String[] generateIncreasedSteps(int amount){
		// Make sure that gridsteps has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version likes higher numbers 
		// more then low numbers
		String[]steps = new String[amount];
		
		for (int j = 0; j < amount; j++){     	
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
		// Make sure that gridsteps has a value (setGridSize) or you will get a list of 0's
		
		// This function generates a list of product steps. In this version the 25% highest 
		// values are more likely to get chosen. (because they might offer multiple steps)
		String[]steps = new String[amount];

		for (int j = 0; j < amount; j++){    
			
			
			Integer range = gridsteps+(gridsteps/4);
			Integer step = (int) (Math.random()*(range-1));
    	    
    	    if (step >= gridsteps){

    	    	//Integer tmp = (int) (step-(gridsteps*0.625));
    	    	Integer tmp = (int) (Math.random()*(gridsteps-1));
    	    	steps[j] = tmp.toString();;
    	    } else {
    	    	steps[j] = step.toString();;
    	    }
		}

		
		return steps;
	}
}