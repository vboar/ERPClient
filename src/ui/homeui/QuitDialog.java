package ui.homeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.loginui.LoginUI;
import ui.util.MyButton;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class QuitDialog extends JDialog{

	private MyButton quit;
	
	private MyButton logout;
	
	private DialogConfig pcfg;
	
	private JFrame home;
	
	public QuitDialog(JFrame frame){
		super(frame,true);
		this.home = frame;
		this.pcfg = ERPConfig.getQUIT_DIALOG_CONFIG();
        ((JComponent) this.getContentPane()).setOpaque(true);
		this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.pcfg.getX(), frame.getY()+this.pcfg.getY());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setTitle(pcfg.getTitle());
		// 初始化组件
        this.initComponent();
		this.repaint();
		this.setVisible(true);
	}

	private void initComponent() {
		this.quit = new MyButton(pcfg.getButtons().element("quit"));
		this.logout = new MyButton(pcfg.getButtons().element("logout"));
		this.quit.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.logout.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				home.dispose();
				new LoginUI();
			}
		});
		this.add(quit);
		this.add(logout);
		}
}
