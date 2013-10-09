package Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class AgentListEditorModule extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622313351868642228L;
	
	DefaultListModel agentListModel = new DefaultListModel();
	JList agentList = new JList(agentListModel);
	JScrollPane agentListScrollPane = new JScrollPane(agentList);
	JPanel agentFormContainer = new JPanel();
	
	public AgentListEditorModule() {
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
		agentFormContainer.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("200px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		add(agentFormContainer, BorderLayout.EAST);
		
		JButton button = new JButton("New Agent");
		agentFormContainer.add(button, "2, 2, left, top");
		JButton button_1 = new JButton("Edit Selected Agent");
		agentFormContainer.add(button_1, "2, 4, left, top");
		JButton button_2 = new JButton("Delete Selected Agent");
		agentFormContainer.add(button_2, "2, 6, left, top");
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