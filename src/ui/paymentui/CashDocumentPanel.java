package ui.paymentui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class CashDocumentPanel extends JPanel {
	
	protected MyLabel operatorLab;
	protected MyLabel documentIdLab;
	protected MyLabel accountNameLab;
	protected MyLabel accountIdLab;
	protected MyLabel totalLab;

	protected MyButton addAccountBtn;
	protected MyButton addBtn;
	protected MyButton deleteBtn;

	protected MySpecialTextField accountFind;
	protected CashLineItemTable table;

	protected JFrame frame;

	protected PanelConfig cfg;
	
	public CashDocumentPanel(JFrame frame){
		this.frame = frame;
	}
	
	abstract protected void initComponent();
}
