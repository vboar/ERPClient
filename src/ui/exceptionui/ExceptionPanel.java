package ui.exceptionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;

/**
 * 库存异常处理面板
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class ExceptionPanel extends JPanel {

	private MyButton overListShow;
	
	private MyButton lossListShow;
	
	private MyButton warningListShow;
	
	private MyButton addLoss;
	
	private MyButton addOver;
	
	private JPanel createLossPanel;
	
	private JPanel createOverPanel;
	
	private JPanel showOverLossPanel;
	
	private JPanel showWarningPanel;
	
	private PanelConfig cfg;
	
	private Image bg;
	
	private JFrame frame;
	
	/**
	 * 构造函数
	 * @param frame
	 */
    public ExceptionPanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		
		// 设置大小、坐标及布局
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		
		// 初始化组件
		this.initComponent();
		this.repaint();
    }

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
	
    /**
     * 初始化组件
     */
	private void initComponent() {
		this.add(new MyLabel(cfg.getLabels().element("title")));
		// 显示报溢单按钮
		this.overListShow = new MyButton(this.cfg.getButtons().element("overlist"));
		this.overListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showExceptionList(false);
			}
		});
		this.add(this.overListShow);
		// 显示报损单按钮
		this.lossListShow = new MyButton(this.cfg.getButtons().element("losslist"));
		this.lossListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showExceptionList(true);
			}
		});
		this.add(this.lossListShow);
		// 显示报警单按钮
		this.warningListShow = new MyButton(this.cfg.getButtons().element("warning"));
		this.warningListShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showWarning();				
			}
		});
		this.add(this.warningListShow);
		// 创建报损单按钮
		this.addLoss = new MyButton(this.cfg.getButtons().element("addloss"));
		this.addLoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateLoss();
			}
		});
		this.add(this.addLoss);
		// 创建报溢单按钮
		this.addOver = new MyButton(this.cfg.getButtons().element("addover"));
		this.addOver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateOver();		
			}
		});
		this.add(this.addOver);
	}

	/**
	 * 显示报损单面板
	 */
	public void showCreateLoss() {
		if(createOverPanel!=null||createLossPanel!=null){
			if(MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑?","确认提示",
					MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)!=MyOptionPane.YES_OPTION){
				return;
			}
		}
		removeAllPanel();
		createLossPanel = new CreateLossPanel(frame, ExceptionPanel.this);
		add(createLossPanel);
		repaint();
	}

	/**
	 * 显示报溢单面板
	 */
	public void showCreateOver() {
		if(createOverPanel!=null||createLossPanel!=null){
			if(MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑?","确认提示",
					MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)!=MyOptionPane.YES_OPTION){
				return;
			}
		}
		removeAllPanel();
		createOverPanel = new CreateOverPanel(frame, ExceptionPanel.this);
		add(createOverPanel);
		repaint();
	}

	/**
	 * 显示报警单面板
	 */
	public void showWarning() {
		if(createOverPanel!=null||createLossPanel!=null){
			if(MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑?","确认提示",
					MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)!=MyOptionPane.YES_OPTION){
				return;
			}
		}
		removeAllPanel();
		showWarningPanel = new ShowWarningListPanel();
		add(showWarningPanel);
		repaint();
	}

	/**
	 * 显示报溢报损单列表
	 * @param isloss true为报损单,false为报溢单
	 */
	public void showExceptionList(boolean isloss) {
		if((createOverPanel!=null&&createOverPanel.isVisible())||
				(createLossPanel!=null&&createLossPanel.isVisible())){
			if(MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑?","确认提示",
					MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)!=MyOptionPane.YES_OPTION){
				return;
			}
		}
		removeAllPanel();
		showOverLossPanel = new ShowOverLossListPanel(isloss);
		add(showOverLossPanel);
		repaint();
	}
	
	/**
	 * 移除当前所有面板
	 */
	private void removeAllPanel() {
		if(createLossPanel != null) remove(createLossPanel); createLossPanel = null;
		if(createOverPanel != null) remove(createOverPanel); createOverPanel = null;
		if(showOverLossPanel != null) remove(showOverLossPanel); showOverLossPanel = null;
		if(showWarningPanel != null) remove(showWarningPanel);showWarningPanel = null;
	}
}
