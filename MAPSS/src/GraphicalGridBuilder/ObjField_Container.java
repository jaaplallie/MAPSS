package GraphicalGridBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GraphicalGridBuilder.gridTypes.SuperType_Obj;

public class ObjField_Container{

	JButton fieldButton;
	SuperType_Obj[] typePossibilities = new SuperType_Obj[0];
	int currentlyDisplayedTypePossibility = 0;
	
	public ObjField_Container(SuperType_Obj[] possibilities){
		typePossibilities = possibilities;
		fieldButton = new JButton("");
		fieldButton.setIcon(typePossibilities[currentlyDisplayedTypePossibility].getImageIcon());
		fieldButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(currentlyDisplayedTypePossibility == (typePossibilities.length-1)){
					currentlyDisplayedTypePossibility = 0;
				}
				else{
					currentlyDisplayedTypePossibility++;
				}
				fieldButton.setIcon(typePossibilities[currentlyDisplayedTypePossibility].getImageIcon());
			}	
		});
//		if(targetPossibilities.length == 1){
//			returnButton.setEnabled(false);
//		}
	}
	
	public JButton getButton(){
		return fieldButton;
	}
	
	public SuperType_Obj getInputObject(){
		return typePossibilities[currentlyDisplayedTypePossibility];
	}
	
	public void setToEmptySelection(){
		currentlyDisplayedTypePossibility =  typePossibilities.length-1;
		updateButton();
	}
	
	public void setToDefaultSelection(){
		currentlyDisplayedTypePossibility =  0;
		updateButton();
	}
	
	public String getPossibilitiesString(){
		String returnVal = "";
		
		for(int i = 0; i < typePossibilities.length; i++){
			returnVal += typePossibilities[i].getTextualIcon();
			if(typePossibilities.length > 1 && i != typePossibilities.length){
				returnVal += ",";
			}
		}
		
		return returnVal;
	}
	
	protected void updateButton(){
//		targetPossibilities[currentlyDisplayedPossibility].verifyIcon();
		fieldButton.setIcon(typePossibilities[currentlyDisplayedTypePossibility].getImageIcon());
	}
}
