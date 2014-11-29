package ui.accountui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.AccountVO;
import businesslogicservice.accountblservice.AccountBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class AccountTablePane extends TablePanel {
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 3;
	
	private Object[][] data;

	private DefaultTableModel dtm;

	public AccountTablePane(TableConfig cfg, AccountBLService controller) {
		super(cfg);
		this.initTable(controller.show());
		this.initComponent();
	}

	protected void initTable(ArrayList<AccountVO> list) {
		this.columnName = cfg.getColumnName();
		this.initData(list);
		this.dtm = new DefaultTableModel(this.data, this.columnName) {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
	}

	private void initData(ArrayList<AccountVO> list) {
		int size;
		if(list == null) size = 0;
		else size = list.size();
		this.data = new Object[size][COLUMN_NUM];
		for(int i=0; i<size; ++i){
			AccountVO vo = list.get(i);
			this.createRow(data[i], vo);
		}
	}

	private void createRow(Object[] row, AccountVO vo) {
		row[0] = vo.account;
		row[1] = vo.name;
		row[2] = vo.balance;
	}
	
	public void addRow(AccountVO vo){
		Object[] newAccount = new Object[COLUMN_NUM];
		this.createRow(newAccount, vo);
		this.dtm.addRow(newAccount);
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	public void updateRow(AccountVO vo){
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.account, row, 0);
		this.dtm.setValueAt(vo.name, row, 1);
		this.dtm.setValueAt(vo.balance, row, 2);
	}
	
	public AccountVO getSelectedVO() {
		int row = this.table.getSelectedRow();
		String account = this.table.getValueAt(row, 0).toString();
		String name = this.table.getValueAt(row, 1).toString();
		double balance = (double)this.table.getValueAt(row, 2);
		return new AccountVO(name, account, balance);
	}
	
	public void showFindTable(ArrayList<AccountVO> list) {
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			AccountVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.account);
			row.add(vo.name);
			row.add(vo.balance);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		this.updateUI();
	}

	public boolean isSelected(){
		if(this.table.getSelectedRow()!=-1)		
			return true;
		else	
			return false;
	}

}
