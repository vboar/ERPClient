package ui.initialui;

import config.ERPConfig;
import config.PanelConfig;
import org.dom4j.Element;
import ui.commodityui.commodityui.CommodityTreePane;

import javax.swing.*;

/**
 * 期初商品信息小面板
 * Created by Vboar on 2014/12/19.
 */
public class CommodityTreePanel extends CommodityTreePane {
    /**
     * 构造函数
     *
     * @param frame 主窗口
     */
    public CommodityTreePanel(JFrame frame) {
        super(ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().
                get("ui.commodityui.commodityui.CommodityPanel").getTree(), frame);
        this.setBounds(this.getX(), 50, this.getWidth(), this.getHeight()-40);
    }

    @Override
    public void addListener() {

    }

}
