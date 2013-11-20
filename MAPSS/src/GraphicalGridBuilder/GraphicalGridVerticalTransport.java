package GraphicalGridBuilder;

public class GraphicalGridVerticalTransport extends GraphicalGridObject {

	GraphicalGridEquiplet eq1, eq2;
	
	public GraphicalGridVerticalTransport(){
		textualIcon = "|";
		this.setImageIcon("img/grid_icons/vertical.png");
	}
	
	public GraphicalGridEquiplet getEq1() {
		return eq1;
	}

	public void setEq1(GraphicalGridEquiplet eq1) {
		this.eq1 = eq1;
	}

	public GraphicalGridEquiplet getEq2() {
		return eq2;
	}

	public void setEq2(GraphicalGridEquiplet eq2) {
		this.eq2 = eq2;
	}
}
