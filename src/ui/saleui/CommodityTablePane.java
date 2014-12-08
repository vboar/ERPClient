package ui.saleui;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.CommodityLineItemVO;
import config.TableConfig;

/**
 * 商品表格
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class CommodityTablePane extends TablePanel {

	private String[] columnName;
	
	private static int COLUMN_NUM = 5;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	/**
	 * 构造函数
	 * @param cfg
	 */
	public CommodityTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.getTable().setRowHeight(18);
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
	
	public void addRow(CommodityLineItemVO vo){
		Object[] newrow= new Object[COLUMN_NUM];
		this.createRow(newrow,vo);
		this.dtm.addRow(newrow);
	}

	private void createRow(Object[] row, CommodityLineItemVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.number;
		row[4]=vo.price;
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
}
