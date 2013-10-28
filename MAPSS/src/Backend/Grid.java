package Backend;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import Agents.EquipletAgent;


public class Grid {
	protected static EquipletAgent[][] equiplets;
	private static AgentContainer container = AgentEnvironmentCreator.getContainer();
	private int x = 0;
	private int y = 0;
	
	public Grid(){
	}
	
	public EquipletAgent[][] create(int width , int length ){
		equiplets = new EquipletAgent[width][length];
		x = width;
		y = length;
		int stepnr = 1;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){				
//				try {

					Agents.EquipletAgent new_Equiplet = new Agents.EquipletAgent(stepnr, new int[]{x,y}, new Object[]{});
					
					equiplets[x][y] = new_Equiplet;					
//					AgentController a_container;
//					a_container = container.acceptNewAgent(new_Equiplet.getCode(), new_Equiplet);
//					a_container.start();
//					Thread.sleep(100);
					//a_container = container.createNewAgent("Equiplet_" + stepnr, "Agents.EquipletAgent", null);
//				} 
//				catch (StaleProxyException | InterruptedException e) {
//					e.printStackTrace();
//				} 
				
				stepnr++;
				
			}
		}
		System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", width+"", length+"", count()+""));
		return equiplets;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static EquipletAgent[][] getGrid(){
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