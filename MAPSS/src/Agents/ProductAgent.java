package Agents;

import jade.core.AID;
import jade.core.Agent; 
import jade.core.behaviours.WakerBehaviour;
import Backend.GridClasses;

/*
This class get's created by the XmlReader class.
It Communicates with
*/

public class ProductAgent extends Agent { 
	GridClasses gc;
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() { 
				System.out.println();
				System.out.println("Hi. I'm product agent " + getAID().getLocalName() + "."); 
				Object[] args = myAgent.getArguments();
				
				String output = "";
				for (Object o : args) {
					 output = output + o.toString() + " ";  
				}
				System.out.println("These are my product's steps: " + output); 
				 
				int grid[][] = GridClasses.getGrid();
				
				//int[] start_xy_values = GridClasses.getEquipletPosition(start_position);
				//System.out.println("I start at equiplet number:" + start_position); 
				//System.out.println("That equiplet's position is " + 
				//		" x:" + start_xy_values[0] + 
				//		", y:" + start_xy_values[1]); 
				//System.out.println();
				
				//Checking if the grid exists and if there's anything useful in it
				boolean dangerous_grit = true;
				if (grid != null){
					for (Object ob : grid) {
						if (ob != null ) {
							dangerous_grit = false;
							break;
						}
					}
				}
				
				if (dangerous_grit == false){
					int current_position = (int)(Math.random()*(grid.length*grid[0].length));
					int[] start_xy_values = GridClasses.getEquipletPosition(current_position);
					
					//System.out.println("I start at equiplet number:" + start_position); 
					//System.out.println("That equiplet's position is " + 
					//		" x:" + start_xy_values[0] + 
					//		", y:" + start_xy_values[1]); 
					//System.out.println();
					
					int next_position;
					int hops = 0;
					
					for (Object o : args) {
						next_position = Integer.parseInt(o.toString());
						int path[] = GridClasses.calculatePath(current_position, next_position);
						int xsteps = path[0];
						int ysteps = path[1];
						//System.out.println("To go from " + current_position + " to " + next_position + " we need " 
						//		+ xsteps + " x steps and " + ysteps + " y steps"); 
						current_position = next_position;
						if (xsteps<0){
							xsteps = -xsteps;
						} 
						if (ysteps<0){
							ysteps = -ysteps;
						} 
						hops = hops + (xsteps+ysteps);
					}	
					System.out.println();
					System.out.println("Totaal aantal hops nodig voor dit product: " + hops);
					System.out.println("Gemiddeld aantal hops per stap: " + hops/args.length);
					System.out.println();
					//Note the empty println()'s make it easier for me to check for errors in the output.
				} else {
					System.out.println("Unfortunately I can't find a suitable grid so no calculations for me"); 
				}
				System.out.println();
			 } 
		} );	
	} 
	
	public int[] calculatePath(int start_equiplet_number, int end_equiplet_number){
		int startposition[] = GridClasses.getEquipletPosition(start_equiplet_number);
		int endposition[] = GridClasses.getEquipletPosition(end_equiplet_number);
		
		int x_difference = startposition[0]-endposition[0];
		int y_difference = startposition[1]-endposition[1];
		
		int path[] = {x_difference, y_difference};
		return path;
	}
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}