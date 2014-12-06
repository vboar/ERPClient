package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.CustomerGiftBLService;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.ResultMessage;
import vo.CustomerGiftVO;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 针对客户级别促销策略面板
 * @author JanelDQ
 * @date 2014/12/6
 */
@SuppressWarnings("serial")
public class CustomerPanel extends JPanel{
	
	private MyButton add;
	
	private MyButton unable;
	
	private MyButton update;
	
	private PanelConfig cfg;
	
	private CustomerTablePane tablepane;
	
	private JFrame frame;
	
	private CustomerGiftBLService controller;
	
	public CustomerPanel(JFrame frame){
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCustomerGiftController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(false);
	}

	private void initComponent() {
		this.add = new MyButton(cfg.getButtons().element("add"));
		this.add.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerGiftInfoDialog(frame,true);
			}
		});
		this.unable = new MyButton(cfg.getButtons().element("unable"));
		this.unable.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablepane.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认停用改促销策略？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						CustomerGiftVO vo = tablepane.getSelectedVO();
						vo.valid = false;
						ResultMessage updresult = controller.update(vo);
						if(updresult == ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(frame, "停用成功！");
						}else{
							MyOptionPane.showMessageDialog(frame, "停用失败！");
						}
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择一个促销策略！");
				}
			}
		});
		this.update = new MyButton(cfg.getButtons().element("update"));
		this.update.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablepane.isSelected()){
					CustomerGiftVO vo = tablepane.getSelectedVO();
					new CustomerGiftInfoDialog(frame, false, vo);
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择一个促销策略！");
				}
			}
		});
		this.add(new MyLabel(cfg.getLabels().element("list")));
		this.tablepane = new CustomerTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.add);
		this.add(this.unable);
		this.add(this.update);
		this.add(this.tablepane);
	}
}
