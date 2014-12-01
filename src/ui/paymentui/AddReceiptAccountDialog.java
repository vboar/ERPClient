package ui.paymentui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import config.InfoDialogConfig;
import org.dom4j.Element;
import ui.util.*;
import vo.AccountVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 创建收款单添加账户框
 * Created by Vboar on 2014/12/1.
 */
public class AddReceiptAccountDialog extends JDialog implements FuzzySearch {

    private MyButton commit;

    private MyButton cancel;

    private MySpecialTextField accountTxt;

    private MyTextField moneyTxt;

    private MyTextField remarkTxt;

    private CreateReceiptPanel panel;

    private InfoDialogConfig cfg;

    private AccountBLService accountController;

    public AddReceiptAccountDialog(InfoDialogConfig cfg, JFrame frame, CreateReceiptPanel panel) {
        super(frame, true);
        ((JComponent) this.getContentPane()).setOpaque(true);
        accountController = ControllerFactoryImpl.getInstance().getAccountController();
        this.cfg = cfg;
        this.setTitle("账户信息");
        this.panel = panel;
        this.setSize(this.cfg.getW(), this.cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
        this.initComponent();
    }

    private void initComponent() {
        this.initLabels(this.cfg.getLabels());
        this.initButtons(this.cfg.getButtons());
        this.initTextFields(this.cfg.getTextFields());
    }

    private void initButtons(Element element) {
        commit = new MyButton(element.element("commit"));
        add(commit);
        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        cancel = new MyButton(element.element("cancel"));
        add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
    }

    private void initLabels(Element element) {
        add(new MyLabel(element.element("account")));
        add(new MyLabel(element.element("money")));
        add(new MyLabel(element.element("remark")));
    }

    private void initTextFields(Element element) {
        moneyTxt = new MyTextField(element.element("money"));
        add(moneyTxt);

        accountTxt = new MySpecialTextField(element.element("account"), this);
        add(accountTxt);

        remarkTxt = new MyTextField(element.element("remark"));
        add(remarkTxt);
    }

    @Override
    public ArrayList<String> getFuzzyResult(String keyword) {
        ArrayList<AccountVO> result = this.accountController.fuzzyFind(keyword);
        ArrayList<String> strs = new ArrayList<String>();
        for(int i = 0; i < result.size(); ++i){
            AccountVO vo = result.get(i);
            strs.add(vo.name + "  " + vo.account);
        }
        return strs;
    }
}
