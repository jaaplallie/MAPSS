package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTable;
import Gui.*;

public class MainWindow {

	public JFrame frame;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					mainWindow window = new mainWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel1 = new JPanel();
		panel1.add(new AgentListEditorModule());
		tabbedPane.addTab("Setup", null, panel1, null);
		
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Simulation", null, panel2, null);
		
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Last Results", null, panel3, null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(100, 15);
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewScenario = new JMenuItem("New Scenario");
		mnFile.add(mntmNewScenario);
		
		JMenuItem mntmImportScenario = new JMenuItem("Import Scenario");
		mnFile.add(mntmImportScenario);
		
		JMenuItem mntmExportScenario = new JMenuItem("Export Scenario");
		mnFile.add(mntmExportScenario);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAgents = new JMenuItem("Agents");
		mnEdit.add(mntmAgents);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mnEdit.add(mntmProducts);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		
	}

}
