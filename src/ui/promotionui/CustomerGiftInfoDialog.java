package ui.promotionui;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.commodityui.commodityui.CommodityTreePane;
import ui.util.MyCheckBox;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import config.DialogConfig;

public class CustomerGiftInfoDialog extends JDialog{

	private MyComboBox level;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyCheckBox presents;
	
	private MyCheckBox discount;
	
	private MyCheckBox voucher;
	
	private DialogConfig cfg;
	
	private boolean isAdd = false;
	
	public CustomerGiftInfoDialog(DialogConfig cfg, JFrame frame, CommodityTreePane tree, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.isAdd = isAdd;
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()+ this.cfg.getY());
		this.initComponent();
	}

	private void initComponent() {
		
	}
	
}
