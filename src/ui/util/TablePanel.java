/**
 * 表格面板
 * @author JaneLDQ
 * @date 2014/11/24
 */
package ui.util;

import config.TableConfig;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public abstract class TablePanel extends JPanel{

	protected RowTableScrollPane rollpane;
	
	protected MyTable table;
	
	protected TableConfig cfg;
	
	public TablePanel(TableConfig cfg){
		this.cfg = cfg;
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setOpaque(false);
	}

	protected abstract void initTable();
	
	protected void initComponent() {
		//创建滚动条面板
		this.rollpane = new RowTableScrollPane(this.table);
		this.rollpane.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()-5));
		//this.rollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//this.rollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
	
}
