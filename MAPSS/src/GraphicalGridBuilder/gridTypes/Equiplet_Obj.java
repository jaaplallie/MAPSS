package GraphicalGridBuilder.gridTypes;


public class Equiplet_Obj extends Super_Obj {

	String name = "";
	
	public Equiplet_Obj(){
		textualIcon = "O";
		this.setImageIcon("img/grid_icons/equiplet.png");
		this.NDC = false;
		this.NEDC = false;
		this.EDC = false;
		this.SEDC = false;
		this.SDC = false;
		this.SWDC = false;
		this.WDC = false;
		this.NWDC = false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	@Override
//	public boolean checkConnected(String callFromDirection, GraphicalGridObject changedSubject) {
//		Boolean returnVal = false;
//		
//		if(callFromDirection.equals("N") || callFromDirection.equals("E") || callFromDirection.equals("S") || callFromDirection.equals("W")){
//			if(
//				(changedSubject instanceof GraphicalGridCrossedTransport && changedSubject.getTextualIcon().equals("+"))||
//				(changedSubject instanceof GraphicalGridTPathTransport)||
//				(changedSubject instanceof GraphicalGridVerticalTransport)
//				
//			){
//				returnVal = true;
//				if(callFromDirection.equals("N")){
//					NDC = false;
//				}
//				else if(callFromDirection.equals("E")){
//					EDC = false;
//				}
//				else if(callFromDirection.equals("S")){
//					SDC = false;
//				}
//				else if(callFromDirection.equals("W")){
//					WDC = false;
//				}
//			}
//			else{
//				returnVal = false;
//				if(callFromDirection.equals("N")){
//					NDC = true;
//				}
//				else if(callFromDirection.equals("E")){
//					EDC = true;
//				}
//				else if(callFromDirection.equals("S")){
//					SDC = true;
//				}
//				else if(callFromDirection.equals("W")){
//					WDC = true;
//				}
//				else if(callFromDirection.equals("NW")){
//					NWDC = true;
//				}
//				else if(callFromDirection.equals("SW")){
//					SWDC = true;
//				}
//				else if(callFromDirection.equals("NE")){
//					NEDC = true;
//				}
//				else if(callFromDirection.equals("SE")){
//					SEDC = true;
//				}
//			}
//		}
//		
//		return returnVal;
//	}
//	
//	@Override
//	protected void verifyIcon() {
//		ImageIcon tmpImageIcon = this.getImageIcon();
//		if(NDC && NEDC && EDC && SEDC && SDC && SWDC && WDC && NWDC){
//			this.setImageIcon("img/grid_icons/equipletNC.png");
//			tmpImageIcon = new ImageIcon("img/grid_icons/equipletNC.png");
//		}
//		else{
//			tmpImageIcon = new ImageIcon("img/grid_icons/equiplet.png");
//		}
//		this.setImageIcon(tmpImageIcon);
//	}
}