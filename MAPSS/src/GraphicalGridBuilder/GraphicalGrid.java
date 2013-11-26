package GraphicalGridBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class GraphicalGrid extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -618628616694879189L;
	String name = "";
	GraphicalGridTarget[][] grid;
	JPanel interfacePanel = new JPanel();
	
	public GraphicalGrid(){
	}
	
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
	
	public void setName(String name) {
		this.name = name;
	}

	public JPanel draw(){
		JPanel returnPanel = new JPanel();
		JPanel gridPanel = new JPanel();
		JPanel gridEditorPanel = new JPanel();
		
		final JButton saveGrid_Btn = new JButton("Save Grid");
		saveGrid_Btn.setEnabled(false);
		saveGrid_Btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(getCustomGridString());
				saveGrid_Btn.setEnabled(false);
			}
		});
		gridEditorPanel.add(saveGrid_Btn);
		
		//fill the GraphicalGrid gridPanel
		gridPanel.setLayout(new MigLayout("", "[grow, fill][grow, fill]", "[]"));

		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				System.out.println(grid[targetY][targetX].getButton().toString());
				System.out.println(grid[targetY][targetX].getPossibilitiesString());
				System.out.println("x: " + targetX);
				System.out.println("y: " + targetY);
				
				grid[targetY][targetX].getButton().addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							saveGrid_Btn.setEnabled(true);
						}
					}
				);
				//returnPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",alignx center,growy");
				grid[targetY][targetX].getButton().setEnabled(false);
				gridPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",growx");
			}
		}
		
		returnPanel.add(gridPanel);
		returnPanel.add(gridEditorPanel);
		
		//fill the 
		return returnPanel;
	}

	private Boolean verify(int subjectY, int subjectX){
		Boolean success = false;
		GraphicalGridTarget subject = null;
		GraphicalGridTarget northTarget = null;
		GraphicalGridTarget westTarget = null;
		GraphicalGridTarget eastTarget = null;
		GraphicalGridTarget southTarget = null;
		GraphicalGridTarget northWestTarget = null;
		GraphicalGridTarget northEastTarget = null;
		GraphicalGridTarget southWestTarget = null;
		GraphicalGridTarget southEastTarget = null;
		
		if(subjectY == -1 || subjectX == -1){
			success = false;
			return success;
		}
		else{
			subject = grid[subjectY][subjectX];
			
			//checks for CornerStoneTargets && BorderTargets (smaller than 8 influence range of targets)
			if(subjectY == 0){
				if(subjectX == 0){
					//north west cornerstone
					//southTarget, southEastTarget, eastTarget 
				}
				else if(subjectX == grid[grid.length].length){
					//north east cornerstone
					//westTarget, southWestTarget, southTarget
					
				}
				else{
					//north border target
					//westTarget, southWestTarget, southTarget, southEastTarget, eastTarget
					
				}
			}
			else if(subjectY == grid.length){
				if(subjectX == 0){
					//south west cornerstone
					//northTarget, northEastTarget, eastTarget
				}
				else if(subjectX == grid[grid.length].length){
					//south east cornerstone
					//westTarget, northWestTarget, northTarget
				}
				else{
					//south border target
					
				}
			}
			else if(subjectX == 0){
				//west border
				
			}
			else if(subjectX == grid[grid.length].length){
				//east border
				
			}
			else{
				//checks for InnerGridTargets (influence range of 8 targets)
				
			}
		}
		return success;
	}
	
	public String getCustomGridString(){
		String customGridString = "";
		ArrayList<GraphicalGridEquiplet> equiplets = new ArrayList<GraphicalGridEquiplet>();
		ArrayList<String> connections = new ArrayList<String>();
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				GraphicalGridObject currentObj = grid[targetY][targetX].getInputObject();
				if (currentObj instanceof GraphicalGridEquiplet) {
					equiplets.add((GraphicalGridEquiplet)currentObj);
					connections.add("");
				} 
			}
		}
		
		System.out.println("EquipletsList Size = " + equiplets.size());
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				GraphicalGridObject currentObj = grid[targetY][targetX].getInputObject();
				if (currentObj instanceof GraphicalGridEquiplet) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					System.out.println("Equiplet detected!");
					//isEquiplet do nothing yet
					//connectionsGrid[targetY][targetX] = equipletLocation
				} 
				else if (currentObj instanceof GraphicalGridHorizontalTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY)][(targetX+1)].getInputObject());
					System.out.println("Creating Horizontal Connection between " + indexeq1 + " and " + indexeq2);
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
				}
				else if (currentObj instanceof GraphicalGridVerticalTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY-1)][(targetX)].getInputObject());
					int indexeq2 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY+1)][(targetX)].getInputObject());
					System.out.println("Creating Horizontal Connection between " + indexeq1 + " and " + indexeq2);
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
				}
				else if (currentObj instanceof GraphicalGridBackSlashTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY-1)][(targetX+1)].getInputObject());
					int indexeq2 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY+1)][(targetX-1)].getInputObject());
					System.out.println("Creating Horizontal Connection between " + indexeq1 + " and " + indexeq2);
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
				}
				else if (currentObj instanceof GraphicalGridForwardSlashTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY-1)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY+1)][(targetX+1)].getInputObject());
					System.out.println("Creating Horizontal Connection between " + indexeq1 + " and " + indexeq2);
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
				}
				else if (currentObj instanceof GraphicalGridCrossedTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY-1)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY+1)][(targetX+1)].getInputObject());
					int indexeq3 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY-1)][(targetX+1)].getInputObject());
					int indexeq4 = equiplets.indexOf((GraphicalGridEquiplet)grid[(targetY+1)][(targetX-1)].getInputObject());
					System.out.println("Creating Horizontal Connection between [" + indexeq1 + " and " + indexeq2 + "] and [" + indexeq3 + " and " + indexeq4 + "]");
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
					connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
				}
				else if (currentObj instanceof GraphicalGridTPathTransport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					System.out.println("TPath detected!");
				}
				else if (currentObj instanceof GraphicalGridNothingObject) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					System.out.println("Nothing Object detected!");
					//isNothing = do Nothing
				}
			}
		}
		
		for(String str : connections){
			if (str.length() > 0 && str.charAt(str.length()-1)=='-') {
			    str = str.substring(0, str.length()-1);
			}
			customGridString += str.trim() + ",";
		}
		if (customGridString.length() > 0 && customGridString.charAt(customGridString.length()-1)==',') {
			customGridString = customGridString.substring(0, customGridString.length()-1);
		}
		customGridString = customGridString.trim();
		return customGridString;
	}
	
	public String[] getTextualGrid(){
		String[] textualGrid = new String[grid.length];
		StringBuilder lineBuilder = new StringBuilder();
		for(int targetY = 0; targetY < grid.length; targetY++){
			lineBuilder = new StringBuilder();
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				GraphicalGridObject currentObj = grid[targetY][targetX].getInputObject();
				lineBuilder.append(currentObj.getTextualInputRepresentation());
			}
			textualGrid[targetY] = lineBuilder.toString();
		}
		return textualGrid;
	}

	@Override
	public String toString() {
		return name + "[x=" + grid.length + "][y=" + grid[0].length + "]";
	}
}
