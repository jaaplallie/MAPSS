package Objects;

import jade.core.Agent;

public class EquipletLink {
	public Agent equiplet_a = new Agent();
	public Agent equiplet_b = new Agent();
	private int transportDirection = 0;	//transport possibilities of the link (single or both ways) (0 = a to b & b to a, 1 = a to b, 2 = b to a)
	public int secondsToTravel = 0; //in seconds
	public int status = 0; //0 = available, 1 = busy a to b, 2 = busy b to a, 3 busy a to b and b to a 
	
	
	public EquipletLink(Agent a, Agent b, int direction){
	}
	
	public Agent getEquipletA(){
		return equiplet_a;
	}
	public Agent getEquipletB(){
		return equiplet_b;
	}
	
	public String getDirectionString(){
		String returnVal = "";
		
		if(transportDirection == 0){
			returnVal = "a to b and b to a";
		}
		else if(transportDirection == 1){
			returnVal = "a to b";
		}
		else{
			returnVal = "b to a";
		}
		
		return returnVal;
	}
	
	public int getDirectionInt(){
		return transportDirection;
	}
	
	public String toString(){
		return "";
	}
	
}