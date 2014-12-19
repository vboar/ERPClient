package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import config.ERPConfig;
import config.TableConfig;
import ui.customerui.CustomerTablePane;
import ui.util.RowTableScrollPane;

import java.awt.*;

/**
 * 期初客户信息小面板
 * Created by Vboar on 2014/12/19.
 */
public class CustomerInfoPanel extends CustomerTablePane {

    private String initialId;

    public CustomerInfoPanel(String initialId, CustomerBLService customerController) {
        super(new TableConfig(ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().
                get("ui.customerui.CustomerPanel").getTablepane()), customerController);
        this.setBounds(getX(), 50, this.getWidth(), this.getHeight() - 80);
        updateData(initialId);
    }

    public void updateData(String id) {
        initialId = id;
        list = ControllerFactoryImpl.getInstance().getInitialController().showCustomer(initialId);
        showFindTable(list);
    }

    @Override
    protected void initComponent() {
        //创建滚动条面板
        this.rollpane = new RowTableScrollPane(this.table);
        this.rollpane.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()-85));
        //this.rollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this.rollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(this.rollpane);
    }

}
