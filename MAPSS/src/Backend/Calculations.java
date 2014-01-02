package Backend;

import java.util.ArrayList;

public class Calculations {
	
	
	
	public static ArrayList<Integer>[][] calculateDistances(int grid_width, int grid_length, Scenario s){
			
		ArrayList<Integer>[][]matrix = new ArrayList[grid_width*grid_length][grid_length*grid_width];
			
			for (int x = 0; x < grid_width*grid_length; x++){	
				int currentposition[] = s.getEquipletPosition(x);
					for (int y = 0; y < grid_width*grid_length; y++){
						int otherposition[] = s.getEquipletPosition(y);
						ArrayList<Integer> X = new ArrayList<Integer>();
						
						X.add(otherposition[0]-currentposition[0]);
						X.add(otherposition[1]-currentposition[1]);
						matrix[x][y] = X;
					}	
				}	
			return matrix;
		}
	
	public static int getDistance(int start, int end, Scenario S){
		ArrayList<Integer> value = S.distances_between_equiplets[start][end];
		int distance = 0;
		for (int i : value){
			distance += Math.abs(i);
		}
		return distance;
	}
	
	public static ArrayList<int[]> calculateAllPaths(Scenario current_scenario, int mode){
		int max = current_scenario.x*current_scenario.y;

		ArrayList<int[]> paths = new ArrayList<int[]>();
		
		for (int i = 0; i < max; i++){
			for (int j = 0; j < max; j++){
				int path[] = calculatePath(i, j, mode, current_scenario);
				paths.add(path);
			}
		}
		return paths;
	}
	
	public static int[] calculatePath(int start_equiplet_number, int end_equiplet_number, int mode, Scenario S) {
		ArrayList<Integer>[] neighbors = S.neighbors;
		int max = S.x*S.y, temp_distance = max;

		int[] visited = new int[max];
		int[] recorded_distances = new int[max];
		
		//set the distance of all equiplets to "infinite" except for the starting one.
		for (int x = 0; x < max; x++){
			recorded_distances[x] = max+1;
		}
		recorded_distances[start_equiplet_number] = 0;
		
		int current_equiplet = start_equiplet_number;
		int next_equiplet = current_equiplet;
		ArrayList<Integer> path = new ArrayList<Integer>();
		
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
						int distance_to_check = Calculations.getDistance(neighborint, end_equiplet_number, S);
						
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
	
	
	
}