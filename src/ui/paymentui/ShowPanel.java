/**
 * 查看单据面板
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.paymentblservice.CashBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPanel extends JPanel {

    private MyComboBox documentTypeBox;

    private MyDatePicker startPicker;

    private MyDatePicker endPicker;

    private MyButton findBtn;

    private MyButton showBtn;

    private ShowPaymentTable receiptTable;

    private ShowPaymentTable paymentTable;

    private ShowCashTable cashTable;

    private PaymentBLService receiptController;

    private PaymentBLService paymentController;

    private CashBLService cashController;

    private PanelConfig cfg;

    public ShowPanel(JFrame frame) {
        cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
        receiptController = ControllerFactoryImpl.getInstance().getReceiptController();
        paymentController = ControllerFactoryImpl.getInstance().getPaymentController();
        cashController = ControllerFactoryImpl.getInstance().getCashController();
        this.setSize(cfg.getW(), cfg.getH());
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        this.initComponent();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(cfg.getBg(), 0, 0, cfg.getW(), cfg.getH(),null);
    }

    private void initComponent() {
        initLabels();
        initBox();
        initPicker();
        initButtons();
        initTable();
    }

    private void initLabels() {
        add(new MyLabel(cfg.getLabels().element("documenttype")));
        add(new MyLabel(cfg.getLabels().element("starttime")));
        add(new MyLabel(cfg.getLabels().element("endtime")));
    }

    private void initBox() {
        documentTypeBox = new MyComboBox(cfg.getComboboxes().element("documenttype"));
        add(documentTypeBox);
    }

    private void initPicker() {
        startPicker = new MyDatePicker(cfg.getDatepicker().element("starttime"));
        endPicker = new MyDatePicker(cfg.getDatepicker().element("endtime"));
        add(startPicker);
        add(endPicker);
    }

    private void initButtons() {
        findBtn = new MyButton(cfg.getButtons().element("find"));
        add(findBtn);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        showBtn = new MyButton(cfg.getButtons().element("show"));
        add(showBtn);
        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initTable() {
        // 默认显示收款单的表格
        receiptTable = new ShowPaymentTable(new TableConfig(cfg.getTablepane()), receiptController);
        this.add(this.receiptTable);
    }

}
