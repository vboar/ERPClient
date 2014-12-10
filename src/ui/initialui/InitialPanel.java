package ui.initialui;

import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyTabbedPane;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class InitialPanel extends JPanel {

    private InitialInfoPanel initialInfoPanel;

    private InitialDoPanel initialDoPanel;

    private InitialHistoryPanel initialHistoryPanel;

    private JFrame frame;

    private PanelConfig cfg;

    private MyTabbedPane tabPane;

    public InitialPanel(JFrame frame) {
        this.frame = frame;
        this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
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
        initialDoPanel = new InitialDoPanel(frame);
        initialHistoryPanel = new InitialHistoryPanel(frame);
        initialInfoPanel = new InitialInfoPanel(frame);
        tabPane = new MyTabbedPane();
        tabPane.setBounds(0, 32, this.getWidth(), this.getHeight()-32);
        this.tabPane.add(initialInfoPanel,"当前账期初信息");
        this.tabPane.add(initialDoPanel,"期初建账");
        this.tabPane.add(initialHistoryPanel,"查看往届账务");
        this.add(this.tabPane);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(cfg.getBg(), 0, 0, cfg.getW(),cfg.getH(),null);
    }

}
