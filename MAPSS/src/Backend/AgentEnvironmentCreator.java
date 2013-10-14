package Backend;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


public class AgentEnvironmentCreator {
	protected static jade.wrapper.AgentContainer mainContainer;
	
	public static jade.wrapper.AgentContainer getContainer(){
		return mainContainer;
	}
	
	public static void main(String[] args) {
		
		// Get a hold on JADE runtime
		Runtime rt = Runtime.instance();
	
		// Exit the JVM when there are no more containers around
		rt.setCloseVM(true);
	
		// Create a default profile
		Profile profile = new ProfileImpl();

		// Create a container for the Agents
		mainContainer = rt.createMainContainer(profile);
	
		// Create the grid
		int gridx = 6, gridy = 4;
		GridClasses gc = new GridClasses();
		gc.createGrid(gridx, gridy);
		
		// Create the SchedulerAgent
		AgentController ac;
		try {
			ac = mainContainer.createNewAgent("Scheduler",
			"agents.SchedulingAgent", new Object[0]);
			ac.start();	
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		//Read XML file and create product agents
		//String home = System.getProperty("user.home");
		//System.out.println("User home directory is: " + home);
		//XmlReader.createProductAgentsFromXML("C:/Users/Mathijs/Desktop/Testfile.xml");
	}
	
}