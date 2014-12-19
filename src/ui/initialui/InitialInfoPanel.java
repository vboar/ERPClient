package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;
import businesslogicservice.initialblservice.InitialBLService;
import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 期初建账当前账期初信息面板
 * Created by Vboar on 2014/12/10.
 */
public class InitialInfoPanel extends JPanel {

    private MyButton commodityBtn;

    private MyButton customerBtn;

    private MyButton accountBtn;

    private CommodityTreePanel commodityTreePanel;

    private CustomerInfoPanel customerInfoPanel;

    private AccountInfoPanel accountInfoPanel;

    private JFrame frame;

    private PanelConfig cfg;

    private InitialBLService controller;

    public InitialInfoPanel(JFrame frame) {
        this.frame = frame;
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

        // 初始化按钮
        commodityBtn = new MyButton(cfg.getButtons().element("commodity"));
        customerBtn = new MyButton(cfg.getButtons().element("customer"));
        accountBtn = new MyButton(cfg.getButtons().element("account"));
        add(commodityBtn);
        add(customerBtn);
        add(accountBtn);
        commodityBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commodityTreePanel.setVisible(true);
                customerInfoPanel.setVisible(false);
                accountInfoPanel.setVisible(false);
            }
        });
        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commodityTreePanel.setVisible(false);
                customerInfoPanel.setVisible(true);
                accountInfoPanel.setVisible(false);
            }
        });
        accountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commodityTreePanel.setVisible(false);
                customerInfoPanel.setVisible(false);
                accountInfoPanel.setVisible(true);
            }
        });

        // 初始化小面板
        commodityTreePanel = new CommodityTreePanel(frame, "currentdata");
        commodityTreePanel.init();
        add(commodityTreePanel);
        customerInfoPanel = new CustomerInfoPanel("currentdata",
                ControllerFactoryImpl.getInstance().getCustomerController());
        add(customerInfoPanel);
        customerInfoPanel.setVisible(false);
        accountInfoPanel = new AccountInfoPanel("currentdata");
        add(accountInfoPanel);
        accountInfoPanel.setVisible(false);
        // TODO
    }
}
