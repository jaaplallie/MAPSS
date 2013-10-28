package Backend;

import java.util.*;
import Agents.*;

public class Scenario {
	String scenarioName;
	Grid scenario_Grid = new Grid();
	EquipletAgent[][] scenario_EquipletGrid;
	ArrayList<ProductAgent> scenario_Products;
	
	public Scenario(String scenarioName){
		this.scenarioName = scenarioName;
		scenario_Products = new ArrayList<ProductAgent>();
	}
	
	public void createGrid(int x, int y){
		scenario_EquipletGrid = scenario_Grid.create(x, y);
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
}
