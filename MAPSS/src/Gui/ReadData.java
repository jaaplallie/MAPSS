package Gui;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Backend.MapssFileHandler;
import Backend.ProgramData;
import Backend.Scenario;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class ReadData extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JComboBox<String> structureBox = new JComboBox<String>();
	private JButton generic_btn = new JButton("View generic data");
	private JButton scenario_btn = new JButton("View scenario file");
	private JButton data_btn = new JButton("View data file");
	private JButton log_btn = new JButton("View log file");
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	
	public ReadData(){
		
		builder.appendColumn("right:pref");
		
		structureBox.setEditable(false);
		structureBox.setVisible(true);

		generic_btn.setVisible(true);
		scenario_btn.setVisible(true);
		data_btn.setVisible(true);
		log_btn.setVisible(true);
		
        builder.append("Select a structure:", structureBox);
        builder.nextLine();
        builder.append("",generic_btn);
        builder.nextLine();
        builder.append("",scenario_btn);
        builder.nextLine();
        builder.append("",data_btn);
        builder.nextLine();
        builder.append("",log_btn);
        builder.nextLine();
     
        generic_btn.addActionListener(this);
        scenario_btn.addActionListener(this);
        data_btn.addActionListener(this);
        log_btn.addActionListener(this);
        
        add(builder.getPanel());
        
	}
	

	
	public void actionPerformed(ActionEvent e) {
		Scenario S = ProgramData.getScenario((String)structureBox.getSelectedItem());
		JButton source = (JButton)e.getSource();
		MainWindow.stringToOutput("reading: " +S.name);
		switch (source.getText()) {
			case "View generic data":
				MainWindow.stringToOutput("Size: " + S.x + " x " + S.y);
				MainWindow.stringToOutput("Products: " + S.getProducts().size());
				MainWindow.stringToOutput("Path difference: " + S.comparePaths());
				MapssFileHandler.averagePathToScreen(S);
				//MapssFileHandler.dataFileToScreen(S);
				break;
			case "View scenario file":
				MapssFileHandler.scenarioFileToScreen(S);
				break;
			case "View data file":
				MapssFileHandler.dataFileToScreen(S);
				break;
			case "View log file":
				MapssFileHandler.logFileToScreen(S);
				break;
			default:
				break;
		}
		
		
	}
	
	
	public static void updateStructureBox(){
		structureBox.removeAllItems();
		for (String name: ProgramData.getScenarioNames()){
			structureBox.addItem(name);
		}
	}
	
	
}
