package ui.messageui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
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
        row[0] = vo.state;
        row[1] = vo.time;
        row[2] = vo.content;
    }

    public void showAll() {
        list = controller.showByUser(new UserVO(Login.currentUserId, null, Login.currentUserType, 0,
                Login.currentUserName));
        initData(list);
        updateUI();
    }

    public void showByState(int state) {
        list = controller.findByStatus(state);
        initData(list);
        updateUI();
    }

    public boolean setRead() {
        MessageVO vo = list.get(table.getSelectedRow());
        if(vo.state == 0) {
            vo.state = 1;
            controller.update(vo);
            dtm.setValueAt(vo.state, table.getSelectedRow(), 0);
            updateUI();
            return true;
        } else {
            return false;
        }
    }

}
