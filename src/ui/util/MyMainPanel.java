/**
 *  中间层面板父类
 *  @author Vboar
 *  @date 2014/11/30
 */
package ui.util;

import config.ERPConfig;
import config.PanelConfig;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public abstract class MyMainPanel extends JPanel {
	
	public PanelConfig pcfg;
	
	private MyLabel title;
	
	private Image bg;
	
	public JFrame frame;
	
	public MyMainPanel(JFrame frame) {
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
