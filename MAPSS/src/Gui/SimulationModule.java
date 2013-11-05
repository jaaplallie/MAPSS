package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Backend.AgentEnvironmentCreator;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class SimulationModule extends JPanel implements ActionListener{

	private DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
	private JButton startScenario_btn = new JButton("Start Scenario in JADE");
	
	public SimulationModule() {
		builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        
        builder.append(startScenario_btn);
        startScenario_btn.addActionListener(this);
        add(builder.getPanel());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(AgentEnvironmentCreator.getCurrentlyLoadedScenario() == null){
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