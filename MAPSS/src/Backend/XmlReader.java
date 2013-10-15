package Backend;

import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XmlReader {
	static jade.wrapper.AgentContainer container = AgentEnvironmentCreator.getContainer();
	protected static void createProductAgentsFromXML(String link_to_file){
		Object[] args = new Object[0];
		String s;
		String product_name = "This product has no name yet";
		int stepcount = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(link_to_file));
			while ((s = br.readLine()) != null) {
				
				//Filter product name from string
				Pattern product_name_pattern = Pattern.compile("(<P>)(.*)(<NPS>)");
				Matcher name_matcher = product_name_pattern.matcher(s);
				while (name_matcher.find()) {	
					product_name = name_matcher.group(2);
				}
				
				//Filter number of steps from string
				Pattern step_count_pattern = Pattern.compile("(<NPS>)(.*)(</NPS><STEPS>)");
				Matcher step_count_matcher = step_count_pattern.matcher(s);
				while (step_count_matcher.find()) {
					stepcount = Integer.parseInt(step_count_matcher.group(2));
				}
				
				//Filter steps from string and put the steps into the arguments
				Pattern step_pattern = Pattern.compile("(</NPS><STEPS>)(.*)(</STEPS></P>)");
				Matcher step_matcher = step_pattern.matcher(s);
				while (step_matcher.find()) {
					String temp = step_matcher.group(2);
					args = temp.split(",");
				}
				
				//Create a product agent and pass along the steps.
				try {
					AgentController ac;
					ac = container.createNewAgent("Product_" + product_name, "agents.ProductAgent", args);
					ac.start();
					Thread.sleep(100);
				} 
				catch (StaleProxyException | InterruptedException e) {
					e.printStackTrace();
				} 
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}