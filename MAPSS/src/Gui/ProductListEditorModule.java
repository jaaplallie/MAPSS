package Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import Backend.ProgramData;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class ProductListEditorModule extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6622313351868642228L;
	
	DefaultListModel productListModel = new DefaultListModel();
	JList productList = new JList(productListModel);
	JScrollPane productListScrollPane = new JScrollPane(productList);
	JPanel productFormContainer = new JPanel();
	
	public ProductListEditorModule() {
		setLayout(new BorderLayout(0, 0));
		
		productListModel.addElement("Product 1");
		productListModel.addElement("Product 2");
		productListModel.addElement("Product 3");
		productListModel.addElement("Product 4");
		productListModel.addElement("Product 5");
		productListModel.addElement("Product 6");
		productListModel.addElement("Product 7");
		productListModel.addElement("Product 8");
		
		productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		productList.setVisibleRowCount(-1);
		
		productListScrollPane.setPreferredSize(new Dimension(100, 150));
		add(productListScrollPane, BorderLayout.WEST);
		productFormContainer.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("200px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		add(productFormContainer, BorderLayout.EAST);
		
		JButton button = new JButton("New product");
		productFormContainer.add(button, "2, 2, left, top");
		JButton button_1 = new JButton("Edit Selected product");
		productFormContainer.add(button_1, "2, 4, left, top");
		JButton button_2 = new JButton("Delete Selected product");
		productFormContainer.add(button_2, "2, 6, left, top");
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (productList.getSelectedIndex() == -1) {
	        //No selection

	        } else {
	        //Selection
	        	//list.getSelectedIndex() = selected index
	        }
	    }
	}
}
