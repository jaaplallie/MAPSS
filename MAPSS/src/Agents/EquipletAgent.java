package Agents;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jade.core.AID;
import jade.core.Agent; 
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentController;
 
import Backend.Grid;

public class EquipletAgent extends Agent { 
	
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() {				 
				 System.out.println("Hi. I'm equiplet agent " + getAID().getLocalName() + ".");
				 
				 AgentController grid[][] = Grid.getGrid();
				 String name = getLocalName();
				 Integer stepNumber = -1;
				 if (name.contains("Equiplet_")){
					 try{
						 stepNumber = Integer.parseInt(name.replace("Equiplet_", "").trim());
					 }
					 catch(NumberFormatException e){
						 e.printStackTrace();
					 }
				 }
				 if(stepNumber != -1){
					 int[] equiplet_xy_values = Grid.getEquipletPosition(stepNumber);
					 System.out.println("My position in the grid is " + 
						" x:" + equiplet_xy_values[0] + 
						", y:" + equiplet_xy_values[1]);
				 }
			 }
		});	
	} 
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}