package GraphicalGridBuilder.gridTypes;

public class Nothing_Obj extends Equiplet_Obj{

	Boolean canBeEq = false;
	
	public Nothing_Obj(Boolean canBeEquiplet){
		textualIcon = " ";
		this.setImageIcon("img/grid_icons/nothing.png");
		canBeEq = canBeEquiplet;
	}
	
	public Boolean canBeEquiplet(){
		return canBeEq;
	}
}
