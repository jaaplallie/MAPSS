package GraphicalGridBuilder;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class GraphicalGridObject {
	
	String textualIcon = " ";
	ImageIcon imageIcon = new ImageIcon();
	//"img/grid_icons/add.png"
	//addBtn.setIcon(icon); 
	
	public GraphicalGridObject(){
	}

	public String getTextualIcon() {
		return textualIcon;
	}

	public void setTextualIcon(String textualIcon) {
		this.textualIcon = textualIcon;
	}
	
	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(String imageIcon) {
		this.imageIcon = new ImageIcon(imageIcon);
	}
}
