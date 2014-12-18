package ui.presentui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.util.AddPresentLineItem;
import ui.util.DocumentWriteoffAndCopy;
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
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPresentPanel extends PresentDocumentPanel implements FuzzySearch,
	AddPresentLineItem, DocumentWriteoffAndCopy{
	
	private int type;
	
	private PresentVO vo;	
	private CustomerVO customerVO;	
	private ArrayList<PresentLineItemVO> commoditylist;	
	private HashMap<String,CustomerVO> customerlist;
	private boolean hasCustomer = false;
	
	private PresentBLService presentController;	
	private CustomerBLService customerController;

	/**
	 * 构造函数
	 * @param frame 主窗口
	 * @param panel 赠送单面板
	 */
	public ShowPresentPanel(JFrame frame,PresentVO vo, int type) {
		super(frame);
		this.vo = vo;
		this.type = type;
		this.commoditylist = vo.list;
		this.customerVO = new CustomerVO();
		this.customerVO.id = vo.customerId;
		this.customerVO.name = vo.customerName;
		this.customerlist = new HashMap<String,CustomerVO>();
		this.presentController = ControllerFactoryImpl.getInstance().getPresentController();
		this.customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = cfg.getBg();
		// 设置面板基本属性
		this.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()));
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
	
	/**
	 * 初始化组件
	 */
	protected void initComponent() {
		this.initLabels();
		this.initButtons();
		// 添加赠品列表
		this.presentTable = new PresentCommodityTablePane(new TableConfig(cfg.getTablepane()));
		for(int i=0; i<commoditylist.size();++i){
			this.presentTable.addRow(commoditylist.get(i));
		}
		this.presentTable.updateUI();
		this.add(this.presentTable);
		// 初始化客户信息输入框
		this.customerTxt = new MySpecialTextField(cfg.getTextFields().element("customerinfo"),this);
		if(type!=1) this.add(this.customerTxt);		
	}
	
	/**
	 * 初始化标签
	 */
	private void initLabels(){
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customeridlab"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element("customernamelab"));
		this.documentId = new MyLabel(cfg.getLabels().element("documentidlab"));
		this.documentId.setText( type!=2 ? vo.id : presentController.createId());
		this.customerIdLab.setText(vo.customerId);
		this.customerNameLab.setText(vo.customerName);
		this.add(this.documentId);
		this.add(this.customerIdLab);
		this.add(this.customerNameLab);
		if(type!=1) this.add(new MyLabel(cfg.getLabels().element("tip")));
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButtons(){
		// 添加客户按钮
		this.addCustomer = new MyButton(cfg.getButtons().element("addcustomer"));
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
		// 添加赠品按钮
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPresentCommodityDialog(ShowPresentPanel.this,frame);
			}
			
		});
		// 删除赠品按钮
		this.deleteBtn = new MyButton(cfg.getButtons().element("delete"));
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
		if(type!=1){
			this.add(this.addCustomer);
			this.add(this.addBtn);
			this.add(this.deleteBtn);
		}
	}
	
	/**
	 * 检查是否填写完整
	 * @return
	 */
	public boolean checkCompleted() {
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
		if(this.checkCompleted()){
			ResultMessage result = this.presentController.create(new PresentVO(this.documentId.getText(),
					null,customerVO.id,customerVO.name,
					this.commoditylist,DocumentStatus.NONCHECKED,DocumentType.PRESENT,false));
			if(result == ResultMessage.SUCCESS){
				MyOptionPane.showMessageDialog(frame, "赠送单提交成功！");
				this.setVisible(false);
			}else{
				MyOptionPane.showMessageDialog(frame, "赠送单提交失败！");
			}
		}else{
			MyOptionPane.showMessageDialog(frame, "请填入完整单据数据！");
		}
	}

}
