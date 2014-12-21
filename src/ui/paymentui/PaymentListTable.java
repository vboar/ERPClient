package ui.paymentui;

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
import vo.PaymentVO;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.TableConfig;

/**
 * 查看收付款单的表格
 * Created by Vboar on 2014/12/4.
 */
@SuppressWarnings("serial")
public class PaymentListTable extends TablePanel {
	
    private static int COLUMN_NUM = 8;

    private ArrayList<PaymentVO> list;

    private PaymentBLService controller;

    public PaymentListTable(TableConfig cfg, PaymentBLService controller) {
        super(cfg);
        list = controller.show();
        this.controller = controller;
        list = controller.show();
        initTable();
        initComponent();
    }

    @Override
    protected void initTable() {
        this.columnNames = cfg.getColumnName();
        initData(list);
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

    public void initData(ArrayList<PaymentVO> list){
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
            PaymentVO temp = list.get(i);
            this.createRow(data[i], temp);
        }
    }

    private void createRow(Object[] row, PaymentVO vo){
        row[0]=vo.id;
        row[1]=vo.time;
        row[2]=vo.customerId;
        row[3]=vo.customerName;
        row[4]=vo.listToStr();
        row[5]=vo.total;
        row[6]=vo.operatorId;
        row[7]=vo.approvalState.toReadableString();
    }

    /**
     * 查询时间段内的单据（顺序）
     * @param time1
     * @param time2
     */
    public void showFindTable(String time1, String time2) {
        list = controller.findByTime(time1, time2);
        this.showFindData(list);
    }

    public void showFindData(ArrayList<PaymentVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			PaymentVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
    }
    
    /**
     * 查看所有单据（逆序）
     */
    public void showAllTable() {
        list = controller.show();
        this.showFindData(list);
    }
    
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.table.getColumnModel().getColumn(0).setMinWidth(160);
        this.table.getColumnModel().getColumn(4).setMinWidth(300);
		this.table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer(){
			@Override
			 public Component getTableCellRendererComponent(JTable table, Object value,
                     boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setToolTipText(FrameUtil.autoLineFeed(value.toString()));
				return this;
			}
		});
        this.updateUI();
	}
	
	public PaymentVO getSelectedVO(){
		PaymentVO vo = null;
		int row = this.table.getSelectedRow();
		if(row<0){
			MyOptionPane.showMessageDialog(PaymentListTable.this, "请先选择一张单据！");
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
}
