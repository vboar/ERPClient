package ui.paymentui;

import businesslogicservice.paymentblservice.PaymentBLService;
import config.TableConfig;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PaymentVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

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

    public ShowPaymentTable(TableConfig cfg, PaymentBLService controller) {
        super(cfg);
        list = controller.show(null, null);
        initTable();
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
        row[4]="未实现的list";
        row[5]=vo.total;
        row[6]=vo.operatorId;
        row[7]=vo.approvalState;
    }

}
