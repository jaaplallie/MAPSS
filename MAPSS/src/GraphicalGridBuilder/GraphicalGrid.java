package GraphicalGridBuilder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class GraphicalGrid extends JFrame{

	GraphicalGridTarget[][] grid;
	JPanel interfacePanel = new JPanel();
	
	public GraphicalGrid(int x, int y){
		int xP = (x*2)-1;
		int yP = (y*2)-1;
		grid = new GraphicalGridTarget[yP][xP];
		
		Boolean xEven = true;
		Boolean yEven = true;
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				if(((targetX+1)%2)==0){
					xEven = false;
				}
				else{
					xEven = true;
				}
				if(((targetY+1)%2)==0){
					yEven = false;
				}
				else{
					yEven = true;
				}
				
				//grid[y][x] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridNothingObject()});
				System.out.println(String.format("tY=[%s] tX=[%s] yE=[%s] xE=[%s] gY=[%s] gX=[%s]", targetY+"", targetX+"", yEven+"", xEven+"", grid.length, grid[targetY].length));
				if(xEven){
					if(yEven){
						//xeven = true & yeven = true
						if(
								((targetY == 0) && (targetX == 0)) || 
								((targetY == 0) && (targetX == (grid[targetY].length-1))) || 
								((targetY == (grid.length-1)) && (targetX == 0)) || 
								((targetY == (grid.length-1)) && (targetX == (grid[targetY].length-1))) 
						){
							grid[targetY][targetX] = new GraphicalGridTarget(new GraphicalGridObject[]{new GraphicalGridEquiplet()});
						}
						else if(
								((targetY == 0) && (targetX != 0)) || 
								((targetY != 0) && (targetX == 0)) || 
								((targetY != 0) && (targetX == (grid[targetY].length-1))) || 
								((targetY == (grid.length-1)) && (targetX != 0))
						){
							if((targetY == 0 || targetY == (grid.length-1))){
								grid[targetY][targetX] = new GraphicalGridTarget(
									new GraphicalGridObject[]{
										new GraphicalGridEquiplet(),
										new GraphicalGridHorizontalTransport(),
										new GraphicalGridTPathTransport(targetY, targetX, grid.length),
										new GraphicalGridNothingObject()
									}
								);
							}
							else{
								grid[targetY][targetX] = new GraphicalGridTarget(
									new GraphicalGridObject[]{
										new GraphicalGridEquiplet(),
										new GraphicalGridVerticalTransport(),
										new GraphicalGridTPathTransport(targetY, targetX, grid.length),
										new GraphicalGridNothingObject()
									}
								);
							}
						}
						else{
							grid[targetY][targetX] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridEquiplet(),
									new GraphicalGridCrossedTransport(),
									new GraphicalGridHorizontalTransport(),
									new GraphicalGridVerticalTransport(),
									new GraphicalGridNothingObject()		
								}
							);
						}
					}
					else{
						//xeven = true & yeven = false
						if((targetX == 0) || (targetX == (grid[targetY].length-1))){
							grid[targetY][targetX] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridVerticalTransport(),
									new GraphicalGridTPathTransport(targetY, targetX, grid.length),
									new GraphicalGridNothingObject()			
								}
							);
						}
						else{
							grid[targetY][targetX] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridVerticalTransport(),
									new GraphicalGridNothingObject()		
								}
							);
						}
					}
				}
				else{
					if(yEven){
						//xeven = false & yeven = true
						if((targetY == 0) || (targetY == (grid.length-1))){
							grid[targetY][targetX] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridHorizontalTransport(),
									new GraphicalGridTPathTransport(targetY, targetX, grid.length),
									new GraphicalGridNothingObject()		
								}
							);
						}
						else{
							grid[targetY][targetX] = new GraphicalGridTarget(
								new GraphicalGridObject[]{
									new GraphicalGridHorizontalTransport(),
									new GraphicalGridVerticalTransport(),
									new GraphicalGridCrossedTransport(),
									new GraphicalGridNothingObject()
								}
							);
							
						}
					}
					else{
						//xeven = false & yeven = false
						grid[targetY][targetX] = new GraphicalGridTarget(
							new GraphicalGridObject[]{
								new GraphicalGridCrossedTransport(),
								new GraphicalGridForwardSlashTransport(),
								new GraphicalGridBackSlashTransport(),
								new GraphicalGridNothingObject()
							}
						);
					}
				}
			}
		}
	}
	
	public JPanel draw(){
		JPanel returnPanel = new JPanel();
		JPanel gridPanel = new JPanel();
		JPanel gridEditorPanel = new JPanel();
		
		//fill the GraphicalGrid gridPanel
		gridPanel.setLayout(new MigLayout("", "[grow, fill][grow, fill]", "[]"));

		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				System.out.println(grid[targetY][targetX].getButton().toString());
				System.out.println(grid[targetY][targetX].getPossibilitiesString());
				System.out.println("x: " + targetX);
				System.out.println("y: " + targetY);
				
				//returnPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",alignx center,growy");
				gridPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",growx");
			}
		}
		
		returnPanel.add(gridPanel);
		returnPanel.add(gridEditorPanel);
		
		//fill the 
		return returnPanel;
	}
	
	public String getGridString(){
		String returnString = "";
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				grid[targetY][targetX].getInput();
				
				//TODO
				/* 
				 * -What class is the GraphicalGridObject?
				 * -Define links between equiplets based on the different objects
				 * -create grid
				 * -insert data into scenario
				 * -insert into jade 
				*/
			}
		}
		return returnString;
	}
}