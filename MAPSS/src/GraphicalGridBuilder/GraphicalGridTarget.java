package GraphicalGridBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GraphicalGridTarget{

	GraphicalGridObject[] targetPossibilities = new GraphicalGridObject[0];
	int currentlyDisplayedPossibility = -1;
	
	public GraphicalGridTarget(GraphicalGridObject[] possibilities){
		targetPossibilities = possibilities;
	}
	
	public JButton getButton(){
		final JButton returnButton = new JButton("");
		returnButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((currentlyDisplayedPossibility < 0 && targetPossibilities.length > 0) || (currentlyDisplayedPossibility == targetPossibilities.length)){
					currentlyDisplayedPossibility = 0;
					returnButton.setText(targetPossibilities[currentlyDisplayedPossibility].getTextualIcon());
				}
				else if(currentlyDisplayedPossibility < targetPossibilities.length){
					currentlyDisplayedPossibility++;
					returnButton.setText(targetPossibilities[currentlyDisplayedPossibility].getTextualIcon());
				}
				else{
					targetPossibilities = new GraphicalGridObject[1];
					targetPossibilities[0] = new GraphicalGridNothingObject();
					returnButton.setText(targetPossibilities[0].getTextualIcon());
				}
			}	
		});
		return returnButton;
	}	
}
