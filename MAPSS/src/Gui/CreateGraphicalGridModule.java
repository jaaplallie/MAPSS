package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;

import Backend.AgentEnvironmentCreator;
import Backend.Grid;
import Backend.ProgramData;
import GraphicalGridBuilder.GraphicalGrid;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class CreateGraphicalGridModule extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1325339968009748727L;
	DefaultFormBuilder builder;
	JPanel optionsPanel = new JPanel();
	JPanel editorPanel = new JPanel();
	JPanel graphicalGridPanel = new JPanel();
	JComboBox<String> graphicalGridComboBox = new JComboBox<String>();
	private JButton newGraphicalGrid_btn, editGraphicalGrid_btn, removeGraphicalGrid_btn;
	private JSpinner input_xSize = new JSpinner();
	private JSpinner input_ySize = new JSpinner();
	private JButton buildGraphicalGrid_Btn;
	
	/**
	 * Create the panel.
	 */
	public CreateGraphicalGridModule() {
		JPanel gridPane = new JPanel();
		builder = new ProgramData().getNewBuilder();
		setLayout(new BorderLayout(0, 0));
		this.updateGraphicalGridComboBox();
		
		newGraphicalGrid_btn = new JButton("New");
		ImageIcon addIcon = new ImageIcon("img/icons/add.png");
		newGraphicalGrid_btn.setIcon(addIcon);
		newGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		newGraphicalGrid_btn.setContentAreaFilled(false);
		newGraphicalGrid_btn.addActionListener(this);
		editGraphicalGrid_btn = new JButton("Edit");
		ImageIcon editIcon = new ImageIcon("img/icons/pencil.png");
		editGraphicalGrid_btn.setIcon(editIcon);
		editGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		editGraphicalGrid_btn.setContentAreaFilled(false);
		editGraphicalGrid_btn.addActionListener(this);
		removeGraphicalGrid_btn = new JButton("Remove");
		ImageIcon removeIcon = new ImageIcon("img/icons/delete.png");
		removeGraphicalGrid_btn.setIcon(removeIcon);
		removeGraphicalGrid_btn.setBorder(BorderFactory.createEmptyBorder());
		removeGraphicalGrid_btn.setContentAreaFilled(false);
		removeGraphicalGrid_btn.addActionListener(this);
		builder.append(newGraphicalGrid_btn);
		builder.append(editGraphicalGrid_btn);
		builder.append(removeGraphicalGrid_btn);
		builder.append(graphicalGridComboBox);
		optionsPanel = builder.getPanel();
		builder = new ProgramData().getNewBuilder();
		add(optionsPanel, BorderLayout.NORTH);
		
		builder.append(new JLabel("Size x-axis :"), input_xSize);
		builder.nextLine();
		buildGraphicalGrid_Btn = new JButton("Build GraphicalGrid");
		buildGraphicalGrid_Btn.addActionListener(this);
		builder.append(new JLabel("Size y-axis :"), input_ySize, buildGraphicalGrid_Btn);
		editorPanel = builder.getPanel();
		
		gridPane.add(editorPanel);
		gridPane.add(graphicalGridPanel);
		add(gridPane, BorderLayout.CENTER);
	}
	
	private void updateGraphicalGridComboBox(){
		for(int index = 0; index < ProgramData.graphicalGridModel.size(); index++){
			graphicalGridComboBox.insertItemAt(ProgramData.graphicalGridModel.get(index).toString(), index);
		}
	}
	
	public void setEnabled(Component[] components, Boolean b){
		for(Component c : components){
			c.setEnabled(b);
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		Integer x_size = -1;
		Integer y_size = -1;
		
		try {
			x_size = Integer.parseInt(input_xSize.getValue().toString());
			y_size = Integer.parseInt(input_ySize.getValue().toString());
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		
		if(x_size <= 1 || y_size <= 1 || x_size > 10 || y_size > 10) {
			JOptionPane.showMessageDialog(null,
			"Error: Please enter numbers that are atleast 2 and max 10", "Error Message",
			JOptionPane.ERROR_MESSAGE);
			if(x_size <= 1){
				input_xSize.setValue(2);
				x_size = 2;
			}
			if(y_size <= 1){
				input_ySize.setValue(2);
				y_size = 2;
			}
			
			if(x_size > 10){
				input_xSize.setValue(10);
				x_size = 10;
			}
			if(y_size > 10){
				input_ySize.setValue(10);
				y_size = 10;
			}
			
		} else {
			//Grid gc = new Grid();
			switch (source.getText()) {
				case "Build GraphicalGrid":
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					graphicalGridPanel.removeAll();
					graphicalGridPanel.add(graphicalGrid.draw());
					graphicalGridPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					//Grid.create(x_size, y_size);
					break;
				default:
					break;
			}
			
			try {
				AgentEnvironmentCreator.addSchedulerAgent();
				AgentEnvironmentCreator.addRemoteMonitoringAgent();
			} catch (StaleProxyException spe) {
				spe.printStackTrace();
			}
			
		}
	}
}
