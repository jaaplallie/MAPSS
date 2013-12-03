package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Backend.AgentEnvironmentCreator;
import Backend.ProgramData;
import GraphicalGridBuilder.GraphicalGrid;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class CreateGraphicalGridModule extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1325339968009748727L;
	DefaultFormBuilder builder;
	JPanel optionsPanel = new JPanel();
	JPanel editorPanel = new JPanel();
	JPanel editorPanel_NewCard,editorPanel_EditCard;
	CardLayout cards;
	JPanel graphicalGridPanel = new JPanel();
	JComboBox<String> graphicalGridComboBox = new JComboBox<String>();
	private JButton newGraphicalGrid_btn, editGraphicalGrid_btn, removeGraphicalGrid_btn, saveGrid_btn;
	private JTextField input_gridName_New;
	private JSpinner input_xSize_New;
	private JSpinner input_ySize_New;
	private JTextField input_gridName_Edit;
	private JSpinner input_xSize_Edit;
	private JSpinner input_ySize_Edit;
	
	private JButton change_gridName_btn;
	private JButton change_xSize_btn;
	private JButton change_ySize_btn;
	private JButton buildGraphicalGridNew_Btn;
	private JButton buildGraphicalGridEdit_Btn;
	
	/**
	 * Create the panel.
	 */
	public CreateGraphicalGridModule() {
		builder = new ProgramData().getNewBuilder();
		setLayout(new BorderLayout(0, 0));
		this.updateGraphicalGridComboBox();
		
		input_gridName_New = new JTextField();
		input_xSize_New = new JSpinner();
		input_ySize_New = new JSpinner();
		input_gridName_Edit = new JTextField();
		input_xSize_Edit = new JSpinner();
		input_ySize_Edit = new JSpinner();
		change_gridName_btn = new JButton("");
		change_xSize_btn = new JButton("");
		change_ySize_btn = new JButton("");
		
		newGraphicalGrid_btn = new JButton("New");
		ImageIcon addIcon = new ImageIcon("img/icons/add.png");
		newGraphicalGrid_btn.setIcon(addIcon);
		newGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		newGraphicalGrid_btn.setContentAreaFilled(false);
		newGraphicalGrid_btn.addActionListener(this);
		editGraphicalGrid_btn = new JButton("Edit");
		ImageIcon editIcon = new ImageIcon("img/icons/pencil.png");
		editGraphicalGrid_btn.setIcon(editIcon);
		change_gridName_btn.setBorder(BorderFactory.createEmptyBorder());
		change_gridName_btn.setContentAreaFilled(false);
		change_gridName_btn.setIcon(editIcon);
		change_xSize_btn.setBorder(BorderFactory.createEmptyBorder());
		change_xSize_btn.setContentAreaFilled(false);
		change_xSize_btn.setIcon(editIcon);
		change_ySize_btn.setBorder(BorderFactory.createEmptyBorder());
		change_ySize_btn.setContentAreaFilled(false);
		change_ySize_btn.setIcon(editIcon);
		editGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		editGraphicalGrid_btn.setContentAreaFilled(false);
		editGraphicalGrid_btn.addActionListener(this);
		removeGraphicalGrid_btn = new JButton("Remove");
		ImageIcon removeIcon = new ImageIcon("img/icons/delete.png");
		removeGraphicalGrid_btn.setIcon(removeIcon);
		removeGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		removeGraphicalGrid_btn.setContentAreaFilled(false);
		removeGraphicalGrid_btn.addActionListener(this);
		saveGrid_btn = new JButton();
		ImageIcon saveIcon = new ImageIcon("img/icons/disk.png");
		saveGrid_btn.setIcon(saveIcon);
		saveGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		saveGrid_btn.setContentAreaFilled(false);
		saveGrid_btn.addActionListener(this);
		optionsPanel.add(newGraphicalGrid_btn);
		optionsPanel.add(Box.createRigidArea(new Dimension(10,0)));
		optionsPanel.add(editGraphicalGrid_btn);
		optionsPanel.add(Box.createRigidArea(new Dimension(10,0)));
		optionsPanel.add(removeGraphicalGrid_btn);
		optionsPanel.add(Box.createRigidArea(new Dimension(10,0)));
		optionsPanel.add(graphicalGridComboBox);
		optionsPanel.add(Box.createRigidArea(new Dimension(10,0)));
		optionsPanel.add(saveGrid_btn);
		optionsPanel.add(Box.createRigidArea(new Dimension(10,0)));
		builder = new ProgramData().getNewBuilder();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		createEditorPanel();
		add(optionsPanel, BorderLayout.NORTH);
		add(editorPanel, BorderLayout.CENTER);
		add(graphicalGridPanel, BorderLayout.SOUTH);
	}
	
	private void updateGraphicalGridComboBox(){	
		for(int index = 0; index < ProgramData.tmpGraphicalGridModel.size(); index++){
			graphicalGridComboBox.insertItemAt(ProgramData.tmpGraphicalGridModel.get(index).toString(), index);
		}
	}
	
	public void setEnabled(Component[] components, Boolean b){
		for(Component c : components){
			c.setEnabled(b);
		}
	}
	
	public JPanel createEditorPanel(){
		builder = new ProgramData().getNewBuilder();
		
		cards = new CardLayout();
		editorPanel.setLayout(cards);
		
		input_gridName_New = new JTextField();
		input_xSize_New = new JSpinner();
		input_ySize_New = new JSpinner();
		change_gridName_btn = new JButton("");
		change_xSize_btn = new JButton("");
		change_ySize_btn = new JButton("");
		ImageIcon editIcon = new ImageIcon("img/icons/pencil.png");
		change_gridName_btn.setBorder(BorderFactory.createEmptyBorder());
		change_gridName_btn.setContentAreaFilled(false);
		change_gridName_btn.setIcon(editIcon);
		change_xSize_btn.setBorder(BorderFactory.createEmptyBorder());
		change_xSize_btn.setContentAreaFilled(false);
		change_xSize_btn.setIcon(editIcon);
		change_ySize_btn.setBorder(BorderFactory.createEmptyBorder());
		change_ySize_btn.setContentAreaFilled(false);
		change_ySize_btn.setIcon(editIcon);
		
		editorPanel_NewCard = new JPanel();
		builder.append(new JLabel("Grid Name :"), input_gridName_New);
		builder.nextLine();
		builder.append(new JLabel("Size x-axis :"), input_xSize_New);
		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize_New);
		builder.nextLine();
		builder.append(Box.createRigidArea(new Dimension(10,0)));
		buildGraphicalGridNew_Btn = new JButton("Build GraphicalGrid");
		buildGraphicalGridNew_Btn.addActionListener(this);
		builder.append(buildGraphicalGridNew_Btn);
		editorPanel.add(builder.getPanel(), "n");
		builder = new ProgramData().getNewBuilder();
		
		editorPanel_EditCard = new JPanel();
		builder.append(new JLabel("Grid Name :"), input_gridName_Edit, change_gridName_btn);
		builder.nextLine();
		builder.append(new JLabel("Size x-axis :"), input_xSize_Edit);
		builder.append(change_xSize_btn);
		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize_Edit, change_ySize_btn);
		builder.nextLine();
		builder.append(Box.createRigidArea(new Dimension(10,0)));
		buildGraphicalGridEdit_Btn = new JButton("Build GraphicalGrid");
		buildGraphicalGridEdit_Btn.addActionListener(this);
		builder.append(buildGraphicalGridEdit_Btn);
		editorPanel.add(builder.getPanel(), "e");
		builder = new ProgramData().getNewBuilder();
		
		return editorPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Integer x_size = -1;
		Integer y_size = -1;
		
		if(source == newGraphicalGrid_btn){
			input_gridName_New.setEnabled(true);
			input_xSize_New.setEnabled(true);
			input_ySize_New.setEnabled(true);
			buildGraphicalGridNew_Btn.setEnabled(true);
			editorPanel_NewCard.invalidate();
			editorPanel_NewCard.validate();
			editorPanel_NewCard.repaint();
			cards.show(editorPanel, "n");
			
		}
		else if(source == editGraphicalGrid_btn){
			input_gridName_New.setEnabled(false);
			input_xSize_New.setEnabled(false);
			input_ySize_New.setEnabled(false);
			buildGraphicalGridNew_Btn.setEnabled(false);
			editorPanel_EditCard.invalidate();
			editorPanel_EditCard.validate();
			editorPanel_EditCard.repaint();
			cards.show(editorPanel, "e");
		}
		
		else {
			//Grid gc = new Grid();
			if(source == buildGraphicalGridNew_Btn) {
				try {
					x_size = Integer.parseInt(input_xSize_New.getValue().toString());
					y_size = Integer.parseInt(input_ySize_New.getValue().toString());
				} catch(NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				
				if(x_size <= 1 || y_size <= 1 || x_size > 10 || y_size > 10) {
					JOptionPane.showMessageDialog(null,
					"Error: Please enter numbers that are atleast 2 and max 10", "Error Message",
					JOptionPane.ERROR_MESSAGE);
					if(x_size <= 1){
						input_xSize_New.setValue(2);
						x_size = 2;
					}
					if(y_size <= 1){
						input_ySize_New.setValue(2);
						y_size = 2;
					}
					
					if(x_size > 10){
						input_xSize_New.setValue(10);
						x_size = 10;
					}
					if(y_size > 10){
						input_ySize_New.setValue(10);
						y_size = 10;
					}
					
				}else{
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					graphicalGridPanel.removeAll();
					graphicalGridPanel.add(graphicalGrid.draw());
					graphicalGridPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					//Grid.create(x_size, y_size);
				}
			}
			if(source == buildGraphicalGridEdit_Btn) {
				try {
					x_size = Integer.parseInt(input_xSize_Edit.getValue().toString());
					y_size = Integer.parseInt(input_ySize_Edit.getValue().toString());
				} catch(NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				
				if(x_size <= 1 || y_size <= 1 || x_size > 10 || y_size > 10) {
					JOptionPane.showMessageDialog(null,
					"Error: Please enter numbers that are atleast 2 and max 10", "Error Message",
					JOptionPane.ERROR_MESSAGE);
					if(x_size <= 1){
						input_xSize_Edit.setValue(2);
						x_size = 2;
					}
					if(y_size <= 1){
						input_ySize_Edit.setValue(2);
						y_size = 2;
					}
					
					if(x_size > 10){
						input_xSize_Edit.setValue(10);
						x_size = 10;
					}
					if(y_size > 10){
						input_ySize_Edit.setValue(10);
						y_size = 10;
					}
					
				}
				else{
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					graphicalGridPanel.removeAll();
					graphicalGridPanel.add(graphicalGrid.draw());
					graphicalGridPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					//Grid.create(x_size, y_size);
				}
			}
			
//			try {
//				AgentEnvironmentCreator.addSchedulerAgent();
//				AgentEnvironmentCreator.addRemoteMonitoringAgent();
//			} catch (StaleProxyException spe) {
//				spe.printStackTrace();
//			}
			
		}
	}
}
