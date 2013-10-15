package Agents;

import jade.core.AID;
import jade.core.Agent; 
import Backend.GridClasses;
 
public class SchedulingAgent extends Agent { 
	
	//Dit is momenteel leeg
	
	protected void setup() { 
		
	} 
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}