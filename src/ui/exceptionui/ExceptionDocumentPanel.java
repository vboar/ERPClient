package ui.exceptionui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class ExceptionDocumentPanel extends JPanel{

	protected MyButton addBtn;
	
	protected MyButton deleteBtn;
	
	protected MyButton commitBtn;
	
	protected MyButton cancelBtn;
	
	protected MyLabel documentId;
	
	protected ExceptionTablePane tablepane;
	
	protected PanelConfig cfg;
	
	protected Image bg;
	
	protected JFrame frame;
	
	public ExceptionDocumentPanel(JFrame frame){
		this.frame = frame;
	}
	
	abstract protected void initComponent();
}
