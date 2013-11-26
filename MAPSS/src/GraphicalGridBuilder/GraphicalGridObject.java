package GraphicalGridBuilder;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class GraphicalGridObject {
	
	String textualIcon = " ";
	ImageIcon imageIcon = new ImageIcon();
	String textualInputRepresentation = "";
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
	
	@SuppressWarnings("unused") //is used in subclasses
	private void setTextualInputRepresentation(String tir){
		textualInputRepresentation = tir;
	}
	
	public String getTextualInputRepresentation(){
		return textualInputRepresentation;
	}
}
