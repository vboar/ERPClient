package ui.paymentui;

import businesslogicservice.paymentblservice.CashBLService;
import config.TableConfig;
import ui.util.TablePanel;

/**
 * 查看现金费用单的表格
 * Created by Vboar on 2014/12/4.
 */
public class ShowCashTable extends TablePanel {

    private CashBLService controller;

    public  ShowCashTable(TableConfig cfg, CashBLService controller) {
        super(cfg);
        this.controller = controller;
    }

    @Override
    protected void initTable() {

    }

    public void showFindTable(String time1, String time2) {

    }

    public void showAllTable() {

    }

}
