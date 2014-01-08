package Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Gui.ChartPresenter;
import Gui.CreateGridModule;
import Gui.MainWindow;
import Gui.ProductSetup;
import Gui.ReadData;

public class MapssFileHandler {
	static PrintWriter logWriter;
	
	public MapssFileHandler(){
	}
	
	public static void logGrid(Scenario S){

		MapssFileHandler.writeLogLn("Grid layout");
		int stepnr = 0;
		for (int y = 0; y < S.y; y++){
			String output = "";
			for (int x = 0; x < S.x; x++){
				
				if (stepnr < 10){
					output += " ";
				}
				output += " " + stepnr;
				stepnr++;
			}
			writeLogLn(output);
		}
		writeLogLn("");
	}
	
	public static void logNeighbors(Scenario S) {
		ArrayList<Integer>[] neighbors = S.neighbors;
		for (int i = 0; i < neighbors.length; i++){
			writeLogLn("Equiplet " + i + " kent: " + neighbors[i]);	
		}
		writeLogLn("");
	}
	
	public static void saveScenario(Scenario S){
		try {
			PrintWriter structureWriter = new PrintWriter("scenarios/" + S.name + ".txt");
			
			structureWriter.println(S.x+"x"+S.y);
			

			
			
			
			for (ArrayList<Integer> neighbor : S.neighbors) {
				structureWriter.println(neighbor);
			}
			
			if (S.products != null){
				//ArrayList<String[]> products = ProductStepGenerators.getBatch(structure_name);
				//System.out.println(S.products);
				for (int[] productsteps : S.products) {
					for (int s: productsteps){
						structureWriter.print(s+" ");
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
	
	
	public static void createDataFile(Scenario S){
		try {
			PrintWriter DataWriter = new PrintWriter("data/" + S.name + ".txt");
			
			DataWriter.println("********************* "+S.name+" *********************");
			DataWriter.println("X: "+S.x);
			DataWriter.println("Y: "+S.y);
			DataWriter.println("****************************************************");
			
			DataWriter.println("Grid layout: ");
			int stepnr = 0;
			for (int y = 0; y < S.y; y++){
				String output = "";
				for (int x = 0; x < S.x; x++){
					
					if (stepnr < 10){
						output += " ";
					}
					output += " " + stepnr;
					stepnr++;
				}
				DataWriter.println(output);
			}
			DataWriter.println("****************************************************");
			
			
			DataWriter.println("Distance Matrix:");
			for (ArrayList<Integer>[] i : S.distances_between_equiplets){
				String output = "";
				for (ArrayList<Integer> j : i){
					output += " " + j;
				}
				DataWriter.println(output);
			}
			DataWriter.println("");
			
			
			ArrayList<Integer>[] neighbors = S.neighbors;
			for (int i = 0; i < neighbors.length; i++){
				DataWriter.println("Equiplet " + i + " kent: " + neighbors[i]);	
			}
			DataWriter.println("****************************************************");
			
			
			DataWriter.println("Paths between equiplets (if a path ends with " + S.getMax()+1
					+ "then that means that the destination is unreachable:");
			int count = 0;
			int equiplet = 0;
			for (int[] path: S.paths_between_equiplets){
				if (count == 0){
					DataWriter.println("****************************************************");
					DataWriter.println("Equiplet: "+equiplet);
					equiplet++;
				} 
				DataWriter.println(equiplet-1+" --> "+count+".");
				DataWriter.print("Path: ");
				for (int derp: path){
					DataWriter.print(derp+ " ");
				}

				count++;
				if (count >= S.x*S.y){
					count = 0;
				} 
				DataWriter.println("");
			}
			
			
			DataWriter.println("****************************************************");
			DataWriter.println("Possible other paths");
			count = 0;
			equiplet = 0;
			
			
			
			for (int[] path: S.possible_other_paths){
				if (count == 0){
					DataWriter.println("****************************************************");
					DataWriter.println("Equiplet: "+equiplet);
					equiplet++;
				} 
				DataWriter.println(equiplet-1+" --> "+count+".");
				DataWriter.print("Path: ");
				for (int derp: path){
					DataWriter.print(derp+ " ");
				}
				
				count++;
				if (count >= S.x*S.y){
					count = 0;
				} 
				DataWriter.println("");
			}
			
			DataWriter.print("Difference between paths: ");
			DataWriter.print(S.comparePaths()+"%");
			DataWriter.println("****************************************************");
			
			DataWriter.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void deleteScenarioFiles(Scenario S){
		
		try {
			File scenariofile = new File("scenarios/"+S.name +".txt");
			scenariofile.delete();
			System.out.println("Scenario file deleted");
		} catch (Exception e){
			System.out.println("unable to delete scenario file");
			e.printStackTrace();
		}
		
		try {
			File datafile = new File("data/"+S.name +".txt");
			datafile.delete();
			System.out.println("Data file deleted");
		} catch (Exception e){
			System.out.println("unable to delete data file");
			e.printStackTrace();
		}
		
		try {
			File logfile = new File("logs/"+S.name +".txt");
			logfile.delete();
			System.out.println("Log file deleted");
		} catch (Exception e){
			System.out.println("unable to delete log file");
			e.printStackTrace();
		}
		
	}
	
	public static void scenarioFileToScreen(Scenario S){
		FileReader fr;
		try {
			fr = new FileReader("scenarios/" + S.name +".txt");
	    	BufferedReader br = new BufferedReader(fr); 
	    	String s; 
	    	while((s = br.readLine()) != null) { 
	    		System.out.println(s);
	    		MainWindow.stringToOutput(s);
	    	}
	    	fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void logFileToScreen(Scenario S){
		
		FileReader fr;
		try {
			fr = new FileReader("logs/" + S.name +".txt");
	    	BufferedReader br = new BufferedReader(fr); 
	    	String s; 
	    	while((s = br.readLine()) != null) { 
	    		MainWindow.stringToOutput(s);
	    	}
	    	fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public static void dataFileToScreen(Scenario S){
		
		FileReader fr;
		try {
			fr = new FileReader("data/" + S.name +".txt");
	    	BufferedReader br = new BufferedReader(fr); 
	    	String s; 
	    	while((s = br.readLine()) != null) { 
	    		MainWindow.stringToOutput(s);
	    	}
	    	fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void loadScenarios(){
		ArrayList<Integer>[] neighbors = new ArrayList[0];
		
		File dir = new File("scenarios");
		int filecounter = 0; //is used
		
		for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".txt"))) {
		    	
		    	FileReader fr;
				try {
					fr = new FileReader("scenarios/" +file.getName());
			    	BufferedReader br = new BufferedReader(fr); 
			    	String s; 
			    	boolean safe = false;
			    	int neighbor_counter = 0;
			    	int x = 0;
			    	int y = 0;
			    	ArrayList<int[]> product_list = new ArrayList<int[]>();
			    	
			    	while((s = br.readLine()) != null) { 
			    		if (neighbor_counter == 0){
			    			String [] ss = s.split("x");
			    			x = Integer.parseInt(ss[0]);
			    			y = Integer.parseInt(ss[1]);
			    			 neighbors = new ArrayList[x*y];
			    		} else if (neighbor_counter <= x*y) {
			    			s = s.replace("[", "");
			    			s = s.replace("]", "");
			    			s = s.replace(" ", "");
			    			String [] ss = s.split(",");
			    			
			    			ArrayList<Integer> known_equiplets = new ArrayList<Integer>();
			    			
			    			for (String product_step: ss){
			    				try {
			    					known_equiplets.add(Integer.parseInt(product_step));
			    				} catch (NumberFormatException e){ }
			    			}
			    			neighbors[neighbor_counter-1] = known_equiplets;
			    		} else {
			    			
			    			safe = true;
			    			String[] temp = s.split(" ");
			    			
			    			int[] steps = new int[temp.length];
			    			
			    			for (int i = 0; i < temp.length; i++) {
			    				steps[i]= Integer.parseInt(temp[i]);
			    			}
			    			
			    			product_list.add(steps);
			    			
			    		}
			    		neighbor_counter++;
			    	} 
					
			    	String name = file.getName().replace(".txt", "");
			    	filecounter++;
			    	
			    	ProgramData.createAndAddScenario(name, x, y, neighbors, product_list);

			    	if (safe == true){
			    		ProductStepGenerators.addProductBatch(name, product_list);
			    	}
			    	

			    	fr.close(); 
			    	
			    	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
		CreateGridModule.updateStructureBox();
		ProductSetup.updateProductStructures();
		ChartPresenter.updateChartStructures();
		ReadData.updateProductStructures();
	}
}
