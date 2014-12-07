package ui.saleui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.presentui.CreatePresentPanel;
import ui.presentui.ShowPanel;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class SalePanel extends JPanel {

	private MyButton createSale;
	
	private MyButton show;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private SalePanel showpanel;
	
    public SalePanel(JFrame frame) {
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		// 设置面板基本属性
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
    }
    
	private void initComponent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
}
