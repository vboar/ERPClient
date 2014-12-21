package ui.promotionui;

import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.TotalGiftVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.TotalGiftBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class TotalGiftTablePane extends TablePanel{

	private static int COLUMN_NUM = 7;
	
	private int padding = 30;
	
	private ArrayList<TotalGiftVO> list;
	
	private TotalGiftBLService controller;
	
	public TotalGiftTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getTotalGiftController();
		this.list = controller.show();
		this.initTable();
		this.initComponent();
	}

	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnNames){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.updateWidth();
	}

	public void initData() {
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				TotalGiftVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}
	}

	private void createRow(Object[] row, TotalGiftVO vo) {
		row[0]=vo.id;
		row[1]=vo.total;
		row[2]=vo.giftInfo;
		row[3]=vo.voucher;
		row[4]=vo.startTime;
		row[5]=vo.endTime;
		row[6]=vo.valid;
	}
	
	public void updateRow(TotalGiftVO vo){
		// 更新模型
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.id, row, 0);
		this.dtm.setValueAt(vo.total, row, 1);
		this.dtm.setValueAt(vo.giftInfo, row, 2);
		this.dtm.setValueAt(vo.voucher, row, 3);
		this.dtm.setValueAt(vo.startTime, row, 4);
		this.dtm.setValueAt(vo.endTime, row, 5);
		this.dtm.setValueAt(vo.valid, row, 6);
		// 更新自有列表
		for(int i=0; i<list.size(); ++i){
			if(list.get(i).id.equals(vo.id)){
				list.set(i, vo);
			}
		}
	}
	
	public TotalGiftVO getSelectedVO(){
		int row = this.table.getSelectedRow();
		String id = this.table.getValueAt(row, 0).toString();
		for(int i=0; i<list.size(); ++i){
			if(list.get(i).id.equals(id)){
				return list.get(i);
			}
		}
		return null;
	}
	
	public void addRow(TotalGiftVO vo){
		Object[] newRow = new Object[COLUMN_NUM];
		this.createRow(newRow,vo);
		this.dtm.addRow(newRow);
		this.list.add(vo);
	}
	
	public void updateData(){
		list = controller.show();
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), padding);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(200);
		this.updateUI();
	}
}
