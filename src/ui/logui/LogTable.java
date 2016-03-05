package ui.logui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.logblservice.LogBLService;
import config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.LogVO;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

/**
 * 系统日志查询表格
 * Created by Vboar on 2014/12/8.
 */
@SuppressWarnings("serial")
public class LogTable extends TablePanel {

    private static int COLUMN_NUM = 3;

    private ArrayList<LogVO> list;

    private LogBLService controller;

    public LogTable(TableConfig cfg) {
        super(cfg);
        controller = ControllerFactoryImpl.getInstance().getLogController();
        this.list = controller.show();
        this.initTable();
        this.initComponent();
    }

    @Override
    protected void initTable() {
        this.columnNames = cfg.getColumnName();
        this.initData(list);
        this.dtm = new DefaultTableModel(this.data, this.columnNames) {
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.table = new MyTable(this.dtm, this.getWidth());
        this.table.getColumnModel().getColumn(0).setMinWidth(180);
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(this.getWidth()-280);
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 20);
        this.table.updateUI();
    }

    private void initData(ArrayList<LogVO> list) {
        int size;
        if(list == null) size = 0;
        else size = list.size();
        this.data = new Object[size][COLUMN_NUM];
        for(int i=size-1; i>=0; --i){
            LogVO vo = list.get(i);
            this.createRow(data[i], vo);
        }
    }

    private void createRow(Object[] row, LogVO vo) {
        row[0] = vo.time;
        row[1] = vo.operator;
        row[2] = vo.content;
    }

    public void showAll() {
        list = controller.show();
        initData(list);
        updateUI();
    }

    public void find(String time1, String time2, String operatorId) {
        list = controller.find(time1, time2, operatorId);
        initData(list);
        this.dtm.setDataVector(data, columnNames);
        updateUI();
    }
}
