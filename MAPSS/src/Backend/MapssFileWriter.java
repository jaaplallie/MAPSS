package Backend;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

import Agents.EquipletAgent;
import Agents.ProductAgent;

public class MapssFileWriter {
	StringBuilder xmlStringBuilder;
	static PrintWriter logWriter;
	
	
	public MapssFileWriter(){
		xmlStringBuilder = new StringBuilder();
	}
	
	public void writeScenarioToXML(Scenario scenario, String fileLocation){
		String defaultString = "Undefined.";
		String scenarioName = defaultString;
		
		  try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("SCN");
			doc.appendChild(rootElement);
			doc.createAttribute("NAME");
			if(!scenario.scenarioName.isEmpty() || scenario.scenarioName != null){
				scenarioName = scenario.scenarioName;
			}
			else{
				scenarioName = defaultString;
			}
			rootElement.setAttribute("NAME", scenarioName);
			Element grd = doc.createElement("GRD");
			doc.createAttribute("XSIZE");
			doc.createAttribute("YSIZE");
			//check
			grd.setAttribute("XSIZE", scenario.scenario_Grid.getX()+"");
			grd.setAttribute("YSIZE", scenario.scenario_Grid.getY()+"");
			Element eqs = doc.createElement("EQS");
			
			EquipletAgent[][] fetched_Grid = Grid.getGrid();
				
			for(int x = 0; x < fetched_Grid.length; x++){
				System.out.println("Grid X = " + fetched_Grid.length);
				for(int y = 0; y < fetched_Grid[x].length; y++){
					System.out.println("Grid X = " + fetched_Grid.length + " Y = " + fetched_Grid[x].length);
					System.out.println("fetchedEQ tostring = " + fetched_Grid[x][y].toString());
					//<EQ><NAME></NAME><XPOS></XPOS><YPOS></YPOS><ARGS></ARGS></EQ>
					EquipletAgent fetched_EQ = fetched_Grid[x][y];
					
					String eq_name = fetched_EQ.getCode()+"";
					String eq_xpos = fetched_EQ.getPosition()[0]+"";
					String eq_ypos = fetched_EQ.getPosition()[1]+"";
					String eq_args = fetched_EQ.getArgsString();
					
					if(!eq_name.isEmpty()){
						if(!eq_xpos.isEmpty()){
							if(!eq_ypos.isEmpty()){
								if(!eq_args.isEmpty()){
									Element new_eq = doc.createElement("EQ");
									Element new_eq_name = doc.createElement("NAME");
									Element new_eq_xpos = doc.createElement("XPOS");
									Element new_eq_ypos = doc.createElement("YPOS");
									Element new_eq_args = doc.createElement("ARGS");
									//check
									new_eq_name.appendChild(doc.createTextNode(eq_name));
									new_eq_xpos.appendChild(doc.createTextNode(eq_xpos));
									new_eq_ypos.appendChild(doc.createTextNode(eq_ypos));	
									new_eq_args.appendChild(doc.createTextNode(eq_args));
									new_eq.appendChild(new_eq_name);
									new_eq.appendChild(new_eq_xpos);
									new_eq.appendChild(new_eq_ypos);
									new_eq.appendChild(new_eq_args);
									eqs.appendChild(new_eq);						
								}
								else{
									System.out.println("args empty where name is: " + eq_name + " and xpos is: " + eq_xpos + " and ypos is: " + eq_ypos);
								}
							}
							else{
								System.out.println("ypos empty where name is: " + eq_name + " and xpos is: " + eq_xpos);
							}
						}
						else{
							System.out.println("xpos empty where name is: " + eq_name);
						}
					}
					else{
						System.out.println("Name empty where arrayPos = [" + x + "][" + y + "]");
					}
				}
			}
			
			grd.appendChild(eqs);
			rootElement.appendChild(grd);
			
			//<P>P6<NPS>18</NPS><STEPS>11,2,11,0,14,11,16,1,6,9,21,15,18,3,6,19,7,11,</STEPS></P>
			for(ProductAgent pa : scenario.scenario_Products){
				String prod_code = pa.getCode();
				String prod_steps = pa.getArgsString();
				
				if(!prod_code.isEmpty()){
					if(!prod_steps.isEmpty()){
						Element new_prod = doc.createElement("P");
						Element new_prod_name = doc.createElement("NAME");
						Element new_prod_steps = doc.createElement("STEPS");
						new_prod_name.appendChild(doc.createTextNode(prod_code));
						new_prod_steps.appendChild(doc.createTextNode(prod_steps));
						new_prod.appendChild(new_prod_name);
						new_prod.appendChild(new_prod_steps);
						rootElement.appendChild(new_prod);
					}
					else{
						System.out.println("prod steps empty where prod code is: " + prod_code);
					}
				}
				else{
					System.out.println("prod code empty");
				}
			}
			
			try {
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(fileLocation));
				
				// Output to console for testing
				//StreamResult result = new StreamResult(System.out);
				
				transformer.transform(source, result);
			}
			catch (NullPointerException ne) {
			    ne.printStackTrace();
			}
			catch (TransformerConfigurationException tce) {
			    System.out.println("* Transformer Factory error");
			    System.out.println(" " + tce.getMessage());

			    Throwable x = tce;
			    if (tce.getException() != null)
			        x = tce.getException();
			    x.printStackTrace(); 
			} 
			catch (TransformerException te) {
			    System.out.println("* Transformation error");
			    System.out.println(" " + te.getMessage());

			    Throwable x = te;
			    if (te.getException() != null){
			        x = te.getException();
			    }
			    x.printStackTrace();
			}
		  } 
		  catch (ParserConfigurationException e) {
			  e.printStackTrace();
		  }
		  finally{
			  System.out.println("Scenario File saved!");
		  }
	}
	
	
	public static void saveStructure(String structure_name){
		try {
			PrintWriter structureWriter = new PrintWriter("structures/" + structure_name + ".txt");
			EquipletAgent[][] grid = Grid.getGrid();
			
			boolean safe_grid = false;
			structureWriter.println("Name: "+structure_name);
			structureWriter.println("Size: " +grid.length+"x"+grid[0].length);
			structureWriter.print("Relations: ");
			for (Object neighbor : Grid.getNeighbors(structure_name)) {
				structureWriter.print(neighbor);
			}
			structureWriter.println("");
			
			
			if (ProductStepGenerators.getProducts(structure_name) != null){
				ArrayList<Object[]> products = ProductStepGenerators.getBatch(structure_name);
				structureWriter.print("Products & steps: ");
				for (Object[] productsteps : products) {
					
					for (Object s: productsteps){
						structureWriter.print(s+"-");
					}
					structureWriter.println("");
				}
				
			}

			structureWriter.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void loadGrid(){
		
	}
	
	public static void createLogFile(String name){
		try {
			logWriter = new PrintWriter("logs/" + name + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void writeLogLn(String s){
		logWriter.println(s);
	}
	
	public static void writeLog(String s){
		logWriter.print(s);
	}
	
	public static void closeLog(){
		logWriter.close();
	}
}
