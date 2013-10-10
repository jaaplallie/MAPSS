package Objects;

import java.util.*;

public class Grid {
	
	//default grid size 5x5
	int size_x = 5;
	int size_y = 5;
	Equiplet[][] equiplets;
	ArrayList<EquipletLink> equipletLinks = new ArrayList<EquipletLink>();
	
	public Grid(){
		//default grid constructor
	}
	
	public Grid(int x, int y){
		setGridSize(x,y);
	}
	
	
	public void addEquiplet(Equiplet a){
		
		System.out.println(String.format("Equiplet [{0}] added to grid.", a.toString()));
	}
	
	public void addEquipletLink(EquipletLink el){
		equipletLinks.add(el);
		System.out.println(String.format("[{0}] added to grid.", el.toString()));
	}
	
	private void setGridSize(int x, int y){
		size_x = x;
		size_y = y;
		equiplets = new Equiplet[x][y];
		System.out.println(String.format("Grid size set to X{0} Y{1}", x, y));
	} 
}