package ui.userui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import util.UserType;
import vo.UserVO;
import businesslogic.userbl.UserController;
import config.TableConfig;

@SuppressWarnings("serial")
public class UserTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 5;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	public UserTablePane(TableConfig cfg, UserController controller) {
		super(cfg);
		this.initTable(controller.show());
		this.initComponent();
	}

	protected void initTable(ArrayList<UserVO> list){
		this.columnName = cfg.getColumnName();
		this.initData(list);
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
	}
	
	public void initData(ArrayList<UserVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			UserVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}
	
	public void addRow(UserVO vo){
		Object[] newUser = new Object[COLUMN_NUM];
		this.createRow(newUser,vo);
		this.dtm.addRow(newUser);
	}

	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	public void updateRow(UserVO vo){
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.id, row, 0);
		this.dtm.setValueAt(vo.name, row, 1);
		this.dtm.setValueAt(vo.password, row, 2);
		this.dtm.setValueAt(vo.type.toFriendString(), row, 3);
		this.dtm.setValueAt(vo.permission, row, 4);
	}
	
	private void createRow(Object[] row, UserVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.password;
		row[3]=vo.type.toFriendString();
		row[4]=vo.permission;
	}
	
	public boolean isSelected(){
		if(this.table.getSelectedRow()!=-1)		
			return true;
		else	
			return false;
	}
	
	public UserVO getSelectedVO(){
		int row = this.table.getSelectedRow();
		String id = this.table.getValueAt(row, 0).toString();
		String name = this.table.getValueAt(row, 1).toString();
		String password = this.table.getValueAt(row, 2).toString();
		UserType type = UserType.check(this.table.getValueAt(row, 3).toString());
		int permission = Integer.parseInt(this.table.getValueAt(row, 4).toString());
		UserVO vo = new UserVO(id,password,type,permission,name);
		return vo;
	}
	
	public void showFindTable(ArrayList<UserVO> list){
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		System.out.println(list.size());
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			UserVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.name);
			row.add(vo.password);
			row.add(vo.type.toFriendString());
			row.add(vo.permission);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		this.updateUI();
		
	}
}
