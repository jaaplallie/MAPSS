package Backend;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import Agents.EquipletAgent;


public class Grid {
	protected static int grid[][];
	protected static AgentController[][] equiplets;
	private static AgentContainer container = AgentEnvironmentCreator.getContainer();
	
	public void create(int width , int length ){
		System.out.println("Grid layout:");
		grid = new int[width][length];
		equiplets = new AgentController[width][length];
		int stepnr = 1;
		for (int i = 0; i < length; i++){
			for (int j = 0; j < width; j++){
				grid[j][i]= stepnr;
				
				try {
					AgentController a_container;
//					Object[] args = {
//							Dictionary<> 
//							String.format("<X_POS={0}>", j),
//							String.format("<Y_POS={0}>", i),
//							String.format("<STEPNR={0}>", stepnr)
//					};
					a_container = container.createNewAgent("Equiplet_" + stepnr, "Agents.EquipletAgent", null);
					equiplets[j][i] = a_container;
					a_container.start();
					
					Thread.sleep(100);
				} 
				catch (StaleProxyException | InterruptedException e) {
					e.printStackTrace();
				} 
				
				stepnr++;
				
			}
		}
		System.out.println();
	}
	
	public static AgentController[][] getGrid(){
		return equiplets;
	}
	
	public int[] getRandomPosition(){
		int x = (int)(Math.random()*equiplets.length);
		int y = (int)(Math.random()*equiplets[0].length);
		int position[] = {x+1,y+1};
		return position;
	}
	
	public static int[] calculatePath(int start_equiplet_number, int end_equiplet_number){
		int startposition[] = getEquipletPosition(start_equiplet_number);
		int endposition[] = getEquipletPosition(end_equiplet_number);
		
		int x_difference = endposition[0]-startposition[0];
		int y_difference = endposition[1]-startposition[1];
		
		int path[] = {x_difference, y_difference};
		return path;
	}
	
	public static int[] getEquipletPosition(int equiplet_number){
		
		int x_length = equiplets.length;
		int x = 1;
		int y = 1;
		
		// If the equiplet is a higher number then the row then 
		// increment with the value of the next row. Repeat until 
		// the right row is found. When finished we have the y row.
		while(x_length < equiplet_number){ 
			x_length = x_length+equiplets.length;
			y += 1;
		}
		
		// Once y is found use the difference to calculate the x row.
		x = equiplet_number - (x_length - equiplets.length);

		int position[] = {x,y};
		return position;
	}
	
	public int count(){
		int y = 0;
		int x = 0;
		for (y = 0; y < equiplets.length; y++){
			for (x = 0; x < equiplets[y].length; x++){
			}
		}
		int returnVal = y * x;
		return returnVal;
	}
}