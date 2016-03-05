package ui.promotionui;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.CommodityLineItemVO;
import config.TableConfig;

@SuppressWarnings("serial")
public class SpecialCommodityTablePane extends TablePanel{

	private static int COLUMN_NUM = 4;
	
	/**
	 * 构造函数
	 * @param cfg
	 */
	public SpecialCommodityTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
	}
	
	/**
	 * 初始化表格
	 */
	protected void initTable(){
		this.columnNames = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnNames);
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	}

}
