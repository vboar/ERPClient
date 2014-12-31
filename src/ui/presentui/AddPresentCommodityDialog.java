package ui.presentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.util.AddCommodityDialog;
import ui.util.AddCommodityLineItem;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyOptionPane;
import config.ERPConfig;

/**
 * 添加赠送单商品信息对话框
 * @author JanelDQ
 * @date 2014/12/3
 */
@SuppressWarnings("serial")
public class AddPresentCommodityDialog extends AddCommodityDialog implements FuzzySearch{

	private boolean isPromotion;
	/**
	 * 构造函数
	 * @param panel 赠送单面板
	 * @param frame 主窗口
	 */
	public AddPresentCommodityDialog(AddCommodityLineItem panel,JFrame frame,boolean isPromotion){
		super(panel,frame,true);
		System.out.println("hei!");
		this.isPromotion = isPromotion;
		this.cfg = ERPConfig.getADDPRESENTCOMMODITY_DIALOG_CONFIG();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
        // 初始化组件
		this.initComponent();
		this.setVisible(true);
	}
	
	/**
	 * 初始化按钮
	 */
	@Override
	protected void initButtons(){
		super.initButtons();
		if(isPromotion)
			return;
		this.remove(commit);
		// 提交按钮
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认添加该商品？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					try{
					int num = Integer.parseInt(numberTxt.getText());
					System.out.println(num+" "+ addCommodityVO.number);
					if(num>addCommodityVO.number){
						MyOptionPane.showMessageDialog(frame, "库存不足，该商品库存仅余"+
					addCommodityVO.number+"件");
						return;
					}
					// 创建赠品信息
					addCommodity(num);
					AddPresentCommodityDialog.this.dispose();
					}catch(NumberFormatException ex){
						MyOptionPane.showMessageDialog(frame, "请正确输入数据！","错误提示",
								MyOptionPane.ERROR_MESSAGE);
					}
				}
			}	
		});
		this.add(this.commit);
	}
	

}
