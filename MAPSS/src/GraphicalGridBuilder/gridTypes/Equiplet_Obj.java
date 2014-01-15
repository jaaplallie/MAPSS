package GraphicalGridBuilder.gridTypes;


public class Equiplet_Obj extends Super_Obj {

	String name = "";
	
	public Equiplet_Obj(){
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
