/**
 * 收款创建单据面板表格
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import config.TableConfig;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.TransferLineItemVO;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ReceiptTable extends TablePanel {

	private String[] columnNames;

	private static int COLUMN_NUM = 4;

	private Object[][] data;

	private DefaultTableModel dtm;

	private CreateReceiptPanel panel;

	public ReceiptTable(TableConfig cfg, CreateReceiptPanel panel) {
		super(cfg);
		initTable();
		initComponent();
	}

	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		this.data = new Object[0][COLUMN_NUM];
		this.table.setRowSorter(null);
	}

	public void addRow(TransferLineItemVO vo){
		Object[] newLineItem= new Object[COLUMN_NUM];
		this.createRow(newLineItem,vo);
		this.dtm.addRow(newLineItem);
	}

	private void createRow(Object[] row, TransferLineItemVO vo){
		row[0]=vo.name;
		row[1]=vo.bankAccount;
		row[2]=vo.account;
		row[3]=vo.remark;
	}

	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}

}
