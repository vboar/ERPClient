package ui.saleui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyTextArea;
import ui.util.MyTextField;
import businesslogicservice.saleblservice.SaleBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CreatePanel extends JPanel {

	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;
	
	private MyButton addCustomer;
	
	private MyLabel documentId;
	
	private MyLabel customerIdLab;
	
	private MyLabel customerNameLab;
	
	private MyLabel totalBeforeDiscountLab;
	
	private MyLabel totalLab;
	
	private MyTextField discountTxt;
	
	private MyTextField voucherTxt;
	
	private MyTextArea remarkTxt;
	
	private MyComboBox storage;
	
	private MyComboBox salesman;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private SaleBLService controller;
	
	public CreatePanel(JFrame frame){
		this.frame = frame;	
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = cfg.getBg();
        this.setSize(cfg.getW(), cfg.getH());
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        this.initComponent();
        this.repaint();
        this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
		
	private void initComponent() {
		this.initLabels();
		this.initButtons();
		this.discountTxt = new MyTextField(cfg.getTextFields().element("discount"));
		this.voucherTxt = new MyTextField(cfg.getTextFields().element("voucher"));
		this.add(discountTxt);
		this.add(voucherTxt);
		this.salesman = new MyComboBox(cfg.getComboboxes().element("salesman"));
		this.storage = new MyComboBox(cfg.getComboboxes().element("storage"));
		this.add(salesman);
		this.add(storage);
		this.remarkTxt = new MyTextArea(cfg.getTextarea().element("remark"));
		this.add(remarkTxt);
	}

	private void initButtons() {
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.deleteBtn = new MyButton(cfg.getButtons().element("delete"));
		this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.addCustomer = new MyButton(cfg.getButtons().element("addcustomer"));
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(cancelBtn);
		this.add(commitBtn);
		this.add(addCustomer);
	}

	private void initLabels() {
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customerid"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element("customername"));
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.totalBeforeDiscountLab = new MyLabel(cfg.getLabels().element("totalbefore"));
		this.totalLab = new MyLabel(cfg.getLabels().element("total"));
		this.add(customerIdLab);
		this.add(customerNameLab);
		this.add(documentId);
		this.add(totalBeforeDiscountLab);
		this.add(totalLab);
	}
}
