package ui.promotionui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.SpecialOfferVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.SpecialOfferBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class SpecialOfferTablePane extends TablePanel{
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<SpecialOfferVO> list;
	
	private SpecialOfferBLService controller;
	
	public SpecialOfferTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getSpecialOfferController();
		this.list = this.controller.show();
		this.initTable();
		this.initComponent();
	}

	@Override
	protected void initTable() {
		this.columnName = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
	}

	public void initData() {
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				SpecialOfferVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}
	}

	private void createRow(Object[] row, SpecialOfferVO vo) {
		row[0]=vo.id;
		row[1]=vo.commodityList;
		row[2]=vo.total;
		row[3]=vo.startTime;
		row[4]=vo.endTime;
		row[5]=vo.valid;
	}
	
	public void updateRow(SpecialOfferVO vo){
		int row = this.table.getSelectedRow();
		this.dtm.setValueAt(vo.id, row, 0);
		this.dtm.setValueAt(vo.total, row, 1);
		this.dtm.setValueAt(vo.commodityList, row,2);
		this.dtm.setValueAt(vo.startTime, row, 3);
		this.dtm.setValueAt(vo.endTime, row, 4);
		this.dtm.setValueAt(vo.valid, row, 5);
	}
	
	public SpecialOfferVO getSelectedVO(){
		int row = this.table.getSelectedRow();
		String id = this.table.getValueAt(row, 0).toString();
		for(int i=0; i<list.size(); ++i){
			if(list.get(i).id.equals(id)){
				return list.get(i);
			}
		}
		return null;
	}
	
	public void addRow(SpecialOfferVO vo){
		Object[] newRow = new Object[COLUMN_NUM];
		this.createRow(newRow,vo);
		this.dtm.addRow(newRow);
		this.list.add(vo);
	}

	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
}
