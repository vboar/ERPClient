package ui.util;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vo.UserVO;
import config.TableConfig;

@SuppressWarnings("serial")
public abstract class TablePanel extends JPanel{

	protected RowTableScrollPane rollpane;
	
	protected MyTable table;
	
	protected RowTable rowheader;
	
	protected TableConfig cfg;
	
	public TablePanel(TableConfig cfg){	
		this.cfg = cfg;
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setOpaque(false);
	}

	protected void initComponent() {
		//创建滚动条面板
		this.rollpane = new RowTableScrollPane(this.table);
		this.rollpane.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()-5));
		this.rollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.rollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(this.rollpane);
	}
	
	abstract protected void initTable(ArrayList<UserVO> list);
}
