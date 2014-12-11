package ui.conditionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.exceptionui.ExceptionListTablePane;
import ui.exceptionui.WarningListTablePane;
import ui.paymentui.ShowCashTable;
import ui.paymentui.ShowPaymentTable;
import ui.presentui.PresentListTablePane;
import ui.purchaseui.PurchaseListPane;
import ui.saleui.SaleListPane;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.DocumentType;
import vo.RequirementVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.HistoryBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel{

	private MyComboBox customer;
	
	private MyComboBox type;
	
	private MyComboBox salesman;
	
	private MyComboBox storage;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton find;
	// 销售单	
	private SaleListPane sales;
	// 进货单
	private PurchaseListPane purchase;
	// 赠送单
	private PresentListTablePane present;	
	// 付款收款单
	private ShowPaymentTable payment;	
	// 现金费用单
	private ShowCashTable cash;	
	// 报溢报损单
	private ExceptionListTablePane exception;	
	// 报警单
	private WarningListTablePane warning;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private HistoryBLService controller;
	
	public HistoryPanel(JFrame frame){
    	this.frame = frame;
    	this.controller = ControllerFactoryImpl.getInstance().getHistoryBLService();
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();	
	}
	
	private void initComponent() {
		// 初始化日期选择器
		this.start = new MyDatePicker(cfg.getDatepicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		this.customer = new MyComboBox(cfg.getComboboxes().element("client"));
		this.salesman = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.type = new MyComboBox(cfg.getComboboxes().element("type"));
		this.storage = new MyComboBox(cfg.getComboboxes().element("store"));
		this.add(customer);
		this.add(salesman);
		this.add(type);
		this.add(storage);
		// 添加标签
		this.add(new MyLabel(cfg.getLabels().element("require")));
		this.add(new MyLabel(cfg.getLabels().element("time")));
		this.add(new MyLabel(cfg.getLabels().element("start")));
		this.add(new MyLabel(cfg.getLabels().element("end")));
		this.add(new MyLabel(cfg.getLabels().element("type")));
		this.add(new MyLabel(cfg.getLabels().element("client")));
		this.add(new MyLabel(cfg.getLabels().element("operator")));
		this.add(new MyLabel(cfg.getLabels().element("store")));
		// 初始化按钮
		this.find = new MyButton(cfg.getButtons().element("find"));
		this.find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showTable();
			}
		});
		this.add(find);
	}
	
	public void showTable(){
		RequirementVO vo = new RequirementVO();
		// 根据时间区间、商品名、客户名、业务员和仓库查询
		String time1 = FrameUtil.getFormattedDate(this.start.getDate());
		String time2 =  FrameUtil.getFormattedDate(this.end.getDate());
		if((time1!=null)&&(time2!=null)&&(time1.compareTo(time2)>0)){
			MyOptionPane.showMessageDialog(frame, "请输入有效日期！","错误提示",
					MyOptionPane.ERROR_MESSAGE);
			return;
		}
		vo.time1 = time1;
		vo.time2 = time2;
		if(salesman.getSelectedItem()!=null)
			vo.salesman = salesman.getSelectedItem().toString();
		if(storage.getSelectedItem()!=null)
			vo.storage = storage.getSelectedItem().toString();	
		if(customer.getSelectedItem()!=null)
			vo.customer = this.customer.getSelectedItem().toString();
		DocumentType documentType = DocumentType.strToType(this.type.getSelectedItem().toString());
		switch(documentType){
		case PRESENT: showPresent(vo); break;
		case OVERFLOW: showException(vo, false); break;
		case LOSS: showException(vo, true);	break;
		case WARNING: showWarning(vo); break;
		case SALE: showSale(vo,false); break;
		case SALERETURN: showSale(vo, true); break;
		case PURCHASE: showPurchase(vo, false);	break;
		case PURCHASERETURN: showPurchase(vo, true); break;
		case RECEIPT: showReceipt(vo); break;
		case PAYMENT: showPayment(vo); break;
		case CASH: showCash(vo);break;
		default:
			return;
		}
	}
	
	public void showPresent(RequirementVO vo){
		removeAllPanel();
		this.present = new PresentListTablePane(new TableConfig(cfg.getTables().element("present")));
		add(present);	
		repaint();
	}
	
	public void showException(RequirementVO vo, boolean isloss){
		removeAllPanel();
		this.exception = new ExceptionListTablePane(
				new TableConfig(cfg.getTables().element("exception")),isloss);
		add(exception);
		repaint();
	}
	
	public void showWarning(RequirementVO vo){
		removeAllPanel();
		this.warning = new WarningListTablePane(
				new TableConfig(cfg.getTables().element("warning")));
		add(warning);
		repaint();
	}
	
	public void showSale(RequirementVO vo, boolean isreturn){
		removeAllPanel();
		this.sales = new SaleListPane(
				new TableConfig(cfg.getTables().element("sale")), isreturn);
		add(sales);
		repaint();
	}
	
	public void showPurchase(RequirementVO vo, boolean isreturn){
		removeAllPanel();
		this.purchase = new PurchaseListPane(
				new TableConfig(cfg.getTables().element("purchase")), isreturn);
		add(purchase);
		repaint();
	}
	
	public void showReceipt(RequirementVO vo){
		removeAllPanel();
		this.payment = new ShowPaymentTable(new TableConfig(cfg.getTables().element("payment")),
				ControllerFactoryImpl.getInstance().getReceiptController());
		add(payment);
		repaint();
	}
	
	public void showPayment(RequirementVO vo){
		removeAllPanel();
		this.payment = new ShowPaymentTable(new TableConfig(cfg.getTables().element("payment")),
		ControllerFactoryImpl.getInstance().getPaymentController());
		add(payment);
		repaint();
	}
	
	public void showCash(RequirementVO vo){
		removeAllPanel();
		this.cash = new ShowCashTable(new TableConfig(cfg.getTables().element("cash")),
				ControllerFactoryImpl.getInstance().getCashController());
		this.cash.showHistory(controller.showCash(vo));
		add(cash);
		repaint();
	}

	private void removeAllPanel() {
		if(sales != null) remove(sales); sales = null;
		if(purchase != null) remove(purchase); purchase = null;
		if(present != null) remove(present); present = null;
		if(payment != null) remove(payment); payment= null;
		if(exception != null) remove(exception); exception = null;
		if(warning != null) remove(warning); warning = null;
		if(cash != null) remove(cash); cash = null;
	}
	
}
