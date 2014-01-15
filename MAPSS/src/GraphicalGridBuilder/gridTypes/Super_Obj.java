package GraphicalGridBuilder.gridTypes;

import javax.swing.ImageIcon;

public abstract class Super_Obj {
	
	String textualIcon = " ";
	ImageIcon imageIcon = new ImageIcon();
	String textualInputRepresentation = "";
	String transportFlow = "Not Implemented or Overridden.";
	
	
	public Super_Obj(){
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

	public ImageIcon setImageIcon(String imageIcon) {
		this.imageIcon = new ImageIcon(imageIcon);
		return this.imageIcon;
	}
	
	public ImageIcon setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
		return this.imageIcon;
	}
	
	protected void setTextualInputRepresentation(String tir){
		textualInputRepresentation = tir;
	}
	
	public String getTextualInputRepresentation(){
		return textualInputRepresentation;
	}
	
	public String getTransportFlow(){
		return transportFlow;
	}	
	
//	public ImageIcon rotateIcon(ImageIcon icon, int rotation) {
//		double rotationAngle = 90;
//		
//		if(rotation == 1 || rotation >= 1){
//			rotationAngle = 90;
//		}
//		
//		ImageIcon returnIcon = new ImageIcon();
//		Image image = icon.getImage();
//		BufferedImage bi = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
//		Graphics bg = bi.getGraphics();
//		bg.drawImage(image, 0, 0, null);
//		bg.dispose();
//		
//	    AffineTransform at = new AffineTransform();
//	    at.rotate(Math.toRadians(rotationAngle), bi.getWidth() / 2.0, bi.getHeight() / 2.0);
//	    at.preConcatenate(findTranslation(at, bi, rotationAngle));
//
//	    BufferedImageOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//	    //op.filter(bi, null);
//	    returnIcon.setImage((Image) op.filter(bi, null));
//	    
//	    if(rotation == 2){
//			returnIcon = rotateIcon(returnIcon, 1);
//		}
//		else if(rotation == 3){
//			returnIcon = rotateIcon(returnIcon, 2);
//		}
//		else{
//			return icon;
//		}
//	    
//	    return returnIcon;
//	}
//
//	private AffineTransform findTranslation(AffineTransform at, BufferedImage bi, double angle) {
//	    Point2D p2din = null, p2dout = null;
//
//	    if (angle > 0 && angle <= 90) {
//	        p2din = new Point2D.Double(0, 0);
//	    } else if (angle < 0 && angle >= -90) {
//	        p2din = new Point2D.Double(bi.getWidth(), 0);
//	    }
//	    p2dout = at.transform(p2din, null);
//	    double ytrans = p2dout.getY();
//
//	    if (angle > 0 && angle <= 90) {
//	        p2din = new Point2D.Double(0, bi.getHeight());
//	    } else if (angle < 0 && angle >= -90) {
//	        p2din = new Point2D.Double(0, 0);
//	    }
//	    p2dout = at.transform(p2din, null);
//	    double xtrans = p2dout.getX();
//
//	    AffineTransform tat = new AffineTransform();
//	    tat.translate(-xtrans, -ytrans);
//	    return tat;
//	}
}
