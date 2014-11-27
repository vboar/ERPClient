/**
 * 客户管理面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.ERPConfig;
import config.PanelConfig;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.customerbl.CustomerController;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel {
	
	private Image bg;
	
	private JFrame frame;
	
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
	}
	
}
