package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Backend.Scenario;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class ScenarioImportExportWindow extends JFrame implements ActionListener{

	private JPanel contentPane;
	private DefaultFormBuilder screenBuilder = getNewBuilder();
	JButton loadButton, importButton, exportButton, removeButton;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScenarioImportExportWindow frame = new ScenarioImportExportWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScenarioImportExportWindow() {
		setName("Load Scenario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setResizable(false);
        
		DefaultListModel<Scenario> scenarioListModel = new DefaultListModel<Scenario>();
		JList<Scenario> scenarioList = new JList<Scenario>(scenarioListModel);
		JScrollPane scenarioListScrollPane = new JScrollPane(scenarioList);
        
		scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scenarioList.setLayoutOrientation(JList.VERTICAL);
		scenarioList.setVisibleRowCount(-1);
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
        exportButton.addActionListener(this);
        buttonContainer.add(exportButton, "cell 0 3,growx,aligny top");
        
        removeButton = new JButton("Remove Selected Scenario");
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
			System.out.println("1");
		}
		else if(event.getSource().equals(importButton)){	
			System.out.println("2");
		}
		else if(event.getSource().equals(exportButton)){	
			System.out.println("3");
		}
		else if(event.getSource().equals(removeButton)){	
			System.out.println("4");
		}
	}

}