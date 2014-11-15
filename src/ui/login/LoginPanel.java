package ui.login;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTextField;
import config.ButtonConfig;
import config.ERPConfig;
import config.LabelConfig;
import config.PanelConfig;
import config.TextFieldConfig;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel{
	
	private JComboBox<String> box;
	
	public LoginPanel(){
		// 设置布局管理器为自由布局
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		//获得面板配置
		PanelConfig panelCfg = ERPConfig.getLOGINFRAME_CONFIG().getPanelsCfg().get(0);
		this.initLabel(panelCfg);
		this.initButton(panelCfg);
		this.initText(panelCfg);
		String[] types = {"库存管理人员","进货销售人员", "财务人员", "总经理", "系统管理员"};		
		this.box = new JComboBox<String>(types);
		this.box.setBounds(350, 80, 145, 30);
		this.add(box);
	}
	
	private void initButton(PanelConfig cfg){
		//获得按钮配置
		ArrayList<ButtonConfig> buttonsCfg = cfg.getButtonsCfg();
		for(int i=0; i<buttonsCfg.size(); i++){
			this.add(new MyButton(buttonsCfg.get(i).getTitle(), buttonsCfg.get(i).getW(),
				buttonsCfg.get(i).getH(), buttonsCfg.get(i).getX(),buttonsCfg.get(i).getY()));
		}	
	}
	
	private void initLabel(PanelConfig cfg){
		//获得标签配置
		ArrayList<LabelConfig> labelsCfg = cfg.getLabelsCfg();
		for(int i=0; i<labelsCfg.size(); i++){
			this.add(new MyLabel(labelsCfg.get(i).getText(), labelsCfg.get(i).getW(),
					labelsCfg.get(i).getH(), labelsCfg.get(i).getX(),labelsCfg.get(i).getY()));
		}	
	}
	
	private void initText(PanelConfig cfg){
		//获得输入框配置
		ArrayList<TextFieldConfig> textfieldCfg = cfg.getTextFieldsCfg();
		for(int i=0; i<textfieldCfg.size(); i++){
			this.add(new MyTextField( textfieldCfg.get(i).getW(),textfieldCfg.get(i).getH(),
					textfieldCfg.get(i).getX(),textfieldCfg.get(i).getY()));
		}	
	}
}
