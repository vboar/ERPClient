package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import ui.presentui.AddPresentCommodityDialog;
import ui.presentui.PresentCommodityTablePane;
import ui.util.AddCommodityLineItem;
import ui.util.DatePickerGroup;
import ui.util.EditDialog;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyCheckBox;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.CustomerGiftVO;
import vo.PresentLineItemVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.CustomerGiftBLService;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

/**
 * 添加针对客户级别促销策略对话框
 * 
 * @author JanelDQ
 * @date 2014/12/6
 */
@SuppressWarnings("serial")
public class CustomerGiftInfoDialog extends EditDialog implements
		AddCommodityLineItem {

	private MyComboBox level;

	private DatePickerGroup start;

	private DatePickerGroup end;

	private MyCheckBox presents;

	private MyCheckBox discount;

	private MyCheckBox voucher;

	private MyTextField discountTxt;

	private MyTextField voucherTxt;

	private MyButton add;

	private MyButton delete;

	private MyButton commit;

	private MyButton cancel;

	private PresentCommodityTablePane presentsTable;

	private CustomerTablePane table;

	private DialogConfig cfg;

	private boolean isAdd = false;

	private ArrayList<PresentLineItemVO> commoditylist = null;

	private CustomerGiftBLService controller;

	public CustomerGiftInfoDialog(final JFrame frame, CustomerTablePane table,
			boolean isAdd) {
		super(frame);
		this.table = table;
		this.isAdd = isAdd;
		this.frame = frame;
		// 获得配置
		this.cfg = ERPConfig.getLEVEL_GIFT_DIALOG_CONFIG();
		// 获得控制器
		this.controller = ControllerFactoryImpl.getInstance()
				.getCustomerGiftController();
		this.commoditylist = new ArrayList<PresentLineItemVO>();
		// 设置对话框基本属性
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()
				+ this.cfg.getY());
		// 初始组件
		this.initComponent();
	}

	public CustomerGiftInfoDialog(JFrame frame, CustomerTablePane table,
			boolean isAdd, CustomerGiftVO vo) {
		this(frame, table, isAdd);
		this.start.setDate(FrameUtil.getDateFormStr(vo.startTime));
		this.end.setDate(FrameUtil.getDateFormStr(vo.endTime));
		this.voucherTxt.setText(Double.toString(vo.voucher));
		this.discountTxt.setText(Double.toString(vo.discount));
		this.voucherTxt.setEditable(true);
		this.discountTxt.setEditable(true);
		this.voucher.setSelected(true);
		this.discount.setSelected(true);
		this.presents.setSelected(true);
		this.voucherTxt.setFocusable(true);
		this.discountTxt.setFocusable(true);
		for (int i = 0; i < vo.giftInfo.size(); ++i) {
			this.addPresentLineItem(vo.giftInfo.get(i));
		}
		this.repaint();
	}

	private void initComponent() {
		// 添加赠品表格
		this.initTable();
		// 添加按钮
		this.initButtons();
		// 添加复选框
		this.level = new MyComboBox(cfg.getComboboxes().element("level"));
		this.add(this.level);
		// 添加标签
		this.add(new MyLabel(cfg.getLabels().element("level")));
		this.add(new MyLabel(cfg.getLabels().element("starttime")));
		this.add(new MyLabel(cfg.getLabels().element("endtime")));
		// 添加日期选择器
		this.start = new DatePickerGroup(cfg.getDatePicker().element("start"));
		this.end = new DatePickerGroup(cfg.getDatePicker().element("end"));
		this.add(this.start);
		this.add(this.end);
		// 添加单选框
		this.presents = new MyCheckBox(cfg.getCheckBox().element("presents"));
		this.presents.setSelected(true);
		this.presents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (presents.isSelected()) {
					presentsTable.setVisible(true);
					add.setVisible(true);
					delete.setVisible(true);
				} else {
					presentsTable.setVisible(false);
					add.setVisible(false);
					delete.setVisible(false);
				}
				CustomerGiftInfoDialog.this.repaint();
			}
		});
		this.discount = new MyCheckBox(cfg.getCheckBox().element("discount"));
		this.discount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (discount.isSelected()) {
					discountTxt.setFocusable(true);
					discountTxt.setEditable(true);
				} else {
					discountTxt.setText("");
					discountTxt.setFocusable(false);
				}
				CustomerGiftInfoDialog.this.repaint();
			}
		});
		this.voucher = new MyCheckBox(cfg.getCheckBox().element("voucher"));
		this.voucher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (voucher.isSelected()) {
					voucherTxt.setFocusable(true);
					voucherTxt.setEditable(true);
				} else {
					voucherTxt.setText("");
					voucherTxt.setFocusable(false);
				}
				CustomerGiftInfoDialog.this.repaint();
			}
		});
		this.add(this.presents);
		this.add(this.discount);
		this.add(this.voucher);
		// 添加输入框
		this.discountTxt = new MyTextField(cfg.getTextFields().element(
				"discount"));
		this.voucherTxt = new MyTextField(cfg.getTextFields()
				.element("voucher"));
		this.discountTxt.setFocusable(false);
		this.voucherTxt.setFocusable(false);
		this.add(this.discountTxt);
		this.add(this.voucherTxt);
	}

	/**
	 * 初始化赠品列表
	 */
	public void initTable() {
		this.presentsTable = new PresentCommodityTablePane(new TableConfig(
				cfg.getTablepane()));
		this.add(this.presentsTable);
	}

	/**
	 * 初始化按钮
	 */
	public void initButtons() {
		this.commit = new MyButton(cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkCompleted()) {
					createCustomerGiftVO();
				} else {
					MyOptionPane.showMessageDialog(frame, "请填入完整信息！");
				}
			}
		});
		this.cancel = new MyButton(cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？",
						"确认提示", MyOptionPane.YES_NO_OPTION,
						MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					CustomerGiftInfoDialog.this.dispose();
				}
			}
		});
		this.add = new MyButton(cfg.getButtons().element("add"));
		this.delete = new MyButton(cfg.getButtons().element("delete"));
		this.add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPresentCommodityDialog(CustomerGiftInfoDialog.this,
						frame);
			}
		});
		this.delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (presentsTable.isSelected()) {
					int result = MyOptionPane.showConfirmDialog(frame,
							"确认删除该赠品？", "确认提示", MyOptionPane.YES_NO_OPTION,
							MyOptionPane.QUESTION_MESSAGE);
					if (result == MyOptionPane.YES_OPTION) {
						delCommodity();
					}
				} else {
					MyOptionPane.showMessageDialog(frame, "请选择要删除的赠品！");
				}
			}
		});
		this.add(this.commit);
		this.add(this.cancel);
		this.add(this.add);
		this.add(this.delete);
	}

	/**
	 * 创建一个促销策略
	 */
	private void createCustomerGiftVO() {
		try {
			int level = this.level.getSelectedIndex();
			double discount = this.discount.isSelected() ? Double
					.parseDouble(this.discountTxt.getText()) : 0;
			double voucher = this.voucher.isSelected() ? Double
					.parseDouble(this.voucherTxt.getText()) : 0;
			String startTime = this.start.getFormatedDate();
			String endTime = this.end.getFormatedDate();
			if (startTime.compareTo(endTime) > 0) {
				MyOptionPane.showMessageDialog(frame, "请输入有效时间区间！");
				return;
			}
			CustomerGiftVO vo = new CustomerGiftVO(controller.createId(),
					level, commoditylist, discount, voucher, startTime,
					endTime, true);
			if (isAdd) {
				ResultMessage result = this.controller.create(vo);
				if (result == ResultMessage.SUCCESS) {
					MyOptionPane.showMessageDialog(frame, "添加成功！");
				} else {
					MyOptionPane.showMessageDialog(frame, "添加失败！");
				}
			} else {
				ResultMessage result = this.controller.update(vo);
				System.out.println(result);
				if (result == ResultMessage.SUCCESS) {
					MyOptionPane.showMessageDialog(frame, "修改成功！");
				} else {
					MyOptionPane.showMessageDialog(frame, "修改失败！");
				}
			}
			this.table.updateData();
			dispose();
		} catch (NumberFormatException ex) {
			MyOptionPane.showMessageDialog(frame, "请按正确格式输入数据！");
		}
	}

	/**
	 * 检查是否填写完整
	 * 
	 * @return
	 */
	private boolean checkCompleted() {
		if ((this.start.getDate() == null)|| (this.end.getDate() == null)
				|| (this.presents.isSelected()&&this.commoditylist.size()==0)
				|| (this.discount.isSelected()&&this.discount.getText().isEmpty()) 
				|| (this.voucher.isSelected()&&this.voucher.getText().isEmpty())
				||(!this.presents.isSelected()&&!this.discount.isSelected()&&
						!this.voucher.isSelected())) {
			return false;
		}
		return true;
	}

	/**
	 * 添加一个商品
	 */
	public void addPresentLineItem(PresentLineItemVO vo) {
		this.commoditylist.add(vo);
		this.presentsTable.addRow(vo);
	}

	/**
	 * 删除一个商品
	 */
	public void delCommodity() {
		this.commoditylist.remove(this.presentsTable.getTable()
				.getSelectedRow());
		this.presentsTable.deleteRow();
	}

	@Override
	public void addCommodityLineItem(CommodityLineItemVO vo) {
		PresentLineItemVO pvo = new PresentLineItemVO(vo.id, vo.name, vo.model, vo.number);
		this.commoditylist.add(pvo);
		this.presentsTable.addRow(pvo);
	}
}
