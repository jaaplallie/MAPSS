package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import Backend.AgentEnvironmentCreator;
import Backend.Grid;
import Backend.ProductStepGenerators;
import Backend.ProgramData;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class SimulationModule extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7220211678403298456L;
	private DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	private JButton startScenario_btn = new JButton("Start Scenario in JADE");
	
	private JSpinner products_snr = new JSpinner();
	private JSpinner product_steps_snr = new JSpinner();
	private JButton generate_products_btn = new JButton("Generate products+steps");
	
	JComboBox<String> productStepComboBox = new JComboBox<String>();
	
	private String regular = "Regular grid (All randomized)";
	private String increased = "Increased (Higher numbers are more popular)";
	private String twentyfive = "Multisteps (25% will be used twice as much)";
	
	static JComboBox<String> structureBox = new JComboBox<String>();
	
	
	public SimulationModule() {   
		
		productStepComboBox.setEditable(false);
		productStepComboBox.setVisible(true);
		productStepComboBox.addItem(regular);
		productStepComboBox.addItem(increased);
		productStepComboBox.addItem(twentyfive);
		
		structureBox.setEditable(false);
		structureBox.setVisible(true);
		
		
        builder.append(new JLabel("Number of products :"), products_snr);

		builder.nextLine();
		builder.append(new JLabel("Number of product steps :"), product_steps_snr);
		builder.nextLine();
		builder.append("Method:", productStepComboBox);
        builder.nextLine();
        builder.append("Structures:", structureBox);
        builder.nextLine();
        builder.appendSeparator();
		builder.append(generate_products_btn);
		generate_products_btn.addActionListener(this);
		builder.nextLine();
	
		builder.appendSeparator();
        builder.append(startScenario_btn);
        startScenario_btn.addActionListener(this);
        productStepComboBox.addActionListener(this);
        add(builder.getPanel());
	}
	
	public static void updateProductStructures(){
		structureBox.removeAllItems();
		for (String name: Grid.getStructureNames()){
			structureBox.addItem(name);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startScenario_btn)){
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
		} else if(e.getSource().equals(generate_products_btn)){
			
			Integer number_of_products = -1;
			Integer max_product_steps = -1;
			String type = "";
			
			try {
				number_of_products = Integer.parseInt(products_snr.getValue().toString());
				max_product_steps = Integer.parseInt(product_steps_snr.getValue().toString());
			} catch(NumberFormatException nfe) {
				nfe.printStackTrace();
			}
			
			if(number_of_products <= 0 || max_product_steps <= 0) {
				JOptionPane.showMessageDialog(null,
				"Error: We need products with steps", "Error Massage",
				JOptionPane.ERROR_MESSAGE);
			} else {
				
		    	if(productStepComboBox.getSelectedItem().equals(regular)){
		    		type = "regular";
		    	}
		    	else if(productStepComboBox.getSelectedItem().equals(increased)){
		    		type = "increased";
				}
		    	else if(productStepComboBox.getSelectedItem().equals(twentyfive)){
		    		type = "twentyfive";
				}
		    	
		    	String structure_name = (String)structureBox.getSelectedItem();
		    	
		    	ProductStepGenerators.generateProductBatch(number_of_products, max_product_steps, type, structure_name);
			
			}
		}
	}
}
