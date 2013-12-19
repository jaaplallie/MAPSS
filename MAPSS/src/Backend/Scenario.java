package Backend;

import java.util.ArrayList;

import Agents.EquipletAgent;
import Agents.ProductAgent;

public class Scenario {
	String name;
	//Grid scenario_Grid = new Grid();
	EquipletAgent[][] structure;
	int[] positions;
	ArrayList<Integer>[] neighbors;
	int[] products;
	int[][] matrix;
	
	//EquipletAgent[][] scenario_EquipletGrid;
	//ArrayList<ProductAgent> scenario_Products;
	
	
	
	
	public Scenario(
			String scenarioName,
			int x_size,
			int y_size,
			int[] positions,
			ArrayList<Integer>[] neighbors,
			int[] products
			){
		this.name = scenarioName;
		this.structure = Grid.createStructure(x_size, y_size, scenarioName, neighbors);
		//this.positions
		//scenario_Products = new ArrayList<ProductAgent>();
	}
	
	public String getScenarioName() {
		return name;
	}

	public void setScenarioName(String scenarioName) {
		this.name = scenarioName;
	}


	
//	public EquipletAgent[][] getGrid(){
//		return Grid;
//	}

	public int[] getProducts() {
		return products;
	}

	
	
}
