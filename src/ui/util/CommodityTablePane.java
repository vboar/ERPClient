package ui.util;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
	
	private ArrayList<CommodityLineItemVO> list;
	/**
	 * 构造函数
	 * @param cfg
	 */
	public CommodityTablePane(TableConfig cfg) {
		super(cfg);
		list = new ArrayList<CommodityLineItemVO>();
		this.initTable();
		this.initComponent();
	}
	
	/**
	 * 初始化表格
	 */
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setRowSorter(null);
	}
	
	public void addRow(CommodityLineItemVO vo){
		Object[] newrow= new Object[COLUMN_NUM];
		list.add(vo);
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
	
	public CommodityLineItemVO getSelectedVO(){
		CommodityLineItemVO vo = null;
		int row = this.table.getSelectedRow();
		for(int i=0; i<list.size();++i){
			if(this.table.getValueAt(row, 0).equals(list.get(i).id)){
				vo = list.get(i);
				return vo;
			}
		}
		return vo;
	}
}
