package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import Backend.AgentEnvironmentCreator;
import Backend.ProgramData;
import Gui.MainWindow;

public class main{	
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
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
