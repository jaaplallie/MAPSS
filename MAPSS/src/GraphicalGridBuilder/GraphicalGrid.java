package GraphicalGridBuilder;

import javax.swing.JFrame;

public class GraphicalGrid extends JFrame{

	GraphicalGridTarget[][] grid;
	
	public GraphicalGrid(int x, int y){
		grid = new GraphicalGridTarget[y][x];
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid.length; targetX++){
				grid[y][x] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridNothingObject()});
			}
		}
	}
	
	public boolean insert(GraphicalGridTarget ggt, int x, int y){
		Boolean returnVal = true;
		
		if(x < 0 || y < 0){
			System.out.println("Something went wrong while inserting...");
			
			if(x < 0){
				System.out.println("x value is less than zero.");
				returnVal = false;
			}
			if(y < 0){
				System.out.println("y value is less than zero.");
				returnVal = false;
			}
		}
		else{
			grid[y][x] = ggt;
		}
		
		return returnVal;
	}
	
	public boolean remove(int x, int y){
		Boolean returnVal = true;
		
		if(x < 0 || y < 0){
			System.out.println("Something went wrong while removing...");
			
			if(x < 0){
				System.out.println("x value is less than zero.");
				returnVal = false;
			}
			if(y < 0){
				System.out.println("y value is less than zero.");
				returnVal = false;
			}	
		}
		else{
			if(grid[y][x].getClass().equals(new GraphicalGridNothingObject().getClass())){
				System.out.println("Grid place was already a nothing Object.\n" +
						"No changes were made to the grid"
				);
			}
			else{
				grid[y][x] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridNothingObject()});
			}
		}
		
		return returnVal;
	}
}
