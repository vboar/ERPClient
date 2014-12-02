/**
 * 创建收款单面板
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.Login;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import ui.util.*;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PaymentVO;
import vo.TransferLineItemVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CreateReceiptPanel extends JPanel implements FuzzySearch {
	
	private MyLabel documentIdLab;
	
	private MyLabel customerLab;
	
	private MyLabel operatorLab;

	private MyLabel customerNameLab;

	private MyButton addCustomerBtn;
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;

	private MySpecialTextField customerFind;

	private ReceiptTable table;
	
	private JFrame frame;

	private AddReceiptAccountDialog addDialog;
	
	private PanelConfig pcfg;
	
	private PaymentBLService receiptController;

	private CustomerBLService customerController;

	private String id;

	private String customerId;

	private String customerName;

	private String operatorId;

	private ArrayList<TransferLineItemVO> lists;

//	private HashMap<String, CustomerVO> customerList;

	private double total;

	public CreateReceiptPanel(JFrame frame) {
		this.frame = frame;
		receiptController = ControllerFactoryImpl.getInstance().getReceiptController();
		customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initVO();
		this.initComponent();
		this.repaint();
		this.setBackground(Color.WHITE);
	}

	@Override
	public void paintComponent(Graphics g){
		// TODO
//		g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(),pcfg.getH(),null);
		g.drawLine(10, 50, 720, 50);
	}
	
	
	private void initComponent() {
		initLabels();
		initButtons();
		initCustomerFind();
		initTable();
	}

	private void initTable() {
		table = new ReceiptTable(new TableConfig(pcfg.getTablepane()), this);
		this.add(table);
	}

	private void initLabels() {
		this.add(new MyLabel(pcfg.getLabels().element("title")));
		this.add(new MyLabel(pcfg.getLabels().element("list")));
		customerLab = new MyLabel(pcfg.getLabels().element("customer"));
		this.add(customerLab);
		customerNameLab = new MyLabel(pcfg.getLabels().element("customername"));
		this.add(customerNameLab);
		operatorLab = new MyLabel(pcfg.getLabels().element("operator"));
		operatorLab.setText(operatorLab.getText() + " " + operatorId);
		this.add(operatorLab);
		documentIdLab = new MyLabel(pcfg.getLabels().element("documentid"));
		documentIdLab.setText(documentIdLab.getText() + " " + id);
		this.add(documentIdLab);
	}

	private void initButtons() {
		addCustomerBtn = new MyButton(pcfg.getButtons().element("addcustomer"));
		add(addCustomerBtn);
		addCustomerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] customer = customerFind.getText().split(" ");
				customerId = customer[0];
				customerName = customer[1];
				customerNameLab.setText("客户姓名：  " + customerName);
			}
		});

		addBtn = new MyButton(pcfg.getButtons().element("add"));
		this.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAddDialog();
			}
		});

		deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
		this.add(deleteBtn);

		commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认创建该收款单？", "创建收款单",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					// 没有选择客户或者没有添加账户
					if(customerId == null || lists.size() == 0) {
						MyOptionPane.showMessageDialog(null, "填写信息有误，创建失败！");
						return;
					}
					// 创建单据
					PaymentVO vo = createReceipt();
					if (receiptController.create(vo) == ResultMessage.SUCCESS) {
						MyOptionPane.showMessageDialog(null, "创建成功！");
					} else {
						MyOptionPane.showMessageDialog(null, "填写信息有误，创建失败！");
					}
				}
			}
		});
		this.add(commitBtn);

		cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 返回单据查看
			}
		});
		this.add(cancelBtn);
	}

	private void initCustomerFind() {
		customerFind = new MySpecialTextField(pcfg.getTextFields().element("customerfind"), this);
		add(customerFind);
	}

	private void initVO() {
		// TODO 获得ID
		id = "123456";
		operatorId = Login.currentUserId;
		lists = new ArrayList<TransferLineItemVO>();
//		customerList = new HashMap<String, CustomerVO>();
		total = 0;
	}

	private void showAddDialog() {
		addDialog= new AddReceiptAccountDialog(ERPConfig.getADDRECEIPTACCOUNT_DIALOG_CONFIG(), frame, this);
		this.addDialog.setVisible(true);
	}

	private PaymentVO createReceipt() {
		for(TransferLineItemVO vo: lists) {
			total += vo.account;
		}
		// TODO time
		PaymentVO vo = new PaymentVO(id, null, customerId, customerName, operatorId,
				lists, total, DocumentStatus.NONCHECKED, false, DocumentType.RECEIPT);
		return vo;
	}

	public void addAccount(TransferLineItemVO vo) {
		lists.add(vo);
		// TODO 表格操作
	}

	public void deleteAccount(String account) {
		for(TransferLineItemVO vo: lists) {
			if(vo.bankAccount.equals(account)) {
				lists.remove(vo);
				break;
			}
		}
		// TODO 表格操作
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> result = customerController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i = 0; i < result.size(); ++i){
			CustomerVO vo = result.get(i);
			strs.add(vo.id + " " + vo.name);
		}
		return strs;
	}

	public ReceiptTable getTable() {
		return table;
	}

	public ArrayList<TransferLineItemVO> getLists() {
		return lists;
	}
}
