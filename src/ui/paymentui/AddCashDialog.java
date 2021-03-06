package ui.paymentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import vo.ClauseLineItemVO;
import config.DialogConfig;

/**
 * 现金费用单添加条目对话框
 * Created by Vboar on 2014/12/7.
 */
@SuppressWarnings("serial")
public class AddCashDialog extends JDialog {

    private MyButton commit;

    private MyButton cancel;

    private MyTextField nameTxt;

    private MyTextField moneyTxt;

    private MyTextField remarkTxt;

    private AddClauseLineItem panel;

    private DialogConfig cfg;

    private ClauseLineItemVO vo;

    public AddCashDialog(DialogConfig cfg, JFrame frame, AddClauseLineItem panel) {
        super(frame, true);
        ((JComponent) this.getContentPane()).setOpaque(true);
        this.cfg = cfg;
        this.panel = panel;
        this.setTitle(cfg.getTitle());
        this.setSize(this.cfg.getW(), this.cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
        this.initComponent();
        this.setVisible(true);
    }

    private void initComponent() {
        this.initLabels(this.cfg.getLabels());
        this.initButtons(this.cfg.getButtons());
        this.initTextFields(this.cfg.getTextFields());
    }

    private void initLabels(Element element) {
        add(new MyLabel(element.element("name")));
        add(new MyLabel(element.element("money")));
        add(new MyLabel(element.element("remark")));
    }

    private void initButtons(Element element) {
        commit = new MyButton(element.element("commit"));
        add(commit);
        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认添加该条目？","确认提示",
                        MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                if(result==MyOptionPane.YES_OPTION){
                    // 判断金额的输入
                    if(moneyTxt.getText().length() == 0 ||
                            Double.parseDouble(moneyTxt.getText()) <= 0) {
                        MyOptionPane.showMessageDialog(null, "请正确输入金额！","错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // 添加条目
                    try{
                        double money = Double.parseDouble(moneyTxt.getText());
                        vo = new ClauseLineItemVO(nameTxt.getText(), money, remarkTxt.getText());
                        panel.addClauseLineItem(vo);
                        dispose();
                    }catch(NumberFormatException ex){
                        MyOptionPane.showMessageDialog(null, "请正确输入数据！","错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cancel = new MyButton(element.element("cancel"));
        add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认取消？", "确认提示",
                        MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
                if(result == MyOptionPane.YES_OPTION){
                    dispose();
                }
            }
        });
    }

    private void initTextFields(Element element) {
        moneyTxt = new MyTextField(element.element("money"));
        add(moneyTxt);

        nameTxt = new MyTextField(element.element("name"));
        add(nameTxt);

        remarkTxt = new MyTextField(element.element("remark"));
        add(remarkTxt);
    }

	@Override
	protected void processWindowEvent(WindowEvent e) {
		boolean flag = false;
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int result = MyOptionPane.showConfirmDialog(this, "是否放弃当前编辑？",
					"确认提示", MyOptionPane.YES_NO_OPTION,
					MyOptionPane.QUESTION_MESSAGE);
			if (result == MyOptionPane.YES_OPTION) {
				flag = true;
				dispose();
			}
		}
		if (flag) {
			// 点击的了YES,那么交给上面去处理关闭的处理
			super.processWindowEvent(e);
		}
	}

}
