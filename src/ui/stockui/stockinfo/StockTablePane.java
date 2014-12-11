package ui.stockui.stockinfo;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.StockInfoVO;
import businesslogicservice.stockblservice.StockBLService;
import config.TableConfig;

/**
 * 库存查看表格面板
 * @author JanelDQ
 * @date 2014/12/2
 */
@SuppressWarnings("serial")
public class StockTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 8;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<StockInfoVO> list;
	
	public StockTablePane(TableConfig cfg, StockBLService controller) {
		super(cfg);
		this.list = controller.showStockInfo(null, null);
		this.initTable();
		this.initComponent();
	}
	
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.initData(list);
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
	}
	
	public void initData(ArrayList<StockInfoVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			StockInfoVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}
	
	private void createRow(Object[] row, StockInfoVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.inNumber;
		row[4]=vo.inMoney;
		row[5]=vo.outNumber;
		row[6]=vo.outMoney;
		row[7]=vo.inNumber-vo.outNumber;
	}
	
	public void showFindTable(ArrayList<StockInfoVO> list){
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			StockInfoVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.name);
			row.add(vo.model);
			row.add(vo.inNumber);
			row.add(vo.inMoney);
			row.add(vo.outNumber);
			row.add(vo.outMoney);
			row.add(vo.inNumber-vo.outNumber);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 20);
		this.updateUI();
		if(list.size()==0){
			MyOptionPane.showMessageDialog(StockTablePane.this, "抱歉，未找到相关记录！");
		}
	}
	
}
