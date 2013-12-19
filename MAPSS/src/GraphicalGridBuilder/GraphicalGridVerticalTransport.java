package GraphicalGridBuilder;

import javax.swing.ImageIcon;

public class GraphicalGridVerticalTransport extends GraphicalGridObject {

	GraphicalGridEquiplet eq1, eq2;
	
	public GraphicalGridVerticalTransport(){
		textualIcon = "|";
		this.setImageIcon("img/grid_icons/vertical.png");
		this.NDC = false;
		this.NEDC = false;
		this.EDC = false;
		this.SEDC = false;
		this.SDC = false;
		this.SWDC = false;
		this.WDC = false;
		this.NWDC = false;
	}
	
	public GraphicalGridEquiplet getEq1() {
		return eq1;
	}

	public void setEq1(GraphicalGridEquiplet eq1) {
		this.eq1 = eq1;
	}

	public GraphicalGridEquiplet getEq2() {
		return eq2;
	}

	public void setEq2(GraphicalGridEquiplet eq2) {
		this.eq2 = eq2;
	}
	
	@Override
	public boolean checkConnected(String callFromDirection, GraphicalGridObject changedSubject) {
		Boolean returnVal = false;
		
		if(callFromDirection.equals("NE") || callFromDirection.equals("SW")){
			if(
					(changedSubject instanceof GraphicalGridCrossedTransport && (changedSubject.getTextualIcon().equals("+")))||
					(changedSubject instanceof GraphicalGridHorizontalTransport)||
					(changedSubject instanceof GraphicalGridTPathTransport)||
					(changedSubject instanceof GraphicalGridEquiplet)
				
			){
				returnVal = true;
				if(callFromDirection.equals("N")){
					NDC = false;
				}
				else if(callFromDirection.equals("E")){
					EDC = false;
				}
				else if(callFromDirection.equals("S")){
					SDC = false;
				}
				else if(callFromDirection.equals("W")){
					WDC = false;
				}
				else if(callFromDirection.equals("NW")){
					NWDC = false;
				}
				else if(callFromDirection.equals("SW")){
					SWDC = false;
				}
				else if(callFromDirection.equals("NE")){
					NEDC = false;
				}
				else if(callFromDirection.equals("SE")){
					SEDC = false;
				}
			}
			else{
				returnVal = false;
				if(callFromDirection.equals("N")){
					NDC = true;
				}
				else if(callFromDirection.equals("E")){
					EDC = true;
				}
				else if(callFromDirection.equals("S")){
					SDC = true;
				}
				else if(callFromDirection.equals("W")){
					WDC = true;
				}
				else if(callFromDirection.equals("NW")){
					NWDC = true;
				}
				else if(callFromDirection.equals("SW")){
					SWDC = true;
				}
				else if(callFromDirection.equals("NE")){
					NEDC = true;
				}
				else if(callFromDirection.equals("SE")){
					SEDC = true;
				}
			}
		}
		return returnVal;
	}
	
	@Override
	protected void verifyIcon() {
		ImageIcon tmpImageIcon = this.getImageIcon();
		if(NDC){
			if (SDC){
				tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalNC.png"), 90);
			}
			else{
				tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalWNC.png"), 90);
			}
		}
		else if (SDC){
			tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalWNC.png"), -90);
		}
		else{
			tmpImageIcon = new ImageIcon("img/grid_icons/vertical.png");
		}
		
		this.setImageIcon(tmpImageIcon);
	}
}
