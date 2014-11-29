package ui.homeui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.commodityui.categoryui.CategoryPanel;
import ui.commodityui.commodityui.CommodityPanel;
import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class StockKeeperPanel extends JPanel{

	private MyButton categoryManageBtn;

	private MyButton commodityManageBtn;
	
	private MyButton presentBtn;
	
	private MyButton stockBtn;
	
	private MyButton stockCheckBtn;
	
	private MyButton exceptionBtn;
	
	private CommodityPanel commodityPanel;
	
	private CategoryPanel categoryPanel;
	
	private PanelConfig pcfg;
	
	private MyLabel title;
	
	private Image bg;
	
	private HomeUI frame;
	
	public StockKeeperPanel(HomeUI frame){
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.repaint();
	}

	private void initComponent() {
		this.title = new MyLabel(pcfg.getLabels().element("title"));
		this.add(this.title);
		this.categoryManageBtn = new MyButton(pcfg.getButtons().element("category"));
		this.categoryManageBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(categoryPanel == null) {
					categoryPanel = new CategoryPanel(frame);
					add(categoryPanel);
				}
				if(commodityPanel != null) {
					remove(commodityPanel);
					commodityPanel = null;
				}
				repaint();
			}
		});
		this.commodityManageBtn = new MyButton(pcfg.getButtons().element("commodity"));
		this.commodityManageBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(commodityPanel == null) {
					commodityPanel = new CommodityPanel(frame);
					add(commodityPanel);
				}
				if(categoryPanel != null) {
					remove(categoryPanel);
					categoryPanel = null;
				}
				repaint();
				
			}
			
		});
		this.presentBtn = new MyButton(pcfg.getButtons().element("present"));
		this.stockBtn = new MyButton(pcfg.getButtons().element("stock"));
		this.stockCheckBtn = new MyButton(pcfg.getButtons().element("stockcheck"));
		this.exceptionBtn = new MyButton(pcfg.getButtons().element("exception"));
		this.add(this.categoryManageBtn);
		this.add(this.commodityManageBtn);
		this.add(this.presentBtn);
		this.add(this.stockBtn);
		this.add(this.stockCheckBtn);
		this.add(this.exceptionBtn);

	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
}
