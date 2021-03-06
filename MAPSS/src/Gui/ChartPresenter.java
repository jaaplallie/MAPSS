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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.ChartCreator;
import Backend.ProgramData;
import Backend.Scenario;
import Backend.Simulations;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class ChartPresenter extends JPanel implements ActionListener, PropertyChangeListener{
	private static final long serialVersionUID = 6423561811886542809L;
	
	JSplitPane splitPane = new JSplitPane();
	
	JPanel saveAsContainer = new JPanel();
	JComboBox<String> chartComboBox = new JComboBox<String>();
	JComboBox<String> saveAsComboBox = new JComboBox<String>();

	Map<Integer, String> chartNamingDictionary = new HashMap<Integer, String>();
	Map<Integer, JFreeChart> chartObjectDictionary = new HashMap<Integer, JFreeChart>();
	ChartPanel chartContainer = new ChartPanel(null);
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	private Task task;
	
	ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>();
	
	
	private static JProgressBar progressBar;
	
	JLabel imageLabel = new JLabel();
	
	int simulation_counter = 1;
	
	static JComboBox<String> structureBox = new JComboBox<String>();
	
	String pool1_name = "Squares";
	String pool2_name = "Rectangles";
	JButton pool1_btn = new JButton("Add structure to "+pool1_name);
	JButton pool2_btn = new JButton("Add structure to "+pool2_name);

	private JTextField pool1_field = new JTextField(pool1_name);
	private JTextField pool2_field = new JTextField(pool2_name);
	
	JButton change_names = new JButton("change names");
	JButton simulation_btn = new JButton("Run simulation");
	
	protected static ArrayList<String> selected_topologies = new ArrayList<String>();
	protected static ArrayList<String> pool1 = new ArrayList<String>();
	protected static ArrayList<String> pool2 = new ArrayList<String>();
	
	public ChartPresenter(){

		add(splitPane);
		
		structureBox.setEditable(false);
		structureBox.setVisible(true);

		chartComboBox.setEditable(false);
		chartComboBox.setVisible(true);
		saveAsComboBox.addItem("PNG");
		saveAsComboBox.addItem("JPEG");
		saveAsComboBox.setEditable(false);
		saveAsComboBox.setVisible(true);
		simulation_btn.setVisible(true);
		
        builder.append("Chart:", chartComboBox);
        builder.nextLine();
        builder.append("Save as:", saveAsComboBox);

        builder.nextLine();
        builder.append("Structures:", structureBox);
        builder.nextLine();
        builder.append("Compare pools", pool1_btn);
        builder.nextLine();
        builder.append("", pool2_btn);

        builder.nextLine();
        
        builder.append("Names",pool1_field);
        builder.nextLine();
        
        builder.append("",pool2_field);
        builder.nextLine();
        
        builder.append("",change_names);
        builder.nextLine();
        
        builder.appendSeparator();
        builder.nextLine();
        builder.append("", simulation_btn);
        builder.nextLine();
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
        pool1_btn.addActionListener(this);
        pool2_btn.addActionListener(this);
        change_names.addActionListener(this);
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
		
		for(int i = chartNamingDictionary.size(); i > 0; i--){
			chartComboBox.addItem(chartNamingDictionary.get(i));
		}
		
		validate();
		System.out.println("ChartPresenter reloaded.");
	}
	
	
	public static void updateStructureBox(){
		structureBox.removeAllItems();
		
		for (String name: ProgramData.getScenarioNames()){
			Scenario S = ProgramData.getScenario(name);

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
        else if (e.getSource().equals(pool1_btn)){
        	String selected = (String) structureBox.getSelectedItem();
        	pool1.add(selected);
        	
        	System.out.println(selected+" added to "+pool1_name);
        	MainWindow.stringToOutput(selected+" added to "+pool1_name);
        }
        else if (e.getSource().equals(pool2_btn)){
        	String selected = (String) structureBox.getSelectedItem();
        	pool2.add(selected);
        	
        	System.out.println(selected+" added to "+pool2_name);
        	MainWindow.stringToOutput(selected+" added to "+pool2_name);
        }
        else if(e.getSource().equals(change_names)){
        	pool1_name = pool1_field.getText();
        	pool2_name = pool2_field.getText();
        	pool1_btn.setText("Add structure to "+pool1_name);
        	pool2_btn.setText("Add structure to "+pool2_name);

        }
        else if(e.getSource().equals(simulation_btn)){
        	simulation_btn.setEnabled(false);
        	pool1_btn.setEnabled(false);
        	pool2_btn.setEnabled(false);
        	change_names.setEnabled(false);
        	
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
    		
    		total.add(pool1);
    		total.add(pool2);
    		
    		for (ArrayList<String> selected_pool: total){
    			for (int i = 0; i < selected_pool.size(); i++){
        			Scenario S = ProgramData.getScenario(selected_pool.get(i));
        			ArrayList<int[]> products = S.getProducts();
        			total_products += products.size();
        			//System.out.println(selected_topologies.get(i));
        		}
    		}
    		
    		progressBar.setMaximum(total_products);
    		progressBar.setStringPainted(true);
    		
    		int count = 0;
    		for (ArrayList<String> selected_pool: total){
    			for (int i = 0; i < selected_pool.size(); i++){

        			System.out.println("Attempting: " + selected_pool.get(i));
        			Scenario S = ProgramData.getScenario(selected_pool.get(i));
        			double value1 = Simulations.productAgentsInRegularGridSimulation(selected_pool.get(i));
        			int X = S.x;
        			int Y = S.y;
        			String name = selected_pool.get(i);
        			System.out.println(name);
        			
        			String pool_name;
        			if (count == 0){
        				pool_name = pool1_name;
        			} else {
        				pool_name = pool2_name;
        			}
        			
        			data_set.addValue(value1, name, pool_name);
        			
        			

        		}
    			count++;
    		}
    		
    		
    		JFreeChart chart = ChartCreator.drawBarChart("MAPSS Simulation Result " + simulation_counter , "scenarios", "average path", data_set);
    		ChartPresenter chartpres = MainWindow.getChart();
    		chartpres.addChart(chart);
    		simulation_counter ++;
        	
    		total.clear();
    		pool1.clear();
    		pool2.clear();
    		simulation_btn.setEnabled(true);
        	pool1_btn.setEnabled(true);
        	pool2_btn.setEnabled(true);
        	change_names.setEnabled(true);
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
