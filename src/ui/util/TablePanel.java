package ui.util;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import config.PanelConfig;

@SuppressWarnings("serial")
public class TablePanel extends JPanel{
	
	JScrollPane rollpane;
	
	public TablePanel(MyTable table, PanelConfig cfg){
		
		this.setSize(cfg.getWidth(), cfg.getHeight());
		this.setLocation(cfg.getX(), cfg.getY());
		this.initComponent(table);

	}

	private void initComponent(MyTable table) {
		//创建滚动条面板
		this.rollpane = new JScrollPane();
		this.rollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.rollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.rollpane.getViewport().setView(table);
		this.add(this.rollpane);
	}
	
	
}
