package Agents;

import jade.core.AID;
import jade.core.Agent; 
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentController;
import java.util.List; 

import Backend.Grid;

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
	
	
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() {
				StringBuilder sb = new StringBuilder();
				sb.append("\nHi. I'm product agent " + getAID().getLocalName() + "."); 
				Object[] args = myAgent.getArguments();
				String output = "";
				 
				 
				for (Object o : args) {
					 output = output + o.toString() + " ";  
				}
				sb.append("\nThese are my product's steps: " + output);
				 
				EquipletAgent grid[][] = Grid.getGrid();
				int start_position = (int)(Math.random()*(grid.length*grid[0].length));
				int[] start_xy_values = Grid.getEquipletPosition(start_position);
				
				sb.append("\nI start at equiplet number:" + start_position); 
				sb.append("\nThat equiplet's position is " + 
						" x:" + start_xy_values[0] + 
						", y:" + start_xy_values[1]); 
				sb.append("\n");
				
				int current_position = start_position;
				int next_position;
				
				int[] path = {};
				
				for (Object o : args) {
					next_position = Integer.parseInt(o.toString());
					path = Grid.calculatePath(current_position, next_position);
					//path = Grid.calculateDifferentPath(current_position, next_position);
					sb.append("path: " + path); 
				}
				sb.append("\n");
				sb.append("\nTotaal aantal hops nodig voor dit product: " + path.length);
				sb.append("\nGemiddeld aantal hops per stap: " + path.length/args.length);
				sb.append("\n");
				System.out.println(sb.toString());
				//Note the empty println()'s make it easier for me to check for errors in the output.
			 } 
		} );	
	} 
	
	public int[] calculatePath(int start_equiplet_number, int end_equiplet_number){
		int startposition[] = Grid.getEquipletPosition(start_equiplet_number);
		int endposition[] = Grid.getEquipletPosition(end_equiplet_number);
		
		int x_difference = startposition[0]-endposition[0];
		int y_difference = startposition[1]-endposition[1];
		
		int path[] = {x_difference, y_difference};
		return path;
	}
	
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}