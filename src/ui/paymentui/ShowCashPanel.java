package ui.paymentui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import ui.util.DocumentShowDialogExtra;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.AccountVO;
import vo.CashVO;
import vo.ClauseLineItemVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.Login;
import businesslogicservice.accountblservice.AccountBLService;
import businesslogicservice.paymentblservice.CashBLService;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowCashPanel extends CashDocumentPanel implements FuzzySearch,
		DocumentShowDialogExtra, AddClauseLineItem {

	private int type;

	private CashVO vo;
	private AccountVO accountVO;
	private boolean hasAccount;
	private double total;
	private ArrayList<ClauseLineItemVO> list;
	private HashMap<String, AccountVO> accountList;

	private CashBLService cashController;
	private AccountBLService accountController;

	public ShowCashPanel(JFrame frame, CashVO vo, int type) {
		super(frame);
		this.type = type;
		this.vo = vo;
		this.list = vo.clauseList;
		this.total = vo.total;
		this.hasAccount = true;
		this.accountVO = new AccountVO();
		this.accountVO.account = vo.bankAccount;
		cashController = ControllerFactoryImpl.getInstance()
				.getCashController();
		accountController = ControllerFactoryImpl.getInstance()
				.getAccountController();
		accountList = new HashMap<String, AccountVO>();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.setPreferredSize(new Dimension(cfg.getW(), cfg.getH()));
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.initComponent();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(cfg.getBg(), 0, 0, cfg.getW(), cfg.getH(), null);
	}

	protected void initComponent() {
		this.initLabels();
		this.initButtons();
		table = new CashLineItemTable(new TableConfig(cfg.getTablepane()));
		for (int i = 0; i < list.size(); ++i) {
			table.addRow(list.get(i));
		}
		table.updateUI();
		this.add(table);
		accountFind = new MySpecialTextField(cfg.getTextFields().element(
				"accountfind"), this);
		accountFind.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					showAccountInfo();
				}
			}
		});
		if (type != 1)
			add(accountFind);
	}

	protected void showAccountInfo() {
		if (accountFind.getText() != null) {
			accountVO = accountList.get(accountFind.getText());
			if (accountVO != null) {
				accountIdLab.setText(accountVO.account);
				accountNameLab.setText(accountVO.name);
				hasAccount = true;
			} else {
				MyOptionPane.showMessageDialog(null, "请重新选择账户！");
			}
		}
	}

	private void initButtons() {
		if (type != 1) {
			// 添加账户按钮
			addAccountBtn = new MyButton(cfg.getButtons().element("addaccount"));
			add(addAccountBtn);
			addAccountBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showAccountInfo();
				}
			});
			// 添加条目按钮
			addBtn = new MyButton(cfg.getButtons().element("add"));
			this.add(addBtn);
			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddCashDialog(ERPConfig.getADDCASHINFO_DIALOG_CONFIG(),
							frame, ShowCashPanel.this);
				}
			});
			// 删除条目按钮
			deleteBtn = new MyButton(cfg.getButtons().element("delete"));
			this.add(deleteBtn);
			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (table.isSelected()) {
						int result = MyOptionPane.showConfirmDialog(frame,
								"确认删除该条目？", "删除条目", MyOptionPane.YES_NO_OPTION,
								MyOptionPane.QUESTION_MESSAGE);
						if (result == MyOptionPane.YES_OPTION) {
							deleteCash();
						}
					} else {
						MyOptionPane.showMessageDialog(frame, "请选择要删除的条目！");
					}
				}
			});
		}
	}

	private void initLabels() {
		this.accountIdLab = new MyLabel(cfg.getLabels().element("accountidlab"));
		this.accountNameLab = new MyLabel(cfg.getLabels().element(
				"accountnamelab"));
		this.operatorLab = new MyLabel(cfg.getLabels().element("operatorlab"));
		this.documentIdLab = new MyLabel(cfg.getLabels().element(
				"documentidlab"));
		this.totalLab = new MyLabel(cfg.getLabels().element("totallab"));

		this.documentIdLab.setText(type != 2 ? vo.id : cashController
				.createId());
		this.accountIdLab.setText(vo.bankAccount);
		this.accountNameLab.setText(vo.bankAccount);
		this.operatorLab.setText(type != 2 ? vo.operator : Login.currentUserId);
		this.totalLab.setText(Double.toString(vo.total));

		this.add(this.operatorLab);
		this.add(this.documentIdLab);
		this.add(accountIdLab);
		this.add(accountNameLab);
		this.add(totalLab);
	}

	public void deleteCash() {
		list.remove(table.getTable().getSelectedRow());
		table.deleteRow();
		// 更新总额
		refreshTotal();
	}

	private void refreshTotal() {
		total = 0;
		for (ClauseLineItemVO vo : list) {
			total += vo.account;
		}
		totalLab.setText(Double.toString(total));
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<AccountVO> result = accountController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i < result.size(); ++i) {
			AccountVO vo = result.get(i);
			String str = vo.account + "-" + vo.name;
			strs.add(str);
			this.accountList.put(str, vo);
		}
		return strs;
	}

	@Override
	public boolean checkCompleted() {
		if (hasAccount && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public DocumentType getDocumentType() {
		return vo.documentType;
	}

	@Override
	public String getDocumentID() {
		return this.documentIdLab.getText();
	}

	@Override
	public void createCopyDocument() {
		if (!hasAccount) {
			MyOptionPane.showMessageDialog(frame, "请先添加转账账户！");
			return;
		}
		CashVO vo = new CashVO(this.documentIdLab.getText(), null,
				this.accountVO.account, null, list, this.total,
				DocumentStatus.NONCHECKED, false, DocumentType.CASH);
		ResultMessage result = cashController.create(vo);
		if (result == ResultMessage.SUCCESS) {
			MyOptionPane.showMessageDialog(frame, "创建现金费用单成功！");
		} else
			MyOptionPane.showMessageDialog(frame, "创建现金费用单失败！");
	}

	@Override
	public void addClauseLineItem(ClauseLineItemVO vo) {
		list.add(vo);
		table.addRow(vo);
		// 更新总额
		refreshTotal();
	}

}
