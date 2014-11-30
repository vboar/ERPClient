package ui.commodityui.commodityui;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CommodityInfoDialog extends JDialog {
	
	private CommodityPanel panel;
	
	private InfoDialogConfig cfg;
	
	private boolean isAdd;

	public CommodityInfoDialog(InfoDialogConfig cfg, JFrame frame, CommodityPanel panel, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle("商品信息");
		this.isAdd = isAdd;
		this.panel = panel;
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()
				+ this.cfg.getY());
		this.initComponent();

	}

	private void initComponent() {
		this.initButtons(this.cfg.getButtons());
		
	}
	
	private void initButtons(Element buttons) {
		// TODO Auto-generated method stub
		
	}


}
