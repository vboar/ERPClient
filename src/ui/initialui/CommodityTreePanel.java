package ui.initialui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
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

    private String initialId;

    /**
     * 构造函数
     *
     * @param frame 主窗口
     */
    public CommodityTreePanel(JFrame frame, String initialId) {
        super(ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().
                get("ui.commodityui.commodityui.CommodityPanel").getTree(), frame);
        this.setBounds(this.getX(), 50, this.getWidth(), this.getHeight()-40);
    }

    @Override
    public void addListener() {}

    @Override
    public void initData() {
        // 获得商品信息
//        this.list = ControllerFactoryImpl.getInstance().getInitialController().showCommodity(initialId);
        this.list = ControllerFactoryImpl.getInstance().getCommodityController().bigShow();
        initTreeTable();
    }

}
