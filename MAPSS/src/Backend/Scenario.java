package Backend;

import java.util.ArrayList;

import Agents.EquipletAgent;
import Agents.ProductAgent;

public class Scenario {
	String scenarioName;
	Grid scenario_Grid = new Grid();
	EquipletAgent[][] scenario_EquipletGrid;
	ArrayList<ProductAgent> scenario_Products;
	
	public Scenario(String scenarioName){
		this.scenarioName = scenarioName;
		scenario_Products = new ArrayList<ProductAgent>();
	}
	
	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

//	public void createGrid(int x, int y){
//		Grid.create(x, y);
//		scenario_EquipletGrid = Grid.getGrid();
//	}
	
	public void insertAgents(ArrayList<EquipletAgent> eqs){
		scenario_Grid.insert(eqs);
	}
	
	public void insertAgentsCreateGrid(ArrayList<EquipletAgent> eqs){
		scenario_Grid.createGridAndInsert(eqs);
	}
	
	public EquipletAgent[][] getGrid(){
		return scenario_EquipletGrid;
	}

	public ArrayList<ProductAgent> getScenario_Products() {
		return scenario_Products;
	}
	
	public void addProduct(ProductAgent pa){
		System.out.println(
				String.format(
						"ProductAgent [%s] added to grid.", 
						pa.getCode()
				)
		);
		scenario_Products.add(pa);
	}

	@Override
	public String toString() {
		return "" + scenarioName + "";
	}
	
	
}
