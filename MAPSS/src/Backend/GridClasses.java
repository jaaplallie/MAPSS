package Backend;


public class GridClasses {
	protected static int grid[][];
	
	public void createGrid(int width , int length ){
		System.out.println("Grid layout:");
		grid = new int[width][length];
		int stepnr = 1;
		for (int i = 0; i < length; i++){
			String output = "";
			for (int j = 0; j < width; j++){
				grid[j][i]= stepnr;
				output += " " + stepnr;
				stepnr++;
				
			}
			System.out.println(output);
		}
		System.out.println();
	}
	
	public static int[][] getGrid(){
		return grid;
	}
	
	public int[] getRandomPosition(){
		int x = (int)(Math.random()*grid.length);
		int y = (int)(Math.random()*grid[0].length);
		int position[] = {x+1,y+1};
		return position;
	}
	
	public static int[] calculatePath(int start_equiplet_number, int end_equiplet_number){
		int startposition[] = getEquipletPosition(start_equiplet_number);
		int endposition[] = getEquipletPosition(end_equiplet_number);
		
		int x_difference = endposition[0]-startposition[0];
		int y_difference = endposition[1]-startposition[1];
		
		int path[] = {x_difference, y_difference};
		return path;
	}
	
	public static int[] getEquipletPosition(int equiplet_number){
		
		int x_length = grid.length;
		int x = 1;
		int y = 1;
		
		// If the equiplet is a higher number then the row then 
		// increment with the value of the next row. Repeat until 
		// the right row is found. When finished we have the y row.
		while(x_length < equiplet_number){ 
			x_length = x_length+grid.length;
			y += 1;
		}
		
		// Once y is found use the difference to calculate the x row.
		x = equiplet_number - (x_length - grid.length);

		int position[] = {x,y};
		return position;
	}
}