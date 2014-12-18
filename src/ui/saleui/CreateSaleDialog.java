package ui.saleui;

import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ui.util.MyOptionPane;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class CreateSaleDialog extends JDialog{

	private DialogConfig cfg;
	
	private CreateSalePanel createpanel;
	
	private JScrollPane jsp;
	
	private JFrame frame;
	
	private SalePanel salepanel;
	
	public CreateSaleDialog(final JFrame frame, SalePanel panel){
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.salepanel = panel;
		this.frame = frame;
		this.cfg = ERPConfig.getADDSALE_DIALOG_CONFIG();
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
	
	private void initComponent() {
		jsp = new JScrollPane();
		jsp.setBounds(0, 0, this.getWidth() , this.getHeight());
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.createpanel = new CreateSalePanel(frame,this,false);
		jsp.setViewportView(this.createpanel);
		this.add(jsp);
	}
	
	public void updateData(){
		this.salepanel.udpateData();
	}
	

	@Override
	protected void processWindowEvent(WindowEvent e) {
		boolean flag = false;
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？",
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
