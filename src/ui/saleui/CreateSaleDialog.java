package ui.saleui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ui.util.EditDialog;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class CreateSaleDialog extends EditDialog{

	private DialogConfig cfg;
	
	private CreateSalePanel createpanel;
	
	private JScrollPane jsp;
	
	private JFrame frame;
	
	private SalePanel salepanel;
	
	public CreateSaleDialog(final JFrame frame, SalePanel panel){
		super(frame);
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
	
}
