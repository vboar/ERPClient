package ui.commodityui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CategoryPanel extends JPanel{
	
	private JFrame homeframe;
	
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
	}

}
