package ui.saleui;

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
import vo.SaleVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.saleblservice.SaleBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleListPane extends TablePanel {

	private static int COLUMN_NUM = 14;

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
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		row[0] = vo.isWriteOff;
		row[1] = vo.id;
		row[2] = vo.time;
		row[3] = vo.customerId;
		row[4] = vo.customerName;
		row[5] = vo.customerVIP;
		row[6] = vo.salesmanId;
		row[7] = vo.operatorId;
		row[8] = vo.storage;
		row[9] = vo.saleListToStr();
		row[10] = vo.totalBeforeDiscount;
		row[11] = vo.discount;
		row[12] = vo.totalAfterDiscount;
		row[13] = vo.approvalState.toReadableString();
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
		String id = (String) this.table.getValueAt(row, 1);
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
		this.showFindData(list);
	}
	
	public void showFindData(ArrayList<SaleVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			SaleVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
		if(list.size()==0){
			MyOptionPane.showMessageDialog(SaleListPane.this, "抱歉，未找到相关数据！");
		}
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 20);
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(160);
        this.table.getColumnModel().getColumn(9).setMinWidth(300);
		this.table.getColumnModel().getColumn(9).setCellRenderer(new DefaultTableCellRenderer(){
			@Override
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
