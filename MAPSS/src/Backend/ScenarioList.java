package Backend;

import java.util.ArrayList;

import Gui.ChartPresenter;
import Gui.SimulationModule;

//Mathijs

public class ScenarioList {
	static ArrayList<Scenario> scenario_list = new ArrayList<Scenario>();
	
	public ScenarioList(){ }
	
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
	
	
}