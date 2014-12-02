/**
 * 销售人员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import ui.customerui.CustomerPanel;
import ui.messageui.MessagePanel;
import ui.presentui.PresentPanel;
import ui.purchaseui.PurchasePanel;
import ui.saleui.SalePanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SalesmanPanel extends MyMainPanel {

	private MyButton saleBtn;

	private MyButton purchaseBtn;

	private MyButton presentBtn;

	private MyButton customerManageBtn;

	private SalePanel salePanel;

	private PurchasePanel purchasePanel;

	private PresentPanel presentPanel;

	private CustomerPanel customerPanel;

	private MessagePanel messagePanel;

	public SalesmanPanel(JFrame frame) {
		super(frame);
	}

	@Override
	public void initComponent(){
		super.initComponent();

		saleBtn = new MyButton(pcfg.getButtons().element("sale"));
		add(saleBtn);
		saleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSale();
			}
		});

		purchaseBtn = new MyButton(pcfg.getButtons().element("purchase"));
		add(purchaseBtn);
		purchaseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPurchase();
			}
		});

		presentBtn = new MyButton(pcfg.getButtons().element("present"));
		add(presentBtn);
		presentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPresent();
			}
		});

		this.customerManageBtn = new MyButton(this.pcfg.getButtons().element("customermanage"));
		this.customerManageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCustomer();
			}
		});
		this.add(this.customerManageBtn);
	}

	public void showSale() {
		removeAllPanel();
		salePanel = new SalePanel(frame);
		add(salePanel);
		repaint();
	}

	public void showPurchase() {
		removeAllPanel();
		purchasePanel = new PurchasePanel(frame);
		add(purchasePanel);
		repaint();
	}

	public void showPresent() {
		removeAllPanel();
		presentPanel = new PresentPanel(frame);
		add(presentPanel);
		repaint();
	}

	public void showCustomer() {
		removeAllPanel();
		customerPanel = new CustomerPanel(frame);
		add(customerPanel);
		repaint();
	}

	@Override
	public void showMesssage() {
		removeAllPanel();
		messagePanel = new MessagePanel(frame);
		add(messagePanel);
		repaint();
	}

	private void removeAllPanel() {
		if(salePanel != null) remove(salePanel); salePanel = null;
		if(purchasePanel != null) remove(purchasePanel); purchasePanel = null;
		if(presentPanel != null) remove(presentPanel); presentPanel = null;
		if(customerPanel != null) remove(customerPanel); customerPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
	}

}
