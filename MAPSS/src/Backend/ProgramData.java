package Backend;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import GraphicalGridBuilder.GraphicalGrid;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ProgramData {
	public static String softwareVersion = "0.1 InDev";
	public static DefaultListModel<Scenario> scenarioListModel = new DefaultListModel<Scenario>();
	public static ArrayList<GraphicalGrid> savedGraphicalGridModel = new ArrayList<GraphicalGrid>();
	public static ArrayList<GraphicalGrid> tmpGraphicalGridModel = new ArrayList<GraphicalGrid>();
	static ArrayList<Scenario> scenario_list = new ArrayList<Scenario>();
	private static Scenario loadedScenario = null;
	
	public ProgramData() {
		super();
	}
	
	public static String getSoftwareVersion() {
		return softwareVersion;
	}

	public void onProgramStart(){ //only call on program start (main.java)
		System.out.println("Initialised ProgramData.");
	}

	public static DefaultListModel<Scenario> getScenarioListModel() {
		return scenarioListModel;
	}

	public static void setCurrentlyLoadedScenario(Scenario scenario){
		loadedScenario = scenario;
	}
	
	public static Scenario getCurrentlyLoadedScenario(){
		return loadedScenario;
	}
	
	public static void saveChangesGraphicalGridModel(ArrayList<GraphicalGrid> graphicalGridModel) {
		ProgramData.savedGraphicalGridModel = graphicalGridModel;
	}

	public DefaultFormBuilder getNewBuilder(){
		DefaultFormBuilder returnBuilder = new DefaultFormBuilder(new FormLayout(""));
		returnBuilder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		returnBuilder.appendColumn("right:pref");
		returnBuilder.appendColumn("3dlu");
		returnBuilder.appendColumn("fill:max(pref; 100px)");
		returnBuilder.appendColumn("5dlu");
		returnBuilder.appendColumn("right:pref");
		returnBuilder.appendColumn("3dlu");
		returnBuilder.appendColumn("fill:max(pref; 100px)");
		
		return returnBuilder;
	}
	
	public static void createAndAddScenario(
			String scenarioName,
			int x_size,
			int y_size,
			ArrayList<Integer>[] neighbors,
			ArrayList<int[]> products)
	{
		Scenario S = new Scenario(scenarioName, x_size, y_size, neighbors, products);
		scenario_list.add(S);
		S.save();
		
	}
	
	public static void removeScenario(Scenario S){
		int index = 0;
		for (int i = 0; i < scenario_list.size(); i++){
			if (scenario_list.get(i).name == S.name){
				index = i;
			}
		}
		scenario_list.remove(index);
		MapssFileHandler.deleteScenarioFiles(S);
	}
	
	public static Scenario getScenario(String name){
		for (Scenario s: scenario_list){
			if (s.name == name){
				return s;
			}
		}
		return null;
	}
	
	public static String[] getScenarioNames(){
		String[] s = new String[scenario_list.size()];
		for (int i = 0; i < scenario_list.size(); i++){
			s[i] = scenario_list.get(i).name;
		}
		return s;
	}
	
	public static Scenario getLatestScenario(){
		
		return scenario_list.get(scenario_list.size()-1);
	}
}
