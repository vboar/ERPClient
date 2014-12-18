package ui.paymentui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class PaymentDocumentPanel extends JPanel{
	
	protected Image bg;
	
	protected MyLabel operatorLab;
	protected MyLabel documentIdLab;
	protected MyLabel customerIdLab;
	protected MyLabel customerNameLab;

	protected MyButton addCustomerBtn;
	protected MyButton addBtn;
	protected MyButton deleteBtn;

	protected MySpecialTextField customerFind;

	protected PaymentLineItemTable table;
	protected JFrame frame;
	
	protected PanelConfig cfg;
	
	public PaymentDocumentPanel(JFrame frame){
		this.frame = frame;
	}
	
	abstract protected void initComponent();

}
