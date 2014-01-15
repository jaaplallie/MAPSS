package GraphicalGridBuilder.gridTypes;


public class BackSlash_Transport extends Super_Obj {
	
	public BackSlash_Transport(String inputTransportFlow){
		textualIcon = "/";
		setImageIcon(inputTransportFlow);
		this.transportFlow = inputTransportFlow;
		init();
	}

	public void init() {
		this.setImageIcon("img/grid_icons/backslash.png");
		
		if(!this.transportFlow.equals("")){
			System.out.println("img/grid_icons/backslash" + this.transportFlow + ".png");
			this.setImageIcon("img/grid_icons/backslash" + this.transportFlow + ".png");
		}
//		if(this.transportFlow.equals("t-b")){
//			icon = rotateIcon(icon, 3);
//		}
//		else if(this.transportFlow.equals("b-t")){
//			icon = rotateIcon(icon, 1);
//		}
//		else{
//			icon = super.rotateIcon(this.setImageIcon("img/grid_icons/slash.png"), 1);
//		}
	}
}
