/**
 * 自定义表格类
 * @author JaneLDQ
 * @date 2014/11/24
 */
package ui.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class MyTable extends JTable {

	private RowSorter<TableModel> sorter;
	
	private int padding = 20;
	
	public MyTable(TableModel dtm, int containerW) {
		super(dtm);
		this.getTableHeader().setReorderingAllowed(false);
		this.sorter = new TableRowSorter<TableModel>(dtm);
		this.setRowSorter(sorter);
		this.setDefaultRenderer(Object.class, new MyTableCellRenderer());
		this.setBackground(new Color(240,240,255));
//		for(int i=0; i<this.getColumnCount();++i){
//			this.getTableHeader().getColumnModel().getColumn(i).setMinWidth(containerW/this.getColumnCount());
//		}
		FrameUtil.setTableColumnWidth(this, containerW, padding);
	}
	
	public void setUnvisibleColumn(int column){
		TableColumn tc = this.getTableHeader().getColumnModel().getColumn(column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        this.updateUI();
        this.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
        this.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}
	
	private class MyTableCellRenderer extends DefaultTableCellRenderer{
		
		public Component getTableCellRendererComponent(JTable table,
				Object value,boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
			if ((row % 2) != 0) {
				setBackground(new Color(246, 243, 236));
			} else {
				setBackground(Color.white);
			}
			if (isSelected) {
				setBackground(new Color(200,210,230));
				setForeground(new Color(40,40,40));
			}
			if(table.getValueAt(row, 0).toString().equals("true")){
				setForeground(new Color(247,171,0));
			}else{
				setForeground(new Color(40,40,40));
			}
			return this;
		}
	}

}
