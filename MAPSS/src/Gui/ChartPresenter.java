package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.ChartCreator;
import Backend.Grid;
import Backend.ProgramData;
import Backend.Simulations;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class ChartPresenter extends JPanel implements ActionListener{
	private static final long serialVersionUID = 6423561811886542809L;
	
	JSplitPane splitPane = new JSplitPane();
	JPanel saveAsContainer = new JPanel();
	JComboBox<String> chartComboBox = new JComboBox<String>();
	JComboBox<String> saveAsComboBox = new JComboBox<String>();
	Map<Integer, String> chartNamingDictionary = new HashMap<Integer, String>();
	Map<Integer, JFreeChart> chartObjectDictionary = new HashMap<Integer, JFreeChart>();
	ChartPanel chartContainer = new ChartPanel(null);
	DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	
	JButton simulation_btn = new JButton("Run simulation (takes along time)");
	
	public ChartPresenter(){
		add(splitPane);
	
		chartComboBox.setEditable(false);
		chartComboBox.setVisible(true);
		saveAsComboBox.addItem("PNG");
		saveAsComboBox.addItem("JPEG");
		saveAsComboBox.setEditable(false);
		saveAsComboBox.setVisible(true);
		simulation_btn.setVisible(true);
		
        builder.append("Chart:", chartComboBox);
        builder.nextLine();
        builder.appendSeparator();
        builder.append("Save as:", saveAsComboBox);
        builder.nextLine();
        builder.appendSeparator();
        builder.append("", simulation_btn);
        splitPane.setLeftComponent(chartContainer);
        splitPane.setRightComponent(builder.getPanel());
        
        chartComboBox.addActionListener(this);
        saveAsComboBox.addActionListener(this);
        simulation_btn.addActionListener(this);
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
        else if(e.getSource().equals(simulation_btn)){
        	
    		DefaultCategoryDataset data_set = new DefaultCategoryDataset();	
    		String regular = "Regular grid";
//    		String increased = "Increased usage";
//    		String twenty_five = "+25% has doubles";
    	
    		//for (int i = 4; i <= 7; i++){
    			
    			double value1 = Simulations.productAgentsInRegularGridSimulation("random");
    			
    			data_set.addValue(value1, Grid.getX()+"x"+Grid.getY(), regular);
    			
 
    			
    		//}
    		JFreeChart chart = ChartCreator.drawBarChart("Standard square grid simulation", "x", "y", data_set);
    		ChartPresenter chartpres = MainWindow.getChart();
    		chartpres.addChart(chart);
        	
        }
	}
}
