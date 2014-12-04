package ui.exceptionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class ExceptionPanel extends JPanel {

	private MyButton overLossListShow;
	
	private MyButton warningListShow;
	
	private MyButton addLoss;
	
	private MyButton addOver;
	
	private JPanel createLossPanel;
	
	private JPanel createOverPanel;
	
	private JPanel showOverLossPanel;
	
	private JPanel showWarningPanel;
	
	private PanelConfig cfg;
	
	private ExceptionBLService controller;
	
	private Image bg;
	
	private JFrame frame;
	
    public ExceptionPanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		this.initComponent();
		this.repaint();
    }
    
	private void initComponent() {
		this.overLossListShow = new MyButton(this.cfg.getButtons().element("overlosslist"));
		this.overLossListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(this.overLossListShow);
		this.warningListShow = new MyButton(this.cfg.getButtons().element("warning"));
		this.warningListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(this.warningListShow);
		this.addLoss = new MyButton(this.cfg.getButtons().element("addloss"));
		this.addLoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(this.addLoss);
		this.addOver = new MyButton(this.cfg.getButtons().element("addover"));
		this.addOver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createLossPanel = new CreateLossPanel(frame, ExceptionPanel.this);
				createLossPanel.setVisible(true);
				add(createLossPanel);
				
			}
		});
		this.add(this.addOver);
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
	

}
