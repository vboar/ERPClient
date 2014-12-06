/**
 * 创建付款单面板
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
import java.util.HashMap;

@SuppressWarnings("serial")
public class CreatePaymentPanel extends JPanel implements FuzzySearch, CreatePanel {
	
	private MyLabel operatorLab;

	private MyLabel documentIdLab;

	private MyLabel customerIdLab;

	private MyLabel customerNameLab;

	private MyButton addCustomerBtn;
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;

	private MySpecialTextField customerFind;

	private PaymentTable table;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private PaymentBLService paymentController;

	private CustomerBLService customerController;

	private CustomerVO customerVO;

	private boolean hasCustomer = false;

	private ArrayList<TransferLineItemVO> lists;

	private HashMap<String,CustomerVO> customerlist;

	private PaymentPanel panel;

    public CreatePaymentPanel(JFrame frame, PaymentPanel panel) {
		this.frame = frame;
		this.panel = panel;
		paymentController = ControllerFactoryImpl.getInstance().getPaymentController();
		customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		customerlist = new HashMap<String,CustomerVO>();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
    }

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(), pcfg.getH(),null);
	}

	private void initComponent() {
		initLabels();
		initButtons();
		initCustomerFind();
		initTable();
	}
	
	private void initTable() {
		table = new PaymentTable(new TableConfig(pcfg.getTablepane()));
		this.add(table);
	}
	
	private void initLabels() {
		this.operatorLab = new MyLabel(pcfg.getLabels().element("operatorlab"));
		operatorLab.setText(Login.currentUserId);
		this.customerIdLab = new MyLabel(pcfg.getLabels().element("customeridlab"));
		this.customerNameLab = new MyLabel(pcfg.getLabels().element("customernamelab"));
		this.documentIdLab = new MyLabel(pcfg.getLabels().element("documentidlab"));
		// TODO
		this.documentIdLab.setText("ID");
		this.add(this.operatorLab);
		this.add(this.documentIdLab);
		this.add(this.customerIdLab);
		this.add(this.customerNameLab);
	}
	
	private void initButtons() {
		// 添加客户按钮
		addCustomerBtn = new MyButton(pcfg.getButtons().element("addcustomer"));
		add(addCustomerBtn);
		addCustomerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		});

		// 添加账户按钮
		addBtn = new MyButton(pcfg.getButtons().element("add"));
		this.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddAccountDialog(ERPConfig.getADDRECEIPTACCOUNT_DIALOG_CONFIG(),
						frame, CreatePaymentPanel.this);
			}
		});

		// 删除账户按钮
		deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
		this.add(deleteBtn);

		// 提交按钮
		commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认创建该付款单？", "创建付款单",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
				}
			}
		});
		this.add(commitBtn);

		// 取消按钮
		cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showShow();
			}
		});
		this.add(cancelBtn);
	}

	private void initCustomerFind() {
		customerFind = new MySpecialTextField(pcfg.getTextFields().element("customerfind"), this);
		add(customerFind);
	}

	public void createPayment() {
		if(checkCompleted()) {
			double total = 0;
			for(TransferLineItemVO vo: lists) {
				total += vo.account;
			}
			ResultMessage result = paymentController.create(new PaymentVO(documentIdLab.getText(),
					null, customerVO.id, customerVO.name, operatorLab.getText(), lists, total,
					DocumentStatus.NONCHECKED, false, DocumentType.PAYMENT));
			if(result == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(null, "付款单提交成功！");
				panel.showShow();
			} else{
				MyOptionPane.showMessageDialog(null, "付款单提交失败！");
			}
		} else{
			MyOptionPane.showMessageDialog(null, "请填入完整单据数据！");
		}
	}
	
	private boolean checkCompleted() {
		if(hasCustomer&&this.lists.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> result = customerController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i = 0; i < result.size(); ++i){
			CustomerVO vo = result.get(i);
			String str = vo.id+"-"+vo.name;
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
	
	

}
