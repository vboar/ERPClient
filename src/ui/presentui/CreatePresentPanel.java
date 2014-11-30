package ui.presentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.TablePanel;
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
	
	private TablePanel presentTable;
	
	private MySpecialTextField customeridTxt;
	
	private MySpecialTextField customernameTxt;
	
	private ArrayList<PresentLineItemVO> commoditylist;
	
	private PanelConfig pcfg;
	
	private PresentBLService presentController;
	
	private CustomerBLService customerController;

	public CreatePresentPanel() {
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
		this.deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
		this.commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认创建？","确认提示",
						MyOptionPane.YES_NO_CANCEL_OPTION, MyOptionPane.QUESTION_MESSAGE);
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
		String documentId = this.documentId.getText();
		String customerId = this.customeridTxt.getText();
		String customerName = this.customeridTxt.getText();
		int presentNum = this.presentTable.getTable().getRowCount();
		ArrayList<PresentLineItemVO> list = new ArrayList<PresentLineItemVO>();
		for(int i=0; i<presentNum; i++){
			
		}
		this.presentTable.getTable().getRowCount();
	}
	
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CustomerVO> list = this.customerController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i=0; i<list.size(); ++i){
			CustomerVO vo = list.get(i);
			strs.add(vo.id);
		}
		return strs;
	}

}
