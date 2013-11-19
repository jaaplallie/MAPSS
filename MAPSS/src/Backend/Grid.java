package Backend;

import java.util.ArrayList;
import java.util.List;


import Backend.Log;
import jade.wrapper.AgentContainer;

import Agents.EquipletAgent;


public class Grid {
	//protected static EquipletAgent[][] equiplets;
	protected static List[] equiplet_positions;
	private static AgentContainer container = AgentEnvironmentCreator.getContainer();
	private static int x = 0;
	private static int y = 0;
	protected static EquipletAgent[][] grid;
	
	protected static List[] neighbors;
	
	protected static ArrayList<int[]> product_paths = new ArrayList<int[]>();
	protected static ArrayList<int[]> productstep_paths = new ArrayList<int[]>();
	
	public Grid(){
	}
	
	public static void createCustom(int width , int length , String relations){
		List[] connections = new List[width*length];
		List<Integer> tempList;
		int telnr = 0;
		for (String s : relations.split(",")){
			String[] temp = s.split("-");
			tempList = new ArrayList<Integer>();
			for (int i = 0; i < temp.length; i++){
				int tempInt = Integer.parseInt(temp[i]);
				tempList.add(tempInt);
			}
			connections[telnr] = tempList;
			telnr++;
		}
		
		neighbors = connections;
		grid = new EquipletAgent[width][length];
		equiplet_positions = new ArrayList[(width*length)];
		
		if (connections.length != width*length){
			//raise error
			System.out.println("You bloody what mate? The number of equiplets and the given size do not mix well");
		}

		x = width;
		y = length;
		int stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){				
				Agents.EquipletAgent new_Equiplet = new Agents.EquipletAgent(stepnr, new int[]{x,y}, new Object[]{});	
				grid[x][y] = new_Equiplet;					
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				stepnr++;
			}
		}
		
		System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", width+"", length+"", count()+""));
		Matrix.createMatrix(width, length);
	}
	
	
	public static void createNormalGrid(int width, int length){
		x=width;
		y=length;
		
		neighbors = new ArrayList[x*y];
		equiplet_positions = new ArrayList[width*length];
		grid = new EquipletAgent[width][length];

		int stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){
				
				ArrayList<Integer> known_equiplets = new ArrayList<Integer>();
				
				//if not first row
				if (y != 0){
					known_equiplets.add(stepnr-width);
				}
				
				//if not end of row
				if (stepnr != (width*(y+1))-1){
					known_equiplets.add(stepnr+1);
				}
				
				//if not last row
				if (stepnr+width < length*width){
					known_equiplets.add(stepnr+width);
				}
				
				//if not start of row
				if (stepnr != width*y){
					known_equiplets.add(stepnr-1);
				}
				
				neighbors[stepnr] = known_equiplets;
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				stepnr++;
			}
		}	
		//max = width*length;
		
		logGrid();
		logNeighbors();
	}
	
	public static void logGrid(){
		Log.writeln("Grid layout");
		int stepnr = 0;
		for (int y = 0; y < grid[0].length; y++){
			String output = "";
			for (int x = 0; x < grid.length; x++){
				output += " " + stepnr;
				stepnr++;
			}
			Log.writeln(output);
		}
		Log.writeln("");
	}
	
	public static void logNeighbors() {
		for (int i = 0; i < neighbors.length; i++){
			Log.writeln("Equiplet " + i + " kent: " + neighbors[i]);	
		}
		Log.writeln("");
	}
	
	public static double getAverageProductPath(){
		double path_total = 0;
		for (int i = 0; i < product_paths.size(); i++){
			path_total += product_paths.get(i).length;
		}
		return path_total/product_paths.size();
		//System.out.println("The average path length is: " + path_total/paths.size());
	}
	
	public static double getAverageProductStepPath(){
		double path_total = 0;
		for (int i = 0; i < productstep_paths.size(); i++){
			path_total += productstep_paths.get(i).length;
		}
		return path_total/productstep_paths.size();
		//System.out.println("The average path length is: " + path_total/paths.size());
	}
	
	
	public static void addProductPath(int[] path){
		product_paths.add(path);
	}
	
	public static void addProductStepPath(int[] path){
		productstep_paths.add(path);
	}
	
	public static void clearProductPaths(){
		product_paths.clear();
	}
	
	public static void clearProductStepPaths(){
		productstep_paths.clear();
	}
	
	
	public void logProductPaths(){
		for (int i = 0; i < product_paths.size(); i++){
			System.out.print("The path of product " + i + " is: ");	
			for (int o: product_paths.get(i)){
				Log.write(o+" ");
			}
			Log.write("\n");
		}
		Log.writeln("");
	}
	
	
	
	
	
	public static void create(int width , int length ){
		grid = new EquipletAgent[width][length];
		equiplet_positions = new ArrayList[(width*length)];

		x = width;
		y = length;
		int stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){				
				Agents.EquipletAgent new_Equiplet = new Agents.EquipletAgent(stepnr, new int[]{x,y}, new Object[]{});
				grid[x][y] = new_Equiplet;					
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				stepnr++;
			}
		}
		System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", width+"", length+"", count()+""));
		Matrix.createMatrix(width, length);
	}
	
	public void insert(ArrayList<EquipletAgent> eqs){
		for(EquipletAgent ea : eqs){
			int pos_X = ea.getPosition()[0];
			int pos_Y = ea.getPosition()[1];
			
			grid[pos_X][pos_Y] = ea;
		}
	}
	
	public boolean createGridAndInsert(ArrayList<EquipletAgent> eqs){
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
			grid = new EquipletAgent[max_X+1][max_Y+1];
			equiplet_positions = new ArrayList[(max_X+1)+(max_Y+1)];
			
			//put every EquipletAgent at the correct position in the multidimensional grid array
			for(EquipletAgent ea : eqs){
				grid[ea.getPosition()[0]][ea.getPosition()[1]] = ea;
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
	
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
	
	public static int getMaxvalue(){
		return x*y;
	}

	public static EquipletAgent[][] getGrid(){
		return grid;
	}
	
	public static List[] getNeighbors(){
		return neighbors;
	}
	
	public int[] getRandomPosition(){
		int x = (int)(Math.random()*grid.length);
		int y = (int)(Math.random()*grid[0].length);
		int position[] = {x ,y};
		return position;
	}
	
	public static int[] getEquipletPosition(int equiplet_number){
		List<Integer> equiplet_coordinates = equiplet_positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		int position[] = {x,y};
		return position;
	}
	
	public static int count(){
		int y = 0;
		int x = 0;
		for (y = 0; y < grid.length; y++){
			for (x = 0; x < grid[y].length; x++){
			}
		}
		int returnVal = y * x;
		return returnVal;
	}

	public static int[] calculateDifferentPath(int start_equiplet_number, int end_equiplet_number) {
		int max = x*y;
		

		int[] visited = new int[max];
		int[] recorded_distances = new int[max];
		
		//set the distance of all equiplets to "infinite"
		for (int x = 0; x < max; x++){
			recorded_distances[x] = max+1;
		}
		
		recorded_distances[start_equiplet_number] = 0;
		List[] neighbors = Grid.getNeighbors();
		
		int current_equiplet = start_equiplet_number;
		int next_equiplet = current_equiplet;
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		while(true){
			
			//Checks the neighbors of the current node and compares the distances.
			//The next equiplet is chosen and the current equiplet will be marked 
			//as "visited".
			
			//check if the next equiplet was already visited
			if (visited[current_equiplet] != 0){
				//System.out.println(current_equiplet +" is blocked");
				
				//if it has. Look among previous scanned neighbors and 
				//select the one with the shortest distance.
				int new_distance = max;
				for (int i = 0; i <max; i++){
					if (visited[i]!=1){
						if (recorded_distances[i] < new_distance){
							new_distance = recorded_distances[i];
							current_equiplet = i;
						}
					}
				}
				//System.out.println("We switch to: "+current_equiplet);
				
				//New_distace still being max means that there are no suitable equiplets left. 
				//The function stops
				if (new_distance == max){
					System.out.println("Destination cannot be reached");
					path.add(max+1);
					break;
				}
			
			} else {
				int temp_distance = max;
				for (Object neighbor : neighbors[current_equiplet]){
					int neighborint = (int)neighbor;
					
					//check if the neighbor has been visited before.
					if (visited[neighborint] == 0 && neighborint != current_equiplet){
						int distance_to_check = Matrix.getDistance(neighborint, end_equiplet_number);
						
						//compare distances and update where necessary
						if (recorded_distances[neighborint] > distance_to_check){
							recorded_distances[neighborint] = distance_to_check;
						} 
						
						if (recorded_distances[neighborint] < temp_distance){
							temp_distance = recorded_distances[neighborint];
							next_equiplet = neighborint;
							//System.out.println("possible: " + next_equiplet);
						}
					}
				}
				visited[current_equiplet] = 1;
				
				if (visited[end_equiplet_number] > 0){
					//Destination has been reached. The function stops.
					path.add(current_equiplet);
					break;
				}
				
				if (next_equiplet != current_equiplet){
					if (current_equiplet != start_equiplet_number){
						path.add(current_equiplet);
					}
					
					current_equiplet = next_equiplet;
					//System.out.println("Going to " + next_equiplet);
				}
				
				
				
			}
		}
		int[] returnvalue = new int[path.size()];
		for (int i = 0; i < path.size(); i++){
			returnvalue[i] = ((Integer) path.get(i));
		}
		return returnvalue;
	}
		
}