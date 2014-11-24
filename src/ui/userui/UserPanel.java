/**
 * 用户管理面板
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.userui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTextField;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class UserPanel extends JPanel{

	private MyButton addbtn;
	
	private MyButton deletebtn;
	
	private MyButton modifybtn;
	
	private MyButton findbtn;
	
	private MyTextField findInput;
	
	private MyLabel userlist;
	
	private Image bg;
	
	@SuppressWarnings("unused")
	private JFrame homeframe;
	
	private PanelConfig pcfg;
	
	public UserPanel(JFrame frame){
		this.homeframe = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getWidth(), pcfg.getHeight());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getWidth(),pcfg.getHeight(),null);
	}
	
	private void initComponent(PanelConfig cfg) {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initTextFields(cfg.getTextFields());
	}

	private void initTextFields(Element textfields) {
		this.findInput = new MyTextField(textfields.element("findinput"));
		this.add(this.findInput);
	}

	private void initLabels(Element labels) {
		this.userlist = new MyLabel(labels.element("userlist"));
		this.add(this.userlist);
	}

	private void initButtons(Element buttons) {
		this.addbtn = new MyButton(buttons.element("add"));
		this.deletebtn = new MyButton(buttons.element("delete"));
		this.modifybtn = new MyButton(buttons.element("modify"));
		this.findbtn = new MyButton(buttons.element("find"));
		this.add(this.addbtn);
		this.add(this.deletebtn);
		this.add(this.modifybtn);
		this.add(this.findbtn);
	}
}
