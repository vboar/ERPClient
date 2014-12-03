package ui.presentui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

@SuppressWarnings("serial")
public class CreatePresentPanel extends JPanel implements FuzzySearch{
	
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

	public CreatePresentPanel(JFrame frame,PresentPanel panel) {
		this.frame = frame;
		this.panel = panel;
		this.commoditylist = new ArrayList<PresentLineItemVO>();
		this.customerlist = new HashMap<String,CustomerVO>();
		this.presentController = ControllerFactoryImpl.getInstance().getPresentController();
		this.customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = this.pcfg.getBg();
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent() {
		this.initLabels();
		this.initButtons();
		this.presentTable = new PresentCommodityTablePane(new TableConfig(pcfg.getTablepane()));
		this.add(this.presentTable);
		this.customerTxt = new MySpecialTextField(pcfg.getTextFields().element("customerinfo"),this);
		this.add(this.customerTxt);
		
	}
	
	private void initLabels(){
		this.customerIdLab = new MyLabel(pcfg.getLabels().element("customeridlab"));
		this.customerNameLab = new MyLabel(pcfg.getLabels().element("customernamelab"));
		this.documentId = new MyLabel(pcfg.getLabels().element("documentidlab"));
		this.documentId.setText(this.presentController.createId());
		this.add(new MyLabel(pcfg.getLabels().element("documentid")));
		this.add(new MyLabel(pcfg.getLabels().element("customerid")));
		this.add(new MyLabel(pcfg.getLabels().element("customerinfo")));
		this.add(new MyLabel(pcfg.getLabels().element("customername")));
		this.add(new MyLabel(pcfg.getLabels().element("customeridlab")));
		this.add(new MyLabel(pcfg.getLabels().element("customernamelab")));
		this.add(new MyLabel(pcfg.getLabels().element("tip")));
		this.add(new MyLabel(pcfg.getLabels().element("presentlist")));
		this.add(this.documentId);
		this.add(this.customerIdLab);
		this.add(this.customerNameLab);
	}
	
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
						MyOptionPane.showMessageDialog(null, "请重新选择客户！");
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
					int result = MyOptionPane.showConfirmDialog(null, "确认删除该赠品？","删除赠品",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(null, "请选择要删除的赠品！");
				}
			}
			
		});
		this.add(this.deleteBtn);
		// 提交按钮
		this.commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认创建？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					createPresent();
				}
			}
		});
		// 取消按钮
		this.cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		this.add(this.commitBtn);
		this.add(this.cancelBtn);
	}
	
	public void createPresent(){
		if(this.checkCompleted()){
			ResultMessage result = this.presentController.create(new PresentVO(this.documentId.getText(),
					null,customerVO.id,customerVO.name,
					this.commoditylist,DocumentStatus.NONCHECKED,DocumentType.PRESENT,false));
			if(result == ResultMessage.SUCCESS){
				MyOptionPane.showMessageDialog(null, "赠送单提交成功！");
				this.setVisible(false);
				this.panel.getListpanel().udpateData();
				this.panel.getListpanel().setVisible(true);	
			}else{
			MyOptionPane.showMessageDialog(null, "赠送单提交失败！");
			}
		}else{
			MyOptionPane.showMessageDialog(null, "请填入完整单据数据！");
		}
	}
	
	private boolean checkCompleted() {
		if(hasCustomer&&this.commoditylist.size()>0){
			return true;
		}
		return false;
	}

	public void delCommodity(){
		this.commoditylist.remove(this.presentTable.getTable().getSelectedRow());
		this.presentTable.deleteRow();
	}
	
	public void addCommodity(PresentLineItemVO vo){
		this.commoditylist.add(vo);
		this.presentTable.addRow(vo);
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

}
