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
	
	private ArrayList<CommodityVO> list;
		
	public CommodityPanel(JFrame frame){
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
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
	
	private void initComponent(PanelConfig cfg) {
		this.initLabels(cfg.getLabels());
		this.findbtn = new MyButton(cfg.getButtons().element("find"));
		this.findbtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				list = controller.fuzzyFind(findTxt.getText());
			}
		});
		this.add(this.findbtn);
		this.treepane = new CommodityTreePane(pcfg.getTree(),this.frame);
		this.add(this.treepane);
		this.findTxt = new MySpecialTextField(pcfg.getTextFields().element("find"), this);
		this.add(this.findTxt);
	}

	private void initLabels(Element labels) {
		this.add(new MyLabel(labels.element("title")));
		this.add(new MyLabel(labels.element("commoditylist")));
		this.add(new MyLabel(labels.element("tip")));
	}
	
	
	
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
