package Backend;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	protected static List[][] matrix;
	protected static ArrayList<List[][]> matrix_list = new ArrayList<List[][]>();
	
	public static void createMatrix(int grid_width, int grid_length, String structure_name){
		
		matrix = new ArrayList[grid_width*grid_length][grid_length*grid_width];
		
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
		matrix_list.add(matrix);
		//logMatrix();
	}
	
	public static void setMatrix(String structure_name){
		int index = Grid.getIndex(structure_name);
		List[][] matrixie = matrix_list.get(index);
		matrix = matrixie;
	}
	
	public static void logMatrix(){
		MapssFileWriter.writeLogLn("Matrix layout:");
		for (List[] i : matrix){
			String output = "";
			for (List j : i){
				output += " " + j;
			}
			MapssFileWriter.writeLogLn(output);
		}
	}
	
	public static int getDistance(int start, int end){
		List value = matrix[start][end];
		int distance = 0;
		for (Object o : value){
			distance += Math.abs((int)o);
		}
		return distance;
	}
	
	
}