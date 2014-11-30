package ui.paymentui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

public class PaymentPanel extends JPanel {
	
	private MyButton createPaymentBtn;
	
	private MyButton createReceiptBtn;
	
	private MyButton createCashBtn;
	
	private MyButton showBtn;
	
	private JPanel createPaymentPanel;
	
	private JPanel createReceiptPanel;
	
	private JPanel createCashPanel;
	
	private JPanel showPanel;
	
	private JFrame frame;
	
	private PanelConfig pcfg;

	public PaymentPanel(JFrame frame) {
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent(pcfg);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(),pcfg.getH(),null);
	}

	private void initComponent(PanelConfig cfg) {
		
		createReceiptPanel = new CreateReceiptPanel(frame);
		this.add(createReceiptPanel);
		
		createPaymentBtn = new MyButton(cfg.getButtons().element("createpayment"));
		createPaymentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(createPaymentBtn);
		
		createReceiptBtn = new MyButton(cfg.getButtons().element("createreceipt"));
		createReceiptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(createReceiptBtn);
		
		createCashBtn = new MyButton(cfg.getButtons().element("createcash"));
		createCashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(createCashBtn);
		
		showBtn = new MyButton(cfg.getButtons().element("show"));
		showBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		this.add(showBtn);
		
		this.add(new MyLabel(cfg.getLabels().element("title")));
		
	}

}
