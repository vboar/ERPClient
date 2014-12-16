package ui.purchaseui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class CreatePurchasePanel extends JPanel implements FuzzySearch,
		AddCommodityLineItem {

	private Image bg;

	private MyButton addBtn;
	private MyButton deleteBtn;
	private MyButton commitBtn;
	private MyButton cancelBtn;
	private MyButton addCustomer;

	private MyLabel documentId;
	private MyLabel customerIdLab;
	private MyLabel customerNameLab;
	private MyLabel totalLab;

	private MyComboBox storage;

	private MySpecialTextField customerTxt;
	private MyTextArea remarkTxt;

	private CommodityTablePane commodityTable;

	private boolean hasCustomer = false;
	private double totalPrice = 0;
	private CustomerVO customerVO;
	private HashMap<String, CustomerVO> customerlist;
	private ArrayList<CommodityLineItemVO> commoditylist;

	private PurchaseBLService purchaseCtrl;
	private CustomerBLService customerCtrl;

	private JFrame frame;

	private CreatePurchaseDialog dialog;

	private PanelConfig cfg;
	
	public CreatePurchasePanel(JFrame frame, CreatePurchaseDialog dialog) {
		this.frame = frame;
		this.dialog = dialog;
		this.customerlist = new HashMap<String,CustomerVO>();
		this.commoditylist = new ArrayList<CommodityLineItemVO>();
		this.customerCtrl = ControllerFactoryImpl.getInstance()
				.getCustomerController();
		this.purchaseCtrl = ControllerFactoryImpl.getInstance()
				.getPurchaseController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = cfg.getBg();
		// 设置对话框基本属性
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}

	private void initComponent() {
		// 添加按钮
		this.initButtons();
		// 添加标签
		this.initLabels();
		// 添加仓库选择框
		this.storage = new MyComboBox(cfg.getComboboxes().element("storage"));
		this.add(storage);
		// 添加进货表格
		this.commodityTable = new CommodityTablePane(new TableConfig(
				cfg.getTablepane()));
		this.add(commodityTable);
		// 备注框
		this.remarkTxt = new MyTextArea(cfg.getTextarea().element("remark"));
		this.add(remarkTxt);
		// 客户信息模糊查找输入框
		this.customerTxt = new MySpecialTextField(cfg.getTextFields().element(
				"findcustomer"), this);
		this.add(customerTxt);
	}

	private void initLabels() {
		// 单据编号标签
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.documentId.setText(purchaseCtrl.createId());
		// 总价
		this.totalLab = new MyLabel(cfg.getLabels().element("total"));
		this.totalLab.setText(Double.toString(totalPrice));
		this.add(totalLab);
		// 客户信息
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customerid"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element(
				"customername"));
		this.add(documentId);
		this.add(customerIdLab);
		this.add(customerNameLab);
	}

	private void initButtons() {
		// 添加客户按钮
		this.addCustomer = new MyButton(cfg.getButtons().element("addcustomer"));
		this.addCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		});
		// 添加商品按钮
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddTradeCommodityDialog(CreatePurchasePanel.this, frame,true);
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
		// 取消按钮
		this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？",
						"确认提示", MyOptionPane.YES_NO_OPTION,
						MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					dialog.dispose();
				}
			}
		});
		// 提交按钮
		this.commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkCompleted()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认创建？",
							"确认提示", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						// 创建一张进货单
						createPurchase();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请填入完整单据信息！");
				}
			}
		});
		this.add(addCustomer);
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(commitBtn);
		this.add(cancelBtn);
	}

	protected void createPurchase() {
		String id = this.documentId.getText();
		String storage = (String)this.storage.getSelectedItem();
		String remark = this.remarkTxt.getArea().getText();
		PurchaseVO vo = new PurchaseVO(id,null,customerVO.id,customerVO.name,null,storage,
				commoditylist, totalPrice,remark,DocumentStatus.NONCHECKED, false,
				DocumentType.PURCHASE);
		ResultMessage result = purchaseCtrl.add(vo);
		if(result == ResultMessage.SUCCESS){
			MyOptionPane.showMessageDialog(frame, "创建进货单成功！");
			dialog.updateData();
		}else{
			MyOptionPane.showMessageDialog(frame, "创建进货单失败！");
		}
		dialog.dispose();
	}

	/**
	 * 检查单据是否填写完整
	 * @return
	 */
	private boolean checkCompleted() {
		if(hasCustomer&&this.commoditylist.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addCommodityLineItem(CommodityLineItemVO vo) {
		for (int i = 0; i < commoditylist.size(); ++i) {
			if (commoditylist.get(i).id.equals(vo.id)) {
				MyOptionPane.showMessageDialog(frame, "已添加过该商品！");
				return;
			}
		}
		this.commoditylist.add(vo);
		this.commodityTable.addRow(vo);
		// 刷新界面总价
		this.totalPrice = this.totalPrice + (vo.price * vo.number);
		this.totalLab.setText(Double.toString(totalPrice));
	}

	public void delCommodity() {
		this.commoditylist.remove(this.commodityTable.getTable()
				.getSelectedRow());
		this.commodityTable.deleteRow();
	}

	protected void updateData() {
		dialog.updateData();
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> list = this.customerCtrl.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			CustomerVO vo = list.get(i);
			String str = vo.id + "-" + vo.name;
			strs.add(str);
			this.customerlist.put(str, vo);
		}
		return strs;
	}

}
