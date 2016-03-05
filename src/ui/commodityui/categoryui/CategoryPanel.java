/**
 * 商品分类管理面板
 * @author JaneLDQ
 * @date 2014/11/27
 */
package ui.commodityui.categoryui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CategoryPanel extends JPanel{
	
	private JFrame homeframe;
	
	private MyLabel title;
	
	private MyLabel category;
	
	private MyLabel tip;
	
	private MyButton showAll;
	
	private PanelConfig pcfg;
	
	private CategoryTreePane treepane;
	
	private Image bg;
	
	/**
	 * 构造函数
	 * @param frame
	 */
	public CategoryPanel(JFrame frame){
		this.homeframe = frame;
		
		// 获得配置
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		// 设置大小和坐标
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		
		// 初始化组件
		this.initComponent(pcfg);
		
		this.repaint();
		this.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	/**
	 * 初始化组件
	 * @param pcfg
	 */
	private void initComponent(PanelConfig pcfg) {
		// 添加分类树面板
		this.treepane = new CategoryTreePane(pcfg.getTree(),homeframe);
		this.add(this.treepane);
		
		// 添加标签
		this.title = new MyLabel(pcfg.getLabels().element("title"));
		this.category = new MyLabel(pcfg.getLabels().element("category"));
		this.tip = new MyLabel(pcfg.getLabels().element("tip"));
		this.add(this.tip);
		this.add(this.title);
		this.add(this.category);
		
		// 添加按钮
		this.showAll = new MyButton(pcfg.getButtons().element("showall"));
		this.showAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				treepane.expandNode();
			}
		});
		this.add(this.showAll);
	}

}
