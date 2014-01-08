package GraphicalGridBuilder.gridTypes;


public class ForwardSlash_Transport extends Super_Obj {
	
	public ForwardSlash_Transport(){
		textualIcon = "\\";
		this.setImageIcon("img/grid_icons/forwardslash.png");
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
//		if(callFromDirection.equals("NW") || callFromDirection.equals("SE")){
//			if(
//				(changedSubject instanceof GraphicalGridCrossedTransport && (changedSubject.getTextualIcon().equals("X") || changedSubject.getTextualIcon().equals("x")))||
//				(changedSubject instanceof GraphicalGridForwardSlashTransport)||
//				(changedSubject instanceof GraphicalGridEquiplet)
//				
//			){
//				returnVal = true;
//				if(callFromDirection.equals("NW")){
//					NWDC = false;
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
//		
//		return returnVal;
//	}
//	
//	@Override
//	protected void verifyIcon() {
//		ImageIcon tmpImageIcon = this.getImageIcon();
//		
//		if(NWDC){
//			if (SEDC){
//				tmpImageIcon = new ImageIcon("img/grid_icons/forwardslashNC.png");
//			}
//			else{
//				tmpImageIcon = new ImageIcon("img/grid_icons/forwardslashNWNC.png");
//			}
//		}
//		else if (SEDC){
//			tmpImageIcon = this.rotateIcon(new ImageIcon("img/grid_icons/forwardslashNWNC.png"), 90);
//			tmpImageIcon = this.rotateIcon(tmpImageIcon, 90);
//		}
//		else{
//			tmpImageIcon = new ImageIcon("img/grid_icons/forwardslash.png");
//		}
//		
//		this.setImageIcon(tmpImageIcon);
//	}
}
