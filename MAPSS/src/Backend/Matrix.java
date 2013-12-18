package Backend;

import java.util.ArrayList;

public class Matrix {

	protected static ArrayList<Integer>[][] matrix;
	protected static ArrayList<ArrayList<Integer>[][]> matrix_list = new ArrayList<ArrayList<Integer>[][]>();
	
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
		ArrayList<Integer>[][] matrixie = matrix_list.get(index);
		matrix = matrixie;
	}
	
	public static void logMatrix(){
		MapssFileWriter.writeLogLn("Matrix layout:");
		for (ArrayList<Integer>[] i : matrix){
			String output = "";
			for (ArrayList<Integer> j : i){
				output += " " + j;
			}
			MapssFileWriter.writeLogLn(output);
		}
	}
	
	public static int getDistance(int start, int end){
		ArrayList<Integer> value = matrix[start][end];
		int distance = 0;
		for (int i : value){
			distance += Math.abs(i);
		}
		return distance;
	}
	
	
}