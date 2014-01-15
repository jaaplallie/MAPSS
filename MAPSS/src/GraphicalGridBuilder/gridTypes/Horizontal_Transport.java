package GraphicalGridBuilder.gridTypes;


public class Horizontal_Transport extends Super_Obj {
	
	public Horizontal_Transport(String inputTransportFlow){
		textualIcon = "-";
		this.setImageIcon("img/grid_icons/horizontal.png");
		this.transportFlow = inputTransportFlow;
		init();
	}
	
	public void init() {
		this.setImageIcon("img/grid_icons/horizontal.png");
		
		if(!this.transportFlow.equals("")){
			System.out.println("img/grid_icons/horizontal" + this.transportFlow + ".png");
			this.setImageIcon("img/grid_icons/horizontal" + this.transportFlow + ".png");
		}
	}
}
