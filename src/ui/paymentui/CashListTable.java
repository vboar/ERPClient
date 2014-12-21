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
import vo.CashVO;
import businesslogicservice.paymentblservice.CashBLService;
import config.TableConfig;

/**
 * 查看现金费用单的表格
 * Created by Vboar on 2014/12/4.
 */
@SuppressWarnings("serial")
public class CashListTable extends TablePanel {

    private static int COLUMN_NUM = 7;

    private ArrayList<CashVO> list;

    private CashBLService controller;

    public  CashListTable(TableConfig cfg, CashBLService controller) {
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

    private void initData(ArrayList<CashVO> list) {
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
            CashVO temp = list.get(i);
            this.createRow(data[i], temp);
        }
    }

    private void createRow(Object[] row, CashVO vo) {
        row[0]=vo.id;
        row[1]=vo.time;
        row[2]=vo.operator;
        row[3]=vo.bankAccount;
        row[4]=vo.listToStr();
        row[5]=vo.total;
        row[6]=vo.approvalState.toReadableString();
    }

    public void showFindTable(String time1, String time2) {
        list = controller.findByTime(time1, time2);
        this.showFindData(list);
    }
    
    public void showAllTable() {
        list = controller.show();
        this.showFindData(list);
    }

    public void showHistory(ArrayList<CashVO> findlist){
    	this.list = findlist;
    	this.showFindData(findlist);
    }
    
    public void showFindData(ArrayList<CashVO> list){
    	this.list = list;
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
        	CashVO temp = list.get(i);
        	this.createRow(data[i], temp);
        }
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
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

	public CashVO getSelectedVO() {
		CashVO vo = null;
		int row = this.table.getSelectedRow();
		if(row<0){
			MyOptionPane.showMessageDialog(CashListTable.this, "请先选择一张单据！");
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
