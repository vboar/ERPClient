package ui.paymentui;

import config.TableConfig;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.ClauseLineItemVO;

import javax.swing.table.DefaultTableModel;

/**
 * 创建现金费用单的表格
 * Created by Vboar on 2014/12/4.
 */

@SuppressWarnings("serial")
public class CashTable extends TablePanel {

    private String[] columnNames;

    private static int COLUMN_NUM = 3;

    private Object[][] data;

    private DefaultTableModel dtm;

    public CashTable(TableConfig cfg) {
        super(cfg);
        initTable();
        initComponent();
    }

    @Override
    protected void initTable() {
        this.columnNames = cfg.getColumnName();
        this.dtm = new DefaultTableModel(this.data, this.columnNames) {
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.table = new MyTable(this.dtm, this.getWidth());
        this.data = new Object[0][COLUMN_NUM];
        this.table.setRowSorter(null);
    }

    public void addRow(ClauseLineItemVO vo){
        Object[] newLineItem= new Object[COLUMN_NUM];
        this.createRow(newLineItem,vo);
        this.dtm.addRow(newLineItem);
    }

    private void createRow(Object[] row, ClauseLineItemVO vo){
        row[0]=vo.name;
        row[1]=vo.account;
        row[2]=vo.remark;
    }

    public void deleteRow(){
        if(this.isSelected()){
            this.dtm.removeRow(this.table.getSelectedRow());
        }
    }

}
