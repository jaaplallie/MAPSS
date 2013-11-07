package Gui;

import jade.Boot;
import jade.BootGUI;
import jade.BootProfileImpl;
import jade.MicroBoot;
import jade.core.AgentContainer;
import jade.core.PlatformManager;
import jade.core.PlatformManagerImpl;
import jade.core.Profile;
import jade.core.ProfileException;
import jade.core.ServiceFinder;
import jade.core.management.AgentManagementService;
import jade.mtp.MTPException;
import jade.wrapper.StaleProxyException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.AgentEnvironmentCreator;
import Backend.ChartCreator;
import Backend.XmlReader;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class MainWindow implements WindowListener{

	public JFrame frame;
	private DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
	ScenarioImportExportWindow scenarioImportExportWindow = new ScenarioImportExportWindow();
	
	public MainWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1031, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(this);
		frame.setTitle("MAPSS - Multi Agent Production System Simulator");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        
		CreateGridModule createGridModule = new CreateGridModule();
		//ProductListEditorModule productListEditorModule = new ProductListEditorModule();
		
		builder.append(createGridModule);
		builder.nextLine();
		
		JPanel setupTab = builder.getPanel();
		builder = getNewBuilder();
		tabbedPane.addTab("Scenario Setup", null, setupTab, null);
		
		SimulationModule simulationModule = new SimulationModule();
		builder.append(simulationModule);
		
		JPanel simulationTab = builder.getPanel();
		builder = getNewBuilder();
		tabbedPane.addTab("Simulation Data", null, simulationTab, null);
		
		ChartCreator chartcreator = new ChartCreator();
		DefaultCategoryDataset data2 = new DefaultCategoryDataset();		
		data2.addValue(9.0, "p1", "Category 1");
		data2.addValue(6.0, "p1", "Category 2");
		data2.addValue(2.0, "p1", "Category 3");
		data2.addValue(9.0, "p2", "Category 1");
		data2.addValue(6.0, "p2", "Category 2");
		data2.addValue(2.0, "p2", "Category 3");
		data2.addValue(9.0, "p3", "Category 1");
		data2.addValue(6.0, "p3", "Category 2");
		data2.addValue(2.0, "p3", "Category 3");
		data2.addValue(9.0, "p4", "Category 1");
		data2.addValue(6.0, "p4", "Category 2");
		data2.addValue(2.0, "p4", "Category 3");
		JFreeChart ch = chartcreator.drawBarChart("Barchart Test", "x", "y", data2);
		ChartPresenter chartpres = new ChartPresenter();
		chartpres.addChart(ch);
		builder.append(chartpres);
		builder.nextLine();
		JPanel lastResultsTab = builder.getPanel();
		builder = getNewBuilder();
		tabbedPane.addTab("Last Results", null, lastResultsTab, null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(100, 15);
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewScenario = new JMenuItem("New Scenario");
		mnFile.add(mntmNewScenario);
		
		JMenuItem mntmImportScenario = new JMenuItem("Import/Export Scenario");
		mnFile.add(mntmImportScenario);
		mntmImportScenario.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scenarioImportExportWindow.setVisible(true);
			}
		});
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAgents = new JMenuItem("Agents");
		mnEdit.add(mntmAgents);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mnEdit.add(mntmProducts);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmJadeGui = new JMenuItem("Jade Boot GUI");
		mnHelp.add(mntmJadeGui);
		mntmJadeGui.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AgentEnvironmentCreator.addRemoteMonitoringAgent();
				} catch (StaleProxyException e1) {
					e1.printStackTrace();
				}
//				String[] bootArgs = { 
//						"-gui",
//						String.format("-mtp \"jade.mtp.http.MessageTransportProtocol(http://{0}:{1}/acc)\"", Profile.LOCAL_HOST, Profile.LOCAL_PORT),
//						"agentInstance:jade.core.Agent()"
//				};
//				
//				@SuppressWarnings("unused")
//				Boot boot = new Boot();
//				Boot.main(bootArgs);
//				AgentEnvironmentCreator.getRuntime().startUp(AgentEnvironmentCreator.getProfile());
			}});
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
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

	@Override
	public void windowActivated(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Main Window Closed.");
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Main Window Closing...");
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	@Override
	public void windowIconified(WindowEvent e) {
	}
	@Override
	public void windowOpened(WindowEvent e) {
	}
}
