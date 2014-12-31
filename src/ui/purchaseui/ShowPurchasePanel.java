package ui.purchaseui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.util.AddCommodityLineItem;
import ui.util.AddTradeCommodityDialog;
import ui.util.CommodityTablePane;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextArea;
import ui.util.DocumentShowDialogExtra;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.CustomerVO;
import vo.PurchaseVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPurchasePanel extends PurchaseDocumentPanel implements
		FuzzySearch, AddCommodityLineItem, DocumentShowDialogExtra{

	private int type;

	private boolean hasCustomer = true;
	private double totalPrice = 0;
	private CustomerVO customerVO;
	private PurchaseVO vo;
	private HashMap<String, CustomerVO> customerlist;
	private ArrayList<CommodityLineItemVO> commoditylist;

	private PurchaseBLService purchaseCtrl;
	private CustomerBLService customerCtrl;

	public ShowPurchasePanel(JFrame frame, PurchaseVO vo, int type, boolean isreturn) {
		super(frame, isreturn);
		this.type = type;
		this.vo = vo;
		this.customerVO = new CustomerVO();
		this.customerVO.id = vo.customerId;
		this.customerVO.name = vo.customerName;
		this.customerlist = new HashMap<String, CustomerVO>();
		this.commoditylist = vo.saleList;
		this.totalPrice = vo.total;
		this.customerCtrl = ControllerFactoryImpl.getInstance()
				.getCustomerController();
		this.purchaseCtrl = isreturn? ControllerFactoryImpl.getInstance().getPurchaseReturnController()
				: ControllerFactoryImpl.getInstance().getPurchaseController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = isreturn ? cfg.getBg1(): cfg.getBg();
		this.setLayout(null);
		this.setBounds(0, 0, cfg.getW(), cfg.getH());
		this.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()));
		this.initComponent();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	protected void initComponent() {
		// 添加按钮
		this.initButtons();
		// 添加输入框
		this.initText();
		// 添加仓库选择框
		this.storage = new MyComboBox(cfg.getComboboxes().element("storage"));
		this.storage.setSelectedIndex(0);
		if (type == 1) {
			this.storage.setFocusable(false);
		}
		this.add(storage);
		// 添加标签
		this.initLabels();
		// 填写进货列表表格
		this.commodityTable = new CommodityTablePane(new TableConfig(
				cfg.getTablepane()));
		for (int i = 0; i < vo.saleList.size(); ++i) {
			this.commodityTable.addRow(vo.saleList.get(i));
		}
		this.add(commodityTable);
	}

	private void initText() {
		// 备注框
		this.remarkTxt = new MyTextArea(cfg.getTextarea().element("remark"));
		this.remarkTxt.getArea().setText(vo.remark);
		// 客户信息模糊查找输入框
		this.customerTxt = new MySpecialTextField(cfg.getTextFields().element(
				"findcustomer"), this);
		this.customerTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					showCustomerInfo();
			}
		});
		// 若为审批或红冲则不可编辑
		if (type == 1) {
			this.remarkTxt.getArea().setEditable(false);
			this.add(remarkTxt);

		} else {
			this.add(remarkTxt);
			this.add(customerTxt);
		}
	}

	private void initLabels() {
		// 填写单据编号标签
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.documentId.setText(vo.id);
		// 填写总价
		this.totalLab = new MyLabel(cfg.getLabels().element("total"));
		this.totalLab.setText(Double.toString(vo.total));
		this.add(totalLab);
		// 填写客户信息
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customerid"));
		this.customerIdLab.setText(vo.customerId);
		this.customerNameLab = new MyLabel(cfg.getLabels().element(
				"customername"));
		this.customerNameLab.setText(vo.customerName);
		this.tip = new MyLabel(cfg.getLabels().element("tip"));
		this.add(documentId);
		this.add(customerIdLab);
		this.add(customerNameLab);
		if(type!=1)
			this.add(tip);
	}

	private void initButtons() {
		// 添加客户按钮
		this.addCustomer = new MyButton(cfg.getButtons().element("addcustomer"));
		this.addCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCustomerInfo();
			}
		});
		// 添加商品按钮
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddTradeCommodityDialog(ShowPurchasePanel.this,
						frame, true);
			}
		});
		// 删除商品按钮
		this.deleteBtn = new MyButton(cfg.getButtons().element("delete"));
		this.deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (commodityTable.isSelected()) {
					int result = MyOptionPane.showConfirmDialog(frame,
							"确认删除该商品？", "删除商品", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						delCommodity();
					}
				} else {
					MyOptionPane.showMessageDialog(frame, "请选择要删除的商品！");
				}
			}
		});
		if(type!=1){
			this.add(addCustomer);
			this.add(addBtn);
			this.add(deleteBtn);
		}
	}
	
	protected void showCustomerInfo() {
		if (customerTxt.getText() != null) {
			customerVO = customerlist.get(customerTxt.getText());
			if (customerVO != null) {
				// 显示客户名和ID
				customerIdLab.setText(customerVO.id);
				customerNameLab.setText(customerVO.name);
				// 当前已添加客户
				hasCustomer = true;
			} else {
				MyOptionPane.showMessageDialog(frame, "请重新选择客户！");
			}
		}
	}
	
	@Override
	public void addCommodityLineItem(CommodityLineItemVO vo) {
		for (int i = 0; i < commoditylist.size(); ++i) {
			if (commoditylist.get(i).id.equals(vo.id)) {
				MyOptionPane.showMessageDialog(frame, "已添加过该商品！");
				return;
			}
		}
		commoditylist.add(vo);
		this.commodityTable.addRow(vo);
		// 刷新界面总价
		totalPrice = totalPrice + (vo.price * vo.number);
		this.totalLab.setText(Double.toString(totalPrice));
	}

	public void delCommodity() {
		CommodityLineItemVO vo = this.commodityTable.getSelectedVO();
		this.totalPrice = this.totalPrice - vo.number*vo.price;
		this.totalLab.setText(Double.toString(totalPrice));
		this.commoditylist.remove(this.commodityTable.getTable()
				.getSelectedRow());
		this.commodityTable.deleteRow();
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> list = customerCtrl.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			CustomerVO vo = list.get(i);
			String str = vo.id + "-" + vo.name;
			strs.add(str);
			customerlist.put(str, vo);
		}
		return strs;
	}
	
	@Override
	public boolean checkCompleted() {
		if (hasCustomer && commoditylist.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public DocumentType getDocumentType() {
		return vo.receiptType;
	}

	@Override
	public String getDocumentID() {
		return vo.id;
	}

	@Override
	public void createCopyDocument() {
		String id = this.documentId.getText();
		String storage = (String) this.storage.getSelectedItem();
		String remark = this.remarkTxt.getArea().getText();
		PurchaseVO vo = new PurchaseVO(id, id, null, customerVO.id,
				customerVO.name, null, storage, commoditylist, totalPrice,
				remark, DocumentStatus.NONCHECKED, false, true, true, DocumentType.PURCHASE);
		ResultMessage result = purchaseCtrl.add(vo);
		if (result == ResultMessage.SUCCESS) {
			MyOptionPane.showMessageDialog(frame, "创建进货红冲复制单成功！");
		} else {
			MyOptionPane.showMessageDialog(frame, "创建红冲复制单失败！");
		}
	}
}