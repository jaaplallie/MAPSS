package Gui;

import jade.wrapper.StaleProxyException;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Backend.AgentEnvironmentCreator;
import Backend.Grid;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class CreateGridModule extends JPanel implements ActionListener{

	DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
	private JSpinner input_xSize = new JSpinner();
	private JSpinner input_ySize = new JSpinner();
	private JButton buildGrid_Btn = new JButton("Build Grid");
	private JPanel gridSizePanel = new JPanel();
	
	private JTextField grid_string = new JTextField("1-2,0-3,0-3,1-2");
	private JButton buildCustomGrid_Btn = new JButton("Build Custom Grid");
	
	
	public CreateGridModule() {
		buildScreen();
	}
	
	public void buildScreen(){
		builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        
        
        builder.append(new JLabel("Size x-axis :"), input_xSize);
		//builder.append(input_xSize);
		builder.nextLine();
		builder.append(new JLabel("Size y-axis :"), input_ySize, buildGrid_Btn);
		//builder.append();
		//builder.append();
		builder.nextLine();
		builder.appendSeparator("Additional values");
		
        builder.append(new JLabel("Input string:"), grid_string);
        builder.append(buildCustomGrid_Btn);
		
		builder.nextLine();
		builder.appendSeparator("Preview");
		
		buildGrid_Btn.addActionListener(this);
		buildCustomGrid_Btn.addActionListener(this);
		
		gridSizePanel = builder.getPanel();
		builder = getNewBuilder();
		add(gridSizePanel);
		setEnabled(gridSizePanel.getComponents(), true);
		
	}

	public void setEnabled(Component[] components, Boolean b){
		for(Component c : components){
			c.setEnabled(b);
		}
	}
	
	private DefaultFormBuilder getNewBuilder(){
		DefaultFormBuilder new_builder = new DefaultFormBuilder(new FormLayout(""));
		new_builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		new_builder.appendColumn("right:pref");
		new_builder.appendColumn("3dlu");
		new_builder.appendColumn("fill:max(pref; 100px)");
		new_builder.appendColumn("5dlu");
		new_builder.appendColumn("right:pref");
		new_builder.appendColumn("3dlu");
		new_builder.appendColumn("fill:max(pref; 100px)");
		
		return new_builder;
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
			"Error: Please enter numbers that are atleast 2", "Error Massage",
			JOptionPane.ERROR_MESSAGE);
		} else {
			setEnabled(gridSizePanel.getComponents(), false);
			
			//Grid gc = new Grid();
			switch (source.getText()) {
				case "Build Grid": 
					Grid.create(x_size, y_size);
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
