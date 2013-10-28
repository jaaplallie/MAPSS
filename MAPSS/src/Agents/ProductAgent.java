package Agents;

import jade.core.AID;
import jade.core.Agent; 
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentController;
 
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
	
	
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() {
				StringBuilder sb = new StringBuilder();
				sb.append("\nHi. I'm product agent " + getAID().getLocalName() + "."); 
				Object[] args = myAgent.getArguments();
				String output = "";
				 
				//GridClasses gc = new GridClasses();
				 
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
				int hops = 0;
				
				for (Object o : args) {
					next_position = Integer.parseInt(o.toString());
					int path[] = Grid.calculatePath(current_position, next_position);
					int xsteps = path[0];
					int ysteps = path[1];
					sb.append("\nTo go from " + current_position + " to " + next_position + " we need " 
							+ xsteps + " x steps and " + ysteps + " y steps."); 
					current_position = next_position;
					if (xsteps<0){
						xsteps = -xsteps;
					} 
					if (ysteps<0){
						ysteps = -ysteps;
					} 
					hops = hops + (xsteps+ysteps);
				}
				sb.append("\n");
				sb.append("\nTotaal aantal hops nodig voor dit product: " + hops);
				sb.append("\nGemiddeld aantal hops per stap: " + hops/args.length);
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