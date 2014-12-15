package ui.conditionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.exceptionui.ExceptionListTablePane;
import ui.exceptionui.WarningListTablePane;
import ui.paymentui.ShowCashTable;
import ui.paymentui.ShowPaymentTable;
import ui.presentui.PresentListTablePane;
import ui.purchaseui.PurchaseListPane;
import ui.saleui.SaleListPane;
import ui.util.DatePickerGroup;
import ui.util.ExcelSaver;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.SavePathDialog;
import util.DocumentType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.RequirementVO;
import vo.UserVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.HistoryBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel implements ExcelSaver{

	private MyComboBox customer;
	
	private MyComboBox type;
	
	private MyComboBox salesman;
	
	private MyComboBox storage;
	
	private DatePickerGroup start;
	
	private DatePickerGroup end;
	
	private MyButton export;
	
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
	
	private RequirementVO vo;
	
	private boolean hasTable = false;
	
	private HistoryBLService controller;
	
	public HistoryPanel(JFrame frame){
    	this.frame = frame;
    	vo = new RequirementVO();
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
	
	@SuppressWarnings("unchecked")
	private void initComponent() {
		// 初始化日期选择器
		this.start = new DatePickerGroup(cfg.getDatepicker().element("start"));
		this.end = new DatePickerGroup(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		// 添加客户信息
		this.customer = new MyComboBox(cfg.getComboboxes().element("client"));
		ArrayList<CustomerVO> cutomerlist = ControllerFactoryImpl.getInstance().getCustomerController()
				.fuzzyFind("");
		this.customer.addItem("所有");
		for(int j=0; j<cutomerlist.size();++j){
			this.customer.addItem(cutomerlist.get(j).id+cutomerlist.get(j).name);
		}	
		// 添加业务员信息
		this.salesman = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.salesman.addItem("所有");
		ArrayList<UserVO> userlist =ControllerFactoryImpl.getInstance().getUserController()
				.fuzzyFindOperator("");
		for(int i=0; i<userlist.size();++i){
			this.salesman.addItem(userlist.get(i).id);
		}
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
		this.export = new MyButton(cfg.getButtons().element("export"));
		this.export.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hasTable){
					if(MyOptionPane.showConfirmDialog(frame, "是否根据当前列表导出Excel文件？","确认提示",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)==MyOptionPane.YES_OPTION){
						new SavePathDialog(frame, HistoryPanel.this);
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "当前无数据可导出！");
				}
			}
		});
		this.add(export);
		this.add(find);
	}
	
	public RequirementVO getRequirementVO(){
		// 根据时间区间、商品名、客户名、业务员和仓库查询
		String time1 = FrameUtil.getFormattedDate(this.start.getDate());
		String time2 =  FrameUtil.getFormattedDate(this.end.getDate());
		if((time1!=null)&&(time2!=null)&&(time1.compareTo(time2)>0)){
			MyOptionPane.showMessageDialog(frame, "请输入有效日期！","错误提示",
					MyOptionPane.ERROR_MESSAGE);
			return null;
		}
		vo.time1 = time1;
		vo.time2 = time2;
		if(!salesman.getSelectedItem().equals("所有"))
			vo.salesman = salesman.getSelectedItem().toString();
		else vo.salesman = null;
		if(!storage.getSelectedItem().equals("所有"))
			vo.storage = storage.getSelectedItem().toString();
		if(!customer.getSelectedItem().equals("所有"))
			vo.customer = this.customer.getSelectedItem().toString().substring(0,7);
		else vo.customer = null;
		return vo;
	}
	
	public void showTable(){
		this.hasTable = true;
		if(this.getRequirementVO()!=null){
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
				new TableConfig(cfg.getTables().element("sale")), isreturn,false);
		add(sales);
		repaint();
	}
	
	public void showPurchase(RequirementVO vo, boolean isreturn){
		removeAllPanel();
		this.purchase = new PurchaseListPane(
				new TableConfig(cfg.getTables().element("purchase")), isreturn,false);
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

	@Override
	public ResultMessage setSavePath(String path) {
		return controller.exportExcel(path,getRequirementVO());
	}

	@Override
	public String getDefaultPath() {
		return controller.getDefaultPath();
	}
	
}
