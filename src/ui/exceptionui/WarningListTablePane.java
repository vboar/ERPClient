package ui.exceptionui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.WarningVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.WarningBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class WarningListTablePane extends TablePanel {
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 3;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<WarningVO> list;
	
	private WarningBLService controller;
	
	public WarningListTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getWarningController();
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
		this.table.setRowSorter(null);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
	}

	private void initData() {
		this.list = controller.show(null, null);
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				WarningVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}
	}
	
	private Object[] createRow(Object[] row, WarningVO vo) {
		row[0]=vo.id;
		row[1]=vo.time;
		row[2]=vo.list;
		return row;
	}
	
	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}

	public void showFindTable(String time1, String time2) {
		if(list==null){
			return;
		}
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			WarningVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.time);
			row.add(vo.list);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
		this.updateUI();
	}

}
