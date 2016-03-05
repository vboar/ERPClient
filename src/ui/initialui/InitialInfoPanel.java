package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;
import businesslogicservice.initialblservice.InitialBLService;
import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyButton;
import vo.InitialVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private String initialId;

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

        updateInitialId();

        // 初始化小面板
        commodityTreePanel = new CommodityTreePanel(frame, initialId);
        commodityTreePanel.init();
        add(commodityTreePanel);
        customerInfoPanel = new CustomerInfoPanel(initialId,
                ControllerFactoryImpl.getInstance().getCustomerController());
        add(customerInfoPanel);
        customerInfoPanel.setVisible(false);
        accountInfoPanel = new AccountInfoPanel(initialId);
        add(accountInfoPanel);
        accountInfoPanel.setVisible(false);
        // TODO
    }

    public void updateCommodityTree() {
        if(commodityTreePanel != null) {
            remove(commodityTreePanel);
        }
        commodityTreePanel = new CommodityTreePanel(frame, initialId);
        commodityTreePanel.init();
        add(commodityTreePanel);
    }

    public void updateInitialId() {
        ArrayList<InitialVO> list = controller.show();
        if(list.size()>0) initialId = list.get(list.size()-1).id;
    }

    public void updateAll() {
        updateInitialId();

        if(commodityTreePanel.isVisible()) {
            updateCommodityTree();
            commodityTreePanel.setVisible(true);
        } else {
            updateCommodityTree();
        }

        customerInfoPanel.updateData(initialId);
        accountInfoPanel.updateData(initialId);
    }

}
