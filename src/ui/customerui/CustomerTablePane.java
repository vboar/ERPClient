/**
 * 客户信息表格面板
 * @author Vboar
 * @date 2014/11/28
 */

package ui.customerui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.CustomerVO;
import businesslogic.customerbl.CustomerController;
import config.TableConfig;

@SuppressWarnings("serial")
public class CustomerTablePane extends TablePanel {
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 12;
	
	private Object[][] data;

	private DefaultTableModel dtm;

	public CustomerTablePane(TableConfig cfg, CustomerController controller) {
		super(cfg);
		this.initTable(controller.show());
		this.initComponent();
	}

	protected void initTable(ArrayList<CustomerVO> list) {
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
	
	public void initData(ArrayList<CustomerVO> list) {
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			CustomerVO vo = list.get(i);
			this.createRow(data[i], vo);
		}
	}

	private void createRow(Object[] row, CustomerVO vo) {
		row[0] = vo.id;
		row[1] = vo.name;
		row[2] = this.categoryToString(vo.category);
		row[3] = this.levelToString(vo.level);
		row[4] = vo.phoneNumber;
		row[5] = vo.address;
		row[6] = vo.postalCode;
		row[7] = vo.email;
		row[8] = vo.creditLimit;
		row[9] = vo.paybles;
		row[10] = vo.receivables;
		row[11] = vo.salesman;
	}
	
	public void addRow(CustomerVO vo){
		Object[] newCustomer = new Object[COLUMN_NUM];
		this.createRow(newCustomer,vo);
		this.dtm.addRow(newCustomer);
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	public void updateRow(CustomerVO vo){
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.id, row, 0);
		this.dtm.setValueAt(vo.name, row, 1);
		this.dtm.setValueAt(this.categoryToString(vo.category), row, 2);
		this.dtm.setValueAt(this.levelToString(vo.level), row, 3);
		this.dtm.setValueAt(vo.phoneNumber, row, 4);
		this.dtm.setValueAt(vo.address, row, 5);
		this.dtm.setValueAt(vo.postalCode, row, 6);
		this.dtm.setValueAt(vo.email, row, 7);
		this.dtm.setValueAt(vo.creditLimit, row, 8);
		this.dtm.setValueAt(vo.paybles, row, 9);
		this.dtm.setValueAt(vo.receivables, row, 10);
		this.dtm.setValueAt(vo.salesman, row, 11);
	}

	public boolean isSelected(){
		if(this.table.getSelectedRow()!=-1)		
			return true;
		else	
			return false;
	}
	
	public CustomerVO getSelectedVO() {
		int row = this.table.getSelectedRow();
		String id = this.table.getValueAt(row, 0).toString();
		String name = this.table.getValueAt(row, 1).toString();
		int category = this.categoryToInt(this.table.getValueAt(row, 2).toString());
		int level = this.categoryToInt(this.table.getValueAt(row, 3).toString());
		String phoneNumber = this.table.getValueAt(row, 4).toString();
		String address = this.table.getValueAt(row, 5).toString();
		String postalCode = this.table.getValueAt(row, 6).toString();
		String email = this.table.getValueAt(row, 7).toString();
		double creditLimit = (Double)this.table.getValueAt(row, 8);
		double paybles = (Double)this.table.getValueAt(row, 9);
		double receivables = (Double)this.table.getValueAt(row, 10);
		String salesman = this.table.getValueAt(row, 11).toString();
		CustomerVO vo = new CustomerVO(id, category, level, name, phoneNumber, address, postalCode, email, creditLimit,
				paybles, receivables, salesman, false);
		return vo;
	}
	
	public void showFindTable(ArrayList<CustomerVO> list) {
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			CustomerVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.name);
			row.add(this.categoryToString(vo.category));
			row.add(this.levelToString(vo.level));
			row.add(vo.phoneNumber);
			row.add(vo.address);
			row.add(vo.postalCode);
			row.add(vo.email);
			row.add(vo.creditLimit);
			row.add(vo.paybles);
			row.add(vo.receivables);
			row.add(vo.salesman);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		this.updateUI();
		
	}
	
	/**
	 * 类别的int转String
	 */
	public String categoryToString(int category) {
		if(category == 0) return "供应商";
		return "销售商";
	}
	
	/**
	 * 类别的String转int
	 * @param str
	 * @return
	 */
	public int categoryToInt(String str) {
		if(str.equals("供应商")) return 0;
		return 1;
	}
	
	/**
	 * 级别的String转int
	 * @param level
	 * @return
	 */
	public String levelToString(int level) {
		if(level == 0) return "-";
		else return "VIP" + level;
	}
	
	/**
	 * 级别的int转String
	 * @param str
	 * @return
	 */
	public int levelToInt(String str) {
		if(str.equals("-")) return 0;
		else return Integer.parseInt(str.substring(3));
	}
	
}
