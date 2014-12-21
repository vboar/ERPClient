package ui.saleui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.util.AddCommodityLineItem;
import ui.util.AddTradeCommodityDialog;
import ui.util.CommodityTablePane;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyCheckBox;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextArea;
import ui.util.MyTextField;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.CustomerVO;
import vo.PromotionVO;
import vo.SaleVO;
import vo.UserVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.saleblservice.SaleBLService;
import businesslogicservice.userblservice.UserBLService;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class CreateSalePanel extends SaleDocumentPanel implements FuzzySearch, 
	AddCommodityLineItem,	CheckPromotion{

	private CreateSaleDialog dialog;
	private MyButton cancelBtn;
	private MyButton commitBtn;
	
	private boolean hasCustomer = false;	
	private double totalPrice = 0;	
	private CustomerVO customerVO;	
	private SaleVO saleVo;
	private ArrayList<PromotionVO> promotionlist;
	private HashMap<String,CustomerVO> customerlist;	
	private ArrayList<CommodityLineItemVO> commoditylist;
	
	private SaleBLService saleCtrl;	
	private CustomerBLService customerCtrl;
	private UserBLService userCtrl;
	
	public CreateSalePanel(JFrame frame, CreateSaleDialog dialog,boolean isreturn){
		super(frame,isreturn);
		this.frame = frame;	
		this.dialog = dialog;
		// 初始化数据成员
		this.saleVo = new SaleVO();
		this.customerlist = new HashMap<String,CustomerVO>();
		this.commoditylist = new ArrayList<CommodityLineItemVO>();
		this.promotionlist = new ArrayList<PromotionVO>();
		// 获得控制器
		this.saleCtrl = ControllerFactoryImpl.getInstance().getSaleController();
		this.customerCtrl = ControllerFactoryImpl.getInstance().getCustomerController();
		this.userCtrl = ControllerFactoryImpl.getInstance().getUserController();
		// 获得配置
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基本属性
		this.bg = cfg.getBg();
        this.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()));
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        // 初始化组件
        this.initComponent();
        // 刷新并设置可见
        this.repaint();
        this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
		
	private void initComponent() {		
		this.initLabels();
		this.initButtons();
		this.initComboBoxes();
		// 商品列表
		this.commodityTable = new CommodityTablePane(new TableConfig(cfg.getTables().element("commodity")));
		this.add(commodityTable);
		// 代金券单选框
		this.voucherBox = new MyCheckBox(cfg.getCheckbox().element("voucher"));
		this.voucherBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(voucherBox.isSelected()){
					voucherTxt.setFocusable(true);
				}else{
					voucherTxt.setText("");
					voucherTxt.setFocusable(false);
				}
			}
		});
		this.add(this.voucherBox);
		// 代金券输入框
		this.voucherTxt = new MyTextField(cfg.getTextFields().element("voucher"));
		this.voucherTxt.setFocusable(false);
		this.add(voucherTxt);
		// 备注框
		this.remarkTxt = new MyTextArea(cfg.getTextarea().element("remark"));
		this.add(remarkTxt);
		// 客户信息模糊查找输入框
		this.customerTxt = new MySpecialTextField(cfg.getTextFields().element("findcustomer"), this);
		this.add(customerTxt);
	}

	@SuppressWarnings("unchecked")
	private void initComboBoxes(){
		this.salesman = new MyComboBox(cfg.getComboboxes().element("salesman"));
		ArrayList<UserVO> list = userCtrl.fuzzyFindOperator("");
		for(int i=0; i<list.size(); i++){
			this.salesman.addItem(list.get(i).id);
		}
		this.salesman.setSelectedIndex(0);
		this.storage = new MyComboBox(cfg.getComboboxes().element("storage"));
		this.add(salesman);
		this.add(storage);
	}

	private void initLabels() {
		// 单据编号标签
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.documentId.setText(saleCtrl.createId());
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customerid"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element("customername"));
		this.promotionLab = new MyLabel(cfg.getLabels().element("showpromotion"));
		this.totalBeforeDiscountLab = new MyLabel(cfg.getLabels().element("totalbefore"));
		this.discountLab = new MyLabel(cfg.getLabels().element("discount"));
		this.totalLab = new MyLabel(cfg.getLabels().element("total"));
		this.add(promotionLab);
		this.add(customerIdLab);
		this.add(customerNameLab);
		this.add(documentId);
		this.add(discountLab);
		this.add(totalBeforeDiscountLab);
		this.add(totalLab);
		this.add(new MyLabel(cfg.getLabels().element("tip")));
	}
	
	private void initButtons() {
		// 添加客户按钮
		this.addCustomer = new MyButton(cfg.getButtons().element("addcustomer"));
		this.addCustomer.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(customerTxt.getText()!=null){
					customerVO = customerlist.get(customerTxt.getText());
					if(customerVO!=null){
						// 显示客户名和ID
						customerIdLab.setText(customerVO.id);
						customerNameLab.setText(customerVO.name);
						for(int i=0; i<salesman.getItemCount();++i){
							if(salesman.getItemAt(i).toString().equals(customerVO.salesman)){
								salesman.setSelectedIndex(i);
								break;
							}
						}
						// 当前已添加客户
						hasCustomer = true;
					}else{
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
				new AddTradeCommodityDialog(CreateSalePanel.this,frame,false);
			}
		});
		// 删除商品按钮
		this.deleteBtn = new MyButton(cfg.getButtons().element("delete"));
		this.deleteBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(commodityTable.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认删除该商品？","删除商品",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择要删除的商品！");
				}
			}
		});
		// 查看优惠按钮
		this.showPromotions = new MyButton(this.cfg.getButtons().element("showpromotion"));
		this.showPromotions.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// 已完成客户信息和商品的添加
				if(hasCustomer&&commoditylist.size()>0){
					// 显示选择促销策略对话框
					showChoosePromotionDialog();
				}else{
					MyOptionPane.showMessageDialog(frame, "请先填写客户及商品信息！");
				}
			}
		});
		// 取消按钮
		this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.cancelBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						dialog.dispose();
				}
			}
		});
		// 提交按钮
		this.commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认创建？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					//创建一张销售单
					createSale();
				}
			}
		});
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(cancelBtn);
		this.add(commitBtn);
		this.add(addCustomer);
		this.add(showPromotions);
	}

	/**
	 * 显示可选优惠对话框
	 */
	protected void showChoosePromotionDialog() {
		// 获得可用优惠列表
		ArrayList<PromotionVO> vip = saleCtrl.calCustomerPromotion(customerVO.level);
		ArrayList<PromotionVO> price = saleCtrl.calTotalGiftPromotion(totalPrice);
		//TODO 
		// 如果不为空，则显示选择对话框
		if((vip!=null&&vip.size()>0)||(price!=null&&price.size()>0)){
			new ShowChoosePromotionDialog(frame,vip,price,this);
		}else{
			MyOptionPane.showMessageDialog(frame, "抱歉！当前无可享受优惠！");
			this.calTotal();
		}
	}

	/**
	 * 创建销售单VO
	 */
	protected void createSale() {
		if(this.checkCompleted()){
			try{
				// 总价
				double totalBeforeDicount = Double.parseDouble(this.totalBeforeDiscountLab.getText());
				double total = Double.parseDouble(this.totalLab.getText());
				double discount = Double.parseDouble(this.discountLab.getText());
				saleVo.totalBeforeDiscount = totalBeforeDicount;
				saleVo.discount = discount;
				saleVo.totalAfterDiscount = total;
				// 如果选择使用代金券，获得使用代金券金额
				if(voucherBox.isSelected()){
					double voucher = Double.parseDouble(this.voucherTxt.getText());
					saleVo.voucher = voucher;
				}
				// 单据ID
				saleVo.id = this.documentId.getText();
				// 客户信息
				saleVo.customerId = customerVO.id;
				saleVo.customerName = customerVO.name;
				saleVo.customerVIP = customerVO.level;
				// 操作员
				saleVo.salesmanId = this.salesman.getSelectedItem().toString();
				// 仓库
				saleVo.storage = this.storage.getSelectedItem().toString();
				// 商品列表
				saleVo.saleList = commoditylist;
				// 单据状态
				saleVo.approvalState = DocumentStatus.NONCHECKED;
				saleVo.isWriteOff = false;
				saleVo.receiptType = DocumentType.SALE;
				// 备注
				String remark = this.remarkTxt.getArea().getText();
				saleVo.remark = saleVo.remark + remark;
				ResultMessage result = this.saleCtrl.add(saleVo);
				if(result == ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(frame, "销售单提交成功！");
					dialog.dispose();
					dialog.updateData();
				}else{
					MyOptionPane.showMessageDialog(frame, "销售单提交失败！");
				}
			}catch(NumberFormatException ex){
				MyOptionPane.showMessageDialog(frame, "请按正确格式输入数据！");
			}
		}else{
			MyOptionPane.showMessageDialog(frame, "请填入完整单据数据！");
		}
	}
	
	/**
	 * 添加一条商品信息
	 */
	@Override
	public void addCommodityLineItem(CommodityLineItemVO vo) {
		for(int i=0; i<commoditylist.size();++i){
			if(commoditylist.get(i).id.equals(vo.id)){
				MyOptionPane.showMessageDialog(frame, "已添加过该商品！");
				return;
			}
		}
		this.commoditylist.add(vo);
		this.commodityTable.addRow(vo);
		// 刷新界面总价
		this.totalPrice = this.totalPrice + (vo.price * vo.number);
		this.totalBeforeDiscountLab.setText(Double.toString(this.totalPrice));
		this.totalLab.setText(Double.toString(this.totalPrice));
	}
	
	/**
	 * 删除一个商品
	 */
	public void delCommodity(){
		this.commoditylist.remove(this.commodityTable.getTable().getSelectedRow());
		this.commodityTable.deleteRow();
	}

	/**
	 * 
	 * 交由逻辑计算折让及折让后总价，添加赠品列表并显示
	 */
	public void calTotal(){
		String vipid = null;
		String priceid = null;
		String showStr = "<html>";
		if(this.promotionlist.size()>0&&this.promotionlist.get(0)!=null){
			vipid = this.promotionlist.get(0).id;
			showStr = showStr + " "+this.promotionlist.get(0).toString() + "<br>";
		}
		if((this.promotionlist.size()>1)&&(this.promotionlist.get(1)!=null)){
			priceid = this.promotionlist.get(1).id;
			showStr = showStr + " " + this.promotionlist.get(1).toString();
		}
		showStr += "<html>";
		// TODO 空指针
		this.saleVo.totalBeforeDiscount = totalPrice;
		this.saleVo = saleCtrl.calAfterPrice(vipid,priceid, this.saleVo);
		// 显示折扣和总价和促销策略
		if(this.saleVo!=null){
			this.discountLab.setText(Double.toString(this.saleVo.discount));
			this.totalLab.setText(Double.toString(this.saleVo.totalAfterDiscount));
			this.promotionLab.setText(showStr);
			this.promotionLab.setAutoscrolls(true);
		}
	}
	
	/**
	 * 模糊查找客户信息
	 */
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> list = this.customerCtrl.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i=0; i<list.size(); ++i){
			CustomerVO vo = list.get(i);
			String str = vo.id+"-"+vo.name;
			strs.add(str);
			this.customerlist.put(str, vo);
		}
		return strs;
	}
	
	/**
	 * 检查单据是否填写完整
	 * @return
	 */
	private boolean checkCompleted() {
		if(hasCustomer&&this.commoditylist.size()>0){
			if(voucherBox.isSelected()&&voucherTxt.getText().isEmpty()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public ArrayList<PromotionVO> getPromotions() {
		return promotionlist;
	}

}
