/**
 * 财务人员最高权限主界面的功能选择按钮
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class HighestCounterPanel extends JPanel {

	private MyButton accountManageBtn;
	
	private MyButton paymentBtn;
	
	private MyButton conditionBtn;
	
	private MyButton logBtn;
	
	private MyButton initialBtn;
	
	private PanelConfig pcfg;
	
	private HomeUI frame;
	
	public HighestCounterPanel(HomeUI frame) {
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(pcfg.getBg(), 0, 0, pcfg.getW(), pcfg.getH(), null);
	}

	private void initComponent() {
		
		MyLabel title = new MyLabel(this.pcfg.getLabels().element("title"));
		this.add(title);
		
		this.accountManageBtn = new MyButton(pcfg.getButtons().element("account"));
		this.accountManageBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
	}
	
}
