package Backend;

import java.util.ArrayList;
import java.util.List;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import Agents.EquipletAgent;


public class Grid {
	protected static EquipletAgent[][] equiplets;
	protected static List[] equiplet_positions;
	private static AgentContainer container = AgentEnvironmentCreator.getContainer();
	private int x = 0;
	private int y = 0;
	
	public Grid(){
	}
	
	public EquipletAgent[][] create(int width , int length ){
		equiplets = new EquipletAgent[width][length];

		equiplet_positions = new ArrayList[(width*length)];

		x = width;
		y = length;
		int stepnr = 0;
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
				
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				
				stepnr++;
				
			}
		}
		System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", width+"", length+"", count()+""));
		
		Matrix.createMatrix(width, length);
		
		return equiplets;
	}
	
	public boolean insert(ArrayList<EquipletAgent> eqs){
		int max_X = -1;
		int max_Y = -1;
		
		for(EquipletAgent ea : eqs){
			if(ea.getPosition()[0] > max_X){
				max_X = ea.getPosition()[0];
			}
			if(ea.getPosition()[1] > max_Y){
				max_Y = ea.getPosition()[1];
			}
		}
		
		if(max_X != -1 && max_Y != -1){
			//size the array and ArrayList according to max sizes
			equiplets = new EquipletAgent[max_X+1][max_Y+1];
			equiplet_positions = new ArrayList[(max_X+1)+(max_Y+1)];
			
			//put every EquipletAgent at the correct position in the multidimensional grid array
			for(EquipletAgent ea : eqs){
				equiplets[ea.getPosition()[0]][ea.getPosition()[1]] = ea;
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(ea.getPosition()[0]); //create position for the matrix
				position.add(ea.getPosition()[1]); //create position for the matrix
				equiplet_positions[ea.getCode()-1] = position;
			}
			System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", (max_X+1)+"", (max_Y+1)+"", count()+""));
			Matrix.createMatrix((max_X), (max_Y));
		}
		else{
			return false;
		}
		
		return true;
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
		int position[] = {x ,y};
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
		
		List<Integer> equiplet_coordinates = equiplet_positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		
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