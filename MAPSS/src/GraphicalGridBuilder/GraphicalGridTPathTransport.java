package GraphicalGridBuilder;

public class GraphicalGridTPathTransport extends GraphicalGridObject {

	Character arm;
	
	public GraphicalGridTPathTransport(int y, int x, int y_max){
		if(y == 0){
			arm = 'b';
			this.setTextualIcon("V");
			this.setImageIcon("img/grid_icons/tbottom.png");
		}
		else if(x == 0){
			arm = 'r';
			this.setTextualIcon("|-");
			this.setImageIcon("img/grid_icons/tright.png");
		}
		else if(y == (y_max-1)){
			arm = 't';
			this.setTextualIcon("^");
			this.setImageIcon("img/grid_icons/ttop.png");
		}
		else{
			arm = 'l';
			this.setTextualIcon("-|");
			this.setImageIcon("img/grid_icons/tleft.png");
		}
	}
}
