package ui.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class MyTable extends JTable {

	private RowSorter<TableModel> sorter;

	public MyTable(TableModel dtm) {
		super(dtm);
		this.getTableHeader().setReorderingAllowed(false);
		this.sorter = new TableRowSorter<TableModel>(dtm);
		this.setRowSorter(sorter);
		this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value,boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
				if ((row % 2) != 0) {
					setBackground(new Color(236, 233, 216));
				} else {
					setBackground(Color.white);
				}
				if (isSelected) {
					setBackground(new Color(105,162,216));
				}
				return this;
			}
		});
		this.setBackground(new Color(240,240,255));
	}

}
