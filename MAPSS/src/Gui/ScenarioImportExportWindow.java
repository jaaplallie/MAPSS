package Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import Backend.AgentEnvironmentCreator;
import Backend.Scenario;
import Backend.XmlReader;
import Backend.XmlWriter;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ScenarioImportExportWindow extends JFrame implements ActionListener, FocusListener{

	private JPanel contentPane;
	private DefaultFormBuilder screenBuilder = getNewBuilder();
	JButton loadButton, importButton, exportButton, removeButton;
	private DefaultListModel<Scenario> scenarioListModel;
	private JList<Scenario> scenarioList;
	
//	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScenarioImportExportWindow frame = new ScenarioImportExportWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ScenarioImportExportWindow() {
		setTitle("Import/Export Scenario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        
		scenarioListModel = new DefaultListModel<Scenario>();
		scenarioList = new JList<Scenario>(scenarioListModel);
		JScrollPane scenarioListScrollPane = new JScrollPane(scenarioList);
        
		scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scenarioList.setLayoutOrientation(JList.VERTICAL);
		scenarioList.setVisibleRowCount(-1);
		scenarioList.addFocusListener(this);
		scenarioListScrollPane.setPreferredSize(new Dimension(175, 250));
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new MigLayout("", "[143px]", "[][23px][][][]"));
		
		JLabel buttonPnlLbl = new JLabel("Choose what you want to do:");
        buttonContainer.add(buttonPnlLbl, "cell 0 0,growx");

        loadButton = new JButton("Load Selected Scenario");
        loadButton.addActionListener(this);
        buttonContainer.add(loadButton, "cell 0 1,growx,aligny top");
        
        importButton = new JButton("Import Scenario");
        importButton.addActionListener(this);
        buttonContainer.add(importButton, "cell 0 2,growx,aligny top");
        
        exportButton = new JButton("Export Scenario");
        exportButton.setEnabled(false);
        exportButton.addActionListener(this);
        buttonContainer.add(exportButton, "cell 0 3,growx,aligny top");
        
        removeButton = new JButton("Remove Selected Scenario");
        removeButton.setEnabled(false);
        removeButton.addActionListener(this);
        buttonContainer.add(removeButton, "cell 0 4,alignx center,aligny top");
        
        screenBuilder.append(scenarioListScrollPane);
        screenBuilder.append(buttonContainer);
        setContentPane(screenBuilder.getPanel());
	}

	public DefaultFormBuilder getNewBuilder(){
		DefaultFormBuilder returnBuilder = new DefaultFormBuilder(new FormLayout(""));
		returnBuilder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		returnBuilder.appendColumn("right:pref");
		returnBuilder.appendColumn("3dlu");
		returnBuilder.appendColumn("fill:max(pref; 100px)");
		returnBuilder.appendColumn("5dlu");
		returnBuilder.appendColumn("right:pref");
		returnBuilder.appendColumn("3dlu");
		returnBuilder.appendColumn("fill:max(pref; 100px)");
		
		return returnBuilder;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(loadButton)){
			AgentEnvironmentCreator.setCurrentlyLoadedScenario(scenarioListModel.get(scenarioList.getSelectedIndex()));
		}
		else if(event.getSource().equals(importButton)){	
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("XML Scenario files (*.xml)", "xml", "XML");
            chooser.setFileFilter(xmlfilter);
            chooser.setDialogTitle("Import scenario file");
			XmlReader xmlReader = new XmlReader();
			Scenario readScenario;
		    int retrival = chooser.showOpenDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		        	readScenario = xmlReader.open(chooser.getSelectedFile().toPath().toString());
		        	AgentEnvironmentCreator.setCurrentlyLoadedScenario(readScenario);
		        	for(Object o : scenarioListModel.toArray()){
		        		Scenario s = (Scenario) o;
		        		if(s.getScenarioName().equals(readScenario.getScenarioName())){
		        			scenarioConflictPopup scp = new scenarioConflictPopup(readScenario, this);
		        			scp.setVisible(true);
		        			this.setEnabled(false);
		        		}
		        	}
		        	scenarioListModel.addElement(readScenario);
		            JOptionPane.showMessageDialog(
		            		null, 
		            		"File succesfully opened." + 
		            		"\nFilename : " + chooser.getSelectedFile() + "\n" +
		            		"Scenario added to scenario list", 
		            		"Success! Scenario loaded!", 
		            		JOptionPane.INFORMATION_MESSAGE
	        		);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(
		            		null, 
		            		"Something went wrong, scenario could not be loaded", 
		            		"Error! Scenario not loaded!", 
		            		JOptionPane.ERROR_MESSAGE
	        		);
		        }
		    }
		}
		else if(event.getSource().equals(exportButton)){		
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("MAPSS XML Scenario files (*.xml)", "xml", "XML");
            chooser.setFileFilter(xmlfilter);
            chooser.setDialogTitle("Export scenario file");
			XmlWriter xmlWriter = new XmlWriter();
		    int retrival = chooser.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		        	xmlWriter.Write(scenarioListModel.get(scenarioList.getSelectedIndex()), chooser.getSelectedFile()+".xml");
		            JOptionPane.showMessageDialog(
		            		null, 
		            		"Scenario succesfully saved as .xml to: \n" +
		            		chooser.getCurrentDirectory() + "\n" +
		            		"Filename : " + chooser.getSelectedFile() + ".xml", 
		            		"Scenario saved succesfully!", 
		            		JOptionPane.INFORMATION_MESSAGE
	        		);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(
		            		null, 
		            		"Something went wrong, scenario could not be saved!" +
		            		"\nPlease try again...", 
		            		"Error! Scenario not saved!", 
		            		JOptionPane.ERROR_MESSAGE
	        		);
		        }
		    }
		}
		else if(event.getSource().equals(removeButton)){	
			System.out.println("4");
			scenarioListModel.remove(scenarioList.getSelectedIndex());
		}
	}

	@Override
	public void focusGained(FocusEvent fe) {
		if(fe.getSource().equals(scenarioList) && scenarioList.getSelectedIndex() != -1){
			exportButton.setEnabled(true);
			removeButton.setEnabled(true);
		}
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if(fe.getSource().equals(scenarioList) && scenarioList.getSelectedIndex() == -1){
			exportButton.setEnabled(false);
			removeButton.setEnabled(false);	
		}
	}

}
