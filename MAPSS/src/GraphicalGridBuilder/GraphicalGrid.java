package GraphicalGridBuilder;

import javax.swing.JFrame;

public class GraphicalGrid extends JFrame{

	GraphicalGridTarget[][] grid;
	
	public GraphicalGrid(int x, int y){
		grid = new GraphicalGridTarget[y][x];
		
		Boolean xEven = true;
		Boolean yEven = true;
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[y].length; targetX++){
				//grid[y][x] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridNothingObject()});
				if(xEven){
					if(yEven){
						if(
								(targetY == 0 && targetX == 0) || 
								(targetY == 0 && targetX == grid[y].length) || 
								(targetY == grid.length && targetX == 0) || 
								(targetY == grid.length && targetX == grid[y].length) 
						){
							grid[y][x] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridEquiplet()});
						}
						else if(
								(targetY == 0 && targetX != 0) || 
								(targetY != 0 && targetX == 0) || 
								(targetY != 0 && targetX == grid[y].length) || 
								(targetY == grid.length && targetX != 0)
								
						){
							grid[y][x] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridNothingObject(),
									new GraphicalGridEquiplet(),
									new GraphicalGridTPathTransport(y,x)
								}
							);
						}
						else{
							grid[y][x] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridNothingObject(),
									new GraphicalGridEquiplet(),
									new GraphicalGridCrossedTransport(),
									new GraphicalGridHorizontalTransport(),
									new GraphicalGridVerticalTransport()			
								}
							);
						}
					}
					else{
						
					}
					xEven = false;
				}
				else{
					xEven = true;
				}
			}
			if(yEven){
				yEven = false;
			}
			else{
				yEven = true;
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
