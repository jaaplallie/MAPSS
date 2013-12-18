package GraphicalGridBuilder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

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
	
//	public ImageIcon rotateIcon(ImageIcon image, double degrees) {
//		BufferedImage buffered = (BufferedImage) image.getImage();
//		Double angle = Math.toRadians(degrees);
//	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
//	    int w = buffered.getWidth(), h = buffered.getHeight();
//	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
//	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//	    GraphicsConfiguration gc = gd.getDefaultConfiguration();
//	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
//	    Graphics2D g = result.createGraphics();
//	    g.translate((neww-w)/2, (newh-h)/2);
//	    g.rotate(angle, w/2, h/2);
//	    g.drawRenderedImage(buffered, null);
//	    g.dispose();
//	    return new ImageIcon(result);
//	}
	
	
	public ImageIcon rotateIcon(ImageIcon icon, double angle) {
		ImageIcon returnIcon = new ImageIcon();
		Image image = icon.getImage();
		//BufferedImage bi = new BufferedImage(image.getWidth(icon.getImageObserver()), image.getHeight(icon.getImageObserver()), BufferedImage.TYPE_INT_RGB);
		BufferedImage bi = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bi.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();
		
	    AffineTransform at = new AffineTransform();
	    at.rotate(Math.toRadians(angle), bi.getWidth() / 2.0, bi.getHeight() / 2.0);
	    at.preConcatenate(findTranslation(at, bi, angle));

	    BufferedImageOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    //op.filter(bi, null);
	    returnIcon.setImage((Image) op.filter(bi, null));
	    return returnIcon;
	}

	private AffineTransform findTranslation(
	                        AffineTransform at, BufferedImage bi, double angle) {
	    Point2D p2din = null, p2dout = null;

	    if (angle > 0 && angle <= 90) {
	        p2din = new Point2D.Double(0, 0);
	    } else if (angle < 0 && angle >= -90) {
	        p2din = new Point2D.Double(bi.getWidth(), 0);
	    }
	    p2dout = at.transform(p2din, null);
	    double ytrans = p2dout.getY();

	    if (angle > 0 && angle <= 90) {
	        p2din = new Point2D.Double(0, bi.getHeight());
	    } else if (angle < 0 && angle >= -90) {
	        p2din = new Point2D.Double(0, 0);
	    }
	    p2dout = at.transform(p2din, null);
	    double xtrans = p2dout.getX();

	    AffineTransform tat = new AffineTransform();
	    tat.translate(-xtrans, -ytrans);
	    return tat;
	}
}
