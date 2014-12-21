package ui.saleui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PromotionVO;
import config.TableConfig;

@SuppressWarnings("serial")
public class PromotionTablePanel extends TablePanel{
	
	private static int COLUMN_NUM = 4;
	
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
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnNames){
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
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				PromotionVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}else{

			this.data = new Object[0][COLUMN_NUM];
		}
	}

	private void createRow(Object[] row, PromotionVO vo){
		row[0]=vo.id;
		row[1]=vo.giftList;
		row[2]=vo.discount;
		row[3]=vo.voucher;
	}

	public PromotionVO getSeleted(){
		int row = this.table.getSelectedRow();
		if(row==-1){
			MyOptionPane.showMessageDialog(PromotionTablePanel.this, "请选择一个促销策略！");
			return null;
		}
		return this.list.get(row);
	}

}
