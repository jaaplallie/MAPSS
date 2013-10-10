package Backend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.swing.JFrame;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import Objects.Grid;

public class ChartCreator {

	//DefaultPieDataset data = new DefaultPieDataset();
	//JFreeChart chart;
	//ChartFrame frame = new ChartFrame("Test", chart);
	
	DefaultCategoryDataset data2 = new DefaultCategoryDataset();
	JFreeChart chart2;
	ChartFrame frame2;
	Component com;
	
	public ChartCreator() {
	}
	
	public void drawBarChart(String title, String x_Label, String y_Label, Dataset ds){
		// create a dataset...
		//DefaultPieDataset data = new DefaultPieDataset();
		//data.setValue("Category 1", 250);
		//data.setValue("Category 2", 500);
		//data.setValue("Category 3", 310);
		// create a chart...
		// JFreeChart chart = ChartFactory.createPieChart(
		// "Sample Pie Chart",
		// data,
		// true, // legend?
		// true, // tooltips?
		// false // URLs?
		// );
		// create and display a frame...
		// frame = new ChartFrame("First", chart);
		// frame.pack();
		// frame.setVisible(true);
		
		// create a dataset...
		DefaultCategoryDataset data2 = new DefaultCategoryDataset();		
		data2.addValue(9.0, "p1", "Category 1");
		data2.addValue(6.0, "p1", "Category 2");
		data2.addValue(2.0, "p1", "Category 3");
		data2.addValue(9.0, "p2", "Category 1");
		data2.addValue(6.0, "p2", "Category 2");
		data2.addValue(2.0, "p2", "Category 3");
		data2.addValue(9.0, "p3", "Category 1");
		data2.addValue(6.0, "p3", "Category 2");
		data2.addValue(2.0, "p3", "Category 3");
		data2.addValue(9.0, "p4", "Category 1");
		data2.addValue(6.0, "p4", "Category 2");
		data2.addValue(2.0, "p4", "Category 3");
		
		// create a chart...
		JFreeChart chart2 = ChartFactory.createBarChart3D(
			title, 
			x_Label, 
			y_Label, 
			data2
		);
		
		// create and display a frame...
		frame2 = new ChartFrame(title, chart2);
		frame2.pack();
		frame2.setVisible(true);
	}
	
	public Graphics2D getChartImage(){
		JFrame frame = (JFrame)frame2;
		frame.setBackground(Color.WHITE);
		frame.setUndecorated(true);
		frame.getContentPane().add(com);
		frame.pack();
		BufferedImage bi = new BufferedImage(com.getWidth(), com.getHeight(), BufferedImage.TYPE_INT_RGB); //ARGB = for transparency
		Graphics2D graphics = bi.createGraphics();
		com.print(graphics);
		graphics.dispose();
		frame.dispose();
		
		return graphics;
	}
	
}
