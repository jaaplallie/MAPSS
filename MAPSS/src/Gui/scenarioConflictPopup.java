package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import Backend.Scenario;

public class scenarioConflictPopup extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -718685378351904341L;
	private JPanel contentPane;
	private JTextField textField;
	JButton btnNewButton;
	Scenario scenario = null;
	String new_name = "";
	ScenarioImportExportWindow parent = null;
	
	/**
	 * Create the frame.
	 * @param scenarioImportExportWindow 
	 */
	public scenarioConflictPopup(Scenario conflictedScenario, ScenarioImportExportWindow parent) {
		scenario = conflictedScenario;
		this.parent = parent;
		setTitle("Naming conflict detected!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 147);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow][]"));
		
		JLabel lbl1 = new JLabel("A naming conflict has been detected!");
		contentPane.add(lbl1, "cell 0 0,alignx center,growy");
		
		JLabel lblNewLabel = new JLabel("New scenario name: ");
		contentPane.add(lblNewLabel, "flowx,cell 0 1,alignx left,aligny center");
		
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 1,growx,aligny center");
		textField.setColumns(20);
		
		btnNewButton = new JButton("Accept");
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton, "cell 0 2,growx,aligny center");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(btnNewButton)){
			System.out.println("Accept Pressed");
			
			if(textField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "No name was given!\nPlease provide a new name for the imported scenario..");
			}
			else{
				System.out.println("Text check passed.");
				//scenario.setScenarioName(textField.getText());
				parent.setEnabled(true);
				parent.validate();
				this.dispose();
			}
		}
	}
}
