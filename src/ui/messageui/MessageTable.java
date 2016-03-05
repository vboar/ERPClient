package ui.messageui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import util.ResultMessage;
import vo.MessageVO;
import vo.UserVO;
import businesslogic.loginbl.Login;
import businesslogicservice.messageblservice.MessageBLService;
import config.TableConfig;

/**
 * 用户消息面板表格
 * Created by Vboar on 2014/12/7.
 */
@SuppressWarnings("serial")
public class MessageTable extends TablePanel {

    private static int COLUMN_NUM = 3;

    private ArrayList<MessageVO> list;

    private MessageBLService controller;

    public MessageTable(TableConfig cfg, MessageBLService controller) {
        super(cfg);
        this.controller = controller;
        list = controller.showByUser(new UserVO(Login.currentUserId, null, Login.currentUserType, 0,
                Login.currentUserName));
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
        this.updateWidth();
    }

    private void updateWidth() {
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 30);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(110);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(420);
		this.updateUI();
	}

	private void initData(ArrayList<MessageVO> list) {
        int size;
        if(list == null) size = 0;
        else size = list.size();
        this.data = new Object[size][COLUMN_NUM];
        for(int i=size-1; i>=0; --i){
            MessageVO vo = list.get(i);
            this.createRow(data[i], vo);
        }
    }

    private void createRow(Object[] row, MessageVO vo) {
        row[0] = vo.state==0 ? "未读":"已读";
        row[1] = vo.time;
        row[2] = vo.content;
    }

    public void showAll() {
        list = controller.showByUser(new UserVO(Login.currentUserId, null, Login.currentUserType, 0,
                Login.currentUserName));
        initData(list);
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
    }

    public void showByState(int state) {
        list = controller.findByStatus(state);
        initData(list);
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
    }

    public ResultMessage setRead() {
        if(table.getSelectedRow() == -1) return ResultMessage.NOT_FOUND;
        MessageVO vo = list.get(table.getSelectedRow());
        if(vo.state == 0) {
            vo.state = 1;
            controller.update(vo);
            dtm.setValueAt(vo.state==0?"未读":"已读", table.getSelectedRow(), 0);
            updateUI();
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.EXIST;
        }
    }

}
