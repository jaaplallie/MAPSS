package GraphicalGridBuilder.gridTypes;


public class Vertical_Transport extends Super_Obj {
	
	public Vertical_Transport(){
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
	
//	@Override
//	public boolean checkConnected(String callFromDirection, GraphicalGridObject changedSubject) {
//		Boolean returnVal = false;
//		
//		if(callFromDirection.equals("NE") || callFromDirection.equals("SW")){
//			if(
//					(changedSubject instanceof GraphicalGridCrossedTransport && (changedSubject.getTextualIcon().equals("+")))||
//					(changedSubject instanceof GraphicalGridHorizontalTransport)||
//					(changedSubject instanceof GraphicalGridTPathTransport)||
//					(changedSubject instanceof GraphicalGridEquiplet)
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
//				else if(callFromDirection.equals("NW")){
//					NWDC = false;
//				}
//				else if(callFromDirection.equals("SW")){
//					SWDC = false;
//				}
//				else if(callFromDirection.equals("NE")){
//					NEDC = false;
//				}
//				else if(callFromDirection.equals("SE")){
//					SEDC = false;
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
//		return returnVal;
//	}
//	
//	@Override
//	protected void verifyIcon() {
//		ImageIcon tmpImageIcon = this.getImageIcon();
//		if(NDC){
//			if (SDC){
//				tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalNC.png"), 90);
//			}
//			else{
//				tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalWNC.png"), 90);
//			}
//		}
//		else if (SDC){
//			tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/horizontalWNC.png"), -90);
//		}
//		else{
//			tmpImageIcon = new ImageIcon("img/grid_icons/vertical.png");
//		}
//		
//		this.setImageIcon(tmpImageIcon);
//	}
}
