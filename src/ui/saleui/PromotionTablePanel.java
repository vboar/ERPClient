package ui.saleui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PromotionVO;
import config.TableConfig;

@SuppressWarnings("serial")
public class PromotionTablePanel extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 4;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PromotionVO> list;
	
	/**
	 * 构造函数
	 * @param cfg
	 */
	public PromotionTablePanel(TableConfig cfg, ArrayList<PromotionVO> list) {
		super(cfg);
		this.list = list;
		this.initTable();
		this.initComponent();
	}
	
	/**
	 * 初始化表格
	 */
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				if(col==0){
					return true;
				}
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setRowSorter(null);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.table.getColumnModel().getColumn(0).setWidth(30);
	}
	
	private void initData() {
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			PromotionVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}

	private void createRow(Object[] row, PromotionVO vo){
		row[0]=vo.giftList;
		row[1]=vo.discount;
		row[2]=vo.voucher;
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	public PromotionVO getSeleted(){
		return this.list.get(this.table.getSelectedRow());
	}

}
