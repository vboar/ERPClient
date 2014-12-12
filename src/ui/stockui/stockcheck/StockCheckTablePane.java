package ui.stockui.stockcheck;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.stockblservice.StockBLService;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.StockVO;
import config.TableConfig;

/**
 * 库存盘点表格面板
 * @author JanelDQ
 * @date 2014/12/4
 */
@SuppressWarnings("serial")
public class StockCheckTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<StockVO> mylist;
	
	private StockBLService controller;
	
	public StockCheckTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getStockController();
		this.mylist = new ArrayList<StockVO>();
		this.initTable();
		this.initComponent();
	}

	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.initData(mylist);
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
	}
	
	public void initData(ArrayList<StockVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			StockVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}
	
	private void createRow(Object[] row, StockVO vo){
		row[0]=vo.batch;
		row[1]=vo.batchNumber;
		row[2]=vo.commodityName;
		row[3]=vo.commodityModel;
		row[4]=vo.number;
		row[5]=vo.avgPrice;
	}
	
	public void showFindTable(ArrayList<StockVO> list){
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			StockVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.batch);
			row.add(vo.batchNumber);
			row.add(vo.commodityName);
			row.add(vo.commodityModel);
			row.add(vo.number);
			row.add(vo.avgPrice);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 20);
		this.updateUI();		
	}

	public ArrayList<StockVO> getList() {
		return mylist;
	}

	public void showCheck(){
		mylist = controller.showCheck();
		System.out.println(mylist.size());
		this.initData(mylist);
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 20);
		this.updateUI();	
	}
	
}
