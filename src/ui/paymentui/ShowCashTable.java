package ui.paymentui;

import businesslogicservice.paymentblservice.CashBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.CashVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 查看现金费用单的表格
 * Created by Vboar on 2014/12/4.
 */
public class ShowCashTable extends TablePanel {

    private String[] columnName;

    private static int COLUMN_NUM = 7;

    private Object[][] data;

    private DefaultTableModel dtm;

    private ArrayList<CashVO> list;

    private CashBLService controller;

    public  ShowCashTable(TableConfig cfg, CashBLService controller) {
        super(cfg);
        list = controller.show();
        this.controller = controller;
        list = controller.show();
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
        // TODO
        row[4]=vo.clauseList.toString();
        row[5]=vo.total;
        row[6]=vo.approvalState;
    }

    public void showFindTable(String time1, String time2) {
        list = controller.findByTime(time1, time2);
        Vector<String> names = new Vector<String>(COLUMN_NUM);
        for(int i=0; i<COLUMN_NUM;++i){
            names.add(columnName[i]);
        }
        Vector<Object> table = new Vector<Object>(list.size());
        for(int i=0; i<list.size(); ++i){
            CashVO vo = list.get(i);
            Vector<Object> row = new Vector<Object>(COLUMN_NUM);
            row.add(vo.id);
            row.add(vo.time);
            row.add(vo.operator);
            // TODO
            row.add(vo.clauseList.toString());
            row.add(vo.total);
            row.add(vo.approvalState);
            table.add(row);
        }
        this.dtm.setDataVector(table, names);
        FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.updateUI();
    }

    public void showAllTable() {
        list = controller.show();
        Vector<String> names = new Vector<String>(COLUMN_NUM);
        for(int i=0; i<COLUMN_NUM;++i){
            names.add(columnName[i]);
        }
        Vector<Object> table = new Vector<Object>(list.size());
        for(int i=list.size()-1; i>=0; --i){
            CashVO vo = list.get(i);
            Vector<Object> row = new Vector<Object>(COLUMN_NUM);
            row.add(vo.id);
            row.add(vo.time);
            row.add(vo.operator);
            row.add(vo.clauseList.toString());
            row.add(vo.total);
            row.add(vo.approvalState);
            table.add(row);
        }
        this.dtm.setDataVector(table, names);
        FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 40);
        this.updateUI();
    }

}
