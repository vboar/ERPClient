package ui.loginui;

import config.DialogConfig;
import dataservice.datafactoryservice.DataFactoryImpl;
import org.dom4j.Element;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录设置面板
 * Created by Vboar on 2014/12/13.
 */
public class LoginSettingDialog extends JDialog {

    private MyTextField addressTxt;

    private MyTextField portTxt;

    private MyButton commitBtn;

    private MyButton cancelBtn;

    private DialogConfig cfg;

    private LoginPanel panel;

    public LoginSettingDialog(DialogConfig cfg, JFrame frame, LoginPanel panel) {
        super(frame, true);
        ((JComponent) this.getContentPane()).setOpaque(true);
        this.cfg = cfg;
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

    private void initTextFields(Element textFields) {
        addressTxt = new MyTextField(textFields.element("address"));
        add(addressTxt);
        addressTxt.setText(DataFactoryImpl.address);

        portTxt = new MyTextField(textFields.element("port"));
        add(portTxt);
        portTxt.setText(DataFactoryImpl.port);
    }

    private void initButtons(Element buttons) {
        commitBtn = new MyButton(buttons.element("commit"));
        add(commitBtn);
        commitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认更改？", "确认提示",
                        MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
                if(result == MyOptionPane.YES_OPTION) {
                    DataFactoryImpl.address = addressTxt.getText();
                    DataFactoryImpl.port = portTxt.getText();
                    dispose();
                }
            }
        });

        cancelBtn = new MyButton(buttons.element("cancel"));
        add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginSettingDialog.this.dispose();
            }
        });
    }

    private void initLabels(Element labels) {
        add(new MyLabel(labels.element("address")));
        add(new MyLabel(labels.element("port")));
    }

}
