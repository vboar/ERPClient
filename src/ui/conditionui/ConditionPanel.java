package ui.conditionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyTabbedPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class ConditionPanel extends JPanel {

	private HistoryPanel historyPanel;
	
	private SaleDetailsPanel detailsPanel;
	
	private BusinessConditionPanel conditionPanel;
	
	private MyTabbedPane tabPane;
	
	private JFrame frame;
	
	private Image bg;
	
	private PanelConfig cfg;
	
    public ConditionPanel(JFrame frame) {
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
    	this.detailsPanel = new SaleDetailsPanel(frame);
    	this.historyPanel = new HistoryPanel(frame);
    	this.conditionPanel = new BusinessConditionPanel();
    	this.tabPane = new MyTabbedPane();
    	this.tabPane.setBounds(0, 32, this.getWidth(), this.getHeight()-32);
    	this.tabPane.add(this.detailsPanel,"销售明细表");
    	this.tabPane.add(this.historyPanel,"经营历程表");
    	this.tabPane.add(this.conditionPanel,"经营情况表");
    	this.add(this.tabPane);
	}

}
