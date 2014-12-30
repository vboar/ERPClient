package ui.presentui;

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
import vo.PresentVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.presentblservice.PresentBLService;
import config.TableConfig;

/**
 * 赠送单列表
 * 
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class PresentListTablePane extends TablePanel {

	private static int COLUMN_NUM = 7;

	private ArrayList<PresentVO> list;

	private PresentBLService controller;

	public PresentListTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance()
				.getPresentController();
		this.initTable();
		this.initComponent();
	}

	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			PresentVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.updateWidth();
	}

	private void initData() {
		this.list = controller.show("", "");
		if (list != null) {
			this.data = new Object[list.size()][COLUMN_NUM];
			for (int i = 0; i < list.size(); ++i) {
				PresentVO temp = list.get(i);
				this.createRow(data[i], temp);
			}
		}
	}

	private Object[] createRow(Object[] row, PresentVO vo) {
		row[0] = vo.isWriteoff;
		row[1] = vo.id;
		row[2] = vo.time;
		row[3] = vo.customerId;
		row[4] = vo.customerName;
		row[5] = vo.listToStr();
		row[6] = vo.documentStatus.toReadableString();
		return row;
	}

	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
	}

	public void showFindTable(String time1, String time2) {
		list = controller.show(time1, time2);
		this.showFindData(list);
	}
	
	public void showFindData(ArrayList<PresentVO> list){
		this.list = list;
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			PresentVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
		if (list.size() == 0) {
			MyOptionPane.showMessageDialog(PresentListTablePane.this,
					"抱歉，未找到相关记录！");
		}
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(160);
        this.table.getColumnModel().getColumn(5).setMinWidth(300);
		this.table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer(){
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

	public PresentVO getSelectedVO() {
		PresentVO vo = null;
		int row = this.table.getSelectedRow();
		if(row<0){
			MyOptionPane.showMessageDialog(PresentListTablePane.this, "请先选择一张单据！");
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
}
