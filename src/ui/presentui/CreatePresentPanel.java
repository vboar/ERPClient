package ui.presentui;

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
import ui.util.MyPopMenu;
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
	
	private MyLabel documentId;
	
	private PresentTablePane presentTable;
	
	private MySpecialTextField customeridTxt;
	
	private MySpecialTextField customernameTxt;
	
	private ArrayList<PresentLineItemVO> commoditylist;
	
	private HashMap<String,CustomerVO> customerlist;
	
	private PanelConfig pcfg;
	
	private JFrame frame;
	
	private PresentBLService presentController;
	
	private CustomerBLService customerController;

	public CreatePresentPanel(JFrame frame) {
		this.frame = frame;
		this.commoditylist = new ArrayList<PresentLineItemVO>();
		this.customerlist = new HashMap<String,CustomerVO>();
		this.presentController = ControllerFactoryImpl.getInstance().getPresentController();
		this.customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.repaint();
	}

	private void initComponent() {
		this.initLabels();
		this.initButtons();
		this.presentTable = new PresentTablePane(new TableConfig(pcfg.getTablepane()));
		this.add(this.presentTable);
		this.customeridTxt = new MySpecialTextField(pcfg.getTextFields().element("customerid"),this);
		this.add(this.customeridTxt);
		
	}
	
	private void initLabels(){
		this.add(new MyLabel(pcfg.getLabels().element("title")));
		this.add(new MyLabel(pcfg.getLabels().element("documentid")));
		this.add(new MyLabel(pcfg.getLabels().element("customerid")));
		this.add(new MyLabel(pcfg.getLabels().element("customername")));
		this.add(new MyLabel(pcfg.getLabels().element("presentlist")));
		this.add(new MyLabel(pcfg.getLabels().element("title")));
	}
	
	private void initButtons(){
		this.addBtn = new MyButton(pcfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPresentCommodityDialog(CreatePresentPanel.this,frame);
			}
			
		});
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
		this.cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		this.add(this.addBtn);
		this.add(this.deleteBtn);
		this.add(this.commitBtn);
		this.add(this.cancelBtn);
	}
	
	public void createPresent(){
		CustomerVO customer = this.customerlist.get(this.customeridTxt.getText());
		ResultMessage result = this.presentController.create(new PresentVO(null,null,customer.id,customer.name,
				this.commoditylist,DocumentStatus.NONCHECKED,DocumentType.PRESENT,false));
		if(result == ResultMessage.SUCCESS){
			MyOptionPane.showMessageDialog(null, "赠送单提交成功！");
		}else{
			MyOptionPane.showMessageDialog(null, "赠送单提交失败！");
		}
	}
	
	public void delCommodity(){
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
