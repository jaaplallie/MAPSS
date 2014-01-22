package Agents;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import Backend.Grid;
import Backend.MapssFileHandler;
import Backend.ProgramData;
import Backend.Scenario;
import Backend.Simulations;
import Gui.ChartPresenter;

public class ProductAgent extends Agent { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4396688048039621513L;
	Grid gc;
	String code;
	Object[] args;
	
	public ProductAgent(String code, int[] arguments){
		this.code = code;
		
		Object[] temp = new Object[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			temp[i]= arguments[i];
		}
		this.args = temp;
		
		setArguments(temp);
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
				MapssFileHandler.writeLogLn("Hi. I'm product agent " + getAID().getLocalName() + "."); 
				MapssFileHandler.writeLogLn(""); //for visual effect
				Object[] args = myAgent.getArguments();
				String output = "";
				int[] productPath;
				int[] otherPath;
				
				Scenario S = ProgramData.getScenario(Simulations.getCurrentName());
				 
				boolean right_sized_grid = true;
				for (Object o : args) {
					 output = output + o.toString() + " ";  
					 if (S.getMax() < Integer.parseInt(o.toString())){
						 right_sized_grid = false;
					 }
				}

				MapssFileHandler.writeLogLn("These are my product's steps: " + output); 

				if (right_sized_grid == true){
					int start_position = 0;
					if (args[0] == null){
						
					} else {
						start_position = Integer.parseInt(args[0].toString());
					}
					
					MapssFileHandler.writeLogLn("I start at equiplet number: " + start_position); 
					
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
						int first_path[] = S.getPath(current_position, next_position);
						int second_path[] = S.getAlternativePath(current_position, next_position);
						
						for (int j: first_path){
							if (j == S.getMax()+1){
								destination_reachable = false;
								break;
							}
						}
						
						for (int j: second_path){
							if (j == S.getMax()+1){
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
							
							//Grid.addProductStepPath(chosen_path);
							
						} else {
							break;
						}
						
					}	
					
					
					if (destination_reachable == true){
						//Grid.addProductPath(productPath);
						
						Simulations.addFinishedProduct();
						MapssFileHandler.writeLog("Path I need to complete the product: ");
						for (int o: productPath){
							MapssFileHandler.writeLog(o+" ");
						}
						MapssFileHandler.writeLogLn("");
						MapssFileHandler.writeLogLn("Total hops needed for this: " + hops_in_path);
						double average = hops_in_path/((args.length)-1);
						
						MapssFileHandler.writeLogLn("Average number of hops needed for each product step: " + average);
						
						MapssFileHandler.writeLog("Alternative path: ");
						for (int o: otherPath){
							MapssFileHandler.writeLog(o+" ");
						}
						MapssFileHandler.writeLogLn("");
						MapssFileHandler.writeLogLn("Total hops needed for this: " + hops_in_other_path);
						double other_average = hops_in_other_path/((args.length)-1);
						
						MapssFileHandler.writeLogLn("Average number of hops needed for each product step: " + other_average);
						
						//Use the shortest path.
						if (average <= other_average){
							Simulations.addPathLenght(average);
						} else {
							Simulations.addPathLenght(other_average);
						}
						
						
					} else {
						MapssFileHandler.writeLog("I couldn't reach everything. This is as far as I got: ");
						
						Simulations.addUnfinishedProduct();
						for (int o: productPath){
							MapssFileHandler.writeLog(o+" ");
						}
						MapssFileHandler.writeLogLn("");
					}
					
					Simulations.addFinishedProduct();
					ChartPresenter.updateProgress(1);
					
				} else {
					MapssFileHandler.writeLogLn("Unfortunately I can't find a suitable grid so no calculations for me");
					if (right_sized_grid == false){
						MapssFileHandler.writeLogLn("The current grid is too small for my product steps");
					}
				}
				MapssFileHandler.writeLogLn("");
				
				myAgent.doDelete();
			 } 
			 
			  
		} );	
	} 
	
	protected void breakdown() {
		MapssFileHandler.writeLogLn("Agent "+getAID().getName()+" is gone now."); 
	}
}