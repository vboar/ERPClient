package ui.promotionui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyLabel;
import ui.util.MyTabbedPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class PromotionPanel extends JPanel {
	
	private MyTabbedPane tabpanel;
	
	private TotalGiftPanel totalPanel;
	
	private SpecialOfferPanel specialPanel;
	
	private CustomerPanel customerPanel;
	
	private Image bg;
	
	private PanelConfig cfg;
	
	private JFrame frame;
	
    public PromotionPanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = cfg.getBg();
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
    }

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(),cfg.getH(),null);
	}
	
	private void initComponent() {
		this.add(new MyLabel(cfg.getLabels().element("title")));
		this.tabpanel = new MyTabbedPane();
    	this.tabpanel = new MyTabbedPane();
    	this.tabpanel.setBounds(0, 32, this.getWidth(), this.getHeight()-32);
		this.totalPanel = new TotalGiftPanel(frame);
		this.customerPanel = new CustomerPanel(frame);
		this.specialPanel = new SpecialOfferPanel(frame);
		this.tabpanel.add(this.customerPanel,"针对客户界别的促销策略");
		this.tabpanel.add(this.totalPanel,"针对总价的促销策略");
		this.tabpanel.add(this.specialPanel,"特价包策略");
		this.add(tabpanel);
	}
	
}
