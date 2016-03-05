package ui.purchaseui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
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

	private static int COLUMN_NUM = 11;
	
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
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		row[0]=vo.isWriteOff;
		row[1]=vo.id;
		row[2]=vo.time;
		row[3]=vo.customerId;
		row[4]=vo.customerName;
		row[5]=vo.storage;
		row[6]=vo.operatorId;
		row[7]=vo.listToStr();
		row[8]=vo.total;
		row[9]=vo.remark;
		row[10]=vo.documentStatus.toReadableString();
		return row;
	}

	public PurchaseVO getSelectedVO(){
		PurchaseVO vo = null;
		int row = this.table.getSelectedRow();
		if(row<0){
			MyOptionPane.showMessageDialog(PurchaseListPane.this, "请先选择一行数据！");
			return null;
		}
		String id = (String)this.table.getValueAt(row, 1);
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
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(7).setMinWidth(300);
        this.table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer(){
			 public Component getTableCellRendererComponent(JTable table, Object value,
                     boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setToolTipText(FrameUtil.autoLineFeed(value.toString()));
				return this;
			}
        });
		this.table.setUnvisibleColumn(0);
        this.updateUI();
	}

}
