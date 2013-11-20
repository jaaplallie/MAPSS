package GraphicalGridBuilder;

public class GraphicalGridEquiplet extends GraphicalGridObject {

	String name = "";

	public GraphicalGridEquiplet(){
		textualIcon = "O";
		this.setImageIcon("img/grid_icons/equiplet.png");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
