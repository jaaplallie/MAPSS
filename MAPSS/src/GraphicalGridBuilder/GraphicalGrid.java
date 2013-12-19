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
				
				final int btn_y = targetY;
				final int btn_X = targetX;
				grid[targetY][targetX].getButton().addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							saveGrid_Btn.setEnabled(true);
							verify(btn_y, btn_X);
						}
					}
				);
				//returnPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",alignx center,growy");
				//grid[targetY][targetX].getButton().setEnabled(false);
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
			System.out.println("Verify Failed, Subject position does not exist in grid.");
			return false;
		}
		else{
			subject = grid[subjectY][subjectX];
			
			//setting the targets for CornerStoneTargets && BorderTargets (smaller than 8 influence range of targets)
			if(subjectY == 0){
				if(subjectX == 0){
					//north west cornerstone
					//southTarget, southEastTarget, eastTarget
					eastTarget = grid[(subjectY)][(subjectX+1)];
					southTarget = grid[(subjectY+1)][(subjectX)];
					southEastTarget = grid[(subjectY+1)][(subjectX+1)];
				}
				else if(subjectX == (grid[grid.length-1].length-1)){
					//north east cornerstone
					//westTarget, southWestTarget, southTarget
					westTarget = grid[(subjectY)][(subjectX-1)];
					southTarget = grid[(subjectY+1)][(subjectX)];
					southWestTarget = grid[(subjectY+1)][(subjectX-1)];				
				}
				else{
					//north border target
					//westTarget, southWestTarget, southTarget, southEastTarget, eastTarget
					westTarget = grid[(subjectY)][(subjectX-1)];
					eastTarget = grid[(subjectY)][(subjectX+1)];
					southTarget = grid[(subjectY+1)][(subjectX)];
					southWestTarget = grid[(subjectY+1)][(subjectX-1)];
					southEastTarget = grid[(subjectY+1)][(subjectX+1)];
				}
			}
			else if(subjectY == grid.length-1){
				if(subjectX == 0){
					//south west cornerstone
					//northTarget, northEastTarget, eastTarget
					northTarget = grid[(subjectY-1)][(subjectX)];
					eastTarget = grid[(subjectY)][(subjectX+1)];
					northEastTarget = grid[(subjectY-1)][(subjectX+1)];
				}
				else if(subjectX == grid[grid.length-1].length-1){
					//south east cornerstone
					//westTarget, northWestTarget, northTarget
					northTarget = grid[(subjectY-1)][(subjectX)];
					westTarget = grid[(subjectY)][(subjectX-1)];
					northWestTarget = grid[(subjectY-1)][(subjectX-1)];
				}
				else{
					//south border target
					//westTarget, northWestTarget, northTarget, northEastTarget, eastTarget
					northTarget = grid[(subjectY-1)][(subjectX)];
					westTarget = grid[(subjectY)][(subjectX-1)];
					eastTarget = grid[(subjectY)][(subjectX+1)];
					northWestTarget = grid[(subjectY-1)][(subjectX-1)];
					northEastTarget = grid[(subjectY-1)][(subjectX+1)];
				}
			}
			else if(subjectX == 0){
				//west border
				//northTarget, northEastTarget, eastTarget, southEastTarget, southTarget
				northTarget = grid[(subjectY-1)][(subjectX)];
				eastTarget = grid[(subjectY)][(subjectX+1)];
				southTarget = grid[(subjectY+1)][(subjectX)];
				northEastTarget = grid[(subjectY-1)][(subjectX+1)];
				southEastTarget = grid[(subjectY+1)][(subjectX+1)];
			}
			else if(subjectX == grid[grid.length-1].length-1){
				//east border
				//northTarget, northWestTarget, westTarget, southWestTarget, southTarget
				northTarget = grid[(subjectY-1)][(subjectX)];
				westTarget = grid[(subjectY)][(subjectX-1)];
				southTarget = grid[(subjectY+1)][(subjectX)];
				northWestTarget = grid[(subjectY-1)][(subjectX-1)];
				southWestTarget = grid[(subjectY+1)][(subjectX-1)];
			}
			else{
				//setting the targets for InnerGridTargets (influence range of 8 targets)
				//all directions targets
				northTarget = grid[(subjectY-1)][(subjectX)];
				westTarget = grid[(subjectY)][(subjectX-1)];
				eastTarget = grid[(subjectY)][(subjectX+1)];
				southTarget = grid[(subjectY+1)][(subjectX)];
				northWestTarget = grid[(subjectY-1)][(subjectX-1)];
				northEastTarget = grid[(subjectY-1)][(subjectX+1)];
				southWestTarget = grid[(subjectY+1)][(subjectX-1)];
				southEastTarget = grid[(subjectY+1)][(subjectX+1)];
			}
		}
		if(northTarget != null){
			Boolean checkResult = northTarget.checkConnected("N", subject);
			if(!checkResult){
				northTarget.updateButton();
				success = false;
				//verify((subjectY-1), subjectX);
			}
		}
		if(westTarget != null){
			Boolean checkResult = westTarget.checkConnected("W", subject);
			if(!checkResult){
				westTarget.updateButton();
				success = false;
				//verify(subjectY, (subjectX-1));
			}
		}
		if(eastTarget != null){
			Boolean checkResult = eastTarget.checkConnected("E", subject);
			if(!checkResult){
				eastTarget.updateButton();
				success = false;
				//verify(subjectY, (subjectX+1));
			}
		}
		if(southTarget != null){
			Boolean checkResult = southTarget.checkConnected("S", subject);
			if(!checkResult){
				southTarget.updateButton();
				success = false;
				//verify((subjectY+1), subjectX);
			}
		}
		if(northWestTarget != null){
			Boolean checkResult = northWestTarget.checkConnected("NW", subject);
			if(!checkResult){
				northWestTarget.updateButton();
				success = false;
				//verify((subjectY-1), (subjectX-1));
			}
		}
		if(northEastTarget != null){
			Boolean checkResult = northEastTarget.checkConnected("NE", subject);
			if(!checkResult){
				northEastTarget.updateButton();
				success = false;
				//verify((subjectY-1), (subjectX+1));
			}
		}
		if(southWestTarget != null){
			Boolean checkResult = southWestTarget.checkConnected("SW", subject);
			if(!checkResult){
				southWestTarget.updateButton();
				success = false;
				//verify((subjectY+1), (subjectX-1));
			}
		}
		if(southEastTarget != null){
			Boolean checkResult = southEastTarget.checkConnected("SE", subject);
			if(!checkResult){
				southEastTarget.updateButton();
				success = false;
				//verify((subjectY+1), (subjectX+1));
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
	
	public void inputSavableFormat(String input){
		String[] inputLines = input.split(System.getProperty("line.separator"));
		
		for(String s : inputLines){
			String[] lineObjects = s.split(",");
		}
	}
	
	public StringBuilder toSavableFormat(){
		StringBuilder sb = new StringBuilder();
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			sb.append("[");
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				GraphicalGridObject currentObj = grid[targetY][targetX].getInputObject();
				sb.append(currentObj.getTextualInputRepresentation());
				if(targetX != grid[targetY].length){
					sb.append(",");
				}
			}
			sb.append("]");
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb;
	}

	@Override
	public String toString() {
		return name + "[x=" + grid.length + "][y=" + grid[0].length + "]";
	}
}
