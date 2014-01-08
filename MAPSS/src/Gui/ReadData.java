package Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;





import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Backend.MapssFileHandler;
import Backend.ProgramData;
import Backend.Scenario;
import Backend.ScenarioList;

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
	private JButton clear_data = new JButton("clear");
	
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	
	private String output = "******************Output******************";
	private JPanel textPanel = new JPanel();
	private JLabel text = new JLabel(output);
	
	
	public ReadData(){
		
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
        
		
		//setLayout(new BorderLayout());
        
        generic_btn.addActionListener(this);
        scenario_btn.addActionListener(this);
        data_btn.addActionListener(this);
        log_btn.addActionListener(this);
        
        add(builder.getPanel());
        
	}
	

	
	public void actionPerformed(ActionEvent e) {
		Scenario S = ScenarioList.getScenario((String)structureBox.getSelectedItem());
		JButton source = (JButton)e.getSource();
		MainWindow.stringToOutput("reading: " +S.name);
		switch (source.getText()) {
			case "View generic data":
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
	
	
	public static void updateProductStructures(){
		structureBox.removeAllItems();
		for (String name: ScenarioList.getScenarioNames()){
			structureBox.addItem(name);
		}
	}
	
	
}
