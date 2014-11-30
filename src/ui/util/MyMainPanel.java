/**
 *  中间层面板父类
 *  @author Vboar
 *  @date 2014/11/30
 */
package ui.util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ui.homeui.HomeUI;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public abstract class MyMainPanel extends JPanel {
	
	public PanelConfig pcfg;
	
	private MyLabel title;
	
	private Image bg;
	
	public HomeUI frame;
	
	public MyMainPanel(HomeUI frame) {
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.repaint();
	}

	public void initComponent() {
		this.title = new MyLabel(pcfg.getLabels().element("title"));
		this.add(this.title);
	}
	
	public abstract void showMesssage();
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}

}
