package ui.presentui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.AddPresentLineItem;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PresentLineItemVO;
import vo.PresentVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.presentblservice.PresentBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 创建赠送单面板
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class CreatePresentPanel extends JPanel implements FuzzySearch, AddPresentLineItem{
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;
	
	private MyButton addCustomer;
	
	private MyLabel documentId;
	
	private MyLabel customerIdLab;
	
	private MyLabel customerNameLab;
	
	private PresentCommodityTablePane presentTable;
	
	private MySpecialTextField customerTxt;
	
	private CustomerVO customerVO;
	
	private ArrayList<PresentLineItemVO> commoditylist;
	
	private HashMap<String,CustomerVO> customerlist;
	
	private boolean hasCustomer = false;
	
	private PanelConfig pcfg;
	
	private Image bg;
	
	private PresentPanel panel;
	
	private JFrame frame;
	
	private PresentBLService presentController;
	
	private CustomerBLService customerController;

	/**
	 * 构造函数
	 * @param frame 主窗口
	 * @param panel 赠送单面板
	 */
	public CreatePresentPanel(JFrame frame,PresentPanel panel) {
		this.frame = frame;
		this.panel = panel;
		this.commoditylist = new ArrayList<PresentLineItemVO>();
		this.customerlist = new HashMap<String,CustomerVO>();
		this.presentController = ControllerFactoryImpl.getInstance().getPresentController();
		this.customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = this.pcfg.getBg();
		// 设置面板基本属性
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	/**
	 * 初始化组件
	 */
	private void initComponent() {
		this.initLabels();
		this.initButtons();
		// 初始化赠品列表
		this.presentTable = new PresentCommodityTablePane(new TableConfig(pcfg.getTablepane()));
		this.add(this.presentTable);
		// 初始化客户信息输入框
		this.customerTxt = new MySpecialTextField(pcfg.getTextFields().element("customerinfo"),this);
		this.add(this.customerTxt);		
	}
	
	/**
	 * 初始化标签
	 */
	private void initLabels(){
		this.customerIdLab = new MyLabel(pcfg.getLabels().element("customeridlab"));
		this.customerNameLab = new MyLabel(pcfg.getLabels().element("customernamelab"));
		this.documentId = new MyLabel(pcfg.getLabels().element("documentidlab"));
		this.documentId.setText(this.presentController.createId());
		this.add(new MyLabel(pcfg.getLabels().element("customeridlab")));
		this.add(new MyLabel(pcfg.getLabels().element("customernamelab")));
		this.add(new MyLabel(pcfg.getLabels().element("tip")));
		this.add(this.documentId);
		this.add(this.customerIdLab);
		this.add(this.customerNameLab);
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButtons(){
		// 添加客户按钮
		this.addCustomer = new MyButton(pcfg.getButtons().element("addcustomer"));
		this.addCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(customerTxt.getText()!=null){
					customerVO = customerlist.get(customerTxt.getText());
					if(customerVO!=null){
						customerIdLab.setText(customerVO.id);
						customerNameLab.setText(customerVO.name);
						hasCustomer = true;
					}else{
						MyOptionPane.showMessageDialog(frame, "请重新选择客户！");
					}
				}
			}
		});
		this.add(this.addCustomer);
		// 添加赠品按钮
		this.addBtn = new MyButton(pcfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPresentCommodityDialog(CreatePresentPanel.this,frame);
			}
			
		});
		this.add(this.addBtn);
		// 删除赠品按钮
		this.deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
		this.deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(presentTable.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认删除该赠品？","删除赠品",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择要删除的赠品！");
				}
			}
			
		});
		this.add(this.deleteBtn);
		// 提交按钮
		this.commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认创建？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					createPresent();
				}
			}
		});
		// 取消按钮
		this.cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		this.cancelBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						panel.showList();
				}
			}
		});
		this.add(this.commitBtn);
		this.add(this.cancelBtn);
	}
	
	/**
	 * 创建赠送单
	 */
	public void createPresent(){
		if(this.checkCompleted()){
			ResultMessage result = this.presentController.create(new PresentVO(this.documentId.getText(),
					null,customerVO.id,customerVO.name,
					this.commoditylist,DocumentStatus.NONCHECKED,DocumentType.PRESENT,false));
			if(result == ResultMessage.SUCCESS){
				MyOptionPane.showMessageDialog(frame, "赠送单提交成功！");
				this.setVisible(false);
				this.panel.getListpanel().udpateData();
				this.panel.showList();
			}else{
				MyOptionPane.showMessageDialog(frame, "赠送单提交失败！");
				this.panel.getListpanel().udpateData();
				this.panel.showList();
			}
		}else{
			MyOptionPane.showMessageDialog(frame, "请填入完整单据数据！");
		}
	}
	
	/**
	 * 检查是否填写完整
	 * @return
	 */
	private boolean checkCompleted() {
		if(hasCustomer&&this.commoditylist.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 删除一个商品
	 */
	public void delCommodity(){
		this.commoditylist.remove(this.presentTable.getTable().getSelectedRow());
		this.presentTable.deleteRow();
	}
	
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> list = this.customerController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i=0; i<list.size(); ++i){
			CustomerVO vo = list.get(i);
			String str = vo.id+"-"+vo.name;
			strs.add(str);
			this.customerlist.put(str, vo);
		}
		return strs;
	}

	@Override
	public void addPresentLineItem(PresentLineItemVO vo) {
		for(int i=0; i<commoditylist.size();++i){
			if(commoditylist.get(i).id.equals(vo.id)){
				MyOptionPane.showMessageDialog(frame, "已添加过该商品！");
				return;
			}
		}
		this.commoditylist.add(vo);
		this.presentTable.addRow(vo);
	}

}
