/**
 * 客户信息表格面板
 * @author Vboar
 * @date 2014/11/28
 */

package ui.customerui;

import java.util.ArrayList;

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
		
		if(vo.category == 0) row[2] = "供应商";
		else row[2] = "销售商";
		
		switch(vo.level) {
		case 0:
			row[3] = "-";
			break;
		default:
			row[3] = "VIP" + Integer.toString(vo.level);
		}

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
		
		if(vo.category == 0) this.dtm.setValueAt("供应商", row, 2);
		else this.dtm.setValueAt("销售商", row, 2);
		
		if(vo.level == 0) this.dtm.setValueAt("-", row, 3);
		else this.dtm.setValueAt("VIP" + vo.level, row, 3);
		
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
	
	
	
}
