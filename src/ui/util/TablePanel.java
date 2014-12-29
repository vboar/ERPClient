/**
 * 表格面板
 * @author JaneLDQ
 * @date 2014/11/24
 */
package ui.util;

import config.TableConfig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class TablePanel extends JPanel{

	protected String[] columnNames;
	
	protected Object[][] data;

	protected DefaultTableModel dtm;
	
	protected MyTable table;
	
	protected RowTableScrollPane rollpane;
	
	protected TableConfig cfg;
	
	public TablePanel(TableConfig cfg){
		this.cfg = cfg;
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setOpaque(false);
	}
	
	public TablePanel(){}
	
	protected abstract void initTable();
	
	protected void initComponent() {
		//创建滚动条面板
		this.rollpane = new RowTableScrollPane(this.table);
		this.rollpane.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()-5));
		this.add(this.rollpane);
	}

	public boolean isSelected(){
		if(this.table.getSelectedRow()!=-1)		
			return true;
		else	
			return false;
	}

	public MyTable getTable() {
		return table;
	}

	public void setRowHeaderWidth(int width){
		this.rollpane.setRowHeaderWidth(width);
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
}
