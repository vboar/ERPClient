package ui.paymentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextField;
import vo.AccountVO;
import vo.TransferLineItemVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.accountblservice.AccountBLService;
import config.DialogConfig;

/**
 * 创建收款单添加账户框
 * Created by Vboar on 2014/12/1.
 */
@SuppressWarnings("serial")
public class AddAccountDialog extends JDialog implements FuzzySearch {

    private MyButton commit;

    private MyButton cancel;

    private MyButton addAccount;

    private MySpecialTextField accountTxt;

    private MyTextField moneyTxt;

    private MyTextField remarkTxt;

    private MyLabel currentAccount;

    private MyLabel currentName;

    private CreatePanel panel;

    private DialogConfig cfg;

    private AccountVO addAccountVO;

    private TransferLineItemVO transferLineItemVO;

    private AccountBLService accountController;

    private HashMap<String, AccountVO> vomap;

    private boolean hasAccount;

    public AddAccountDialog(DialogConfig cfg, JFrame frame, CreatePanel panel) {
        super(frame, true);
        ((JComponent) this.getContentPane()).setOpaque(true);
        accountController = ControllerFactoryImpl.getInstance().getAccountController();
        this.cfg = cfg;
        this.panel = panel;
        vomap = new HashMap<String, AccountVO>();
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

    private void initButtons(Element element) {

        addAccount = new MyButton(element.element("addaccount"));
        add(addAccount);
        addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	showAccountInfo();
            }
        });

        commit = new MyButton(element.element("commit"));
        add(commit);
        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = MyOptionPane.showConfirmDialog(null, "确认添加该账户条目？","确认提示",
                        MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                if(result==MyOptionPane.YES_OPTION){
                    // 判断账户的选择
                    if(!hasAccount) {
                        MyOptionPane.showMessageDialog(null, "请选择账户！","错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // 判断金额的输入
                    if(moneyTxt.getText().length() == 0 ||
                            Double.parseDouble(moneyTxt.getText()) <= 0) {
                        MyOptionPane.showMessageDialog(null, "请正确输入金额！","错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // 判断账户的重复性
                    if(checkRepeat()) {
                        MyOptionPane.showMessageDialog(null, "已经存在该账户的条目了！","错误提示",
                                MyOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // 添加条目
                    try{
                        addAccount();
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
                int result = MyOptionPane.showConfirmDialog(null, "确认取消？","确认提示",
                        MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
                if(result == MyOptionPane.YES_OPTION){
                    dispose();
                }
            }
        });
    }

    private void initLabels(Element element) {
        add(new MyLabel(element.element("account")));
        add(new MyLabel(element.element("name")));
        add(new MyLabel(element.element("money")));
        add(new MyLabel(element.element("remark")));
        add(new MyLabel(element.element("tip")));
        currentAccount = new MyLabel(element.element("currentaccount"));
        currentName = new MyLabel(element.element("currentname"));
        add(currentAccount);
        add(currentName);
    }

    private void initTextFields(Element element) {
        moneyTxt = new MyTextField(element.element("money"));
        add(moneyTxt);

        accountTxt = new MySpecialTextField(element.element("account"), this);
        accountTxt.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e){
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			showAccountInfo();
        		}
        	}
		});
        add(accountTxt);

        remarkTxt = new MyTextField(element.element("remark"));
        add(remarkTxt);
    }

    protected void showAccountInfo() {
        addAccountVO = vomap.get(accountTxt.getText());
        if(addAccountVO!=null){
            currentAccount.setText(addAccountVO.account);
            currentName.setText(addAccountVO.name);
            hasAccount = true;
        }else{
            MyOptionPane.showMessageDialog(null, "请重新选择商品！");
        }
	}

	public void addAccount() {
        if(addAccountVO != null) {
            transferLineItemVO = new TransferLineItemVO(addAccountVO.name, addAccountVO.account,
                    Double.parseDouble(moneyTxt.getText()), remarkTxt.getText());
            panel.addAccount(transferLineItemVO);
        } else {
            MyOptionPane.showMessageDialog(null, "请选择账户信息！");
        }
    }

    @Override
    public ArrayList<String> getFuzzyResult(String keyword) {
        ArrayList<AccountVO> result = this.accountController.fuzzyFind(keyword);
        ArrayList<String> strs = new ArrayList<String>();
        for(int i = 0; i < result.size(); ++i){
            String str = result.get(i).name+"-"+result.get(i).account;
            strs.add(str);
            vomap.put(str, result.get(i));
        }
        return strs;
    }

    /**
     * 检查账户的重复性
     * @return
     */
    private boolean checkRepeat() {
        for(TransferLineItemVO vo: panel.getLists()) {
            if(addAccountVO.account.equals(vo.bankAccount)) {
                return true;
            }
        }
        return false;
    }
}
