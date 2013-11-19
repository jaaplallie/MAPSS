package GraphicalGridBuilder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphicalGridTarget{

	JPanel targetContainer;
	JButton returnButton;
	GraphicalGridObject[] targetPossibilities = new GraphicalGridObject[0];
	int currentlyDisplayedPossibility = 0;
	
	public GraphicalGridTarget(GraphicalGridObject[] possibilities){
		targetPossibilities = possibilities;
		targetContainer = new JPanel(new BorderLayout());
		returnButton = new JButton("");
		returnButton.setIcon(targetPossibilities[currentlyDisplayedPossibility].getImageIcon());
		returnButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(currentlyDisplayedPossibility == (targetPossibilities.length-1)){
					currentlyDisplayedPossibility = 0;
				}
				else{
					currentlyDisplayedPossibility++;
				}
				returnButton.setIcon(targetPossibilities[currentlyDisplayedPossibility].getImageIcon());
			}	
		});
		if(targetPossibilities.length == 1){
			returnButton.setEnabled(false);
		}
		targetContainer.add(returnButton);
	}
	
	public JPanel getButton(){
		return targetContainer;
	}
	
	public GraphicalGridObject getInput(){
		return targetPossibilities[currentlyDisplayedPossibility];
	}
	
	public String getPossibilitiesString(){
		String returnVal = "";
		
		for(int i = 0; i < targetPossibilities.length; i++){
			returnVal += targetPossibilities[i].getTextualIcon();
			if(targetPossibilities.length > 1 && i != targetPossibilities.length){
				returnVal += ",";
			}
		}
		
		return returnVal;
	}
}
