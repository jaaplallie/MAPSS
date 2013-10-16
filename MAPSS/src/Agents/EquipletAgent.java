package Agents;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentController;
import Backend.Grid;

public class EquipletAgent extends Agent { 
	
	String code;
	int position[];
	Object[] args;
	
	public EquipletAgent(String code, int[] position, Object[] args){
		this.code = code;
		this.position = position;
		this.args = args;
		setArguments(args);
	}
	
	public String getCode() {
		return code;
	}
	
	public int[] getPosition(){
		return position;
	}

	public Object[] getArgs() {
		return args;
	}
	
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() {				 
				 System.out.println("Hi. I'm equiplet agent " + getAID().getLocalName() + ".");
				 
				 EquipletAgent grid[][] = Grid.getGrid();
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