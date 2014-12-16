package ui.accountui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import org.dom4j.Element;
import ui.util.*;
import util.ResultMessage;
import vo.AccountVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AccountPanel extends JPanel implements FuzzySearch {
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton updateBtn;
	
	private MyButton findBtn;
	
	private MyButton showBtn;
	
	private MySpecialTextField findBox;
	
	private AccountTablePane accountTable;
	
	private AccountInfoDialog accountInfoDialog;
	
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
		this.findBox.addKeyListener(new KeyListener(){
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					findAccount(findBox.getText());
				}
			}
			
		});
	}

	private void initLabels(Element labels) {
		this.add(new MyLabel(labels.element("accountlist")));
		this.add(new MyLabel(labels.element("counter")));
		this.add(new MyLabel(labels.element("title")));
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
		this.addBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showAddDialog();
			}
		});
	}

	private void initDeleteBtn(Element element) {
		this.deleteBtn = new MyButton(element);
		this.deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(accountTable.isSelected()) {
					int result = MyOptionPane.showConfirmDialog(null, "确认删除该账户信息？","删除账户",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION) {
						ResultMessage delResult = deleteAccount();
						if(delResult == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "删除成功！");
						} else {
							MyOptionPane.showMessageDialog(null, "删除失败！","提示信息", 
									MyOptionPane.ERROR_MESSAGE, null);
						}
					}
				} else {
					MyOptionPane.showMessageDialog(null, "请选择要删除的账户信息！");
				}
			}
			
		});
	}

	private void initUpdateBtn(Element element) {
		this.updateBtn = new MyButton(element);
		this.updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(accountTable.isSelected()) {
					showUpdateDialog();
				} else {
					MyOptionPane.showMessageDialog(null, "请选择要修改的账户信息！");
				}
			}
			
		});
	}

	private void initFindBtn(Element element) {
		this.findBtn = new MyButton(element);
		this.findBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findAccount(findBox.getText());
			}
			
		});
		
	}

	private void initShowBtn(Element element) {
		this.showBtn = new MyButton(element);
		this.showBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				accountTable.showFindTable(accountController.show());
				accountTable.updateUI();
			}
			
		});
		
	}
	
	private void showAddDialog() {
		this.accountInfoDialog = new AccountInfoDialog(ERPConfig.getACCOUNTINFO_DIALOG_CONFIG(),
				frame, this, true);
		this.accountInfoDialog.setVisible(true);
	}
	
	private void showUpdateDialog() {
		AccountVO vo = this.accountTable.getSelectedVO();
		this.accountInfoDialog = new AccountInfoDialog(ERPConfig.getACCOUNTINFO_DIALOG_CONFIG(),
				frame, this, false, vo);
		this.accountInfoDialog.setVisible(true);
	}
	
	public ResultMessage addAccount(AccountVO vo) {
		ResultMessage result = this.accountController.add(vo);
		if(result == ResultMessage.SUCCESS) {
			this.accountTable.addRow(vo);
		}
		return result;
	}
	
	public ResultMessage deleteAccount() {
		AccountVO vo = this.accountTable.getSelectedVO();
		ResultMessage result = this.accountController.delete(vo);
		if(result == ResultMessage.SUCCESS) {
			this.accountTable.deleteRow();
		}
		return result;
	}
	
	public ResultMessage updateAccount(AccountVO vo) {
		ResultMessage result = this.accountController.update(vo);
		if(result == ResultMessage.SUCCESS) {
			this.accountTable.updateRow(vo);
		}
		return result;
	}
	
	public void findAccount(String keyWord) {
		//TODO 查找结果不对
		this.accountTable.showFindTable(accountController.fuzzyFind(keyWord));
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
			strs.add(vo.name + "  " + vo.account);
		}
		return strs;
	}

}
