package ui.saleui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.DocumentStatus;
import vo.SaleVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.saleblservice.SaleBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleListPane extends TablePanel {

	private String[] columnNames;

	private static int COLUMN_NUM = 13;

	private Object[][] data;

	private DefaultTableModel dtm;

	private ArrayList<SaleVO> list;

	private SaleBLService saleCtrl;

	private boolean isCreate;
	
	public SaleListPane(TableConfig cfg, boolean isreturn, boolean isCreate) {
		super(cfg);
		this.isCreate = isCreate;
		if (!isreturn) {
			this.saleCtrl = ControllerFactoryImpl.getInstance()
					.getSaleController();
		} else {
			this.saleCtrl = ControllerFactoryImpl.getInstance()
					.getSaleReturnController();
		}
		this.initTable();
		this.initComponent();
	}

	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		this.table.setRowSorter(null);
		this.updateWidth();
	}

	private void initData() {
		if(isCreate){
			list = saleCtrl.findByStatus(DocumentStatus.PASSED);
		}else{
			list = saleCtrl.show();
		}
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			SaleVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
	}

	private Object[] createRow(Object[] row, SaleVO vo) {
		row[0] = vo.id;
		row[1] = vo.time;
		row[2] = vo.customerId;
		row[3] = vo.customerName;
		row[4] = vo.customerVIP;
		row[5] = vo.salesmanId;
		row[6] = vo.operatorId;
		row[7] = vo.storage;
		row[8] = vo.saleList;
		row[9] = vo.totalBeforeDiscount;
		row[10] = vo.discount;
		row[11] = vo.totalAfterDiscount;
		row[12] = vo.approvalState.toReadableString();
		return row;
	}

	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
	}

	public SaleVO getSelectedVO() {
		SaleVO vo = null;
		int row = this.table.getSelectedRow();
		String id = (String) this.table.getValueAt(row, 0);
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			if (id.equals(vo.id)) {
				return vo;
			}
		}
		return vo;
	}

	public void showFindTable(String time1, String time2) {
		list = saleCtrl.findByTime(time1, time2);
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for (int i = 0; i < COLUMN_NUM; ++i) {
			names.add(columnNames[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for (int i = 0; i < list.size(); ++i) {
			SaleVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.time);
			row.add(vo.customerId);
			row.add(vo.customerName);
			row.add(vo.customerVIP);
			row.add(vo.salesmanId);
			row.add(vo.operatorId);
			row.add(vo.storage);
			row.add(vo.saleList);
			row.add(vo.totalBeforeDiscount);
			row.add(vo.discount);
			row.add(vo.totalAfterDiscount);
			row.add(vo.approvalState.toReadableString());
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		this.updateWidth();
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 20);
        this.table.getColumnModel().getColumn(0).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(160);
        this.table.getColumnModel().getColumn(8).setMinWidth(300);
        this.updateUI();
	}
	
}
