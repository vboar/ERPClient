package ui.saleui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.AddCommodityLineItem;
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
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import businesslogicservice.saleblservice.SaleBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class CreatePanel extends JPanel implements FuzzySearch, AddCommodityLineItem{

	private Image bg;
	
	private MyButton addBtn;
	private MyButton deleteBtn;	
	private MyButton commitBtn;
	private MyButton cancelBtn;
	private MyButton addCustomer;
	private MyButton showPromotions;
	
	private MyLabel documentId;
	private MyLabel customerIdLab;
	private MyLabel customerNameLab;
	private MyLabel totalBeforeDiscountLab;
	private MyLabel voucherlab;
	private MyLabel totalLab;
	private MyLabel discountLab;	
	
	private MyComboBox storage;	
	private MyComboBox salesman;
	
	private MyCheckBox voucherBox;
	
	private MySpecialTextField customerTxt;	
	private MyTextField voucherTxt;	
	private MyTextArea remarkTxt;

	private CommodityTablePane commodityTable;
	
	private CreateSaleDialog dialog;

	private JFrame frame;
	
	private PanelConfig cfg;
	
	private CustomerVO customerVO;	
	private boolean hasCustomer = false;	
	private double totalPrice = 0;	
	private ArrayList<PromotionVO> promotionlist;
	private SaleVO saleVo;
	private HashMap<String,CustomerVO> customerlist;	
	private ArrayList<CommodityLineItemVO> commoditylist;
	
	private SaleBLService saleCtrl;	
	private CustomerBLService customerCtrl;
	
	public CreatePanel(JFrame frame, CreateSaleDialog dialog){
		this.frame = frame;	
		this.dialog = dialog;
		
		this.saleVo = new SaleVO();
		this.customerlist = new HashMap<String,CustomerVO>();
		this.commoditylist = new ArrayList<CommodityLineItemVO>();
		this.promotionlist = new ArrayList<PromotionVO>();
		
		this.saleCtrl = ControllerFactoryImpl.getInstance().getSaleController();
		this.customerCtrl = ControllerFactoryImpl.getInstance().getCustomerController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = cfg.getBg();
		
        this.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()));
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        
        this.initComponent();
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
		this.commodityTable = new CommodityTablePane(new TableConfig(cfg.getTables().element("commodity")));
		this.add(commodityTable);
		this.voucherTxt = new MyTextField(cfg.getTextFields().element("voucher"));
		this.voucherTxt.setFocusable(false);
		this.add(voucherTxt);
		this.remarkTxt = new MyTextArea(cfg.getTextarea().element("remark"));
		this.add(remarkTxt);
		this.customerTxt = new MySpecialTextField(cfg.getTextFields().element("findcustomer"), this);
		this.add(customerTxt);
	}

	@SuppressWarnings("unchecked")
	private void initComboBoxes(){
		this.salesman = new MyComboBox(cfg.getComboboxes().element("salesman"));
		this.salesman.addItem("0001");
		this.storage = new MyComboBox(cfg.getComboboxes().element("storage"));
		this.storage.addItem("1号仓库");
		this.add(salesman);
		this.add(storage);
	}
	
	private void initButtons() {
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddSaleCommodityDialog(CreatePanel.this,frame);
			}
		});
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
		this.commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认创建？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					createSale();
				}
			}
		});
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
		this.showPromotions = new MyButton(this.cfg.getButtons().element("showpromotion"));
		this.showPromotions.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				showChoosePromotionDialog();
			}
		});
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(cancelBtn);
		this.add(commitBtn);
		this.add(addCustomer);
		this.add(showPromotions);
	}

	protected void showChoosePromotionDialog() {
		ArrayList<PromotionVO> vip = saleCtrl.calCustomerPromotion(customerVO.level);
		ArrayList<PromotionVO> price = saleCtrl.calTotalGiftPromotion(totalPrice);
		new ShowChoosePromotionDialog(frame,vip,price,this);
	}

	protected void createSale() {
		if(this.checkCompleted()){
			try{
				String remark = this.remarkTxt.getArea().getText();
				double totalBeforeDicount = Double.parseDouble(this.totalBeforeDiscountLab.getText());
				double total = Double.parseDouble(this.totalLab.getText());
				double discount = Double.parseDouble(this.discountLab.getText());
				if(voucherBox.isSelected()){
					double voucher = Double.parseDouble(this.voucherTxt.getText());
					saleVo.voucher = voucher;
				}
				saleVo.id = saleCtrl.createId();
				saleVo.customer = customerVO.id;
				saleVo.customerName = customerVO.name;
				saleVo.customerVIP = customerVO.level;
				saleVo.salesmanId = this.salesman.getSelectedItem().toString();
				saleVo.storage = this.storage.getSelectedItem().toString();
				saleVo.saleList = commoditylist;
				saleVo.totalBeforeDiscount = totalBeforeDicount;
				saleVo.discount = discount;
				saleVo.totalAfterDiscount = total;
				saleVo.approvalState = DocumentStatus.NONCHECKED;
				saleVo.isWriteOff = false;
				saleVo.receiptType = DocumentType.SALE;
				saleVo.remark = remark;
				ResultMessage result = this.saleCtrl.add(saleVo);
				if(result == ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(frame, "销售单提交成功！");
					dialog.dispose();
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

	private void initLabels() {
		this.customerIdLab = new MyLabel(cfg.getLabels().element("customerid"));
		this.customerNameLab = new MyLabel(cfg.getLabels().element("customername"));
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.totalBeforeDiscountLab = new MyLabel(cfg.getLabels().element("totalbefore"));
		this.discountLab = new MyLabel(cfg.getLabels().element("discount"));
		this.voucherlab = new MyLabel(cfg.getLabels().element("voucher"));
		this.totalLab = new MyLabel(cfg.getLabels().element("total"));
		this.add(customerIdLab);
		this.add(customerNameLab);
		this.add(documentId);
		this.add(discountLab);
		this.add(voucherlab);
		this.add(totalBeforeDiscountLab);
		this.add(totalLab);
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
		this.commoditylist.remove(this.commodityTable.getTable().getSelectedRow());
		this.commodityTable.deleteRow();
	}

	public void calTotal(){
		String vipid = null;
		String priceid = null;
		if(this.promotionlist.get(0)!=null){
			vipid = this.promotionlist.get(0).id;
		}
		if(this.promotionlist.get(1)!=null){
			priceid = this.promotionlist.get(1).id;
		}
		this.saleVo = saleCtrl.calAfterPrice( vipid,priceid, saleVo);
		this.discountLab.setText(Double.toString(this.saleVo.discount));
		this.totalLab.setText(Double.toString(this.saleVo.totalAfterDiscount));
	}
	
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
	}

	public ArrayList<PromotionVO> getPromotionlist() {
		return promotionlist;
	}

}
