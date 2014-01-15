package GraphicalGridBuilder.gridTypes;


public class Crossed_Transport extends Super_Obj {
	
	public Crossed_Transport(String inputTransportFlow){
		textualIcon = "X";
		this.setImageIcon("img/grid_icons/crossedslash.png");		
		this.transportFlow = inputTransportFlow;
		init();
	}
	
	public Crossed_Transport(String s, String inputTransportFlow){
		if(s.equals("+")){
			textualIcon = "+";
			this.setImageIcon("img/grid_icons/crossedplus.png");
		}
		else{
			textualIcon = "X";
			this.setImageIcon("img/grid_icons/crossedslash.png");
		}		
		this.transportFlow = inputTransportFlow;
		init();
	}
	
	public void init() {
		this.setImageIcon("img/grid_icons/crossedslash.png");
		
		if(!this.transportFlow.equals("")){
			System.out.println("img/grid_icons/crossedslash" + this.transportFlow + ".png");
			this.setImageIcon("img/grid_icons/crossedslash" + this.transportFlow + ".png");
		}
	}
}
