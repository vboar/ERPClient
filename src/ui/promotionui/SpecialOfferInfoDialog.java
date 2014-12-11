package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.AddCommodityDialog;
import ui.util.AddCommodityLineItem;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.SpecialOfferVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.SpecialOfferBLService;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SpecialOfferInfoDialog extends JDialog implements AddCommodityLineItem{

	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton add;
	
	private MyButton delete;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private MyTextField total;
	
	private SpecialCommodityTablePane commodityTable;
	
	private JFrame frame;
	
	private DialogConfig cfg;
	
	private boolean isAdd = false;
	
	private SpecialOfferBLService controller;
	
	private ArrayList<CommodityLineItemVO> list;

	private SpecialOfferTablePane table;
	
	public SpecialOfferInfoDialog(JFrame frame, SpecialOfferTablePane table, boolean isAdd){
		super(frame, true);
		this.isAdd = isAdd;
		this.frame = frame;
		this.table = table;
		// 获得配置
		this.cfg = ERPConfig.getSPECIAL_OFFER_DIALOG_CONFIG();
		// 获得控制器
		this.controller = ControllerFactoryImpl.getInstance().getSpecialOfferController();
		this.list = new ArrayList<CommodityLineItemVO>();
		// 设置对话框基本属性
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()+ this.cfg.getY());
		// 初始组件
		this.initComponent();
	}

	public SpecialOfferInfoDialog(JFrame frame, SpecialOfferTablePane table,boolean isAdd, SpecialOfferVO vo){
		this(frame,table,isAdd);
		this.start.setDate(FrameUtil.getDateFormStr(vo.startTime));
		this.end.setDate(FrameUtil.getDateFormStr(vo.endTime));
		this.total.setText(Double.toString(vo.total));
		for(int i=0; i<vo.commodityList.size(); ++i){
			this.addCommodityLineItem(vo.commodityList.get(i));
		}
	}
	
	private void initComponent() {
		// 添加赠品表格
		this.initTable();
		// 添加按钮
		this.initButtons();
		// 添加标签
		this.add(new MyLabel(cfg.getLabels().element("list")));
		this.add(new MyLabel(cfg.getLabels().element("total")));
		this.add(new MyLabel(cfg.getLabels().element("starttime")));
		this.add(new MyLabel(cfg.getLabels().element("endtime")));
		// 添加日期选择器
		this.start = new MyDatePicker(cfg.getDatePicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatePicker().element("end"));
		this.add(this.start);
		this.add(this.end);
		// 添加输入框
		this.total = new MyTextField(cfg.getTextFields().element("total"));
		this.add(this.total);
	}

	private void initButtons() {
		this.commit = new MyButton(cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkCompleted()){
					createSpecialOfferVO();
				}else{
					MyOptionPane.showMessageDialog(frame, "请填入完整信息！");
				}
			}
		});
		this.cancel = new MyButton(cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					SpecialOfferInfoDialog.this.dispose();
				}
			}
		});
		this.add = new MyButton(cfg.getButtons().element("add"));
		this.delete = new MyButton(cfg.getButtons().element("delete"));
		this.add.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCommodityDialog(SpecialOfferInfoDialog.this, frame);
			}
		});
		this.delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(commodityTable.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认删除该商品？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择要删除的商品！");
				}
			}
		});
		this.add(this.commit);
		this.add(this.cancel);
		this.add(this.add);
		this.add(this.delete);
	}

	protected void createSpecialOfferVO() {
		try{
			String startTime = FrameUtil.getFormattedDate(this.start.getDate());
			String endTime = FrameUtil.getFormattedDate(this.end.getDate());
			double total = Double.parseDouble(this.total.getText());
			if(startTime.compareTo(endTime)>0){
				MyOptionPane.showMessageDialog(frame, "请输入有效时间区间！");
				return;
			}
			SpecialOfferVO vo = new SpecialOfferVO(controller.createId(),list,
					total,startTime,endTime,true);
			if(isAdd){
				ResultMessage result = this.controller.create(vo);
				if(result == ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(frame, "添加成功！");
				}else{
					MyOptionPane.showMessageDialog(frame, "添加失败！");
				}
			}else{
				ResultMessage result = this.controller.update(vo);
				if(result == ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(frame, "修改成功！");
				}else{
					MyOptionPane.showMessageDialog(frame, "修改失败！");
				}
			}
			dispose();
			table.updateData();
		}catch(NumberFormatException ex){
			MyOptionPane.showMessageDialog(frame, "请按正确格式输入数据！");
		}
	}

	private void initTable() {
		this.commodityTable = new SpecialCommodityTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.commodityTable);
	}
	
	/**
	 * 检查是否填写完整
	 * @return
	 */
	private boolean checkCompleted() {
		if((this.start.getDate()!=null)&&(this.end.getDate()!=null)&&(
				!this.total.getText().isEmpty())&&
				this.list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除一个商品
	 */
	public void delCommodity(){
		this.list.remove(this.commodityTable.getTable().getSelectedRow());
		this.commodityTable.deleteRow();
	}

	/**
	 * 添加一个商品
	 * @param vo
	 */
	@Override
	public void addCommodityLineItem(CommodityLineItemVO vo) {
		this.list.add(vo);
		this.commodityTable.addRow(vo);
	}
}
