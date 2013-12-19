package Backend;

import java.util.ArrayList;
import java.util.List;

import Agents.EquipletAgent;
import Gui.ChartPresenter;
import Gui.SimulationModule;

/****************************************************************************************

Hello there. To save you some time here is a small table of contents for this file:
1. Creation Functions. Functions that are used to create the grid or any other structure.
2. Calculations. Pretty self explanatory. There's a few attempts at math there.
3. Getters/Setters. Can you guess?
4. Other. Everything else. Includes a few log functions + few functions to clear/change some values.

****************************************************************************************/

public class Grid {
	
	private static int x = 0;
	private static int y = 0;

	protected static String name_of_current_structure;
	
	protected static ArrayList<ArrayList<Integer>[]> position_list = new ArrayList<ArrayList<Integer>[]>();
	protected static ArrayList<ArrayList<Integer>[]> neighbor_list = new ArrayList<ArrayList<Integer>[]>();

	protected static ArrayList<EquipletAgent[][]> saved_grids = new ArrayList<EquipletAgent[][]>();
	protected static ArrayList<String> grid_names = new ArrayList<String>();
	
	protected static ArrayList<int[]> product_paths = new ArrayList<int[]>();
	protected static ArrayList<int[]> productstep_paths = new ArrayList<int[]>();
	
	protected static double product_lengths = 0;
	
	public Grid(){
	}

	/***************************************** 1. Creation Functions***********************************************/

	public static EquipletAgent[][] createStructure(int width , int length, String name, ArrayList<Integer>[] neighbors){
		x=width;
		y=length;
		
		ArrayList<Integer>[] equiplet_positions = new ArrayList[width*length];
		EquipletAgent[][] grid = new EquipletAgent[width][length];

		int stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){
				
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				stepnr++;
			}
		}	

		neighbor_list.add(neighbors);
		saved_grids.add(grid);
		grid_names.add(name);
		position_list.add(equiplet_positions);
		
		Grid.setGrid(name);
		
		Matrix.createGridMatrix(width, length);
		ChartPresenter.updateChartStructures();
		SimulationModule.updateProductStructures();
		
		return grid;
	}
	
	
	public void insert(ArrayList<EquipletAgent> eqs){
		for(EquipletAgent ea : eqs){
			int pos_X = ea.getPosition()[0];
			int pos_Y = ea.getPosition()[1];
			
			//EquipletAgent[][] grid[pos_X][pos_Y] = ea;
		}
	}
	
