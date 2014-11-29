package ui.commodityui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.commodityui.categoryui.CategoryPanel;
import ui.commodityui.commodityui.CommodityPanel;
import ui.homeui.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CommodityButtonPanel extends JPanel{

	private MyButton categoryManageBtn;

	private MyButton commodityManageBtn;
	
	private MyButton presentBtn;
	
	private MyButton stockBtn;
	
	private MyButton stockCheckBtn;
	
	private MyButton exceptionBtn;
	
	private PanelConfig pcfg;
	
	private MyLabel title;
	
	private Image bg;
	
	private HomeUI frame;
	
	public CommodityButtonPanel(HomeUI frame){
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
				frame.add(new CategoryPanel(frame));
			}
		});
		this.commodityManageBtn = new MyButton(pcfg.getButtons().element("commodity"));
		this.commodityManageBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.add(new CommodityPanel(frame));
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
