package ui.purchaseui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.DocumentStatus;
import vo.PurchaseVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class PurchaseListPane extends TablePanel{

	private String[] columnNames;

	private static int COLUMN_NUM = 10;

	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PurchaseVO> list;

	private PurchaseBLService purchaseCtrl;
	
	private boolean iscreate;
	
	public PurchaseListPane(TableConfig cfg,boolean isreturn,boolean iscreate) {
		super(cfg);
		this.iscreate = iscreate;
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
		this.updateWidth();
	}

	private void initData() {
		if(iscreate) list = purchaseCtrl.findByStatus(DocumentStatus.PASSED);
		else list = purchaseCtrl.show();
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
		row[9]=vo.documentStatus.toReadableString();
		return row;
	}

	public PurchaseVO getSelectedVO(){
		PurchaseVO vo = null;
		int row = this.table.getSelectedRow();
		if(row<0){
			MyOptionPane.showMessageDialog(PurchaseListPane.this, "请先选择一行数据！");
			return null;
		}
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
		this.showFindData(list);
	}
	
	public void showFindData(ArrayList<PurchaseVO> list){
		this.list = list;
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			PurchaseVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
		if (list.size() == 0) {
			MyOptionPane.showMessageDialog(PurchaseListPane.this,
					"抱歉，未找到相关记录！");
		}
	}
	
	public void updateData(){
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
	}
	
	public void showFindOne(PurchaseVO vo){
		if(vo!=null){
			this.data = new Object[1][COLUMN_NUM];
			this.createRow(data[0], vo);
			this.dtm.setDataVector(data, columnNames);
			this.updateWidth();
		}else{
			MyOptionPane.showMessageDialog(PurchaseListPane.this, "未找到单据！");
		}
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.table.getColumnModel().getColumn(0).setMinWidth(160);
        this.table.getColumnModel().getColumn(6).setMinWidth(300);
        this.updateUI();
	}

}
