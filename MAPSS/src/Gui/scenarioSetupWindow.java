package Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class scenarioSetupWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622313351868642228L;
	
	DefaultListModel agentListModel = new DefaultListModel();
	JList agentList = new JList(agentListModel);
	JScrollPane agentListScrollPane = new JScrollPane(agentList);
	JPanel agentFormContainer = new JPanel();
	
	public scenarioSetupWindow() {
		setLayout(new BorderLayout());
		agentListModel.addElement("Jane Doe");
		agentListModel.addElement("John Smith");
		agentListModel.addElement("Kathy Green");
		agentListModel.addElement("Jane Smith");
		agentListModel.addElement("John Green");
		agentListModel.addElement("Kathy Doe");
		agentListModel.addElement("Jane Green");
		agentListModel.addElement("John Doe");
		setLayout(new BorderLayout(0, 0));
		agentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		agentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		agentList.setVisibleRowCount(-1);
		agentListScrollPane.setPreferredSize(new Dimension(100, 150));
		add(agentListScrollPane, BorderLayout.WEST);
		
		agentFormContainer.add(new JButton("hoihe1"));
		agentFormContainer.add(new JButton("hoihe2"));
		agentFormContainer.add(new JButton("hoihe3"));
		add(agentFormContainer, BorderLayout.EAST);
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (agentList.getSelectedIndex() == -1) {
	        //No selection

	        } else {
	        //Selection
	        	//list.getSelectedIndex() = selected index
	        }
	    }
	}
}
