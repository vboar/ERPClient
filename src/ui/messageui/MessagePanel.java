/**
 * 用户消息面板
 * @author vboar
 * @date 2014/12/07
 */

package ui.messageui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.messageblservice.MessageBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import org.dom4j.Element;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MessagePanel extends JPanel {

    private MyButton nonreadBtn;

    private MyButton readBtn;

    private MyButton allBtn;

    private MyButton setreadBtn;

    private MessageTable table;

    private JFrame frame;

    private MessageBLService controller;

    private PanelConfig pcfg;

    public MessagePanel(JFrame frame) {
        this.frame = frame;
        controller = ControllerFactoryImpl.getInstance().getMessageController();
        this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        this.setLayout(null);
        this.initComponent(pcfg);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(), pcfg.getH(), null);
    }

    private void initComponent(PanelConfig cfg) {
        this.initButtons(cfg.getButtons());
        this.initLabels(cfg.getLabels());
        this.initTable(cfg.getTablepane());

    }

    private void initTable(Element element) {
        table = new MessageTable(new TableConfig(element), controller);
        add(table);
    }

    private void initLabels(Element element) {
        add(new MyLabel(element.element("title")));
    }

    private void initButtons(Element element) {
        nonreadBtn = new MyButton(element.element("nonread"));
        add(nonreadBtn);
        nonreadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.showByState(0);
            }
        });

        readBtn = new MyButton(element.element("read"));
        add(readBtn);
        readBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.showByState(1);
            }
        });

        allBtn = new MyButton(element.element("all"));
        add(allBtn);
        allBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.showAll();
            }
        });

        setreadBtn = new MyButton(element.element("setread"));
        add(setreadBtn);
        setreadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = table.setRead();
                if(!result) {
                    MyOptionPane.showMessageDialog(null, "消息已经读过！");
                }
            }
        });
    }

}
