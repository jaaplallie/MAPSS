package Objects;

import jade.core.Agent;

public class Equiplet extends Agent{
	public String name = "Equiplet";
	//Part which can be produced
	private int locationInGrid_x = 0;
	private int locationInGrid_y = 0;
	
	public Equiplet(){
	}
	
	public Equiplet(String name){
		this.name = name;
	}
	
	public void setLocation(int x, int y){
		System.out.println(String.format("Location of Equiplet {0}", this.name));
	}
	
	public String getLocation(){
		return "X = " + locationInGrid_x + " Y = " + locationInGrid_y ;
	}
	
}