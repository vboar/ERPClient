package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.initialblservice.InitialBLService;
import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyButton;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Vboar on 2014/12/10.
 */
public class InitialDoPanel extends JPanel {

    private MyButton initialBtn;

    private JFrame frame;

    private PanelConfig cfg;

    private InitialBLService controller;

    private InitialPanel initialPanel;

    public InitialDoPanel(JFrame frame, InitialPanel initialPanel) {
        this.frame = frame;
        this.initialPanel = initialPanel;
        this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        controller = ControllerFactoryImpl.getInstance().getInitialController();
        // 设置面板基础属性
        this.setSize(cfg.getW(), cfg.getH());
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        // 初始化组件
        this.initComponent();
        this.setVisible(true);
        this.repaint();
    }

    private void initComponent() {
        initialBtn = new MyButton(cfg.getButtons().element("initial"));
        add(initialBtn);
        initialBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认进行期初建账？", "确认提示",
                        MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
                if(result == MyOptionPane.YES_OPTION) {
                    controller.initialAccount();
                    initialPanel.getInitialHistoryPanel().updateAll();
                    initialPanel.getInitialInfoPanel().updateAll();
                    MyOptionPane.showMessageDialog(null, "期初建账成功！");
                }
            }
        });
    }
}
