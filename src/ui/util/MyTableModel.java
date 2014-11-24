package ui.util;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel{

	private String[] columnName;
	
	private Object[][] data;
	
	public MyTableModel(String[] columnName, Object[][] data){
		this.columnName = columnName;
		this.data = data;
	}
	
	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		return this.columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
