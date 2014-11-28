/**
 * 客户管理面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.customerbl.CustomerController;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel {
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton modifyBtn;
	
	private MyButton findBtn;
	
	private MyLabel customerList;
	
	private MySpecialTextField findBox;
	
	private CustomerTablePane customerTable;
	
	private Image bg;
	
	private JFrame frame;
	
	private Boolean hasADialog;
	
	private PanelConfig pcfg;
	
	private CustomerController customerController;

	public CustomerPanel(JFrame frame) {
		this.frame = frame;
		customerController = ControllerFactoryImpl.getInstance().getCustomerController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(),pcfg.getH(),null);
	}
	
	/**
	 * 初始化面板元素
	 * @param cfg
	 */
	private void initComponent(PanelConfig cfg) {
		
	}
	
	private void initAddBtn(Element add) {
		this.addBtn = new MyButton(add);
		this.addBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!hasADialog){
					// TODO
				}
			}		
		});
	}
	
	
	
}
