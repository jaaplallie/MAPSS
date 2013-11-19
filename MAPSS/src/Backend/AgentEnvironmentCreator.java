package Backend;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import Agents.EquipletAgent;
import Agents.ProductAgent;

public class AgentEnvironmentCreator {
	
	private static AgentContainer mainContainer;
	static Runtime rt = Runtime.instance();
	static Profile p;
	
	public static void main(String[] args) {
		start();
	}
	
	public AgentEnvironmentCreator(){
		setup();
	}
	
	public AgentEnvironmentCreator(String mainhost){
		setup(mainhost);
	}
	
	@SuppressWarnings("static-access")
	public static void startScenario(){
		Scenario started_Scenario = ProgramData.getCurrentlyLoadedScenario();
		System.out.println("\nScenario " + started_Scenario.scenarioName + " started.");
		EquipletAgent[][] Grid = started_Scenario.scenario_Grid.getGrid();
		for (int y = 0; y < started_Scenario.scenario_Grid.getY(); y++){
			for (int x = 0; x < started_Scenario.scenario_Grid.getX(); x++){
				try {
					addEquipletAgent(Grid[x][y]);
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}
			}
		}
		for(ProductAgent pa : started_Scenario.getScenario_Products()){
			try {
				addProductAgent(pa);
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\nScenario " + started_Scenario.scenarioName + " finished.");
	}
	
	public static void start(){
		setup();

		// Create a container for the Agents
		//cController = rt.createMainContainer(p);
	
		// Create the grid
//		int gridx = 6, gridy = 4;
//		GridClasses gc = new GridClasses();
//		gc.createGrid(gridx, gridy);
		//createGrid(6, 4);
		
		// Create the SchedulerAgent
//		AgentController ac;
		try {
			addSchedulerAgent();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		//Read XML file and create product agents
		//String home = System.getProperty("user.home");
		//System.out.println("User home directory is: " + home);
		//XmlReader.createProductAgentsFromXML("C:/Users/Mathijs/Desktop/Testfile.xml");
	}
	
	private static void setup(){
		p = new ProfileImpl();
		p.setParameter(Profile.GUI, "false");
		mainContainer = rt.createMainContainer(p);			
		rt.setCloseVM(true);
	}
	
	private void setup(String host){
		p = new ProfileImpl();
		p.setParameter(Profile.GUI, "false");
		p.setParameter(Profile.MAIN_HOST, host);
		mainContainer = rt.createAgentContainer(p);			
		rt.setCloseVM(true);
	}
	
	public static void addRemoteMonitoringAgent() throws StaleProxyException{
		AgentController ac = mainContainer.createNewAgent("RemoteMonitor", "jade.tools.rma.rma", new Object[0]);
		ac.start();
	}
	
	public static void addSnifferAgent() throws StaleProxyException{
		AgentController ac = mainContainer.createNewAgent("Sniffer", "jade.tools.sniffer.Sniffer", new Object[0]);
		ac.start();
	}
	
	public void addAgent(String name, Agent a) throws StaleProxyException {		
		AgentController ac = mainContainer.acceptNewAgent(name, a);
		ac.start();
	}
	
	//MAPSS SPECIFIC
	public static void addSchedulerAgent() throws StaleProxyException{
		AgentController ac = mainContainer.createNewAgent("Scheduler", "Agents.SchedulingAgent", new Object[0]);
		ac.start();
	}
	
	public static void addEquipletAgent(EquipletAgent agent) throws StaleProxyException{
		AgentController ac = mainContainer.acceptNewAgent("Equiplet_"+agent.getCode(), agent);
		ac.start(); 
	}
	
	public static void addProductAgent(ProductAgent agent) throws StaleProxyException{
		AgentController ac = mainContainer.acceptNewAgent(agent.getCode(), agent);
		ac.start();
	}
	
	//public static Grid createGrid(int x, int y){
	//	Grid gc = new Grid();
	//	gc.create(x, y);
	//	return gc;
	//}
	
	public static AgentContainer getContainer(){
		return mainContainer;
	}
	
	public static jade.core.Runtime getRuntime() {
		return rt;
	}

	public static jade.core.Profile getProfile() {
		return p;
	}
	
}