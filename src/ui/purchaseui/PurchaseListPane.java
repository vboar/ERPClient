package ui.purchaseui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PurchaseVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class PurchaseListPane extends TablePanel{

	private String[] columnNames;

	private static int COLUMN_NUM = 9;

	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PurchaseVO> list;

	private PurchaseBLService purchaseCtrl;
	
	public PurchaseListPane(TableConfig cfg,boolean isreturn) {
		super(cfg);
		if(!isreturn){
			this.purchaseCtrl = ControllerFactoryImpl.getInstance().getPurchaseController();
		}else{
			this.purchaseCtrl = ControllerFactoryImpl.getInstance().getPurchaseReturnController();
		}
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
		this.table.setRowSorter(null);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
	}

	private void initData() {
		list = purchaseCtrl.show();
		this.data = new Object[list.size()][COLUMN_NUM];
		for(int i=0; i<list.size(); ++i){
			PurchaseVO temp = list.get(i);	
			this.createRow(data[i], temp);
		}
	}
	
	private Object[] createRow(Object[] row, PurchaseVO vo) {
		row[0]=vo.id;
		row[1]=vo.time;
		row[2]=vo.customerId;
		row[3]=vo.customerName;
		row[4]=vo.storage;
		row[5]=vo.operatorId;
		row[6]=vo.saleList;
		row[7]=vo.total;
		row[8]=vo.remark;
		return row;
	}

	public PurchaseVO getSelectedVO(){
		PurchaseVO vo = null;
		int row = this.table.getSelectedRow();
		String id = (String)this.table.getValueAt(row, 0);
		for(int i=0; i<list.size(); i++){
			vo = list.get(i);
			if(id.equals(vo.id)){
				return vo;
			}
		}
		return vo;
	}
	
	public void showFindTable(String time1, String time2) {
		list = purchaseCtrl.findByTime(time1, time2);
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnNames[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			PurchaseVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.time);
			row.add(vo.customerId);
			row.add(vo.customerName);
			row.add(vo.storage);
			row.add(vo.operatorId);
			row.add(vo.saleList);
			row.add(vo.total);
			row.add(vo.remark);
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
		this.updateUI();
	
	}

	public void updateData(){
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}
	
	public void showFindOne(PurchaseVO vo){
		if(vo!=null){
			this.data = new Object[1][COLUMN_NUM];
			this.createRow(data[0], vo);
			this.dtm.setDataVector(data, columnNames);
			FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
			this.updateUI();
		}else{
			MyOptionPane.showMessageDialog(PurchaseListPane.this, "未找到单据！");
		}
	}

}
