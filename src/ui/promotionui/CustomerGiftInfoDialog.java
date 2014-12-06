package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.presentui.PresentCommodityTablePane;
import ui.util.MyButton;
import ui.util.MyCheckBox;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MyTextField;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

/**
 * 添加针对客户级别促销策略对话框
 * @author JanelDQ
 * @date 2014/12/6
 */
@SuppressWarnings("serial")
public class CustomerGiftInfoDialog extends JDialog{

	private MyComboBox level;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyCheckBox presents;
	
	private MyCheckBox discount;
	
	private MyCheckBox voucher;
	
	private MyTextField dicountTxt;
	
	private MyTextField voucherTxt;
	
	private MyButton add;
	
	private MyButton delete;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private PresentCommodityTablePane presentsTable;
	
	private DialogConfig cfg;
	
	private boolean isAdd = false;
	
	public CustomerGiftInfoDialog(JFrame frame, boolean isAdd) {
		super(frame, true);
		this.isAdd = isAdd;
		this.cfg = ERPConfig.getLEVEL_GIFT_DIALOG_CONFIG();
		// 设置对话框基本属性
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()+ this.cfg.getY());
		// 初始组件
		this.initComponent();
		this.setVisible(true);
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
		this.start = new MyDatePicker(cfg.getDatePicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatePicker().element("end"));
		this.add(this.start);
		this.add(this.end);
		// 添加单选框
		this.presents = new MyCheckBox(cfg.getCheckBox().element("presents"));
		this.discount = new MyCheckBox(cfg.getCheckBox().element("discount"));
		this.voucher = new MyCheckBox(cfg.getCheckBox().element("voucher"));
		this.add(this.presents);
		this.add(this.discount);
		this.add(this.voucher);
		// 添加输入框
		this.dicountTxt = new MyTextField(cfg.getTextFields().element("discount"));
		this.voucherTxt = new MyTextField(cfg.getTextFields().element("voucher"));
		this.add(this.dicountTxt);
		this.add(this.voucherTxt);
	}
	
	/**
	 * 初始化赠品列表
	 */
	public void initTable(){
		this.presentsTable = new PresentCommodityTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.presentsTable);
	}
	
	/**
	 * 初始化按钮
	 */
	public void initButtons(){
		this.commit = new MyButton(cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.cancel = new MyButton(cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add = new MyButton(cfg.getButtons().element("add"));
		this.delete = new MyButton(cfg.getButtons().element("delete"));
		this.add.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(this.commit);
		this.add(this.cancel);
		this.add(this.add);
		this.add(this.delete);
	}
	
}
