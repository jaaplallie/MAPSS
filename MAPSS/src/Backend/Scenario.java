package Backend;

import java.util.ArrayList;
import java.util.List;

import Agents.EquipletAgent;
import Agents.ProductAgent;

/****************************************************************************************

	This class is used as a datafile. It stores the data needed for the 
	product agent, the product step generator and the simulation class.
	
	It will ask Calculations to perform most of the calculations needed for 
	it's data. 
	
	The list below displays most of the data that is stored within.
	A thing to add is that because it stores 2 different kinds of paths between 
	equiplets, it is capable of comparing them and tell how much difference
	there is in %. This is useful for the scheduling.

****************************************************************************************/

public class Scenario {
	public String name;
	public int x, y;
	protected ArrayList<Integer>[] positions;
	protected ArrayList<Integer>[] neighbors;
	protected ArrayList<int[]> products;
	protected ArrayList<Integer>[][] distances_between_equiplets;
	protected ArrayList<int[]> paths_between_equiplets;
	protected ArrayList<int[]> possible_other_paths;
	
	public Scenario(
			String scenarioName,
			int x_size,
			int y_size,
			ArrayList<Integer>[] neighbors,
			ArrayList<int[]> products
			){
		this.name = scenarioName;
		this.x = x_size;
		this.y = y_size;
		this.neighbors = neighbors;
		this.products = products;
		
		this.positions = new ArrayList[x_size*y_size];
		
		int stepnr = 0;
		for (int y = 0; y < y_size; y++){
			for (int x = 0; x < x_size; x++){
				
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				this.positions[stepnr] = position;
				stepnr++;
			}
		}	
		
		this.distances_between_equiplets = Calculations.calculateDistances(x_size, y_size, this);
		this.paths_between_equiplets = Calculations.calculateAllPaths(this, 0);
		this.possible_other_paths = Calculations.calculateAllPaths(this, 1);
	}
	
	public int[] getEquipletPosition(int equiplet_number){
		List<Integer> equiplet_coordinates = this.positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		int position[] = {x,y};
		return position;
	}
	
	public boolean hasProducts(){
		if (this.products.size() > 0){
			return true;
		}
		return false;
	}
	
	public int[] getPosition(int equiplet_number){
		List<Integer> equiplet_coordinates = this.positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		int position[] = {x,y};
		return position;
	}
	
	public void addProducts(ArrayList<int[]> products){
		this.products = products;
		this.save();
	}
	
	public ArrayList<int[]> getProducts() {
		return this.products;
	}
	
	public void deleteProducts(){
		this.products.clear();
		this.save();
	}
	
	public int getMax(){
		return x*y;
	}
	
	
	public int[] getPath (int start, int end){

		return this.paths_between_equiplets.get((start*(this.y*this.x))+end);
		
	}
	
	public int[] getAlternativePath (int start, int end){
		return this.possible_other_paths.get((start*(this.y*this.x))+end);
	}
	
	
	public double comparePaths(){
		double size = 0;
		double difference = 0;
		
		//Loop between paths
		for (int i = 0; i < this.paths_between_equiplets.size(); i++){

			//Get one primary and one secondary path
			int[] first_path = this.paths_between_equiplets.get(i);
			int[] alternative_path = this.possible_other_paths.get(i);
			
			if (first_path.length == alternative_path.length){
				for (int j = 0; j < first_path.length; j++){
					size++;
					
					//check if values are the same
					if (first_path[j] != alternative_path[j]){
						difference++;
					}
				} 
			} else {
				difference++;
			}	
		}
		
		double result = ((difference/size)*100);
		return result;
	}
	
	public void save(){
		MapssFileHandler.saveScenario(this);
		MapssFileHandler.createDataFile(this.name);
	}
	

	
//	public String getScenarioName() {
//		return name;
//	}
//
//	public void setScenarioName(String scenarioName) {
//		this.name = scenarioName;
//	}


	
//	public EquipletAgent[][] getGrid(){
//		return Grid;
//	}



	
	
}
