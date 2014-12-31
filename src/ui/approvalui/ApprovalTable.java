package ui.approvalui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.DocumentStatus;
import util.DocumentType;
import vo.CashVO;
import vo.DocumentVO;
import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PresentVO;
import vo.PurchaseVO;
import vo.SaleVO;
import businesslogicservice.approvalblservice.ApprovalBLService;
import config.TableConfig;

/**
 * Created by Vboar on 2014/12/8.
 */
@SuppressWarnings("serial")
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
        list = controller.show(status, startTime, endTime);
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

    public void showTableByType(int way) {
        list.clear();
        if(type == null) {
            list = controller.show(status, startTime, endTime);
        } else
        switch(type){
            case PRESENT:
                ArrayList<PresentVO> presentList = controller.findPresent(way, status, startTime, endTime);
                for(PresentVO vo: presentList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case OVERFLOW:
                ArrayList<ExceptionVO> exceptionList = controller.findOverflow(way, status, startTime, endTime);
                for(ExceptionVO vo: exceptionList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case LOSS:
                exceptionList = controller.findLoss(0, status, startTime, endTime);
                for(ExceptionVO vo: exceptionList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case SALE:
                ArrayList<SaleVO> saleList = controller.findSale(way, status, startTime, endTime);
                for(SaleVO vo: saleList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case SALERETURN:
                saleList = controller.findSaleReturn(way, status, startTime, endTime);
                for(SaleVO vo: saleList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case PURCHASE:
                ArrayList<PurchaseVO> purchaseList = controller.findPurchase(way, status, startTime, endTime);
                for(PurchaseVO vo: purchaseList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case PURCHASERETURN:
                purchaseList = controller.findPurchaseReturn(way, status, startTime, endTime);
                for(PurchaseVO vo: purchaseList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case RECEIPT:
                ArrayList<PaymentVO> paymentList = controller.findReceipt(way, status, startTime, endTime);
                for(PaymentVO vo: paymentList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case PAYMENT:
                paymentList = controller.findPayment(way, status, startTime, endTime);
                for(PaymentVO vo: paymentList) {
                    list.add((DocumentVO) vo);
                }
                break;
            case CASH:
                ArrayList<CashVO> cashList = controller.findCash(way, status, startTime, endTime);
                for(CashVO vo: cashList) {
                    list.add((DocumentVO) vo);
                }
                break;
            default:
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

    public DocumentVO getSelectedVO() {
        if(table.getSelectedRow() == -1) {
            return null;
        }
        DocumentVO vo = list.get(this.table.getSelectedRow());
        return vo;
    }
}
