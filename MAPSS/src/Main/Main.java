package Main;

import java.awt.EventQueue;

import Backend.AgentEnvironmentCreator;
import Backend.MapssFileHandler;
import Backend.ProgramData;
import Backend.Scenario;
import Gui.MainWindow;


public class Main{	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Multi-Agent Production System Simulator (MAPSS) started.");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentEnvironmentCreator aec = new AgentEnvironmentCreator();
					ProgramData pd = new ProgramData();
					pd.onProgramStart();
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					MapssFileHandler.loadScenarios();
					MainWindow.resetOutput();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
