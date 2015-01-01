package ui.paymentui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.util.DocumentShowDialogExtra;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PaymentVO;
import vo.TransferLineItemVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.Login;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPaymentPanel extends PaymentDocumentPanel implements
		FuzzySearch, DocumentShowDialogExtra, CreatePanel {

	private int type;

	private PaymentVO vo;
	private boolean hasCustomer = true;
	private CustomerVO customerVO;
	private ArrayList<TransferLineItemVO> lists;
	private HashMap<String, CustomerVO> customerlist;

	private PaymentBLService paymentController;
	private CustomerBLService customerController;

	public ShowPaymentPanel(JFrame frame, PaymentVO vo, int type,
			boolean isreceipt) {
		super(frame);
		this.type = type;
		this.vo = vo;
		this.customerVO = new CustomerVO();
		this.customerVO.id = vo.customerId;
		this.customerVO.name = vo.customerName;
		this.lists = vo.transferList;
		customerlist = new HashMap<String, CustomerVO>();
		paymentController = isreceipt ? ControllerFactoryImpl.getInstance()
				.getReceiptController() : ControllerFactoryImpl.getInstance()
				.getPaymentController();
		customerController = ControllerFactoryImpl.getInstance()
				.getCustomerController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.bg = isreceipt ? cfg.getBg1() : cfg.getBg();
		this.setPreferredSize(new Dimension(cfg.getW(), cfg.getH()));
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.initComponent();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}

	protected void initComponent() {
		initLabels();
		initButtons();
		table = new PaymentLineItemTable(new TableConfig(cfg.getTablepane()));
		for (int i = 0; i < this.lists.size(); ++i) {
			table.addRow(lists.get(i));
		}
		table.updateUI();
		this.add(table);
		customerFind = new MySpecialTextField(cfg.getTextFields().element(
				"customerfind"), this);
		customerFind.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					showCustomerInfo();
				}
			}
		});
		if (type != 1)
			add(customerFind);
	}

	private void initLabels() {
		this.operatorLab = new MyLabel(cfg.getLabels().element("operatorlab"));
		this.customerIdLab = new MyLabel(cfg.getLabels().element(
				"customeridlab"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element(
				"customernamelab"));
		this.documentIdLab = new MyLabel(cfg.getLabels().element(
				"documentidlab"));

		this.documentIdLab.setText(type == 2 ? paymentController.createId()
				: vo.id);
		this.customerIdLab.setText(vo.customerId);
		this.customerNameLab.setText(vo.customerName);
		this.operatorLab.setText(type == 2 ? Login.currentUserId
				: vo.operatorId);

		this.add(this.operatorLab);
		this.add(this.documentIdLab);
		this.add(this.customerIdLab);
		this.add(this.customerNameLab);
	}

	private void initButtons() {
		if (type != 1) {
			// 添加客户按钮
			addCustomerBtn = new MyButton(cfg.getButtons().element(
					"addcustomer"));
			addCustomerBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showCustomerInfo();
				}
			});
			// 添加账户按钮
			addBtn = new MyButton(cfg.getButtons().element("add"));
			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddAccountDialog(ERPConfig
							.getADDRECEIPTACCOUNT_DIALOG_CONFIG(), frame,
							ShowPaymentPanel.this);
				}
			});
			// 删除账户按钮
			deleteBtn = new MyButton(cfg.getButtons().element("delete"));
			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (table.isSelected()) {
						int result = MyOptionPane.showConfirmDialog(frame,
								"确认删除该账户条目？", "删除账户条目",
								MyOptionPane.YES_NO_OPTION,
								MyOptionPane.QUESTION_MESSAGE);
						if (result == MyOptionPane.YES_OPTION) {
							deleteAccount();
						}
					} else {
						MyOptionPane.showMessageDialog(frame, "请选择要删除的账户条目！");
					}
				}
			});
			this.add(addBtn);
			this.add(addCustomerBtn);
			this.add(deleteBtn);
		}
	}
	
	protected void showCustomerInfo() {
		if(customerFind.getText() != null){
			customerVO = customerlist.get(customerFind.getText());
			if(customerVO != null){
				customerIdLab.setText(customerVO.id);
				customerNameLab.setText(customerVO.name);
				hasCustomer = true;
			}else{
				MyOptionPane.showMessageDialog(null, "请重新选择客户！");
			}
		}
	}
	
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> result = customerController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i < result.size(); ++i) {
			CustomerVO vo = result.get(i);
			String str = vo.id + "-" + vo.name;
			strs.add(str);
			this.customerlist.put(str, vo);
		}
		return strs;
	}

	@Override
	public void addAccount(TransferLineItemVO vo) {
		lists.add(vo);
		table.addRow(vo);
	}

	@Override
	public void deleteAccount() {
		lists.remove(table.getTable().getSelectedRow());
		table.deleteRow();
	}

	@Override
	public ArrayList<TransferLineItemVO> getLists() {
		return lists;
	}

	@Override
	public DocumentType getDocumentType() {
		return vo.documentType;
	}

	@Override
	public String getDocumentID() {
		return vo.id;
	}

	@Override
	public void createCopyDocument() {
		if (checkCompleted()) {
			double total = 0;
			for (TransferLineItemVO vo : lists) {
				total += vo.account;
			}
			ResultMessage result = paymentController.create(new PaymentVO(
					documentIdLab.getText(), null, customerVO.id,
					customerVO.name, operatorLab.getText(), lists, total,
					DocumentStatus.NONCHECKED, false, true, DocumentType.PAYMENT));
			if (result == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(null, "付款单提交成功！");
			} else {
				MyOptionPane.showMessageDialog(null, "付款单提交失败！");
			}
		} else {
			MyOptionPane.showMessageDialog(null, "请填入完整单据数据！");
		}
	}

	@Override
	public boolean checkCompleted() {
		if (hasCustomer && this.lists.size() > 0) {
			return true;
		}
		return false;
	}
}
