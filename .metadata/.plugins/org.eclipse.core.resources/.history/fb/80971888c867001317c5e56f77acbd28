package Agents;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

import java.util.ArrayList;

import Backend.Grid;
import Backend.MapssFileWriter;
import Backend.Simulations;
import Gui.ChartPresenter;

public class ProductAgent extends Agent { 
	Grid gc;
	String code;
	Object[] args;
	
	public ProductAgent(String code, Object[] args){
		this.code = code;
		this.args = args;
		setArguments(args);
	}
	
	public String getCode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}
	
	public String getArgsString() {
		String returnVal = "";
		for(int i = 0; i < args.length; i++){
			if(i < args.length && args.length > 1){
				returnVal += ",";
			}
			returnVal += args[i].toString();
		}
		return returnVal;
	}
	
	protected void setup()  { 
		addBehaviour(new WakerBehaviour(this, 0)  { 
			 protected void handleElapsedTimeout() { 
				MapssFileWriter.writeLogLn("Hi. I'm product agent " + getAID().getLocalName() + "."); 
				Object[] args = myAgent.getArguments();
				String output = "";
				int[] productPath;
				 
				boolean right_sized_grid = true;
				for (Object o : args) {
					 output = output + o.toString() + " ";  
					 if (Grid.getMaxvalue() < Integer.parseInt(o.toString())){
						 right_sized_grid = false;
					 }
				}

				MapssFileWriter.writeLogLn("These are my product's steps: " + output); 
				
				//Checking if the grid exists and if there's anything useful in it
				EquipletAgent[][] grid = Grid.getGrid();
				
				boolean safe_grid = false;
				if (grid != null){
					for (Object ob : grid) {
						if (ob != null ) {
							safe_grid = true;
							break;
						}
					}
				}
				
				
				if (safe_grid == true & right_sized_grid == true){
					//int start_position = (int)(Math.random()*(grid.length*grid[0].length));
					int start_position = 0;
					if (args[0] == null){
						
					} else {
						start_position = Integer.parseInt(args[0].toString());
					}
					
					int[] start_xy_values = Grid.getEquipletPosition(start_position);
					//ArrayList<Integer> productPath = 
					
					MapssFileWriter.writeLogLn("I start at equiplet number: " + start_position); 
					MapssFileWriter.writeLogLn("That equiplet's position is " + 
							" x:" + start_xy_values[0] + 
							", y:" + start_xy_values[1]); 
					
					int current_position = start_position;
					int next_position;
					double hops = 0;
					ArrayList<Integer> hops_per_step_counter = new ArrayList<Integer>();
					
					productPath = new int[]{start_position};
					
					//Object O = args[0];
					
					//for (Object o : args) {
					for (int i = 1; i < args.length; i++){
						next_position = Integer.parseInt(args[i].toString());
						//next_position = Integer.parseInt(o.toString());
						int path[] = Grid.calculateDifferentPath(current_position, next_position);
						Grid.addProductStepPath(path);
						
						current_position = next_position;
						hops += path.length;
						
						int[] tempPath = new int[(int) hops+1];
						System.arraycopy(productPath, 0, tempPath, 0, productPath.length);
						System.arraycopy(path, 0, tempPath, productPath.length, path.length);
						
						productPath = new int[(int) hops+1];
						productPath = tempPath;
						
						hops_per_step_counter.add(path.length);
					}	
					
					Grid.addProductPath(productPath);
					
					MapssFileWriter.writeLog("Path I need to complete the product: ");
					for (int o: productPath){
						MapssFileWriter.writeLog(o+" ");
					}
					MapssFileWriter.writeLogLn("");
					
					Simulations.addFinishedProduct();
					ChartPresenter.updateProgress(1);
					
					MapssFileWriter.writeLogLn("Total hops needed for this product: " + hops);
					double average = hops/args.length;
					MapssFileWriter.writeLogLn("Average number of hops needed for each product step: " + average);
			
					
					
					
					
				} else {
					MapssFileWriter.writeLogLn("Unfortunately I can't find a suitable grid so no calculations for me");
					if (safe_grid == false){
						MapssFileWriter.writeLogLn("The grid is either not there or it contains empty values");
					}
					if (right_sized_grid == false){
						MapssFileWriter.writeLogLn("The current grid is too small for my product steps");
					}
				}
				MapssFileWriter.writeLogLn("");
				
				myAgent.doDelete();
			 } 
			 
			  
		} );	
	} 
	
	protected void breakdown() {
		MapssFileWriter.writeLogLn("Agent "+getAID().getName()+" is gone now."); 
	}
}