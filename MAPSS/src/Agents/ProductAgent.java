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
				System.out.println(getAID().getLocalName());
				MapssFileWriter.writeLogLn("Hi. I'm product agent " + getAID().getLocalName() + "."); 
				Object[] args = myAgent.getArguments();
				String output = "";
				int[] productPath;
				int[] otherPath;
				 
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
					int start_position = 0;
					if (args[0] == null){
						
					} else {
						start_position = Integer.parseInt(args[0].toString());
					}
					
					int[] start_xy_values = Grid.getEquipletPosition(start_position);
					
					MapssFileWriter.writeLogLn("I start at equiplet number: " + start_position); 
					
					int current_position = start_position;
					int next_position;
					double hops_in_path = 0;
					double hops_in_other_path = 0;
					
					productPath = new int[]{start_position};
					otherPath = new int[]{start_position};
					
					boolean destination_reachable = true;
					for (int i = 1; i < args.length; i++){
						next_position = Integer.parseInt(args[i].toString());
						
						// Fetch 2 path's
						int first_path[] = Grid.calculatePath(current_position, next_position, 0, Grid.getCurrentNeighbors());
						for (int j: first_path){
							if (j == Grid.getMaxvalue()+1){
								destination_reachable = false;
								break;
							}
						}
						
						int second_path[] = Grid.calculatePath(current_position, next_position, 1, Grid.getCurrentNeighbors());
						for (int j: second_path){
							if (j == Grid.getMaxvalue()+1){
								destination_reachable = false;
								break;
							}
						}
						
						if (destination_reachable == true){
							current_position = next_position;
							
							hops_in_path += first_path.length;
							
							int[] tempPath = new int[(int) hops_in_path+1];
							System.arraycopy(productPath, 0, tempPath, 0, productPath.length);
							System.arraycopy(first_path, 0, tempPath, productPath.length, first_path.length);
							
							productPath = new int[(int) hops_in_path+1];
							productPath = tempPath;
							
							
							hops_in_other_path += second_path.length;
							
							tempPath = new int[(int) hops_in_other_path+1];
							System.arraycopy(otherPath, 0, tempPath, 0, otherPath.length);
							System.arraycopy(second_path, 0, tempPath, otherPath.length, second_path.length);
							
							otherPath = new int[(int) hops_in_other_path+1];
							otherPath = tempPath;
							
							
							// Choose the shortest path
							int chosen_path[];
							if (productPath.length <= otherPath.length){
								chosen_path = first_path;
								
							} else {
								chosen_path = second_path;
							}
							
							Grid.addProductStepPath(chosen_path);
							
						} else {
							break;
						}
						
					}	
					
					
					
					
					
					if (destination_reachable == true){
						//Grid.addProductPath(productPath);
						
						Simulations.addFinishedProduct();
						MapssFileWriter.writeLog("Path I need to complete the product: ");
						for (int o: productPath){
							MapssFileWriter.writeLog(o+" ");
						}
						MapssFileWriter.writeLogLn("");
						MapssFileWriter.writeLogLn("Total hops needed for this: " + hops_in_path);
						double average = hops_in_path/((args.length)-1);
						
						MapssFileWriter.writeLogLn("Average number of hops needed for each product step: " + average);
						MapssFileWriter.writeLogLn("");
						
						MapssFileWriter.writeLog("Alternative path: ");
						for (int o: otherPath){
							MapssFileWriter.writeLog(o+" ");
						}
						MapssFileWriter.writeLogLn("");
						MapssFileWriter.writeLogLn("Total hops needed for this: " + hops_in_other_path);
						double other_average = hops_in_other_path/((args.length)-1);
						
						MapssFileWriter.writeLogLn("Average number of hops needed for each product step: " + other_average);
						
						//Use the shortest path.
						if (average <= other_average){
							Grid.addPathLenght(average);
						} else {
							Grid.addPathLenght(other_average);
						}
						
						
					} else {
						MapssFileWriter.writeLog("I couldn't reach everything. This is as far as I got: ");
						
						Simulations.addUnfinishedProduct();
						for (int o: productPath){
							MapssFileWriter.writeLog(o+" ");
						}
						MapssFileWriter.writeLogLn("");
					}
					
					
					
					//Simulations.addFinishedProduct();
					
					ChartPresenter.updateProgress(1);
					
					
			
					
					
					
					
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