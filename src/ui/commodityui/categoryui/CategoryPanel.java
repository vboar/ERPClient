package ui.commodityui.categoryui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CategoryPanel extends JPanel{
	
	private JFrame homeframe;
	
	private MyLabel title;
	
	private MyLabel category;
	
	private MyLabel tip;
	
	private PanelConfig pcfg;
	
	private CategoryTreePane treepane;
	
	private Image bg;
		
	public CategoryPanel(JFrame frame){
		this.homeframe = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
		this.setVisible(true);
	}

	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(PanelConfig pcfg) {
		this.treepane = new CategoryTreePane(pcfg.getTree(),homeframe);
		this.add(this.treepane);
		this.title = new MyLabel(pcfg.getLabels().element("title"));
		this.category = new MyLabel(pcfg.getLabels().element("category"));
		this.tip = new MyLabel(pcfg.getLabels().element("tip"));
		this.add(this.title);
		this.add(this.category);
		this.add(this.tip);
	}

}