//	public boolean createGridAndInsert(ArrayList<EquipletAgent> eqs){
//		int max_X = -1;
//		int max_Y = -1;
//		
//		for(EquipletAgent ea : eqs){
//			if(ea.getPosition()[0] > max_X){
//				max_X = ea.getPosition()[0];
//			}
//			if(ea.getPosition()[1] > max_Y){
//				max_Y = ea.getPosition()[1];
//			}
//		}
//		
//		if(max_X != -1 && max_Y != -1){
//			//size the array and ArrayList according to max sizes
//			EquipletAgent[][] grid = new EquipletAgent[max_X+1][max_Y+1];
//			ArrayList<Integer>[] equiplet_positions = new ArrayList[(max_X+1)+(max_Y+1)];
//			
//			//put every EquipletAgent at the correct position in the multidimensional grid array
//			for(EquipletAgent ea : eqs){
//				grid[ea.getPosition()[0]][ea.getPosition()[1]] = ea;
//				ArrayList<Integer> position = new ArrayList<Integer>();
//				position.add(ea.getPosition()[0]); //create position for the matrix
//				position.add(ea.getPosition()[1]); //create position for the matrix
//				equiplet_positions[ea.getCode()-1] = position;
//			}
//			//System.out.println(String.format("Grid X[%s] Y[%s] created. \n[%s] Equiplets installed.", (max_X+1)+"", (max_Y+1)+"", max_Y*max_X+""));
//			//Matrix.createMatrix((max_X), (max_Y), structure_name);
//		}
//		else{
//			return false;
//		}
//		return true;
//	}
	
	/*****************************************End of Creation Functions***********************************************/
	
	/***************************************** 2. Calculations***********************************************/

	public static int[] calculatePath(int start_equiplet_number, int end_equiplet_number, int mode, ArrayList<Integer>[] neighbors) {
		int max = x*y, temp_distance = x*y;

		int[] visited = new int[max];
		int[] recorded_distances = new int[max];
		
		//set the distance of all equiplets to "infinite"
		for (int x = 0; x < max; x++){
			recorded_distances[x] = max+1;
		}
		
		recorded_distances[start_equiplet_number] = 0;
		
		int current_equiplet = start_equiplet_number;
		int next_equiplet = current_equiplet;
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		int index = getIndex(name_of_current_structure);
		
		
		while(true){
			//Checks the neighbors of the current node and compares the distances.
			//The next equiplet is chosen and the current equiplet will be marked as "visited".
			
			if (visited[current_equiplet] != 0){ //check if the next equiplet was already visited
				
				//if it has. Look among previous scanned neighbors and select the one with the shortest distance.
				int new_distance = max;
				for (int i = 0; i <max; i++){
					if (visited[i]!=1){
						
						// It will happen that 2 or more equiplets have the shortest distance.
						// "mode" determines wich one will proceed. 
						// The difference between functions is < and <= 
						if (mode > 0) {
							if (recorded_distances[i] <= new_distance){
								new_distance = recorded_distances[i];
								current_equiplet = i;
							}
						} else {
							if (recorded_distances[i] < new_distance){
								new_distance = recorded_distances[i];
								current_equiplet = i;
							}
						}
					}
				}
				
				//New_distace still being max means that there are no suitable equiplets left. The function stops
				if (new_distance == max){
					//System.out.println("Destination cannot be reached");
					path.add(max+1); //add an unlikely number to show that it's unreachable
					break;
				}
			
			} else {

				//ArrayList<Integer>[] neighbors = neighbor_list.get(index);
				for (Object neighbor : neighbors[current_equiplet]){
					int neighborint = (int)neighbor;
					
					//check if the neighbor has been visited before.
					if (visited[neighborint] == 0 && neighborint != current_equiplet){
						int distance_to_check = Matrix.getDistance(neighborint, end_equiplet_number);
						
						//compare distances and update where necessary
						if (recorded_distances[neighborint] > distance_to_check){
							recorded_distances[neighborint] = distance_to_check;
						} 
							
						// It will happen that 2 or more equiplets have the shortest distance.
						// "mode" determines wich one will proceed. 
						// The difference between functions is < and <= 
						if (mode > 0) {
							if (recorded_distances[neighborint] < temp_distance){
								temp_distance = recorded_distances[neighborint];
								next_equiplet = neighborint;
							}
						} else {
							if (recorded_distances[neighborint] <= temp_distance){
								temp_distance = recorded_distances[neighborint];
								next_equiplet = neighborint;
							}
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
	
	/*****************************************End of Calculations***********************************************/
	
	/***************************************** 3. Getters/Setters***********************************************/
	
	public static void setGrid(String structure_name){
		name_of_current_structure = structure_name;
	}
	
	public static EquipletAgent[][] getGrid(){
		int index = Grid.getIndex(name_of_current_structure);
        return saved_grids.get(index);
}
	
	public static int getIndex(String structure_name){
		int counter = 0, found = 0;
		for(String s: grid_names){
			if (s == structure_name){
				found = counter;
				break;
			}
			counter++;
		}
		return found;
	}
	
	public static ArrayList<Integer>[] getNeighbors(String structure_name){
		return neighbor_list.get(Grid.getIndex(structure_name));
	}
	
	public static ArrayList<Integer>[] getCurrentNeighbors (){
		return neighbor_list.get(Grid.getIndex(name_of_current_structure));
	}
	
	
	public static ArrayList<String> getStructureNames(){
		return grid_names;
	}
	
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
	
	public static int getMaxvalue(){
		int count = 0;
		int index = getIndex(name_of_current_structure);
		ArrayList<Integer>[] neighbors = neighbor_list.get(index);
		for (ArrayList<Integer> AI: neighbors){
			//System.out.println("AI size: "+AI.size());
			if (AI.size() > 0){
				
				count++;
			}
		}
		//System.out.println("Count: "+count);
		return count;
	}

	
	public static EquipletAgent[][] getStructure(String structure_name){
		int counter = 0, found = 0;
		for(String s: grid_names){
			if (s == structure_name){
				found = counter;
				break;
			}
			counter++;
		}
		return saved_grids.get(found);
	}
	
	public static int[] getEquipletPosition(int equiplet_number){
		int index = Grid.getIndex(name_of_current_structure);
		
		ArrayList<Integer>[] equiplet_positions = position_list.get(index);
		List<Integer> equiplet_coordinates = equiplet_positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		int position[] = {x,y};
		return position;
	}
	
	public static double getTotalAverageProductSteps(){
//		double path_total = 0;
//		for (int i = 0; i < product_paths.size(); i++){
//			path_total += product_paths.get(i).length;
//		}
//		return path_total/product_paths.size();
		return product_lengths;
	}
	
	public static double getAverageProductStepPath(){
		double path_total = 0;
		for (int i = 0; i < productstep_paths.size(); i++){
			path_total += productstep_paths.get(i).length;
		}
		return path_total/productstep_paths.size();
	}
	
	/*****************************************End of Getters/Setters***********************************************/
		
	
	/***************************************** 4. Other***********************************************/

	public static void addProductPath(int[] path){
		product_paths.add(path);
	}
	
	public static void addPathLenght(double hops) {
		product_lengths += hops;
		
	}
	
	public static void addProductStepPath(int[] path){
		productstep_paths.add(path);
	}
	
	public static void clearProductPaths(){
		product_paths.clear();
		product_lengths = 0;
	}
	
	public static void clearProductStepPaths(){
		productstep_paths.clear();
	}
	
	public void logProductPaths(){
		for (int i = 0; i < product_paths.size(); i++){
			MapssFileWriter.writeLog("The path of product " + i + " is: ");	
			for (int o: product_paths.get(i)){
				MapssFileWriter.writeLog(o+" ");
			}
			MapssFileWriter.writeLog("\n");
		}
		MapssFileWriter.writeLogLn("");
	}
	
	public static void logGrid(){
		int index = Grid.getIndex(name_of_current_structure);
		EquipletAgent[][] grid = saved_grids.get(index);
		MapssFileWriter.writeLogLn("Grid layout");
		int stepnr = 0;
		for (int y = 0; y < grid[0].length; y++){
			String output = "";
			for (int x = 0; x < grid.length; x++){
				
				if (stepnr < 10){
					output += " ";
				}
				output += " " + stepnr;
				stepnr++;
			}
			MapssFileWriter.writeLogLn(output);
		}
		MapssFileWriter.writeLogLn("");
	}
	
	public static void logNeighbors() {
		int index = getIndex(name_of_current_structure);
		ArrayList<Integer>[] neighbors = neighbor_list.get(index);
		for (int i = 0; i < neighbors.length; i++){
			MapssFileWriter.writeLogLn("Equiplet " + i + " kent: " + neighbors[i]);	
		}
		MapssFileWriter.writeLogLn("");
	}


	
	/*****************************************End of Other***********************************************/

}