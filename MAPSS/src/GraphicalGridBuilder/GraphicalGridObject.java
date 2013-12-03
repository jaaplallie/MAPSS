package GraphicalGridBuilder;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public abstract class GraphicalGridObject {
	
	String textualIcon = " ";
	ImageIcon imageIcon = new ImageIcon();
	String textualInputRepresentation = "";
	Boolean connected;
	Boolean NDC, NEDC, EDC, SEDC, SDC, SWDC, WDC, NWDC; //Region Connected Booleans (for icons)

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
	
	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	
	protected void setTextualInputRepresentation(String tir){
		textualInputRepresentation = tir;
	}
	
	public String getTextualInputRepresentation(){
		return textualInputRepresentation;
	}
	
	public boolean checkConnected(String callFromDirection, GraphicalGridObject changedSubject){
		return false;
	}
	
	public void setIconDisconnected(String regionToDisconnect, Boolean value){
		switch(regionToDisconnect.toUpperCase()){
			case "N": NDC = value; break;
			case "NE": NEDC = value; break;
			case "E": EDC = value; break;
			case "SE": SEDC = value; break;
			case "S": SDC = value; break;
			case "SW": SWDC = value; break;
			case "W": WDC = value; break;
			case "NW": NWDC = value; break;
			default: System.out.println("Region not recognized, can't (dis)connect the unrecognized region."); break;
		}
		verifyIcon();
	}
	
	protected void verifyIcon(){
	}
	
	public ImageIcon rotateIcon(ImageIcon image, double degrees) {
		BufferedImage buffered = (BufferedImage) image.getImage();
		Double angle = Math.toRadians(degrees);
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = buffered.getWidth(), h = buffered.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(buffered, null);
	    g.dispose();
	    return new ImageIcon(result);
	}
}
