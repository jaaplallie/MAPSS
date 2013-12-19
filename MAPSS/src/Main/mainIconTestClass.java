package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Backend.ProgramData;

public class mainIconTestClass implements WindowListener, ActionListener{
	
	JFrame frame = new JFrame();
	JButton button;
	
	public mainIconTestClass() {
		super();
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1031, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(this);
		
		button = new JButton();
		button.setIcon(new ImageIcon("img/grid_icons/horizontal.png"));
		button.addActionListener(this);
		frame.getContentPane().add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ImageIcon icon = (ImageIcon) button.getIcon();
		icon = rotateIcon(icon, 90);
		button.setIcon(icon);
	}

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

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}