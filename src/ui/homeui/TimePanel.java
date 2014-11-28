package ui.homeui;

import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class TimePanel extends JPanel {

	private MyLabel timeLab;
	
	private PanelConfig pcfg;

	private Image bg;

	public TimePanel() {		
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		this.initTimeLabel();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initTimeLabel(){
		this.timeLab = new MyLabel(this.pcfg.getLabels().element("timelab"));
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  EEEE  HH:mm:ss");
		String nowtime = dateFormat.format(now);
		this.timeLab.setText(nowtime);
		this.add(this.timeLab);
	}
	
}
