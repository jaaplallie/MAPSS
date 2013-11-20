package Gui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class CreateTopologieModule extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106768618928241729L;

	/**
	 * Create the panel.
	 */
	public CreateTopologieModule() {
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JPanel graphicalGridPanel = new JPanel();
		splitPane.setLeftComponent(graphicalGridPanel);
		
		JPanel editorPanel = new JPanel();
		splitPane.setRightComponent(editorPanel);
	}

}
