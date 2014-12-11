/**
 * 查看单据面板
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.paymentblservice.CashBLService;
import businesslogicservice.paymentblservice.PaymentBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPanel extends JPanel {

    private MyComboBox documentTypeBox;

    private DatePickerGroup startPicker;

    private DatePickerGroup endPicker;

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
        startPicker = new DatePickerGroup(cfg.getDatepicker().element("starttime"));
        endPicker = new DatePickerGroup(cfg.getDatepicker().element("endtime"));
        add(startPicker);
        add(endPicker);
    }

    private void initButtons() {
        findBtn = new MyButton(cfg.getButtons().element("find"));
        add(findBtn);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date day1 = startPicker.getDate();
                Date day2 = endPicker.getDate();
                String time1 = null;
                String time2 = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                if((day1!=null)&&(day2!=null)){
                    time1 = dateFormat.format(day1);
                    time2 = dateFormat.format(day2);
                    if(time1.compareTo(time2)>0){
                        MyOptionPane.showMessageDialog(ShowPanel.this, "请输入有效日期！", "错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                    }
                }else if((day1==null)&&(day2!=null)){
                    time2 = dateFormat.format(day2);
                }else if(day1!=null){
                    time1 = dateFormat.format(day1);
                }
                // 显示制定类型的单据表格
                switch (documentTypeBox.getSelectedIndex()) {
                    case 0:
                        receiptTable.showFindTable(time1, time2);
                        receiptTable.setVisible(true);
                        paymentTable.setVisible(false);
                        cashTable.setVisible(false);
                        break;
                    case 1:
                        paymentTable.showFindTable(time1, time2);
                        receiptTable.setVisible(false);
                        paymentTable.setVisible(true);
                        cashTable.setVisible(false);
                        break;
                    case 2:
                        cashTable.showFindTable(time1, time2);
                        receiptTable.setVisible(false);
                        paymentTable.setVisible(false);
                        cashTable.setVisible(true);
                }
            }
        });

        showBtn = new MyButton(cfg.getButtons().element("show"));
        add(showBtn);
        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示指定类型的单据表格
                switch (documentTypeBox.getSelectedIndex()) {
                    case 0:
                        receiptTable.showAllTable();
                        receiptTable.setVisible(true);
                        paymentTable.setVisible(false);
                        cashTable.setVisible(false);
                        break;
                    case 1:
                        paymentTable.showAllTable();
                        receiptTable.setVisible(false);
                        paymentTable.setVisible(true);
                        cashTable.setVisible(false);
                        break;
                    case 2:
                        cashTable.showAllTable();
                        receiptTable.setVisible(false);
                        paymentTable.setVisible(false);
                        cashTable.setVisible(true);
                }
            }
        });
    }

    private void initTable() {
        // 默认显示收款单的表格
        receiptTable = new ShowPaymentTable(new TableConfig(cfg.getTables().element("payment")),
                receiptController);
        this.add(this.receiptTable);
        receiptTable.showAllTable();
        paymentTable = new ShowPaymentTable(new TableConfig(cfg.getTables().element("payment")),
                paymentController);
        this.add(this.paymentTable);
        paymentTable.setVisible(false);
        cashTable = new ShowCashTable(new TableConfig(cfg.getTables().element("cash")),
                cashController);
        this.add(cashTable);
        cashTable.setVisible(false);
    }

}
