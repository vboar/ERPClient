package ui.presentui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class PresentDocumentPanel extends JPanel{
	
	protected Image bg;
	
	protected MyButton addBtn;	
	protected MyButton deleteBtn;	
	protected MyButton commitBtn;	
	protected MyButton cancelBtn;	
	protected MyButton addCustomer;
	
	protected MyLabel documentId;	
	protected MyLabel customerIdLab;	
	protected MyLabel customerNameLab;
	
	protected PresentCommodityTablePane presentTable;
	
	protected MySpecialTextField customerTxt;
	
	protected PanelConfig cfg;
	
	protected JFrame frame;
	
	public PresentDocumentPanel(JFrame frame){
		this.frame = frame;
	}

	abstract protected void initComponent();
	
}
