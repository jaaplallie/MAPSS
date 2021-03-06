package Agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
 
public class SchedulingAgent extends Agent { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5622265932486253175L;
	private int[][][] timetable; 
	private int time;
	//Dit is momenteel iets minder leeg
	
	protected void setup() {
//		int length = Grid.getY();
//		int width = Grid.getX();
		time = 50;
		//timetable = new int[length][width][time];
		
		
		addBehaviour(new WaitForMessages());
		
		
	} 
	
	
	private class WaitForMessages extends CyclicBehaviour {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6860382376677613594L;

		public void action() {
			ACLMessage msg = myAgent.receive(); 
			if (msg != null) { 
				String o = msg.getOntology();
				switch (o){
					case "EquipletRequest":
						String[] data = msg.getContent().split(".");
						int equiplet = Integer.parseInt(data[0]);
						int time = Integer.parseInt(data[1]);
						
						//int[] position = Grid.getEquipletPosition(equiplet);
						//int x = position[0];
						//int y = position[1];
						int x = 0;
						int y = 0;
						
						if (timetable[x][y][time] != 0 ){
							timetable[x][y][time] = 1;
							ACLMessage reply = msg.createReply(); 
							reply.setContent("Approved");
							myAgent.send(reply); 
						} else {
							ACLMessage reply = msg.createReply(); 
							reply.setContent("Denied");
							myAgent.send(reply); 
						}

						break;
					default:
						break;
				}
			} 
			block();
		} 
		
	}
	
	
	
	protected void breakdown() {
		System.out.println("Agent "+getAID().getName()+" is gone now."); 
	}
}