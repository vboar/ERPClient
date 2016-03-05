package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.ResultMessage;
import vo.SpecialOfferVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.promotionblservice.SpecialOfferBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SpecialOfferPanel extends JPanel{
	
	private MyButton add;
	
	private MyButton unable;
	
	private MyButton update;
	
	private PanelConfig cfg;
	
	private JFrame frame;

	private SpecialOfferTablePane tablepane;
	
	private SpecialOfferBLService controller;
	
	public SpecialOfferPanel(JFrame frame){
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getSpecialOfferController();
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
				SpecialOfferInfoDialog diag = new SpecialOfferInfoDialog(frame,tablepane,true);
				diag.setVisible(true);
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
						SpecialOfferVO vo = tablepane.getSelectedVO();
						vo.valid = false;
						ResultMessage updresult = controller.update(vo);
						if(updresult == ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(frame, "停用成功！");
						}else{
							MyOptionPane.showMessageDialog(frame, "停用失败！");
						}
					}
					tablepane.updateData();
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
					SpecialOfferVO vo = tablepane.getSelectedVO();
					SpecialOfferInfoDialog dialog = new SpecialOfferInfoDialog(frame, tablepane,false, vo);
					dialog.setVisible(true);
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择一个促销策略！");
				}
			}
		});
		this.add(new MyLabel(cfg.getLabels().element("list")));
		this.tablepane = new SpecialOfferTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.add);
		this.add(this.unable);
		this.add(this.update);
		this.add(this.tablepane);
	}
}
