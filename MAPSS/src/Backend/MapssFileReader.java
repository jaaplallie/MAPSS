package Backend;

import jade.wrapper.AgentContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Agents.EquipletAgent;
import Agents.ProductAgent;
import Gui.ChartPresenter;


public class MapssFileReader {
	static AgentContainer container = AgentEnvironmentCreator.getContainer();
	static ArrayList<Integer>[] neighbors;
	static boolean first = true;
	
	public MapssFileReader(){
		
	}
	
	
	public static void loadStructures(){
		
		File dir = new File("structures");
		int filecounter = 0;
		
		for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".txt"))) {
		    	
		    	FileReader fr;
				try {
					fr = new FileReader("structures/" +file.getName());
			    	BufferedReader br = new BufferedReader(fr); 
			    	String s; 
			    	boolean safe = false;
			    	int neighbor_counter = 0;
			    	int x = 0;
			    	int y = 0;
			    	ArrayList<String[]> product_list = new ArrayList<String[]>();
			    	
			    	while((s = br.readLine()) != null) { 
			    		System.out.println("neighbor_counter is: "+neighbor_counter);
			    		System.out.println("x*y is: "+x*y);
			    		if (neighbor_counter == 0){
			    			//neighbors = new ArrayList[0];
			    			String [] ss = s.split("x");
			    			x = Integer.parseInt(ss[0]);
			    			System.out.println("x is: "+x);
			    			y = Integer.parseInt(ss[1]);
			    			System.out.println("y is: "+y);
			    			neighbors = new ArrayList[x*y];
			    		} else if (neighbor_counter <= x*y) {
			    			s = s.replace("[", "");
			    			s = s.replace("]", "");
			    			s = s.replace(" ", "");
			    			String [] ss = s.split(",");
			    			
			    			
			    			
			    			ArrayList<Integer> known_equiplets = new ArrayList<Integer>();
			    			
			    			for (String product_step: ss){
			    				//System.out.println(product_step);
			    				known_equiplets.add(Integer.parseInt(product_step));
			    				
			    			}
			    			neighbors[neighbor_counter-1] = known_equiplets;
			    		} else {
			    			
			    			safe = true;
			    			String[] steps = s.split(" ");
			    			//System.out.println("s is: "+s);
			    			product_list.add(steps);
			    			
			    		}
			    		neighbor_counter++;
			    	} 
					
			    	String name = file.getName().replace(".txt", "");
			    	filecounter++;
			    	System.out.println(Grid.getStructureNames().size());
			    	if (Grid.getStructureNames().size() < filecounter){
			    		Grid.createStructure(x, y, name, neighbors);
			    	}
			    	
			    	

			    	
			    	
			    	if (safe == true){
			    		ProductStepGenerators.addProductBatch(name, product_list);
			    	}

			    	fr.close(); 
			    	ChartPresenter.updateChartStructures();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}	
	}
	
	
	public Scenario openScenarioXml(String filePath){
		return createScenarioFromXml(filePath);
	}
	
	protected static Scenario createScenarioFromXml(String fileLocation){
		Scenario new_scenario = new Scenario("Undefined Scenario Name");
		
		try {
			 
			File xmlFile = new File(fileLocation);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
		 
			//optional, but recommended
			doc.getDocumentElement().normalize();
		 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			Element scnE = doc.getDocumentElement();
			
			if(scnE.hasAttribute("NAME")){
				if(!scnE.getAttribute("NAME").isEmpty()){
					new_scenario.scenarioName = scnE.getAttribute("NAME");
				}
			}
			
			Node gridElement = scnE.getElementsByTagName("GRD").item(0);
			String gridXSizeStr = ((Element)gridElement).getAttribute("XSIZE"); //Read Grid XSIZE
			String gridYSizeStr = ((Element)gridElement).getAttribute("YSIZE"); //Read Grid YSIZE
			int gridXSize = -1;
			int gridYSize = -1;
			try{
				gridXSize = Integer.parseInt(gridXSizeStr);
				gridYSize = Integer.parseInt(gridYSizeStr);
			}
			catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
			NodeList eqList = gridElement.getChildNodes();
			NodeList pList = doc.getElementsByTagName("P");
		 
			System.out.println("============================");
			
			//<EQ><NAME></NAME><XPOS></XPOS><YPOS></YPOS><ARGS></ARGS></EQ>
			ArrayList<EquipletAgent> createdEqs = new ArrayList<EquipletAgent>();
			for (int temp = 0; temp < eqList.getLength(); temp++) {
		 
				Node nNode = eqList.item(temp);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					System.out.println("----------------------------");
					System.out.println("\nCurrent Equiplet Agent : EquipletAgent_" + eElement.getElementsByTagName("NAME").item(0).getTextContent());
					System.out.println("Step : " + eElement.getElementsByTagName("NAME").item(0).getTextContent());
					System.out.println("X-Position : " + eElement.getElementsByTagName("XPOS").item(0).getTextContent());
					System.out.println("Y-Position : " + eElement.getElementsByTagName("YPOS").item(0).getTextContent());
					System.out.println("Given Arguments : " + eElement.getElementsByTagName("ARGS").item(0).getTextContent());
					
					int stepNumber = -1;
					int xpos = -1;
					int ypos = -1;
					String[] argsArray = eElement.getElementsByTagName("ARGS").item(0).getTextContent().split(",");
					
					try{
						stepNumber = Integer.parseInt(eElement.getElementsByTagName("NAME").item(0).getTextContent());
						xpos = Integer.parseInt(eElement.getElementsByTagName("XPOS").item(0).getTextContent());
						ypos = Integer.parseInt(eElement.getElementsByTagName("YPOS").item(0).getTextContent());
					}
					catch(NumberFormatException nfe){
						nfe.printStackTrace();
					}
					
					EquipletAgent new_eq = new EquipletAgent(stepNumber, 
							new int[]{xpos, ypos}, 
							argsArray
					);
					createdEqs.add(new_eq);		 
				}
				
			}
			if(gridXSize != -1 && gridYSize != -1){
				
				//new_scenario.createGrid(gridXSize, gridYSize);
				new_scenario.insertAgents(createdEqs);
			}
			else{
				new_scenario.insertAgentsCreateGrid(createdEqs);
			}
			
			System.out.println("\nv^v^v^v^v^v^v^v^v^v^v^v^v^v^\n");
			
			//<P>P6<NPS>18</NPS><STEPS>11,2,11,0,14,11,16,1,6,9,21,15,18,3,6,19,7,11,</STEPS></P>
			for (int temp = 0; temp < pList.getLength(); temp++) {
				 
				Node nNode = pList.item(temp);		 

		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					System.out.println("\nCurrent Product Agent : ProductAgent_" + eElement.getElementsByTagName("NAME").item(0).getTextContent());
					System.out.println("Name : " + eElement.getElementsByTagName("NAME").item(0).getTextContent());
					System.out.println("Steps : " + eElement.getElementsByTagName("STEPS").item(0).getTextContent());
					System.out.println("----------------------------");
					
					ProductAgent npa = new ProductAgent(
							eElement.getElementsByTagName("NAME").item(0).getTextContent(), 
							eElement.getElementsByTagName("STEPS").item(0).getTextContent().split(",")
					);
					new_scenario.addProduct(npa);
				}
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
	    }
		return new_scenario;
	}
}