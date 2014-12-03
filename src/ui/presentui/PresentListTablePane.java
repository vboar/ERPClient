package ui.presentui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PresentVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.presentblservice.PresentBLService;
import config.TableConfig;

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
		System.out.println(vo.customerName);
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
		System.out.println("upadate!");
		System.out.println(list.size());
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}
	
}
