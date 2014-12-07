package ui.paymentui;

import businesslogicservice.paymentblservice.PaymentBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PaymentVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 查看收付款单的表格
 * Created by Vboar on 2014/12/4.
 */
public class ShowPaymentTable extends TablePanel {

    private String[] columnName;

    private static int COLUMN_NUM = 8;

    private Object[][] data;

    private DefaultTableModel dtm;

    private ArrayList<PaymentVO> list;

    private PaymentBLService controller;

    public ShowPaymentTable(TableConfig cfg, PaymentBLService controller) {
        super(cfg);
<<<<<<< HEAD
        list = controller.show();
=======
        this.controller = controller;
<<<<<<< Updated upstream
>>>>>>> 90b24e01d9eb673d919e121d1ef827f728208bb2
=======
        list = controller.show();
>>>>>>> Stashed changes
        initTable();
        initComponent();
    }

    @Override
    protected void initTable() {
        this.columnName = cfg.getColumnName();
        initData(list);
        this.dtm = new DefaultTableModel(this.data,this.columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.table = new MyTable(this.dtm,this.getWidth());
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
        // TODO
        row[4]=vo.transferList.toString();
        row[5]=vo.total;
        row[6]=vo.operatorId;
        row[7]=vo.approvalState;
    }

    /**
     * 查询时间段内的单据（顺序）
     * @param time1
     * @param time2
     */
    public void showFindTable(String time1, String time2) {
        list = controller.findByTime(time1, time2);
        Vector<String> names = new Vector<String>(COLUMN_NUM);
        for(int i=0; i<COLUMN_NUM;++i){
            names.add(columnName[i]);
        }
        Vector<Object> table = new Vector<Object>(list.size());
        for(int i=0; i<list.size(); ++i){
            PaymentVO vo = list.get(i);
            Vector<Object> row = new Vector<Object>(COLUMN_NUM);
            row.add(vo.id);
            row.add(vo.time);
            row.add(vo.customerId);
            row.add(vo.customerName);
            // TODO
            row.add(vo.transferList.toString());
            row.add(vo.total);
            row.add(vo.operatorId);
            row.add(vo.approvalState);
            table.add(row);
        }
        this.dtm.setDataVector(table, names);
        FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.updateUI();
    }

    /**
     * 查看所有单据（逆序）
     */
    public void showAllTable() {
        list = controller.show();
        Vector<String> names = new Vector<String>(COLUMN_NUM);
        for(int i=0; i<COLUMN_NUM;++i){
            names.add(columnName[i]);
        }
        Vector<Object> table = new Vector<Object>(list.size());
        for(int i=list.size()-1; i>=0; --i){
            PaymentVO vo = list.get(i);
            Vector<Object> row = new Vector<Object>(COLUMN_NUM);
            row.add(vo.id);
            row.add(vo.time);
            row.add(vo.customerId);
            row.add(vo.customerName);
            row.add(vo.transferList.toString());
            row.add(vo.total);
            row.add(vo.operatorId);
            row.add(vo.approvalState);
            table.add(row);
        }
        this.dtm.setDataVector(table, names);
        FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.updateUI();
    }

}
