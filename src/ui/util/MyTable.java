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
	
	private int containerW;
	
	public MyTable(TableModel dtm, int containerW) {
		super(dtm);
		this.containerW = containerW;
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
		this.setWidth(this, containerW);
	}
	
	public void setWidth(JTable table, int containerW){
		//计算表格总体宽度 getTable().
        int allwidth = table.getIntercellSpacing().width;
        for (int j = 0; j < table.getColumnCount(); j++) {
            //计算该列中最长的宽度
            int max = 0;
            //计算表头的宽度
            int headerwidth = table.getTableHeader().
              getDefaultRenderer().getTableCellRendererComponent(
                      table, table.getColumnModel().
              getColumn(j).getIdentifier(), false, false,
              -1, j).getPreferredSize().width;
            //列宽至少应为列头宽度
            max += headerwidth+2*padding;
            //设置列宽
            table.getColumnModel().
              getColumn(j).setPreferredWidth(max);
            //给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
            allwidth += max + table.getIntercellSpacing().width;
        }
        if (allwidth > containerW) {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
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

}
