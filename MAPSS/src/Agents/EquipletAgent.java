package Agents;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentController;
import Backend.Grid;

//comment test

public class EquipletAgent extends Agent { 
	
	int code = -1;
	int position[];
	Object[] args;
	
	public EquipletAgent(int code, int[] position, Object[] args){
		this.code = code;
		this.position = position;
		this.args = args;
		setArguments(args);
	}
	
	public int getCode() {
		return code;
	}
	
	public int[] getPosition(){
		return position;
	}

	public Object[] getArgs() {
		return args;
	}
	
	public String getArgsString() {
		String returnVal = "";
		for(int i = 0; i < args.length; i++){
			if(i < args.length && args.length > 1){
				returnVal += ",";
			}
			returnVal += args[i].toString();
		}
		return returnVal;
	}
	
	protected void setup() { 
		addBehaviour(new WakerBehaviour(this, 0) { 
			 protected void handleElapsedTimeout() {
//				 //int[] equiplet_xy_values = Grid.getEquipletPosition(getCode(), );				 	 
//				 System.out.println(
//					 "Hi. I'm equiplet agent " + getAID().getLocalName() + "." +
//					 "\nMy position in the grid is x:" + equiplet_xy_values[0] + ", y:" + equiplet_xy_values[1] + "\n"
//				 );
			 }
		});
	}
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}