package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Backend.AgentEnvironmentCreator;
import Backend.ProgramData;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class SimulationModule extends JPanel implements ActionListener{

	private DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	private JButton startScenario_btn = new JButton("Start Scenario in JADE");
	
	public SimulationModule() {       
        builder.append(startScenario_btn);
        startScenario_btn.addActionListener(this);
        add(builder.getPanel());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(ProgramData.getCurrentlyLoadedScenario() == null){
			JOptionPane.showMessageDialog(
            		null, 
            		"No Scenario Loaded! Could not start simulation", 
            		"Error!", 
            		JOptionPane.ERROR_MESSAGE
    		);
		}
		else{
			AgentEnvironmentCreator.startScenario();
		}
	}

}
