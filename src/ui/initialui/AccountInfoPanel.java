package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import businesslogicservice.controllerfactoryblservice.ControllerFactory;
import config.ERPConfig;
import config.TableConfig;
import ui.accountui.AccountTablePane;
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
        InitialVO vo = new InitialVO(initialId, null);
        this.list = ControllerFactoryImpl.getInstance().getInitialController().showAccount(vo);
        this.showFindTable(list);
    }

    @Override
    public void init() {
        this.initTable();
        this.initComponent();
    }

}
