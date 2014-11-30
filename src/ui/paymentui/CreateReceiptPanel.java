package ui.paymentui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CreateReceiptPanel extends JPanel {
	
	private MyLabel title;
	
	private MyLabel documentId;
	
	private MyLabel list;
	
	private MyLabel customer;
	
	private MyLabel operator;
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private PaymentBLService receiptController;

	public CreateReceiptPanel(JFrame frame) {
		this.frame = frame;
		receiptController = ControllerFactoryImpl.getInstance().getReceiptController();
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
		g.drawLine(10, 50, 720, 50);
	}
	
	
	private void initComponent(PanelConfig cfg) {
		
		title = new MyLabel(cfg.getLabels().element("title"));
		this.add(title);
		list = new MyLabel(cfg.getLabels().element("list"));
		this.add(list);
		customer = new MyLabel(cfg.getLabels().element("customer"));
		this.add(customer);
		operator = new MyLabel(cfg.getLabels().element("operator"));
		this.add(operator);
		documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.add(documentId);
		
		addBtn = new MyButton(cfg.getButtons().element("add"));
		this.add(addBtn);
		
		deleteBtn = new MyButton(cfg.getButtons().element("delete"));
		this.add(deleteBtn);
		
		commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.add(commitBtn);
		
		cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.add(cancelBtn);
	}
	
}
