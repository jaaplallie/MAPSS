package GraphicalGridBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import Backend.Grid;
import Backend.ProgramData;
import Backend.Scenario;
import GraphicalGridBuilder.gridTypes.BackSlash_Transport;
import GraphicalGridBuilder.gridTypes.Crossed_Transport;
import GraphicalGridBuilder.gridTypes.Equiplet_Obj;
import GraphicalGridBuilder.gridTypes.ForwardSlash_Transport;
import GraphicalGridBuilder.gridTypes.Horizontal_Transport;
import GraphicalGridBuilder.gridTypes.Nothing_Obj;
import GraphicalGridBuilder.gridTypes.Super_Obj;
import GraphicalGridBuilder.gridTypes.TPath_Transport;
import GraphicalGridBuilder.gridTypes.Vertical_Transport;
import Gui.ChartPresenter;
import Gui.CreateGridModule;
import Gui.ProductSetup;

import com.jgoodies.forms.builder.DefaultFormBuilder;

public class GraphicalGrid extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -618628616694879189L;
	String name = "";
	ObjField_Container[][] grid;
	private DefaultFormBuilder builder = new ProgramData().getNewBuilder();
	JPanel gridPanel = new JPanel();
	JPanel gridEditorPanel = new ProgramData().getNewBuilder().getPanel();
	
	public GraphicalGrid(){
	}
	
	public GraphicalGrid(int x, int y){
		int xP = (x*2)-1;
		int yP = (y*2)-1;
		grid = new ObjField_Container[yP][xP];
		
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
							grid[targetY][targetX] = new ObjField_Container(new Super_Obj[]{new Equiplet_Obj(),new Nothing_Obj(true)});
						}
						else if(
								((targetY == 0) && (targetX != 0)) || 
								((targetY != 0) && (targetX == 0)) || 
								((targetY != 0) && (targetX == (grid[targetY].length-1))) || 
								((targetY == (grid.length-1)) && (targetX != 0))
						){
							if((targetY == 0 || targetY == (grid.length-1))){
								grid[targetY][targetX] = new ObjField_Container(
									new Super_Obj[]{
										new Equiplet_Obj(),
										new Horizontal_Transport(""),
										new Horizontal_Transport("l-r"),
										new Horizontal_Transport("r-l"),
										new TPath_Transport(targetY, targetX, grid.length),
										new Nothing_Obj(true)
									}
								);
							}
							else{
								grid[targetY][targetX] = new ObjField_Container(
									new Super_Obj[]{
										new Equiplet_Obj(),
										new Vertical_Transport(""),
										new Vertical_Transport("t-b"),
										new Vertical_Transport("b-t"),
										new TPath_Transport(targetY, targetX, grid.length),
										new Nothing_Obj(true)
									}
								);
							}
						}
						else{
							grid[targetY][targetX] = new ObjField_Container(
								new Super_Obj[]{
									new Equiplet_Obj(),
									new Crossed_Transport(""),
									new Crossed_Transport("b-t b-t"),
									new Crossed_Transport("b-t t-b b-t"),
									new Crossed_Transport("b-t t-b"),
									new Crossed_Transport("t-b b-t b-t"),
									new Crossed_Transport("t-b b-t t-b"),
									new Crossed_Transport("t-b b-t"),
									new Crossed_Transport("t-b t-b b-t"),
									new Crossed_Transport("t-b t-b"),
									new Horizontal_Transport(""),
									new Horizontal_Transport("l-r"),
									new Horizontal_Transport("r-l"),
									new Vertical_Transport(""),
									new Vertical_Transport("t-b"),
									new Vertical_Transport("b-t"),
									new Nothing_Obj(true)		
								}
							);
						}
					}
					else{
						//xeven = true & yeven = false
						if((targetX == 0) || (targetX == (grid[targetY].length-1))){
							grid[targetY][targetX] = new ObjField_Container(
								new Super_Obj[]{
									new Vertical_Transport(""),
									new Vertical_Transport("t-b"),
									new Vertical_Transport("b-t"),
//									new GraphicalGridTPathTransport(targetY, targetX, grid.length),
									new Nothing_Obj(false)			
								}
							);
						}
						else{
							grid[targetY][targetX] = new ObjField_Container(
								new Super_Obj[]{
									new Vertical_Transport(""),
									new Vertical_Transport("t-b"),
									new Vertical_Transport("b-t"),
									new Nothing_Obj(false)		
								}
							);
						}
					}
				}
				else{
					if(yEven){
						//xeven = false & yeven = true
						if((targetY == 0) || (targetY == (grid.length-1))){
							grid[targetY][targetX] = new ObjField_Container(
								new Super_Obj[]{
									new Horizontal_Transport(""),
									new Horizontal_Transport("l-r"),
									new Horizontal_Transport("r-l"),
//									new GraphicalGridTPathTransport(targetY, targetX, grid.length),
									new Nothing_Obj(false)		
								}
							);
						}
						else{
							grid[targetY][targetX] = new ObjField_Container(
								new Super_Obj[]{
									new Horizontal_Transport(""),
									new Horizontal_Transport("l-r"),
									new Horizontal_Transport("r-l"),
									new Vertical_Transport(""),
									new Vertical_Transport("t-b"),
									new Vertical_Transport("b-t"),
									new Crossed_Transport(""),
									new Crossed_Transport("b-t b-t"),
									new Crossed_Transport("b-t t-b b-t"),
									new Crossed_Transport("b-t t-b"),
									new Crossed_Transport("t-b b-t b-t"),
									new Crossed_Transport("t-b b-t t-b"),
									new Crossed_Transport("t-b b-t"),
									new Crossed_Transport("t-b t-b b-t"),
									new Crossed_Transport("t-b t-b"),
									new Nothing_Obj(false)
								}
							);
							
						}
					}
					else{
						//xeven = false & yeven = false
						grid[targetY][targetX] = new ObjField_Container(
							new Super_Obj[]{
								new Crossed_Transport(""),
								new Crossed_Transport("b-t b-t"),
								new Crossed_Transport("b-t t-b b-t"),
								new Crossed_Transport("b-t t-b"),
								new Crossed_Transport("t-b b-t b-t"),
								new Crossed_Transport("t-b b-t t-b"),
								new Crossed_Transport("t-b b-t"),
								new Crossed_Transport("t-b t-b b-t"),
								new Crossed_Transport("t-b t-b"),
								new ForwardSlash_Transport(""),
								new ForwardSlash_Transport("t-b"),
								new ForwardSlash_Transport("b-t"),
								new BackSlash_Transport(""),
								new BackSlash_Transport("t-b"),
								new BackSlash_Transport("b-t"),
								new Nothing_Obj(false)
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
		JPanel gridEditorPanel = builder.getPanel();
		builder = new ProgramData().getNewBuilder();

		final JButton default_Btn = new JButton("Set to Default");
		default_Btn.setEnabled(true);
		default_Btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int targetY = 0; targetY < grid.length; targetY++){
					for(int targetX = 0; targetX < grid[targetY].length; targetX++){
						grid[targetY][targetX].setToDefaultSelection();
					}
				}
			}
		});
		final JButton blanco_Btn = new JButton("Set to Blanco");
		blanco_Btn.setEnabled(true);
		blanco_Btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int targetY = 0; targetY < grid.length; targetY++){
					for(int targetX = 0; targetX < grid[targetY].length; targetX++){
						grid[targetY][targetX].setToEmptySelection();
					}
				}
			}
		});
		final JButton saveGrid_Btn = new JButton("Save Structure");
		saveGrid_Btn.setEnabled(true);
		saveGrid_Btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String relation_list = getCustomGridString();
				System.out.println(relation_list);
				Scenario S = ProgramData.getLatestScenario();
				Grid.createCustom(S.x, S.y, S.name, relation_list);
				//ProgramData.removeScenario(S);

				ChartPresenter.updateChartStructures();
				ProductSetup.updateProductStructures();
				CreateGridModule.updateStructureBox();
				saveGrid_Btn.setEnabled(false);
			}
		});
		builder.append(blanco_Btn);
		builder.nextLine();
		builder.append(default_Btn);
		builder.nextLine();
		builder.append(saveGrid_Btn);
