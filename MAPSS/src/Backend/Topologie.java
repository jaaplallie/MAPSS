package Backend;

public class Topologie {
	
	String name = "";
	Grid topologieGrid = new Grid();
	int meantForGridX = 0;
	int meantForGridY = 0;
	
	public Topologie(){
		
	}
	
	public Topologie(String name, Grid grid, int meantForX, int meantForY){
		this.name = name;
		topologieGrid = grid;
		this.meantForGridX = meantForX;
		this.meantForGridY = meantForY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Grid getTopologieGrid() {
		return topologieGrid;
	}

	public void setTopologieGrid(Grid topologieGrid) {
		this.topologieGrid = topologieGrid;
	}

	public int getMeantForGridX() {
		return meantForGridX;
	}

	public int getMeantForGridY() {
		return meantForGridY;
	}

	public String ToString(){
		return String.format("This topology's name is {%s} and is meant for a {%i} xsize and a {%i} ysize.", name, meantForGridX, meantForGridY);
	}
	
}
