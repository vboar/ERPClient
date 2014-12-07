/**
 * 创建现金费用单面板
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.Login;
import businesslogicservice.paymentblservice.CashBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import ui.util.*;
import vo.AccountVO;
import vo.ClauseLineItemVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateCashPanel extends JPanel implements FuzzySearch {

    private MyLabel operatorLab;

    private MyLabel documentIdLab;

    private MyLabel accountNameLab;

    private MyLabel accountIdLab;

    private MyButton addAccountBtn;

    private MyButton addBtn;

    private MyButton deleteBtn;

    private MyButton commitBtn;

    private MyButton cancelBtn;

    private MySpecialTextField accountFind;

    private CashTable table;

    private JFrame frame;

    private PanelConfig pcfg;

    private PaymentPanel panel;

    private ArrayList<ClauseLineItemVO> list;

    private CashBLService controller;

    private AccountVO accountVO;

    private boolean hasAccount;

    private HashMap<String,AccountVO> accountList;

    public CreateCashPanel(JFrame frame, PaymentPanel panel) {
        this.frame = frame;
        this.panel = panel;
        controller = ControllerFactoryImpl.getInstance().getCashController();
        list = new ArrayList<ClauseLineItemVO>();
        accountList = new HashMap<String, AccountVO>();
        this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        this.setLayout(null);
        this.initComponent();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(), pcfg.getH(),null);
    }

    private void initComponent() {
        initTable();
        initLabels();
        initButtons();
        initAccountFind();
    }

    private void initAccountFind() {
        accountFind = new MySpecialTextField(pcfg.getTextFields().element("accountfind"), this);
        add(accountFind);
    }

    private void initButtons() {
        // 添加账户按钮
        addAccountBtn = new MyButton(pcfg.getButtons().element("addaccount"));
        add(addAccountBtn);
        addAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(accountFind.getText() != null){
                    accountVO = accountList.get(accountFind.getText());
                    if(accountVO != null){
                        accountIdLab.setText(accountVO.account);
                        accountNameLab.setText(accountVO.name);
                        hasAccount = true;
                    }else{
                        MyOptionPane.showMessageDialog(null, "请重新选择账户！");
                    }
                }
            }
        });

        // 添加账户按钮
        addBtn = new MyButton(pcfg.getButtons().element("add"));
        this.add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        // 删除账户按钮
        deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
        this.add(deleteBtn);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.isSelected()) {
                    int result = MyOptionPane.showConfirmDialog(frame, "确认删除该条目？","删除条目",
                            MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                    if(result == MyOptionPane.YES_OPTION){
                        deleteCash();
                    }
                } else {
                    MyOptionPane.showMessageDialog(frame, "请选择要删除的条目！");
                }
            }
        });

        // 提交按钮
        commitBtn = new MyButton(pcfg.getButtons().element("commit"));
        commitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认创建该现金费用单？", "创建现金费用单",
                        MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
                if(result == MyOptionPane.YES_OPTION) {
                    createCash();
                }
            }
        });
        this.add(commitBtn);

        // 取消按钮
        cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.showShow();
            }
        });
        this.add(cancelBtn);
    }

    private void initLabels() {
        this.accountIdLab = new MyLabel(pcfg.getLabels().element("accountidlab"));
        this.accountNameLab = new MyLabel(pcfg.getLabels().element("accountnamelab"));
        this.operatorLab = new MyLabel(pcfg.getLabels().element("operatorlab"));
        operatorLab.setText(Login.currentUserId);
        this.documentIdLab = new MyLabel(pcfg.getLabels().element("documentidlab"));
        this.documentIdLab.setText(controller.createId());
        this.add(this.operatorLab);
        this.add(this.documentIdLab);
        this.add(accountIdLab);
        this.add(accountNameLab);
    }

    private void initTable() {
        table = new CashTable(new TableConfig(pcfg.getTablepane()));
        this.add(table);
    }

    public void createCash() {

    }

    public void addCash(ClauseLineItemVO vo) {

    }

    public void deleteCash() {

    }


    @Override
    public ArrayList<String> getFuzzyResult(String keyword) {
        return null;
    }
}
