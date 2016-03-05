package ui.saleui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.CommodityTablePane;
import ui.util.MyButton;
import ui.util.MyCheckBox;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import ui.util.MyTextArea;
import ui.util.MyTextField;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class SaleDocumentPanel extends JPanel{
	
	protected Image bg;
	
	protected MyButton addBtn;
	protected MyButton deleteBtn;	
	protected MyButton addCustomer;
	protected MyButton showPromotions;
	
	protected MyLabel documentId;
	protected MyLabel customerIdLab;
	protected MyLabel customerNameLab;
	protected MyLabel totalBeforeDiscountLab;
	protected MyLabel totalLab;
	protected MyLabel discountLab;	
	protected MyLabel promotionLab;
	
	protected MyComboBox storage;	
	protected MyComboBox salesman;
	
	protected MyCheckBox voucherBox;
	
	protected MySpecialTextField customerTxt;	
	protected MyTextField voucherTxt;	
	protected MyTextArea remarkTxt;

	protected CommodityTablePane commodityTable;
	
	protected boolean isreturn;

	protected JFrame frame;
	
	protected PanelConfig cfg;
	
	public SaleDocumentPanel(JFrame frame, boolean isreturn){
		this.frame = frame;
		this.isreturn = isreturn;
	}
}
