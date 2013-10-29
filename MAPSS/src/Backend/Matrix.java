package Backend;

import java.util.ArrayList;
import java.util.List;


public class Matrix {

	protected static List[][] matrix;
	
	public static void createMatrix(int grid_width, int grid_length){
		
		matrix = new ArrayList[grid_width*grid_length][grid_length*grid_width];
		
		System.out.println("Matrix layout:");
		
		for (int x = 0; x < grid_width*grid_length; x++){	
			int currentposition[] = Grid.getEquipletPosition(x);
				for (int y = 0; y < grid_width*grid_length; y++){
					int otherposition[] = Grid.getEquipletPosition(y);
					ArrayList<Integer> X = new ArrayList<Integer>();
					
					X.add(otherposition[0]-currentposition[0]);
					X.add(otherposition[1]-currentposition[1]);
					matrix[x][y] = X;
				}	
			}	
		
		//Print the matrix. 
		for (List[] i : matrix){
			String output = "";
			for (List j : i){
				output += " " + j;
			}
			System.out.println(output);
		}
		
		
	}
}