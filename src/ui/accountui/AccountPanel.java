package ui.accountui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import vo.AccountVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class AccountPanel extends JPanel implements FuzzySearch {
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton updateBtn;
	
	private MyButton findBtn;
	
	private MyButton showBtn;
	
	private MySpecialTextField findBox;
	
	private AccountTablePane accountTable;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private AccountBLService accountController;
	
	public AccountPanel(JFrame frame) {
		this.frame = frame;
		accountController = ControllerFactoryImpl.getInstance().getAccountController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent(pcfg);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(),pcfg.getH(),null);
	}

	private void initComponent(PanelConfig cfg) {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initFindComboBox(cfg.getTextFields());
		this.initTable(cfg.getTablepane());
	}

	private void initTable(Element tablepane) {
		this.accountTable = new AccountTablePane(new TableConfig(tablepane), accountController);
		this.add(this.accountTable);
	}

	private void initFindComboBox(Element textFields) {
		this.findBox = new MySpecialTextField(textFields.element("findinput"),this);
		this.add(findBox);
	}

	private void initLabels(Element labels) {
		this.add(new MyLabel(labels.element("accountlist")));
		this.add(new MyLabel(labels.element("modulename")));
		this.add(new MyLabel(labels.element("counter")));
	}

	private void initButtons(Element buttons) {
		this.initAddBtn(buttons.element("add"));
		this.initDeleteBtn(buttons.element("delete"));
		this.initUpdateBtn(buttons.element("update"));
		this.initFindBtn(buttons.element("find"));
		this.initShowBtn(buttons.element("show"));
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(updateBtn);
		this.add(findBtn);
		this.add(showBtn);
	}

	private void initAddBtn(Element element) {
		this.addBtn = new MyButton(element);
		
	}

	private void initDeleteBtn(Element element) {
		this.deleteBtn = new MyButton(element);
		
	}

	private void initUpdateBtn(Element element) {
		this.updateBtn = new MyButton(element);
		
	}

	private void initFindBtn(Element element) {
		this.findBtn = new MyButton(element);
		
	}

	private void initShowBtn(Element element) {
		this.showBtn = new MyButton(element);
		
	}
	
	

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<AccountVO> result = this.accountController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i = 0; i < result.size(); ++i){
			AccountVO vo = result.get(i);
			strs.add(vo.account);
		}
		return strs;
	}

}
