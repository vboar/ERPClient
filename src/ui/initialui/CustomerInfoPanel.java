package ui.initialui;

import businesslogicservice.customerblservice.CustomerBLService;
import config.ERPConfig;
import config.TableConfig;
import ui.customerui.CustomerTablePane;

/**
 * 期初客户信息小面板
 * Created by Vboar on 2014/12/19.
 */
public class CustomerInfoPanel extends CustomerTablePane {

    public CustomerInfoPanel(CustomerBLService controller) {
        super(new TableConfig(ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().
                get("ui.customerui.CustomerPanel").getTablepane()), controller);
        this.setBounds(getX(), 50, this.getWidth(), this.getHeight()-40);
    }

}
