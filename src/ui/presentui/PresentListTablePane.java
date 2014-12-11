package ui.presentui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.presentblservice.PresentBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PresentVO;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 赠送单列表
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class PresentListTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PresentVO> list;
	
	private PresentBLService controller;
	
	public PresentListTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getPresentController();
		this.initTable();
		this.initComponent();
	}

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
				PresentVO temp = list.get(i);
				this.createRow(data[i], temp);
			}
		}
	}

	private Object[] createRow(Object[] row, PresentVO vo) {
		row[0]=vo.id;
		row[1]=vo.time;
		row[2]=vo.customerId;
		row[3]=vo.customerName;
		row[4]=vo.list.toString();
		row[5]=vo.documentStatus.toReadableString();
		return row;
	}
	
	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}

	public void showFindTable(String time1, String time2) {
		list = controller.show(time1, time2);
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			PresentVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.time);
			row.add(vo.customerId);
			row.add(vo.customerName);
			row.add(vo.list.toString());
			row.add(vo.documentStatus.toReadableString());
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
		this.updateUI();
		if(list.size()==0){
			MyOptionPane.showMessageDialog(PresentListTablePane.this, "抱歉，未找到相关记录！");
		}
	}
	
}
