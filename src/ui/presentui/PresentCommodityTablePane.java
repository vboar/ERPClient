package ui.presentui;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PresentLineItemVO;
import config.TableConfig;

/**
 * 赠送单商品表格
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class PresentCommodityTablePane extends TablePanel {

	private String[] columnName;
	
	private static int COLUMN_NUM = 4;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	/**
	 * 构造函数
	 * @param cfg
	 */
	public PresentCommodityTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
	}
	
	/**
	 * 初始化表格
	 */
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnName);
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setRowSorter(null);
	}
	
	public void addRow(PresentLineItemVO vo){
		Object[] newPresent= new Object[COLUMN_NUM];
		this.createRow(newPresent,vo);
		this.dtm.addRow(newPresent);
	}

	private void createRow(Object[] row, PresentLineItemVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.number;
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
}
