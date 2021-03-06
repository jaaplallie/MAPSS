package Main;

import java.awt.EventQueue;

import Backend.AgentEnvironmentCreator;
import Backend.MapssFileHandler;
import Backend.ProgramData;
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
					@SuppressWarnings("unused")
					AgentEnvironmentCreator aec = new AgentEnvironmentCreator();
					ProgramData pd = new ProgramData();
					pd.onProgramStart();
//					MapssFileHandler.loadScenarios();
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					
					MainWindow.resetOutput();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
