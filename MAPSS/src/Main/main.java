package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.AgentEnvironmentCreator;
import Backend.ChartCreator;
import Backend.Simulations;
import Gui.ChartPresenter;
import Gui.MainWindow;

public class main{
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("Multi-Agent Production System Simulator (MAPSS) started.");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentEnvironmentCreator aec = new AgentEnvironmentCreator();
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Temporarely, delete this later
				
//				Simulations.productAgentsInRegularGridSimulation(
//						max_product_steps, products, gridx, gridy, "Regular grid simulation", "random");
//				Simulations.productAgentsInRegularGridSimulation(
//						max_product_steps, products, gridx, gridy, "Increased usage grid simulation", "increase");
//				Simulations.productAgentsInRegularGridSimulation(
//						max_product_steps, products, gridx, gridy, "+25% grid simulation", "+25%");
				
				
//				int products = 2, gridx = 5, gridy = 5;
//				int max_product_steps = 10;
//				DefaultCategoryDataset data_set = new DefaultCategoryDataset();	
//				String regular = "Regular grid simulation";
//				String increased = "Increased usage grid simulation";
//				String twenty_five = "+25% grid simulation";
//				for (int i = 1; i <= 1; i++){
//					double value1 = Simulations.productAgentsInRegularGridSimulation(
//							max_product_steps, products, gridx, gridy, regular + i, "random");
//					
//					data_set.addValue(value1, ""+i, regular);
//					
//					double value2 = Simulations.productAgentsInRegularGridSimulation(
//							max_product_steps, products, gridx, gridy, increased + i, "increase");
//					
//					data_set.addValue(value2, ""+i, increased);
//					
//					double value3 = Simulations.productAgentsInRegularGridSimulation(
//							max_product_steps, products, gridx, gridy, twenty_five + i, "+25%");
//					
//					data_set.addValue(value3, ""+i, twenty_five);
//				}
//				JFreeChart chart = ChartCreator.drawBarChart("log_name", "x", "y", data_set);
//				ChartPresenter chartpres = new ChartPresenter();
//				chartpres.addChart(chart);
//				
//				

				
				
				//
			}
		});
	}
}
