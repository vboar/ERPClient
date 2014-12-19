/**
 * 商品管理面板
 * @author JaneLDQ
 * @date 2014/11/30
 */
package ui.commodityui.commodityui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CommodityPanel extends JPanel implements FuzzySearch{
	
	private MyButton findbtn;

	private MySpecialTextField findTxt;
	
	private CommodityTreePane treepane;
	
	private Image bg;

	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private CommodityBLService controller;
	
	/**
	 * 构造函数
	 * @param frame
	 */
	public CommodityPanel(JFrame frame){
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		
		// 获得控制器对象
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		
		// 设置大小、坐标、布局
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		
		// 初始化组件
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(),pcfg.getH(),null);
	}
	
	/**
	 * 初始化组件
	 * @param cfg 配置对象
	 */
	private void initComponent(PanelConfig cfg) {
		// 初始化标签
		this.initLabels(cfg.getLabels());
		// 初始化查找按钮
		this.findbtn = new MyButton(cfg.getButtons().element("find"));
		this.findbtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				treepane.findCommodity(findTxt.getText());
			}
		});
		this.add(this.findbtn);
		
		// 初始化商品树形面板
		this.treepane = new CommodityTreePane(pcfg.getTree(),this.frame);
		treepane.init();
		this.add(this.treepane);
		
		// 初始化查找输入框
		this.findTxt = new MySpecialTextField(pcfg.getTextFields().element("find"), this);
		this.add(this.findTxt);
	}

	/**
	 * 初始化标签
	 * @param labels 标签配置对象
	 */
	private void initLabels(Element labels) {
		this.add(new MyLabel(labels.element("title")));
		this.add(new MyLabel(labels.element("commoditylist")));
		this.add(new MyLabel(labels.element("tip")));
	}
	
	/**
	 * 模糊查找
	 */
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.controller.fuzzyFind(keyword);
		ArrayList<String> results = new ArrayList<String>();
		if(list!=null){
			for(int i=0; i<list.size(); ++i){
				results.add(list.get(i).name+"-"+list.get(i).model);
			}
		}
		return results;
	}

}
