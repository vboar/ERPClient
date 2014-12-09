/**
 * 单据审批面板
 * @author vboar
 * @date 2014/12/08
 */
package ui.approvalui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.approvalblservice.ApprovalBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import org.dom4j.Element;
import ui.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ApprovalPanel extends JPanel {

    private MyButton noncheckBtn;

    private MyButton checkBtn;

    private MyButton findBtn;

    private MyButton passBtn;

    private MyButton failBtn;

    private MyButton detailBtn;

    private MyComboBox typeBox;

    private MyDatePicker start;

    private MyDatePicker end;

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
        start = new MyDatePicker(datepicker.element("start"));
        end = new MyDatePicker(datepicker.element("end"));
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

            }
        });

        noncheckBtn = new MyButton(buttons.element("noncheck"));
        add(noncheckBtn);
        noncheckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        checkBtn = new MyButton(buttons.element("check"));
        add(checkBtn);
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        findBtn = new MyButton(buttons.element("find"));
        add(findBtn);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        passBtn = new MyButton(buttons.element("pass"));
        add(passBtn);
        passBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        failBtn = new MyButton(buttons.element("fail"));
        add(failBtn);
        failBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