//		gridEditorPanel.add(blanco_Btn);
//		gridEditorPanel.add(saveGrid_Btn);
		gridEditorPanel = builder.getPanel();
		
		
		//fill the GraphicalGrid gridPanel
		gridPanel.setLayout(new MigLayout("", "[grow, fill][grow, fill]", "[]"));

		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				System.out.println(grid[targetY][targetX].getButton().toString());
				System.out.println(grid[targetY][targetX].getPossibilitiesString());
				System.out.println("x: " + targetX);
				System.out.println("y: " + targetY);
				
				final int tar_y = targetY;
				final int tar_X = targetX;
				grid[targetY][targetX].getButton().addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							System.out.println(grid[tar_y][tar_X].typePossibilities[grid[tar_y][tar_X].currentlyDisplayedTypePossibility].getClass().toString());
							saveGrid_Btn.setEnabled(true);
//							verify(btn_y, btn_X);
						}
					}
				);
				grid[targetY][targetX].getButton().addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                if (e.getButton() == 3) { // if right click
		                	grid[tar_y][tar_X].setToEmptySelection();
		                }
		            }
		        });
				gridPanel.add(grid[targetY][targetX].getButton(), "cell " + targetX + " " + targetY + ",growx");
			}
		}
		
		returnPanel.add(gridPanel);
		returnPanel.add(gridEditorPanel);
		builder = new ProgramData().getNewBuilder();
		
		return returnPanel;
	}
	
	public String getCustomGridString(){
		String customGridString = "";
		ArrayList<Equiplet_Obj> equiplets = new ArrayList<Equiplet_Obj>();
		ArrayList<String> connections = new ArrayList<String>();
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				Super_Obj currentObj = grid[targetY][targetX].getInputObject();
				if (currentObj instanceof Nothing_Obj) {
					if(((Nothing_Obj) currentObj).canBeEquiplet()){
						equiplets.add((Equiplet_Obj)currentObj);
						connections.add("");
					}
				}
				else if (currentObj instanceof TPath_Transport) {
					equiplets.add((Equiplet_Obj)currentObj);
					connections.add("");
				} 
				else if (currentObj instanceof Equiplet_Obj) {
					equiplets.add((Equiplet_Obj)currentObj);
					connections.add("");
				} 
			}
		}
		
		System.out.println("EquipletsList Size = " + equiplets.size());
		
		for(int targetY = 0; targetY < grid.length; targetY++){
			for(int targetX = 0; targetX < grid[targetY].length; targetX++){
				Super_Obj currentObj = grid[targetY][targetX].getInputObject();
				if (currentObj instanceof Nothing_Obj) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					System.out.println("Nothing Object detected!");
					
					if(((Nothing_Obj) currentObj).canBeEquiplet()){
						System.out.println("can be EQ!");
						int defeq = equiplets.indexOf((Nothing_Obj)grid[(targetY)][(targetX)].getInputObject());
						connections.set(defeq, " ");
						//isNothing = do not create link only pretend that equiplet is created for correct gridstring
					}else{
						System.out.println("can not be EQ!");
					}
					
				}
				else if (currentObj instanceof Equiplet_Obj) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					System.out.println("Equiplet detected!");
					//isEquiplet do nothing yet
					//connectionsGrid[targetY][targetX] = equipletLocation
				} 
				else if (currentObj instanceof Horizontal_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX+1)].getInputObject());
					System.out.println("Creating Horizontal Connection between " + indexeq1 + " and " + indexeq2);
					if(currentObj.getTransportFlow().equals("l-r")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					}
					else if(currentObj.getTransportFlow().equals("r-l")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
					else{
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
					
				}
				else if (currentObj instanceof Vertical_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-1)][(targetX)].getInputObject());
					int indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+1)][(targetX)].getInputObject());
					System.out.println("Creating Vertical Connection between " + indexeq1 + " and " + indexeq2);
					if(currentObj.getTransportFlow().equals("t-b")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					}
					else if(currentObj.getTransportFlow().equals("b-t")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
					else{
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
				}
				else if (currentObj instanceof BackSlash_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-1)][(targetX+1)].getInputObject());
					int indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+1)][(targetX-1)].getInputObject());
					System.out.println("Creating Diagonal Connection between " + indexeq1 + " and " + indexeq2);
					if(currentObj.getTransportFlow().equals("t-b")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					}
					else if(currentObj.getTransportFlow().equals("b-t")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
					else{
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
				}
				else if (currentObj instanceof ForwardSlash_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-1)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+1)][(targetX+1)].getInputObject());
					System.out.println("Creating Diagonal Connection between " + indexeq1 + " and " + indexeq2);
					if(currentObj.getTransportFlow().equals("t-b")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					}
					else if(currentObj.getTransportFlow().equals("b-t")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
					else{
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					}
				}
				else if (currentObj instanceof Crossed_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-1)][(targetX-1)].getInputObject());
					int indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+1)][(targetX+1)].getInputObject());
					int indexeq3 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-1)][(targetX+1)].getInputObject());
					int indexeq4 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+1)][(targetX-1)].getInputObject());
					System.out.println("Creating Crossed Connection between [" + indexeq1 + " and " + indexeq2 + "] and [" + indexeq3 + " and " + indexeq4 + "]");
					
					if(currentObj.getTransportFlow().equals("b-t b-t")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
					else if(currentObj.getTransportFlow().equals("b-t t-b b-t")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
					else if(currentObj.getTransportFlow().equals("b-t t-b")){
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
					}
					else if(currentObj.getTransportFlow().equals("t-b b-t b-t")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
					else if(currentObj.getTransportFlow().equals("t-b b-t t-b")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
					}
					else if(currentObj.getTransportFlow().equals("t-b b-t")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
					else if(currentObj.getTransportFlow().equals("t-b t-b b-t")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
					else if(currentObj.getTransportFlow().equals("t-b t-b")){
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
					}
					else{
						connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
						connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
						connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq4+"-");
						connections.set(indexeq4, connections.get(indexeq4) + "" + indexeq3+"-");
					}
				}
				else if (currentObj instanceof TPath_Transport) {
					System.out.println("TARGETX=[" + targetX + "] TARGETY=[" + targetY + "]");
					int indexeq1 = -1;
					int indexeq2 = -1;
					int indexeq3 = -1;
					if(((TPath_Transport) currentObj).getArm().equals('b')){
						indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX-2)].getInputObject()); //left or top
						indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX+2)].getInputObject()); //right or bottom
						indexeq3 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+2)][(targetX)].getInputObject()); //tpath arm
					}
					else if(((TPath_Transport) currentObj).getArm().equals('r')){
						indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-2)][(targetX)].getInputObject()); //left or top
						indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+2)][(targetX)].getInputObject()); //right or bottom
						indexeq3 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX+2)].getInputObject()); //tpath arm
					}
					else if(((TPath_Transport) currentObj).getArm().equals('t')){
						indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX-2)].getInputObject()); //left or top
						indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX+2)].getInputObject()); //right or bottom
						indexeq3 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-2)][(targetX)].getInputObject()); //tpath arm				
					}
					else if(((TPath_Transport) currentObj).getArm().equals('l')){
						indexeq1 = equiplets.indexOf((Equiplet_Obj)grid[(targetY-2)][(targetX)].getInputObject()); //left or top
						indexeq2 = equiplets.indexOf((Equiplet_Obj)grid[(targetY+2)][(targetX)].getInputObject()); //right or bottom
						indexeq3 = equiplets.indexOf((Equiplet_Obj)grid[(targetY)][(targetX-2)].getInputObject()); //tpath arm
					}
					System.out.println("Creating T Connection between [" + indexeq1 + " and " + indexeq2 + "] and [" + indexeq1 + " and " + indexeq3 + "] and [" + indexeq2 + " and " + indexeq3 + "]");
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq2+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq1+"-");
					connections.set(indexeq1, connections.get(indexeq1) + "" + indexeq3+"-");
					connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq1+"-");
					connections.set(indexeq2, connections.get(indexeq2) + "" + indexeq3+"-");
					connections.set(indexeq3, connections.get(indexeq3) + "" + indexeq2+"-");
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
				Super_Obj currentObj = grid[targetY][targetX].getInputObject();
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
