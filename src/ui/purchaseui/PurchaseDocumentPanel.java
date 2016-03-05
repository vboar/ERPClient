package ui.purchaseui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.CommodityTablePane;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import ui.util.MyTextArea;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class PurchaseDocumentPanel extends JPanel{
	
	protected Image bg;

	protected MyButton addBtn;
	protected MyButton deleteBtn;
	protected MyButton addCustomer;

	protected MyLabel documentId;
	protected MyLabel customerIdLab;
	protected MyLabel customerNameLab;
	protected MyLabel totalLab;
	protected MyLabel tip;

	protected MyComboBox storage;

	protected MySpecialTextField customerTxt;
	protected MyTextArea remarkTxt;

	protected CommodityTablePane commodityTable;

	protected JFrame frame;

	protected PanelConfig cfg;
	
	protected boolean isreturn;
	
	public PurchaseDocumentPanel(JFrame frame, boolean isreturn){
		this.frame = frame;
		this.isreturn = isreturn;
	}
	
	abstract protected void initComponent();
}
