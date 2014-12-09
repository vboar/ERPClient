package ui.approvalui;

import businesslogicservice.approvalblservice.ApprovalBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.DocumentStatus;
import util.DocumentType;
import vo.DocumentVO;
import vo.PresentVO;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Vboar on 2014/12/8.
 */
public class ApprovalTable extends TablePanel {

    private String[] columnName;

    private static int COLUMN_NUM = 5;

    private Object[][] data;

    private DefaultTableModel dtm;

    private ArrayList<DocumentVO> list;

    private ArrayList<Boolean> selects;

    private DocumentStatus status;

    private DocumentType type;

    private String startTime;

    private String endTime;

    private ApprovalBLService controller;

    public ApprovalTable(TableConfig cfg, ApprovalBLService controller) {
        super(cfg);
        this.controller = controller;
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
                if(col == 4) return true;
                return false;
            }
        };
        this.table = new MyTable(this.dtm,this.getWidth());
        this.table.setRowSorter(null);
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 35);
        getTable().getColumnModel().getColumn(4).setCellEditor(getTable().getDefaultEditor(Boolean.class));
        getTable().getColumnModel().getColumn(4).setCellRenderer(getTable().getDefaultRenderer(Boolean.class));

    }

    private void initData() {
        showTable();
    }

    public void showTable() {
//        list = controller.show(status, startTime, endTime);
        list = new ArrayList<DocumentVO>();
        PresentVO vo = new PresentVO("123", "145", "111", "555", null, DocumentStatus.NONCHECKED,
                DocumentType.PRESENT, false);
        list.add(vo);
        selects = new ArrayList<Boolean>();
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
            DocumentVO dvo = list.get(i);
            this.createRow(data[i], dvo);
        }
        updateUI();
    }

    private Object[] createRow(Object[] row, DocumentVO vo) {
        row[0] = vo.getStatus().toReadableString();
        row[1] = vo.getType().toReadableString();
        row[2] = vo.getId();
        row[3] = vo.getTime();
        row[4] = false;
        selects.add(false);
        return row;
    }
}
