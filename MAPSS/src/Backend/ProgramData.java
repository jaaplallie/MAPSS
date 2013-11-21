package Backend;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import GraphicalGridBuilder.GraphicalGrid;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ProgramData {
	public static DefaultListModel<Scenario> scenarioListModel = new DefaultListModel<Scenario>();
	public static ArrayList<GraphicalGrid> graphicalGridModel = new ArrayList<GraphicalGrid>();
	private static Scenario loadedScenario = null;
	
	public ProgramData() {
		super();
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
	
}
