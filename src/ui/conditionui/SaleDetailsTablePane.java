package ui.conditionui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.RequirementVO;
import vo.SaleDetailsVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;
import config.TableConfig;

/**
 * 销售明细表格
 * @author JanelDQ
 * @date 2014/12/7
 */
@SuppressWarnings("serial")
public class SaleDetailsTablePane extends TablePanel{
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<SaleDetailsVO> list;
	
	private SaleDetailsBLService controller;

	public SaleDetailsTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getSaleDetailsBLService();
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
		this.list = new ArrayList<SaleDetailsVO>();
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				SaleDetailsVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}
	}

	private Object[] createRow(Object[] row, SaleDetailsVO vo) {
		row[0]=vo.time;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.number;
		row[4]=vo.price;
		row[5]=vo.total;
		return row;
	}
	
	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}

	public void showFindTable(RequirementVO requierevo) {
		list = controller.show(requierevo);
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			SaleDetailsVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.time);
			row.add(vo.name);
			row.add(vo.model);
			row.add(vo.number);
			row.add(vo.price);
			row.add(vo.total);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
		this.updateUI();
	}
	
}
