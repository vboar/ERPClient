package ui.approvalui;

import businesslogicservice.approvalblservice.ApprovalBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.DocumentVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Vboar on 2014/12/8.
 */
public class ApprovalTable extends TablePanel {

    private String[] columnName;

    private static int COLUMN_NUM = 6;

    private Object[][] data;

    private DefaultTableModel dtm;

    private ArrayList<DocumentVO> list;

    private ArrayList<Boolean> isSelected;

    public ApprovalTable(TableConfig cfg, ApprovalBLService controller) {
        super(cfg);
        this.initTable();
        this.initComponent();
    }

    @Override
    protected void initTable() {
        this.columnName = cfg.getColumnName();
        this.initData();
        this.dtm = new DefaultTableModel(this.data,this.columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.table = new MyTable(this.dtm,this.getWidth());
        this.table.setRowSorter(null);
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 30);

    }

    private void initData() {

    }
}
