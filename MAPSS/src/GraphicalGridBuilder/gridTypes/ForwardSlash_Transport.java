package GraphicalGridBuilder.gridTypes;


public class ForwardSlash_Transport extends SuperType_Obj {
	
	public ForwardSlash_Transport(String inputTransportFlow){
		textualIcon = "\\";
		this.setImageIcon("img/grid_icons/forwardslash.png");	
		this.transportFlow = inputTransportFlow;
		init();
	}
	
	public void init() {
		this.setImageIcon("img/grid_icons/forwardslash.png");
		
		if(!this.transportFlow.equals("")){
			System.out.println("img/grid_icons/forwardslash" + this.transportFlow + ".png");
			this.setImageIcon("img/grid_icons/forwardslash" + this.transportFlow + ".png");
		}
	}
}
