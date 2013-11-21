package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
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

public class CreateGraphicalGridModule extends JPanel {

	private static final long serialVersionUID = 1325339968009748727L;
	DefaultFormBuilder builder;
	JPanel editorPanel = new JPanel();
	JPanel graphicalGridPanel = new JPanel();
	private JSpinner input_xSize = new JSpinner();
	private JSpinner input_ySize = new JSpinner();
	private JButton buildGraphicalGrid_Btn = new JButton("Build GraphicalGrid");
	
	/**
	 * Create the panel.
	 */
	public CreateGraphicalGridModule() {
		builder = new ProgramData().getNewBuilder();
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		splitPane.setLeftComponent(editorPanel);
		splitPane.setRightComponent(graphicalGridPanel);
		
		builder.append(new JLabel("Size x-axis :"), input_xSize);
		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize, buildGraphicalGrid_Btn);
		editorPanel.add(builder.getPanel());
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
				case "Build Grid":
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					graphicalGridPanel.add(graphicalGrid.draw(), BorderLayout.CENTER);
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
