package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;
import config.ERPConfig;
import config.TableConfig;
import ui.accountui.AccountTablePane;
import ui.util.RowTableScrollPane;
import vo.InitialVO;

import java.awt.*;

import vo.InitialVO;

/**
 * 期初账户信息小面板
 * Created by Vboar on 2014/12/19.
 */
public class AccountInfoPanel extends AccountTablePane {

    private String initialId;

    public AccountInfoPanel(String initialId) {
        super(new TableConfig(ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().
                get("ui.accountui.AccountPanel").getTablepane()), null);
        this.setBounds(getX(), 50, this.getWidth(), this.getHeight()-40);
        this.initialId = initialId;
        this.list = ControllerFactoryImpl.getInstance().getInitialController().showAccount(initialId);
        this.showFindTable(list);
    }

    @Override
    public void init() {
        this.initTable();
        this.initComponent();
    }

    public void updateData(String id) {
        initialId = id;
        list = ControllerFactoryImpl.getInstance().getInitialController().showAccount(initialId);
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
