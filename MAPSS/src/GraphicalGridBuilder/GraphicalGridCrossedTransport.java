package GraphicalGridBuilder;

public class GraphicalGridCrossedTransport extends GraphicalGridObject {

	GraphicalGridEquiplet eq1, eq2, eq3, eq4;
	
	public GraphicalGridCrossedTransport(){
		textualIcon = "X";
		this.setImageIcon("img/grid_icons/crossedslash.png");
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

	public GraphicalGridEquiplet getEq3() {
		return eq3;
	}

	public void setEq3(GraphicalGridEquiplet eq3) {
		this.eq3 = eq3;
	}

	public GraphicalGridEquiplet getEq4() {
		return eq4;
	}

	public void setEq4(GraphicalGridEquiplet eq4) {
		this.eq4 = eq4;
	}
	
	
}
