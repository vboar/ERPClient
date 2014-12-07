package ui.conditionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.exceptionui.ExceptionListTablePane;
import ui.exceptionui.WarningListTablePane;
import ui.paymentui.CashTable;
import ui.paymentui.PaymentTable;
import ui.presentui.PresentListTablePane;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import util.DocumentType;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import businesslogicservice.exceptionblservice.WarningBLService;
import businesslogicservice.paymentblservice.CashBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import businesslogicservice.presentblservice.PresentBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel{

	private MyComboBox client;
	
	private MyComboBox type;
	
	private MyComboBox operator;
	
	private MyComboBox store;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton find;
	
	// TODO 进货单、销售单	
	// 赠送单
	private PresentListTablePane present;	
	// 付款收款单
	private PaymentTable payment;	
	// 现金费用单
	private CashTable cash;	
	// 报溢报损单
	private ExceptionListTablePane exception;	
	// 报警单
	private WarningListTablePane warning;
	
	private PresentBLService presentCtrl;
	private PaymentBLService paymentCtrl;	
	private CashBLService cashCtrl;
	private ExceptionBLService lossCtrl;
	private ExceptionBLService overCtrl;
	private WarningBLService warningCtrl;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	public HistoryPanel(JFrame frame){
    	this.frame = frame;
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化控制器
		this.initControllers();
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();	
	}

	private void initControllers(){
		this.paymentCtrl = ControllerFactoryImpl.getInstance().getPaymentController();
		this.cashCtrl = ControllerFactoryImpl.getInstance().getCashController();
		this.presentCtrl = ControllerFactoryImpl.getInstance().getPresentController();
		this.lossCtrl = ControllerFactoryImpl.getInstance().getLossController();
		this.overCtrl = ControllerFactoryImpl.getInstance().getOverflowController();
		this.warningCtrl = ControllerFactoryImpl.getInstance().getWarningController();
	}
	
	private void initComponent() {
		// 初始化日期选择器
		this.start = new MyDatePicker(cfg.getDatepicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		this.client = new MyComboBox(cfg.getComboboxes().element("client"));
		this.operator = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.type = new MyComboBox(cfg.getComboboxes().element("type"));
		this.store = new MyComboBox(cfg.getComboboxes().element("store"));
		this.add(client);
		this.add(operator);
		this.add(type);
		this.add(store);
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
		DocumentType documentType = DocumentType.strToType(this.type.getSelectedItem().toString());
		switch(documentType){
		case PRESENT:
			removeAllPanel();
			this.present = new PresentListTablePane(new TableConfig(cfg.getTables().element("present")));
			
			add(present);	
			repaint();
			break;
		case OVERFLOW:
			removeAllPanel();
			this.exception = new ExceptionListTablePane(
					new TableConfig(cfg.getTables().element("exception")),false);
			add(exception);
			repaint();
			break;
		case LOSS:
			removeAllPanel();
			this.exception = new ExceptionListTablePane(
					new TableConfig(cfg.getTables().element("exception")),true);
			add(exception);
			repaint();
			break;
		case WARNING:
			removeAllPanel();
			this.warning = new WarningListTablePane(
					new TableConfig(cfg.getTables().element("warning")));
			add(warning);
			repaint();
			break;
		case SALE:
			break;
		case SALERETURN:
			break;
		case PURCHASE:
			break;
		case PURCHASERETURN:
			break;
		case RECEIPT:
			removeAllPanel();
			this.payment = new PaymentTable(new TableConfig(cfg.getTables().element("payment")));
			add(payment);
			repaint();
			break;
		case PAYMENT:
			removeAllPanel();
			this.payment = new PaymentTable(new TableConfig(cfg.getTables().element("payment")));
			add(payment);
			repaint();
			break;
		case CASH:
			break;
		default:
			return;
		}
	}
	
	// TODO 
	private void removeAllPanel() {
		if(present != null) remove(present); present = null;
		if(payment != null) remove(payment); payment= null;
		if(exception != null) remove(exception); exception = null;
		if(warning != null) remove(warning); warning = null;
		if(cash != null) remove(cash); cash = null;
	}
	
}
