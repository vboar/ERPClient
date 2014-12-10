package ui.purchaseui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyTabbedPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class PurchaseMainPanel extends JPanel {
	
	private PurchasePanel purchasepanel;
	
	private PurchaseReturnPanel returnpanel;
	
	private MyTabbedPane tabPane;
	
	private JFrame frame;
	
	private Image bg;
	
	private PanelConfig cfg;
	
	public PurchaseMainPanel(JFrame frame){
    	this.frame = frame;
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(),cfg.getH(),null);
	}
	
	private void initComponent() {
		this.purchasepanel = new PurchasePanel(frame);
		this.returnpanel = new PurchaseReturnPanel(frame);
    	this.tabPane = new MyTabbedPane();
    	this.tabPane.setBounds(0, 0, this.getWidth(), this.getHeight());
    	this.tabPane.add(this.purchasepanel,"  进 货  ");
    	this.tabPane.add(this.returnpanel,"进货退货");
    	this.add(this.tabPane);
	}
	

}
