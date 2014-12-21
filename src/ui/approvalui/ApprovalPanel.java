/**
 * 单据审批面板
 * @author vboar
 * @date 2014/12/08
 */
package ui.approvalui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.DatePickerGroup;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.DocumentStatus;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.approvalblservice.ApprovalBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import util.DocumentType;

@SuppressWarnings("serial")
public class ApprovalPanel extends JPanel {

    private MyButton noncheckBtn;

    private MyButton passcheckBtn;

    private MyButton failcheckBtn;

    private MyButton findBtn;

    private MyButton passBtn;

    private MyButton failBtn;

    private MyButton detailBtn;

    private MyComboBox typeBox;

    private DatePickerGroup start;

    private DatePickerGroup end;

    private ApprovalTable table;

    private ApprovalBLService controller;

    private PanelConfig pcfg;

    private JFrame frame;

    public ApprovalPanel(JFrame frame) {
        this.frame = frame;
        controller = ControllerFactoryImpl.getInstance().getApprovalBLService();
        this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        this.setLayout(null);
        this.initComponent(pcfg);
        this.repaint();

    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(),pcfg.getH(),null);
    }

    private void initComponent(PanelConfig cfg) {
        this.initButtons(cfg.getButtons());
        this.initLabels(cfg.getLabels());
        this.initComboBox(cfg.getComboboxes());
        this.initTable(cfg.getTablepane());
        this.initPicker(cfg.getDatepicker());
    }

    private void initPicker(Element datepicker) {
        start = new DatePickerGroup(datepicker.element("start"));
        end = new DatePickerGroup(datepicker.element("end"));
        add(start);
        add(end);
    }

    private void initTable(Element tablepane) {
        table = new ApprovalTable(new TableConfig(tablepane), controller);
        add(table);
    }

    private void initComboBox(Element element) {
        typeBox = new MyComboBox(element.element("type"));
        add(typeBox);
        typeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (typeBox.getSelectedIndex()) {
                    case 0: table.setType(null); break;
                    case 1: table.setType(DocumentType.PURCHASE); break;
                    case 2: table.setType(DocumentType.PURCHASERETURN); break;
                    case 3: table.setType(DocumentType.SALE); break;
                    case 4: table.setType(DocumentType.SALERETURN); break;
                    case 5: table.setType(DocumentType.PRESENT); break;
                    case 6: table.setType(DocumentType.RECEIPT); break;
                    case 7: table.setType(DocumentType.PAYMENT); break;
                    case 8: table.setType(DocumentType.CASH); break;
                    case 9: table.setType(DocumentType.OVERFLOW); break;
                    case 10: table.setType(DocumentType.LOSS); break;
                }
                table.showTableByType(1);
            }
        });
    }

    private void initLabels(Element labels) {
        add(new MyLabel(labels.element("title")));
        add(new MyLabel(labels.element("list")));
        add(new MyLabel(labels.element("type")));
        add(new MyLabel(labels.element("start")));
        add(new MyLabel(labels.element("end")));
    }

    private void initButtons(Element buttons) {
        noncheckBtn = new MyButton(buttons.element("noncheck"));
        add(noncheckBtn);
        noncheckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setStatus(DocumentStatus.NONCHECKED);
                table.showTable();
            }
        });

        passcheckBtn = new MyButton(buttons.element("passcheck"));
        add(passcheckBtn);
        passcheckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setStatus(DocumentStatus.PASSED);
                table.showTable();
            }
        });

        failcheckBtn = new MyButton(buttons.element("failcheck"));
        add(failcheckBtn);
        failcheckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setStatus(DocumentStatus.FAILED);
                table.showTable();
            }
        });

        findBtn = new MyButton(buttons.element("find"));
        add(findBtn);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date day1 = start.getDate();
                Date day2 = end.getDate();
                String time1 = null;
                String time2 = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                if((day1!=null)&&(day2!=null)){
                    time1 = dateFormat.format(day1);
                    time2 = dateFormat.format(day2);
                    if(time1.compareTo(time2)>0){
                        MyOptionPane.showMessageDialog(ApprovalPanel.this, "请输入有效日期！", "错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                    }
                }else if((day1==null)&&(day2!=null)){
                    time2 = dateFormat.format(day2);
                }else if(day1!=null){
                    time1 = dateFormat.format(day1);
                }
                table.setStartTime(time1);
                table.setEndTime(time2);
                table.showTable();
            }
        });

        passBtn = new MyButton(buttons.element("pass"));
        add(passBtn);
        passBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.isChoose()) {
                    if(table.getStatus() != DocumentStatus.NONCHECKED) {
                        MyOptionPane.showMessageDialog(frame, "审批操作非法！");
                        return;
                    }
                    int result = MyOptionPane.showConfirmDialog(frame, "确认通过审批？","审批通过",
                            MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                    if(result == MyOptionPane.YES_OPTION){
                        table.updateData(DocumentStatus.PASSED);
                        table.showTable();
                    }
                } else {
                    MyOptionPane.showMessageDialog(frame, "请选择要通过审批的单据！");
                }
            }
        });

        failBtn = new MyButton(buttons.element("fail"));
        add(failBtn);
        failBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.isChoose()) {
                    if(table.getStatus() != DocumentStatus.NONCHECKED) {
                        MyOptionPane.showMessageDialog(frame, "审批操作非法！");
                        return;
                    }
                    int result = MyOptionPane.showConfirmDialog(frame, "确认不通过审批？","审批通过",
                            MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                    if(result == MyOptionPane.YES_OPTION){
                        table.updateData(DocumentStatus.FAILED);
                        table.showTable();
                    }
                } else {
                    MyOptionPane.showMessageDialog(frame, "请选择要不通过审批的单据！");
                }
            }
        });

        detailBtn = new MyButton(buttons.element("detail"));
        add(detailBtn);
        detailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
