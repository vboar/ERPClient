package ui.purchaseui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
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
import businesslogicservice.writeoffblservice.WriteoffBLService;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class PurchaseDocument extends JDialog {

	/**
	 * 审批-0,红冲-1,红冲并复制-2;
	 */
	private int type;

	private DialogConfig cfg;

	private JFrame frame;
	private Image bg;
	private PurchaseDocumentPanel panel;

	private PurchaseVO vo;
	private WriteoffBLService writeoffCtrl;

	public PurchaseDocument(JFrame frame, int type, PurchaseVO vo) {
		super(frame, true);
		this.frame = frame;
		this.type = type;
		this.vo = vo;
		this.cfg = ERPConfig.getPURCHASEDOCUMENT_DIALOG_CONFIG();
		this.writeoffCtrl = ControllerFactoryImpl.getInstance().getWriteoffController();
		this.bg = cfg.getBg();
		this.panel = new PurchaseDocumentPanel();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getY(), cfg.getW(), cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()
				+ this.cfg.getY());
		this.add(panel);
		this.repaint();
		this.setVisible(true);
	}

	protected class PurchaseDocumentPanel extends JPanel implements
			FuzzySearch, AddCommodityLineItem {

		private MyButton addBtn;
		private MyButton deleteBtn;
		private MyButton passBtn;
		private MyButton rejectBtn;
		private MyButton copy;
		private MyButton writeoff;
		private MyButton cancelBtn;

		private MyLabel documentId;
		private MyLabel customerIdLab;
		private MyLabel customerNameLab;
		private MyLabel totalLab;

		private MyComboBox storage;

		private MySpecialTextField customerTxt;
		private MyTextArea remarkTxt;

		private CommodityTablePane commodityTable;
		
		private boolean hasCustomer = true;
		private double totalPrice = 0;
		private CustomerVO customerVO;
		private HashMap<String, CustomerVO> customerlist;
		private ArrayList<CommodityLineItemVO> commoditylist;

		private PurchaseBLService purchaseCtrl;
		private CustomerBLService customerCtrl;

		public PurchaseDocumentPanel() {
			this.customerVO = new CustomerVO();
			this.customerVO.id = vo.customerId;
			this.customerVO.name = vo.customerName;
			this.customerlist = new HashMap<String,CustomerVO>();
			this.commoditylist = vo.saleList;
			this.customerCtrl = ControllerFactoryImpl.getInstance()
					.getCustomerController();
			this.purchaseCtrl = ControllerFactoryImpl.getInstance()
					.getPurchaseController();
			this.setLayout(null);
			this.setBounds(0, 0, cfg.getW(), cfg.getH());
			this.initComponent();
			this.repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
		}

		private void initComponent() {
			// 添加按钮
			this.initButtons();
			// 添加输入框
			this.initText();
			// 添加仓库选择框
			this.storage = new MyComboBox(cfg.getComboboxes()
					.element("storage"));
			this.storage.setSelectedIndex(0);
			if (type == 0 || type == 1) {
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
			this.customerTxt = new MySpecialTextField(cfg.getTextFields()
					.element("findcustomer"), this);
			this.customerTxt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
				}
			});
			// 若为审批或红冲则不可编辑
			if (type == 0 || type == 1) {
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
			this.customerIdLab = new MyLabel(cfg.getLabels().element(
					"customerid"));
			this.customerIdLab.setText(vo.customerId);
			this.customerNameLab = new MyLabel(cfg.getLabels().element(
					"customername"));
			this.customerNameLab.setText(vo.customerName);
			this.add(documentId);
			this.add(customerIdLab);
			this.add(customerNameLab);
		}

		private void initButtons() {
			// 添加商品按钮
			this.addBtn = new MyButton(cfg.getButtons().element("add"));
			this.addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddTradeCommodityDialog(PurchaseDocumentPanel.this,
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
			// 取消按钮
			this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
			this.cancelBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = MyOptionPane.showConfirmDialog(frame,
							"是否取消当前操作？", "确认提示", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						PurchaseDocument.this.dispose();
					}
				}
			});
			// 复制按钮
			this.copy = new MyButton(cfg.getButtons().element("copy"));
			this.copy.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkCompleted()) {
						int result = MyOptionPane.showConfirmDialog(frame,
								"确认创建？", "确认提示", MyOptionPane.YES_NO_OPTION,
								MyOptionPane.QUESTION_MESSAGE);
						if (result == MyOptionPane.YES_OPTION) {
							// 创建一张进货单
							if(writeoffCtrl.create(vo.receiptType, vo.id)==ResultMessage.SUCCESS){
								createPurchase();
							}else{
								MyOptionPane.showMessageDialog(frame, "红冲操作失败！");
							}
						}
					} else {
						MyOptionPane.showMessageDialog(frame, "请填入完整单据信息！");
					}
				}
			});
			// 审批通过按钮
			this.passBtn = new MyButton(cfg.getButtons().element("check"));
			this.passBtn.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 审批通过
				}
			});
			// 审批不同按钮
			this.rejectBtn = new MyButton(cfg.getButtons().element("reject"));
			this.rejectBtn.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO 审批不通过
				}
			});
			// 红冲按钮
			this.writeoff = new MyButton(cfg.getButtons().element("writeoff"));
			this.writeoff.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 红冲
					writeoffCtrl.create(vo.receiptType, vo.id);			
				}
			});
			if (type == 0) {
				this.add(passBtn);
				this.add(rejectBtn);
				this.add(cancelBtn);
			} else if (type == 1) {
				this.add(writeoff);
				this.add(cancelBtn);
			} else {
				this.add(addBtn);
				this.add(deleteBtn);
				this.add(copy);
				this.add(cancelBtn);
			}
		}

		protected void createPurchase() {
			String id = this.documentId.getText();
			String storage = (String) this.storage.getSelectedItem();
			String remark = this.remarkTxt.getArea().getText();
			PurchaseVO vo = new PurchaseVO(id, null, customerVO.id,
					customerVO.name, null, storage, commoditylist, totalPrice,
					remark, DocumentStatus.NONCHECKED, false,
					DocumentType.PURCHASE);
			ResultMessage result = purchaseCtrl.add(vo);
			if (result == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(frame, "创建进货红冲复制单成功！");
			} else {
				MyOptionPane.showMessageDialog(frame, "创建红冲复制单失败！");
			}
			PurchaseDocument.this.dispose();
		}

		/**
		 * 检查单据是否填写完整
		 * 
		 * @return
		 */
		private boolean checkCompleted() {
			if (hasCustomer && commoditylist.size() > 0) {
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
			commoditylist.add(vo);
			this.commodityTable.addRow(vo);
			// 刷新界面总价
			totalPrice = totalPrice + (vo.price * vo.number);
			this.totalLab.setText(Double.toString(totalPrice));
		}

		public void delCommodity() {
			commoditylist.remove(commodityTable.getTable().getSelectedRow());
			commodityTable.deleteRow();
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
	}
}
