package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Backend.AgentEnvironmentCreator;
import Backend.Grid;
import Backend.Matrix;
import Backend.ProductStepGenerators;
import Backend.ProgramData;
import GraphicalGridBuilder.GraphicalGrid;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class CreateGridModule extends JPanel implements ActionListener{
	//
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	private JSpinner input_xSize = new JSpinner();
	private JSpinner input_ySize = new JSpinner();
	private JButton buildGrid_Btn = new JButton("Build Grid");
	private JPanel previewPanel = new JPanel();
	private JPanel gridSizePanel = new JPanel();
	
	private JTextField grid_string = new JTextField("1-2,0-3,0-3,1-2");
	private JButton buildCustomGrid_Btn = new JButton("Build Custom Grid");
	
	
	public CreateGridModule() {
		buildScreen();
	}
	
	public void buildScreen(){
        builder.append(new JLabel("Size x-axis :"), input_xSize);

		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize, buildGrid_Btn);

		builder.nextLine();
		builder.appendSeparator("Additional values");
		
        builder.append(new JLabel("Input string:"), grid_string);
        builder.append(buildCustomGrid_Btn);
		
		builder.nextLine();
		builder.appendSeparator("Preview");
		builder.append(previewPanel);
		
		buildGrid_Btn.addActionListener(this);
		buildCustomGrid_Btn.addActionListener(this);
		
		gridSizePanel = builder.getPanel();
		builder = new ProgramData().getNewBuilder();
		add(gridSizePanel);
		setEnabled(gridSizePanel.getComponents(), true);
		
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
		
		if(x_size <= 1 || y_size <= 1) {
			JOptionPane.showMessageDialog(null,
			"Error: Please enter numbers that are at least 2", "Error Message",
			JOptionPane.ERROR_MESSAGE);
		} else {
			setEnabled(gridSizePanel.getComponents(), false);
			
			//Grid gc = new Grid();
			switch (source.getText()) {
				case "Build Grid":
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					previewPanel.add(graphicalGrid.draw(), BorderLayout.CENTER);
					previewPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					Grid.createNormalGrid(x_size, y_size);
					Matrix.createMatrix(x_size, y_size);
					ProductStepGenerators.setGridSize(x_size*y_size);
					break;
				case "Build Custom Grid":
					Grid.createCustom(x_size, y_size, grid_string.getText());
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
