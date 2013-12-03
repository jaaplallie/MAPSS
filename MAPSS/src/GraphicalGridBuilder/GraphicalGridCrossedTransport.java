package GraphicalGridBuilder;

import javax.swing.ImageIcon;

public class GraphicalGridCrossedTransport extends GraphicalGridObject {

	GraphicalGridEquiplet eq1, eq2, eq3, eq4;
	
	public GraphicalGridCrossedTransport(){
		textualIcon = "X";
		this.setImageIcon("img/grid_icons/crossedslash.png");
		this.NDC = false;
		this.NEDC = false;
		this.EDC = false;
		this.SEDC = false;
		this.SDC = false;
		this.SWDC = false;
		this.WDC = false;
		this.NWDC = false;
	}
	
	public GraphicalGridCrossedTransport(String s){
		if(s.equals("+")){
			textualIcon = "+";
			this.setImageIcon("img/grid_icons/crossedplus.png");
		}
		else{
			textualIcon = "X";
			this.setImageIcon("img/grid_icons/crossedslash.png");
		}
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

	public GraphicalGridEquiplet getEq3() {
		return eq3;
	}

	public void setEq3(GraphicalGridEquiplet eq3) {
		this.eq3 = eq3;
	}

	public GraphicalGridEquiplet getEq4() {
		return eq4;
	}

	public void setEq4(GraphicalGridEquiplet eq4) {
		this.eq4 = eq4;
	}
	
	@Override
	public boolean checkConnected(String callFromDirection, GraphicalGridObject changedSubject) {
		Boolean returnVal = false;
		if(
			(callFromDirection.equals("NE") || callFromDirection.equals("SW")) && (
				(changedSubject instanceof GraphicalGridCrossedTransport && (changedSubject.getTextualIcon().equals("X") || changedSubject.getTextualIcon().equals("x")))||
				(changedSubject instanceof GraphicalGridBackSlashTransport)||
				(changedSubject instanceof GraphicalGridEquiplet)
			)	
		){
			returnVal = true;
			if(callFromDirection.equals("NE")){
				NEDC = false;
			}
			else if(callFromDirection.equals("SW")){
				SWDC = false;
			}
		}
		else if(
				(callFromDirection.equals("NW") || callFromDirection.equals("SE")) && (
					(changedSubject instanceof GraphicalGridCrossedTransport && (changedSubject.getTextualIcon().equals("X") || changedSubject.getTextualIcon().equals("x")))||
					(changedSubject instanceof GraphicalGridForwardSlashTransport)||
					(changedSubject instanceof GraphicalGridEquiplet)
				)
			){
			returnVal = true;
			if(callFromDirection.equals("NW")){
				NWDC = false;
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
		return returnVal;
	}
	
	@Override
	protected void verifyIcon() {
		textualIcon = "X";
		if(textualIcon.equals("+")){
			//not implemented yet
		}
		else{
			if(NWDC && !NEDC && !SWDC && !SEDC){
				this.setImageIcon("img/grid_icons/crossedslashNWNC.png");
			}
			else if(!NWDC && NEDC && !SWDC && !SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslashNWNC.png"), 90));
			}
			else if(!NWDC && !NEDC && SWDC && !SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/forwardslashNWNC.png"), 270));
			}
			else if(!NWDC && !NEDC && !SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/forwardslashNWNC.png"), 180));
			}//--
			else if(NWDC && NEDC && !SWDC && !SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslashNWSWNC.png"), 90));
			}
			else if(!NWDC && NEDC && SWDC && !SEDC){
				this.setImageIcon("img/grid_icons/crossedslashNESWNC.png");
			}
			else if(!NWDC && !NEDC && SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslashNWSWNC.png"), 270));
			}
			else if(NWDC && !NEDC && !SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslashNESWNC.png"), 90));
			}
			else if(NWDC && !NEDC && SWDC && !SEDC){
				this.setImageIcon("img/grid_icons/crossedslashNWSWNC.png");
			}
			else if(!NWDC && NEDC && !SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslashNWSWNC.png"), 180));
			}//--
			else if(NWDC && NEDC && SWDC && !SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslash3NC.png"), 270));
			}
			else if(!NWDC && NEDC && SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslash3NC.png"), 90));
			}
			else if(NWDC && !NEDC && SWDC && SEDC){
				this.setImageIcon(this.rotateIcon(new ImageIcon("img/grid_icons/crossedslash3NC.png"), 180));
			}
			else if(NWDC && NEDC && !SWDC && SEDC){
				this.setImageIcon("img/grid_icons/crossedslash3NC.png");
			}
			else if(NWDC && NEDC && SWDC && SEDC){
				this.setImageIcon("img/grid_icons/crossedslashNC.png");
			}
			else{
				this.setImageIcon("img/grid_icons/crossedslash.png");
			}
			
		}
	}
}
