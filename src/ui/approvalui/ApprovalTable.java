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

import javax.lang.model.type.ArrayType;
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

    private DocumentStatus status;

    private DocumentType type;

    private String startTime;

    private String endTime;

    private ApprovalBLService controller;

    public ApprovalTable(TableConfig cfg, ApprovalBLService controller) {
        super(cfg);
        this.controller = controller;
        status = DocumentStatus.NONCHECKED;
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
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 30);
        getTable().getColumnModel().getColumn(4).setCellEditor(getTable().getDefaultEditor(Boolean.class));
        getTable().getColumnModel().getColumn(4).setCellRenderer(getTable().getDefaultRenderer(Boolean.class));

    }

    private void initData() {
        list = controller.show(status, startTime, endTime);
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
            DocumentVO dvo = list.get(i);
            this.createRow(data[i], dvo);
        }
    }

    public void showTable() {
        // 得到已审批的单据包括通过和不通过的
        if(status == null) {
            list = controller.show(DocumentStatus.PASSED, startTime, endTime);
            ArrayList<DocumentVO> tempList = controller.show(DocumentStatus.FAILED, startTime, endTime);
            for(DocumentVO vo: tempList) {
                list.add(vo);
            }
        } else {
            list = controller.show(status, startTime, endTime);
        }
        this.data = new Object[list.size()][COLUMN_NUM];
        for(int i=0; i<list.size(); ++i){
            DocumentVO dvo = list.get(i);
            this.createRow(data[i], dvo);
        }
        this.dtm.setDataVector(data, columnName);
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 30);
        getTable().getColumnModel().getColumn(4).setCellEditor(getTable().getDefaultEditor(Boolean.class));
        getTable().getColumnModel().getColumn(4).setCellRenderer(getTable().getDefaultRenderer(Boolean.class));
        this.updateUI();
    }

    private Object[] createRow(Object[] row, DocumentVO vo) {
        row[0] = vo.getStatus().toReadableString();
        row[1] = vo.getType().toReadableString();
        row[2] = vo.getId();
        row[3] = vo.getTime();
        row[4] = false;
        return row;
    }

    public boolean isChoose() {
        for(int i = 0; i < table.getRowCount(); i++) {
            if((boolean)table.getValueAt(i, 4)) {
                return true;
            }
        }
        return false;
    }

    public void updateData(DocumentStatus status) {
        for(int i = 0; i < table.getRowCount(); i++) {
            if((boolean)table.getValueAt(i, 4)) {
                DocumentVO vo = list.get(i);
                vo.setStatus(status);
                controller.approveDocument(vo);
            }
        }
        showTable();
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public DocumentStatus getStatus() {
        return status;
    }
}
