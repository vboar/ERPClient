/**
 * 用户信息表格面板
 * @author JaneLDQ
 * @date 2014/11/25
 */
package ui.userui;

import businesslogicservice.userblservice.UserBLService;
import config.TableConfig;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.UserType;
import vo.UserVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("serial")
public class UserTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 5;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<UserVO> list;
	
	/**
	 * 构造函数
	 * @param cfg
	 * @param controller
	 */
	public UserTablePane(TableConfig cfg, UserBLService controller) {
		super(cfg);
		this.list = controller.show();
		this.initTable();
		this.initComponent();
	}

	/**
	 * 初始化表格
	 */
	protected void initTable() {
		this.columnName = cfg.getColumnName();
		this.initData(list);
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setUnvisibleColumn(2);
	}

	/**
	 * 初始化数据
	 * @param list
	 */
	public void initData(ArrayList<UserVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			UserVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}
	
	/**
	 * 增加一行
	 * @param vo
	 */
	public void addRow(UserVO vo){
		Object[] newUser = new Object[COLUMN_NUM];
		this.createRow(newUser,vo);
		this.dtm.addRow(newUser);
	}

	/**
	 * 删除一行
	 */
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	/**
	 * 更新一行
	 * @param vo
	 */
	public void updateRow(UserVO vo){
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.id, row, 0);
		this.dtm.setValueAt(vo.name, row, 1);
		this.dtm.setValueAt(vo.password, row, 2);
		this.dtm.setValueAt(vo.type.toFriendString(), row, 3);
		this.dtm.setValueAt(this.permissionToStr(vo.permission), row, 4);
	}
	
	/**
	 * 创建一行
	 * @param row
	 * @param vo
	 */
	private void createRow(Object[] row, UserVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.password;
		row[3]=vo.type.toFriendString();
		row[4]=this.permissionToStr(vo.permission);
	}
	
	/**
	 * 获得所选VO
	 * @return
	 */
	public UserVO getSelectedVO(){
		int row = this.table.getSelectedRow();
		String id = this.table.getValueAt(row, 0).toString();
		String name = this.table.getValueAt(row, 1).toString();
		String password = this.table.getValueAt(row, 2).toString();
		UserType type = UserType.check(this.table.getValueAt(row, 3).toString());
		int permission = this.permissionToInt(this.table.getValueAt(row, 4).toString());
		UserVO vo = new UserVO(id,password,type,permission,name);
		return vo;
	}
	
	/**
	 * 显示查找结果
	 * @param list
	 */
	public void showFindTable(ArrayList<UserVO> list){
		System.out.println("findList"+list.size());
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			UserVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.name);
			row.add(vo.password);
			row.add(vo.type.toFriendString());
			row.add(this.permissionToStr(vo.permission));
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		this.table.setUnvisibleColumn(2);
		this.updateUI();
	}
	
	/**
	 * 将权限由string转为int
	 * @param str
	 * @return
	 */
	private int permissionToInt(String str){
		if(str.equals("普通权限")){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 将权限由int转为string
	 * @param permission
	 * @return
	 */
	private String permissionToStr(int permission){
		if(permission==0){
			return "普通权限";
		}else{
			return "最高权限";
		}
	}
}
