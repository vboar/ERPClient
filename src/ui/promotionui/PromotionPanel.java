package ui.promotionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class PromotionPanel extends JPanel {

	private MyButton totalGiftBtn;
	
	private MyButton customerBtn;
	
	private MyButton specialOfferBtn;
	
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
		this.totalPanel = new TotalGiftPanel(frame);
		this.customerPanel = new CustomerPanel(frame);
		this.specialPanel = new SpecialOfferPanel(frame);
		this.customerBtn = new MyButton(cfg.getButtons().element("customer"));
		this.customerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setPanelVisible(0);
			}
		});
		this.totalGiftBtn = new MyButton(cfg.getButtons().element("totalgift"));
		this.totalGiftBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setPanelVisible(1);
			}
		});
		this.specialOfferBtn = new MyButton(cfg.getButtons().element("specialoffer"));
		this.specialOfferBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setPanelVisible(2);
			}
		});
		this.add(new MyLabel(cfg.getLabels().element("title")));
		this.add(this.totalGiftBtn);
		this.add(this.customerBtn);
		this.add(this.specialOfferBtn);
		this.add(this.totalPanel);
		this.add(this.customerPanel);
		this.add(this.specialPanel);
	}

	private void setPanelVisible(int i){
		switch(i){
		case 0:
			this.customerPanel.setVisible(true);
			this.totalPanel.setVisible(false);
			this.specialPanel.setVisible(false);
			break;
		case 1:
			this.customerPanel.setVisible(false);
			this.totalPanel.setVisible(true);
			this.specialPanel.setVisible(false);
			break;
		case 2:
			this.customerPanel.setVisible(false);
			this.totalPanel.setVisible(false);
			this.specialPanel.setVisible(true);
		}
	}
	
}
