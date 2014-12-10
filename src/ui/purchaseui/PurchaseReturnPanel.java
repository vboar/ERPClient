package ui.purchaseui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.ERPConfig;
import config.PanelConfig;

public class PurchaseReturnPanel extends JPanel{

	private PanelConfig cfg;
	
	private JFrame frame;
	
    public PurchaseReturnPanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基本属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
    }

	private void initComponent() {
		// TODO Auto-generated method stub
		
	}
	
}
