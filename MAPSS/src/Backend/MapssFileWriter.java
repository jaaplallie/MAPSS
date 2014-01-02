package Backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Agents.EquipletAgent;
import Agents.ProductAgent;

public class MapssFileWriter {
	static PrintWriter logWriter;
	
	public MapssFileWriter(){
	}
	
	public static void logGrid(Scenario S){

		MapssFileWriter.writeLogLn("Grid layout");
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
	
	
	public static void createDataFile(String name){
		try {
			PrintWriter DataWriter = new PrintWriter("data/" + name + ".txt");
			
			
			Scenario S = ScenarioList.getScenario(name);
			DataWriter.println("********************* "+name+" *********************");
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
			
			
			DataWriter.println("Paths between equiplets (if a path ends with an impossible "
					+ "number then that means that the destination is unreachable:");
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
				DataWriter.print(equiplet-1+" --> "+count+": ");
				count++;
				
				for (int derp: path){
					DataWriter.print(derp+ " ");
				}

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
	
}
