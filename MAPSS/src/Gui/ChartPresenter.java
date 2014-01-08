package Gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.ChartCreator;
import Backend.ProgramData;
import Backend.Scenario;
import Backend.ScenarioList;
import Backend.Simulations;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class ChartPresenter extends JPanel implements ActionListener, PropertyChangeListener{
	private static final long serialVersionUID = 6423561811886542809L;
	
	JSplitPane splitPane = new JSplitPane();
	JPanel saveAsContainer = new JPanel();
	JComboBox<String> chartComboBox = new JComboBox<String>();
	JComboBox<String> saveAsComboBox = new JComboBox<String>();
	JCheckBox check = new JCheckBox("Different color for each (similar) structure");
	Map<Integer, String> chartNamingDictionary = new HashMap<Integer, String>();
	Map<Integer, JFreeChart> chartObjectDictionary = new HashMap<Integer, JFreeChart>();
	ChartPanel chartContainer = new ChartPanel(null);
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	private Task task;
	
	private static JProgressBar progressBar;
	
	JLabel imageLabel = new JLabel();
	
	int simulation_counter = 1;
	
	static JComboBox<String> structureBox = new JComboBox<String>();
	
	
	JButton add_btn = new JButton("Add structure to simulation");
	JButton add_all_btn = new JButton("Add all structures");
	JButton simulation_btn = new JButton("Run simulation");
	
	protected static ArrayList<String> selected_topologies = new ArrayList<String>();
	
	
	public ChartPresenter(){
		add(splitPane);
		
		structureBox.setEditable(false);
		structureBox.setVisible(true);
		structureBox.addItem("Test");
	
		chartComboBox.setEditable(false);
		chartComboBox.setVisible(true);
		saveAsComboBox.addItem("PNG");
		saveAsComboBox.addItem("JPEG");
		saveAsComboBox.setEditable(false);
		saveAsComboBox.setVisible(true);
		simulation_btn.setVisible(true);
		add_btn.setVisible(true);
		
        builder.append("Chart:", chartComboBox);
        builder.nextLine();
        builder.appendSeparator();
        builder.append("Save as:", saveAsComboBox);

        builder.nextLine();
        builder.appendSeparator();
        builder.append("Structures:", structureBox);
        builder.nextLine();
        builder.append("", add_btn);
        builder.nextLine();
        builder.append("", add_all_btn);
        builder.nextLine();
        builder.appendSeparator();
        builder.nextLine();
        builder.append("", check);
        builder.nextLine();
        builder.append("", simulation_btn);
        
        builder.nextLine();
        builder.appendSeparator();
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        
        builder.append("", progressBar);

        splitPane.setLeftComponent(chartContainer);
        splitPane.setRightComponent(builder.getPanel());
        
        chartComboBox.addActionListener(this);
        saveAsComboBox.addActionListener(this);
        structureBox.addActionListener(this);
        simulation_btn.addActionListener(this);
        add_btn.addActionListener(this);
        add_all_btn.addActionListener(this);
	}
	
	public void setEnabled(Component[] components, Boolean b){
		for(Component c : components){
			c.setEnabled(b);
		}
	}

	public void addChart(JFreeChart chart){
		int new_key = chartNamingDictionary.size() + 1;
		String chartTitle = chart.getTitle().getText();
		chartNamingDictionary.put(new_key, chartTitle);
		chartObjectDictionary.put(new_key, chart);
		chartComboBox.addItem(chartTitle);
		reload();
	}
	
	public void reload(){
		chartComboBox.removeAllItems();
		for(Entry<Integer, String> entry : chartNamingDictionary.entrySet()){
			chartComboBox.addItem(entry.getValue());
		}
		validate();
		System.out.println("ChartPresenter reloaded.");
	}
	
	public void empty(){
		chartNamingDictionary.clear();
		chartObjectDictionary.clear();
		chartComboBox.removeAllItems();
		validate();
		System.out.println("ChartPresenter emptied.");
	}
	
	public static void updateChartStructures(){
		structureBox.removeAllItems();
		
		for (String name: ScenarioList.getScenarioNames()){
			Scenario S = ScenarioList.getScenario(name);

			if (S.getProducts().size() > 0){
				structureBox.addItem(name);
			}
			
		}
	}
	
	public static void updateProgress(int amount){
		progressBar.setValue(amount + progressBar.getValue());
        //progressBar.setStringPainted(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(chartComboBox)){
        	@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
        	for(Entry<Integer, String> entry : chartNamingDictionary.entrySet()){
        		if(entry.getValue() == (String)cb.getSelectedItem()){
        			JFreeChart selectedChart = chartObjectDictionary.get(entry.getKey());
        			chartContainer.setChart(selectedChart);
        			validate();
        			break;
        		}
    		}
        }
        else if(e.getSource().equals(saveAsComboBox)){
        	@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
        	String selected = cb.getSelectedItem().toString();
        	ChartCreator chartcreator = new ChartCreator();
        	if(selected.equals("PNG")){
					try {
						chartcreator.saveChartAsPNG(chartContainer.getChart());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        	}
        	else if(selected.equals("JPEG")){
        		try {
					chartcreator.saveChartAsJPEG(chartContainer.getChart());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        	
        	else{
        		
        	}
        }
        else if(e.getSource().equals(add_all_btn)){

        	for (int i = 0 ; i < structureBox.getItemCount(); i++){
        		String selected = (String) structureBox.getItemAt(i);
            	selected_topologies.add(selected);
        	}
        	System.out.println("All scenario's added");
        }
        else if(e.getSource().equals(add_btn)){
        	String selected = (String) structureBox.getSelectedItem();
        	selected_topologies.add(selected);
        	
        	System.out.println(selected+" added");
        }
        else if(e.getSource().equals(simulation_btn)){
        	
        	task = new Task();
            task.addPropertyChangeListener(this);
            task.execute();
            
            //
        	
        }
        
        
	}
	
	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
        	
        	DefaultCategoryDataset data_set = new DefaultCategoryDataset();	
    		
    		int total_products = 0;
    		for (int i = 0; i < selected_topologies.size(); i++){
    			Scenario S = ScenarioList.getScenario(selected_topologies.get(i));
    			ArrayList<int[]> products = S.getProducts();
    			total_products += products.size();
    			//System.out.println(selected_topologies.get(i));
    		}
    		progressBar.setMaximum(total_products);
    		progressBar.setStringPainted(true);
    		
    		for (int i = 0; i < selected_topologies.size(); i++){

    			System.out.println("Attempting: " + selected_topologies.get(i));
    			Scenario S = ScenarioList.getScenario(selected_topologies.get(i));
    			double value1 = Simulations.productAgentsInRegularGridSimulation(selected_topologies.get(i), selected_topologies.get(i));
    			int X = S.x;
    			int Y = S.y;
    			String name = selected_topologies.get(i);
    			System.out.println(name);
    			
    			if (check.isSelected()) {
    				data_set.addValue(value1, " ", name);
    			} else{
    				data_set.addValue(value1, name, " ");
    			}
	
    		}
    		
    		JFreeChart chart = ChartCreator.drawBarChart("MAPSS Simulation Result " + simulation_counter , "scenarios", "average path", data_set);
    		ChartPresenter chartpres = MainWindow.getChart();
    		chartpres.addChart(chart);
    		simulation_counter ++;
        	
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
        	progressBar.setValue(0);
        	selected_topologies.clear();
        }
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);

        }
	}
	
	
}
