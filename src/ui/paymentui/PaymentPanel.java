/**
 * 收付款面板
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		showShow();
		
		createPaymentBtn = new MyButton(cfg.getButtons().element("createpayment"));
		createPaymentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showCreatePayment();
			}
		});
		this.add(createPaymentBtn);
		
		createReceiptBtn = new MyButton(cfg.getButtons().element("createreceipt"));
		createReceiptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showCreateReceipt();
			}
		});
		this.add(createReceiptBtn);
		
		createCashBtn = new MyButton(cfg.getButtons().element("createcash"));
		createCashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				MyOptionPane.showMessageDialog(null, "功能正在开发中...");
//				showCreateCash();
			}
		});
		this.add(createCashBtn);
		
		showBtn = new MyButton(cfg.getButtons().element("show"));
		showBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showShow();
			}
		});
		this.add(showBtn);
		
		this.add(new MyLabel(cfg.getLabels().element("title")));
		
	}

	public void showCreateReceipt() {
		removeAllPanel();
		createReceiptPanel = new CreateReceiptPanel(frame, this);
		add(createReceiptPanel);
		repaint();
	}

	public void showCreatePayment() {
		removeAllPanel();
		createPaymentPanel = new CreatePaymentPanel(frame, this);
		add(createPaymentPanel);
		repaint();
	}

	public void showCreateCash() {
		removeAllPanel();
		createCashPanel = new CreateCashPanel(frame);
		add(createCashPanel);
		repaint();
	}

	public void showShow() {
		removeAllPanel();
		showPanel = new ShowPanel(frame);
		add(showPanel);
		repaint();
	}

	private void removeAllPanel() {
		if(createReceiptPanel != null) remove(createReceiptPanel); createReceiptPanel = null;
		if(createPaymentPanel != null) remove(createPaymentPanel); createPaymentPanel = null;
		if(createCashPanel != null) remove(createCashPanel); createCashPanel = null;
		if(showPanel != null) remove(showPanel); showPanel = null;
	}

}
