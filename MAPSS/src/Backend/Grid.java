package Backend;

import java.util.ArrayList;

import Agents.EquipletAgent;
import Gui.MainWindow;

/****************************************************************************************

This class performs the calculations needed to create a structure. Once the 
calculations are finished it will create a scenario and add the appropiate 
data to that scenario.
This class is also used to contain information that is only used during the 
simulation. It keeps track of the following:
- The name of the structure that is currently in use. This is useful for the 
product agent
- The total lengths of all the paths used in the simulation of ONE structure. 

****************************************************************************************/

public class Grid {

	public Grid(){
	}

	/***************************************** 1. Creation Functions***********************************************/

	
	public static void createCustom(int width , int length, String name, String relation_list){
		String[] relations = relation_list.split(",");
		
		ArrayList<Integer>[] neighbors = new ArrayList[width*length];
		
		if (width*length > relations.length){
			neighbors = new ArrayList[width*length];
		} else {
			neighbors = new ArrayList[relations.length];
		}
		
		ArrayList<Integer> tempList;
        int telnr = 0;
		
		for (String s : relations){
            String[] temp = s.split("-");
            tempList = new ArrayList<Integer>();
            for (int i = 0; i < temp.length; i++){
            	try {
                    int tempInt = Integer.parseInt(temp[i]);
                    tempList.add(tempInt);
            	}
                catch (NumberFormatException exc){
                }
            }
            neighbors[telnr] = tempList;
            telnr++;
		}
		
		if (width*length > relations.length){
			//if the given x and y size results in a grid bigger then the string covers then fill
			//the remaining with blanks.
			for (int i =0; i<(width*length - relations.length);i++){
				tempList = new ArrayList<Integer>();
				neighbors[telnr] = tempList;
                telnr++;
                System.out.println("+1 blank");
			}
		}
		ArrayList<int[]> products = new ArrayList<int[]>();
		
		if (ProgramData.getScenario(name) != null) { //check if it already exists.
			//if it does, just update everthing.
			
			Scenario S = ProgramData.getScenario(name);
			S.x = width;
			S.y = length;
			S.neighbors = neighbors;
			S.save();
			MainWindow.stringToOutput("Structure '" + name + "' updated.");
			
		} else {
			ProgramData.createAndAddScenario(name, width, length, neighbors, products);
			MainWindow.stringToOutput("Structure '" + name + "' created.");
		}
	}
	
	
	public static void createStructure(int width, int length, String name){
		ArrayList[] neighbors = new ArrayList[width*length];
		
		int stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){
				ArrayList<Integer> known_equiplets = new ArrayList<Integer>();
				
				if (y != 0){ //if not first row
					known_equiplets.add(stepnr-width);
				}
				if (stepnr != (width*(y+1))-1){ //if not end of row
					known_equiplets.add(stepnr+1);
				}
				if (stepnr+width < length*width){ //if not last row
					known_equiplets.add(stepnr+width);
				}
				if (stepnr != width*y){ //if not start of row
					known_equiplets.add(stepnr-1);
				}
				
				neighbors[stepnr] = known_equiplets;
				stepnr++;
			}
		}
		ArrayList<Integer>[] equiplet_positions = new ArrayList[width*length];
		EquipletAgent[][] grid = new EquipletAgent[width][length];

		stepnr = 0;
		for (int y = 0; y < length; y++){
			for (int x = 0; x < width; x++){
				
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(x);
				position.add(y);
				equiplet_positions[stepnr] = position;
				stepnr++;
			}
		}	
		ArrayList<int[]> products = new ArrayList<int[]>();
		ProgramData.createAndAddScenario(name, width, length, neighbors, products);
		MainWindow.stringToOutput("Structure '" + name + "' created.");
	}
}