package ui.exceptionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class ExceptionPanel extends JPanel {

	private MyButton overListShow;
	
	private MyButton lossListShow;
	
	private MyButton warningListShow;
	
	private MyButton addLoss;
	
	private MyButton addOver;
	
	private JPanel createLossPanel;
	
	private JPanel createOverPanel;
	
	private JPanel showOverLossPanel;
	
	private JPanel showWarningPanel;
	
	private PanelConfig cfg;
	
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
		this.overListShow = new MyButton(this.cfg.getButtons().element("overlist"));
		this.overListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showExceptionList(false);
			}
		});
		this.add(this.overListShow);
		this.lossListShow = new MyButton(this.cfg.getButtons().element("losslist"));
		this.lossListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showExceptionList(true);
			}
		});
		this.add(this.lossListShow);
		this.warningListShow = new MyButton(this.cfg.getButtons().element("warning"));
		this.warningListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showWarning();				
			}
		});
		this.add(this.warningListShow);
		this.addLoss = new MyButton(this.cfg.getButtons().element("addloss"));
		this.addLoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateLoss();
			}
		});
		this.add(this.addLoss);
		this.addOver = new MyButton(this.cfg.getButtons().element("addover"));
		this.addOver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateOver();		
			}
		});
		this.add(this.addOver);
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}

	public void showCreateLoss() {
		removeAllPanel();
		createLossPanel = new CreateExceptionPanel(frame, ExceptionPanel.this,true);
		add(createLossPanel);
		repaint();
	}

	public void showCreateOver() {
		removeAllPanel();
		createOverPanel = new CreateExceptionPanel(frame, ExceptionPanel.this,false);
		add(createOverPanel);
		repaint();
	}

	public void showWarning() {
		removeAllPanel();
		showWarningPanel = new ShowWarningPanel();
		add(showWarningPanel);
		repaint();
	}

	public void showExceptionList(boolean isloss) {
		removeAllPanel();
		showOverLossPanel = new ShowOverLossPanel(isloss);
		add(showOverLossPanel);
		repaint();
	}
	
	private void removeAllPanel() {
		if(createLossPanel != null) remove(createLossPanel); createLossPanel = null;
		if(createOverPanel != null) remove(createOverPanel); createOverPanel = null;
		if(showOverLossPanel != null) remove(showOverLossPanel); showOverLossPanel = null;
		if(showWarningPanel != null) remove(showWarningPanel);showWarningPanel = null;
	}
}
