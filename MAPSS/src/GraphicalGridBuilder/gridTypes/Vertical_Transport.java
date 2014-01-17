package GraphicalGridBuilder.gridTypes;


public class Vertical_Transport extends SuperType_Obj {
	
	public Vertical_Transport(String inputTransportFlow){
		textualIcon = "|";
		this.setImageIcon("img/grid_icons/vertical.png");		
		this.transportFlow = inputTransportFlow;
		init();
	}
	
	public void init() {
		this.setImageIcon("img/grid_icons/vertical.png");
		
		if(!this.transportFlow.equals("")){
			System.out.println("img/grid_icons/vertical" + this.transportFlow + ".png");
			this.setImageIcon("img/grid_icons/vertical" + this.transportFlow + ".png");
		}
	}
}
