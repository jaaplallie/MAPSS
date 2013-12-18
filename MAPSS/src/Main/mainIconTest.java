package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Gui.MainWindow;

public class mainIconTest implements ActionListener{

	static JButton button = new JButton();
	static ImageIcon buttonIcon = new ImageIcon("img/grid_icons/horizontal.png");
	
	public static void main(String[] args){
		mainIconTestClass window = new mainIconTestClass();
		window.frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button)){
			
		}
	}
}
