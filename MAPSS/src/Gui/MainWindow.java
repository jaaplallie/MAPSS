package Gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow implements WindowListener, WindowFocusListener, WindowStateListener {

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
		frame.addWindowListener(this);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("305px"),
				ColumnSpec.decode("305px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("305px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("1px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("150px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		AgentListEditorModule agentListEditorModule = new AgentListEditorModule();
		panel1.add(agentListEditorModule, "3, 2, left, top");
		tabbedPane.addTab("Setup", null, panel1, null);
		ProductListEditorModule productListEditorModule = new ProductListEditorModule();
		panel1.add(productListEditorModule, "4, 2, 2, 1, left, top");
		
	
		
		JSeparator separator = new JSeparator();
		panel1.add(separator, "8, 2, left, center");
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		panel1.add(horizontalStrut, "2, 4");
		
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


	@Override
	public void windowStateChanged(WindowEvent arg0) {	
	}
	@Override
	public void windowGainedFocus(WindowEvent arg0) {
	}
	@Override
	public void windowLostFocus(WindowEvent arg0) {
	}
	@Override
	public void windowActivated(WindowEvent e) {
	}
	@Override
	public void windowClosed(WindowEvent e) {
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Main Window Closed.");
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
