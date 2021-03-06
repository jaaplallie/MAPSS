package Backend;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartCreator {
	
	String title = "";
	DefaultCategoryDataset data2 = new DefaultCategoryDataset();
	JFreeChart chart2;
	ChartFrame frame2;
	
	public ChartCreator() {
	}
	
	public static JFreeChart drawBarChart(String title, String x_Label, String y_Label, DefaultCategoryDataset ds){		
		JFreeChart chart = ChartFactory.createBarChart3D(
			title, 
			x_Label, 
			y_Label, 
			ds
		);
		return chart;
	}
	
	public JFreeChart drawAreaChart(String title, String x_Label, String y_Label, DefaultCategoryDataset ds){		
		JFreeChart chart = ChartFactory.createAreaChart(
			title, 
			x_Label, 
			y_Label, 
			ds
		);
		return chart;
	}
	
	public void openChartInNewFrame(JFreeChart chart){
		ChartFrame new_frame = new ChartFrame(chart.getTitle().getText(), chart);
		new_frame.pack();
		new_frame.setVisible(true);
	}
	
	public void saveChartAsPNG(JFreeChart chart) throws IOException{
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	            ChartUtilities.saveChartAsPNG(new File(chooser.getSelectedFile() + ".png"), chart, 400, 300);
	            JOptionPane.showMessageDialog(
	            		null, 
	            		"Chart succesfully saved as PNG to: \n" + chooser.getCurrentDirectory() + 
	            		"\nFilename : " + chooser.getSelectedFile() + ".png", 
	            		"Success!", 
	            		JOptionPane.INFORMATION_MESSAGE
        		);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(
	            		null, 
	            		"Something went wrong, chart could not be saved as PNG", 
	            		"Error!", 
	            		JOptionPane.ERROR_MESSAGE
        		);
	        }
	    }
	}
	public void saveChartAsJPEG(JFreeChart chart) throws IOException{
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	            ChartUtilities.saveChartAsJPEG(new File(chooser.getSelectedFile() + ".jpeg"), chart, 400, 300);
	            JOptionPane.showMessageDialog(
	            		null, 
	            		"Chart succesfully saved as JPEG to: \n" + chooser.getCurrentDirectory() + 
	            		"\nFilename : " + chooser.getSelectedFile() + ".jpeg", 
	            		"Success!", 
	            		JOptionPane.INFORMATION_MESSAGE
        		);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(
	            		null, 
	            		"Something went wrong, chart could not be saved as JPEG", 
	            		"Error!", 
	            		JOptionPane.ERROR_MESSAGE
        		);
	        }
	    }
	}	
}
