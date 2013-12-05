package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Backend.AgentEnvironmentCreator;
import Backend.Grid;
import Backend.MapssFileWriter;
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
	private JTextField name_field = new JTextField();
	private JButton buildGrid_Btn = new JButton("Build Grid");
	private JPanel previewPanel = new JPanel();
	private JPanel gridSizePanel = new JPanel();
	
	static ArrayList[] neighbors;
	
	private JTextField grid_string = new JTextField("1-2,0-3,0-3,1-2");
	private JButton buildCustomGrid_Btn = new JButton("Build Custom Grid");
	
	
	public CreateGridModule() {
		buildScreen();
	}
	
	public void buildScreen(){
        builder.append(new JLabel("Size x-axis :"), input_xSize);

		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize);
		
		builder.nextLine();
		builder.append(new JLabel("Name :"), name_field);
		
		builder.append(buildGrid_Btn);

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
			String name = name_field.getText();
			if (name != null && name.isEmpty()){
				name = "No_name"+x_size+"x"+y_size;
			} else {
				name += x_size+"x"+y_size;
			}
			switch (source.getText()) {
			
				case "Build Grid":
					previewPanel.removeAll();
					GraphicalGrid graphicalGrid = new GraphicalGrid(x_size, y_size);
					previewPanel.add(graphicalGrid.draw(), BorderLayout.CENTER);
					previewPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					
					neighbors = new ArrayList[x_size*y_size];
					
					int stepnr = 0;
					for (int y = 0; y < y_size; y++){
						for (int x = 0; x < x_size; x++){
							ArrayList<Integer> known_equiplets = new ArrayList<Integer>();
							
							if (y != 0){ //if not first row
								known_equiplets.add(stepnr-x_size);
							}
							if (stepnr != (x_size*(y+1))-1){ //if not end of row
								known_equiplets.add(stepnr+1);
							}
							if (stepnr+x_size < y_size*x_size){ //if not last row
								known_equiplets.add(stepnr+x_size);
							}
							if (stepnr != x_size*y){ //if not start of row
								known_equiplets.add(stepnr-1);
							}
							
							neighbors[stepnr] = known_equiplets;
							stepnr++;
						}
					}
					
					Grid.createStructure(x_size, y_size, name, neighbors);
					MapssFileWriter.saveStructure(name, neighbors, null);
					//Grid.createNormalGrid(x_size, y_size, name);
					
					break;
				case "Build Custom Grid":
					
					previewPanel.removeAll();
					GraphicalGrid graphicalGrid2 = new GraphicalGrid(x_size, y_size);
					previewPanel.add(graphicalGrid2.draw(), BorderLayout.CENTER);
					previewPanel.setVisible(true);
					invalidate();
					validate();
					repaint();
					
					
					neighbors = new ArrayList[x_size*y_size];
					ArrayList<Integer> tempList;
	                int telnr = 0;
	                String relations = grid_string.toString();
					
					for (String s : relations.split(",")){
                        String[] temp = s.split("-");
                        tempList = new ArrayList<Integer>();
                        for (int i = 0; i < temp.length; i++){
                                int tempInt = Integer.parseInt(temp[i]);
                                tempList.add(tempInt);
                        }
                        neighbors[telnr] = tempList;
                        telnr++;
					}
					
					Grid.createStructure(x_size, y_size, name, neighbors);
					MapssFileWriter.saveStructure(name, neighbors, null);
			
					//Grid.createCustom(x_size, y_size, grid_string.getText());

					break;
				default:
					break;
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
