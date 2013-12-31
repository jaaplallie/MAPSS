package Backend;

import java.util.ArrayList;
import java.util.List;

import Agents.EquipletAgent;
import Agents.ProductAgent;




public class Scenario {
	public String name;
	public int x, y;
	//EquipletAgent[][] grid_sizes;
	protected ArrayList<Integer>[] positions;
	ArrayList<Integer>[] neighbors;
	ArrayList<int[]> products;
	ArrayList<Integer>[][] distances_between_equiplets;
	ArrayList<int[]> paths_between_equiplets;
	ArrayList<int[]> possible_other_paths;
	
	//Grid scenario_Grid = new Grid();
	//EquipletAgent[][] scenario_EquipletGrid;
	//ArrayList<ProductAgent> scenario_Products;
	

	
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
		
		this.distances_between_equiplets = Calculations.calcDistances(x_size, y_size, this);
		this.paths_between_equiplets = Calculations.calculateAllPaths(this, 0);
		this.possible_other_paths = Calculations.calculateAllPaths(this, 1);
		
		//this.positions
		//scenario_Products = new ArrayList<ProductAgent>();
	}
	
	public int[] getEquipletPosition(int equiplet_number){
		//System.out.println("Are you here?");
		//int index = Grid.getIndex(name_of_current_structure);
		
		//ArrayList<Integer>[] equiplet_positions = position_list.get(index);
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
		//int index = Grid.getIndex(name_of_current_structure);
		
		List<Integer> equiplet_coordinates = this.positions[equiplet_number];
		
		int x = equiplet_coordinates.get(0);
		int y = equiplet_coordinates.get(1);
		int position[] = {x,y};
		return position;
	}
	
	public void addProducts(ArrayList<int[]> products){
		this.products = products;
		MapssFileWriter.saveScenario(this);
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
		return this.possible_other_paths.get(start*this.y+end+1);
	}
	
	
	public void comparePaths(){
		
		
	}
	
	
	
	
	
	
	
	public void save(){
		
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
