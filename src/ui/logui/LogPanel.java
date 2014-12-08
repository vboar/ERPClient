package ui.logui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.logblservice.LogBLService;
import businesslogicservice.userblservice.UserBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import org.dom4j.Element;
import ui.util.*;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class LogPanel extends JPanel implements FuzzySearch {

    private MyButton findBtn;

    private MyButton showBtn;

    private MySpecialTextField operatorTxt;

    private MyDatePicker start;

    private MyDatePicker end;

    private LogTable table;

    private JFrame frame;

    private LogBLService controller;

    private UserBLService userController;

    private PanelConfig pcfg;

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(), pcfg.getH(), null);
    }

    public LogPanel(JFrame frame) {
        this.frame = frame;
        controller = ControllerFactoryImpl.getInstance().getLogController();
        userController = ControllerFactoryImpl.getInstance().getUserController();
        this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        this.setLayout(null);
        this.initComponent(pcfg);
        this.repaint();
    }

    private void initComponent(PanelConfig cfg) {
        this.initButtons(cfg.getButtons());
        this.initLabels(cfg.getLabels());
        this.initTable(cfg.getTablepane());
        this.initTxt(cfg.getTextFields());
        this.initPicker(cfg.getDatepicker());
    }

    private void initLabels(Element element) {
        add(new MyLabel(element.element("title")));
        add(new MyLabel(element.element("start")));
        add(new MyLabel(element.element("end")));
        add(new MyLabel(element.element("operator")));
    }

    private void initButtons(Element element) {
        findBtn = new MyButton(element.element("find"));
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
                        MyOptionPane.showMessageDialog(LogPanel.this, "请输入有效日期！", "错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                    }
                }else if((day1==null)&&(day2!=null)){
                    time2 = dateFormat.format(day2);
                }else if(day1!=null){
                    time1 = dateFormat.format(day1);
                }
                table.find(time1, time2, operatorTxt.getText());
            }
        });

        showBtn = new MyButton(element.element("show"));
        add(showBtn);
        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.showAll();
            }
        });
    }

    private void initTable(Element element) {
        table = new LogTable(new TableConfig(element));
        add(table);
    }

    private void initTxt(Element element) {
        operatorTxt = new MySpecialTextField(element.element("operator"), this);
        add(operatorTxt);
    }

    private void initPicker(Element element) {
        start = new MyDatePicker(element.element("start"));
        end = new MyDatePicker(element.element("end"));
        add(start);
        add(end);
    }

    @Override
    public ArrayList<String> getFuzzyResult(String keyword) {
        ArrayList<UserVO> result = this.userController.fuzzyFind(keyword);
        ArrayList<String> strs = new ArrayList<String>();
        for(int i=0; i<result.size(); ++i){
            UserVO vo = result.get(i);
            strs.add(vo.id);
        }
        return strs;
    }
}
