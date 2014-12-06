package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.AddCommodityLineItem;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextField;
import vo.CommodityLineItemVO;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddCommodityDialog extends JDialog implements FuzzySearch{
	
	private MySpecialTextField commodityTxt;
	
	private MyTextField numberTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private MyButton add;
	
	private MyLabel currentId;
	
	private MyLabel currentName;
	
	private MyLabel currentModel;
	
	private DialogConfig cfg;
	
	private CommodityVO addCommodityVO;
	
	private CommodityLineItemVO commodityLineItemVO;
	
	private AddCommodityLineItem panel;
	
	private JFrame frame;
	
	private HashMap<String, CommodityVO> vomap;
	
	private CommodityBLService controller;
	
	/**
	 * 构造函数
	 * @param panel 赠送单面板
	 * @param frame 主窗口
	 */
	public AddCommodityDialog(AddCommodityLineItem panel,JFrame frame){
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.panel = panel;
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		this.vomap = new HashMap<String,CommodityVO>();
		this.cfg = ERPConfig.getADDCOMMODITY_DIALOG_CONFIG();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
        // 初始化组件
		this.initComponent();
		this.setVisible(true);
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		// 初始化按钮
		this.initButtons();
		// 初始化输入框
		this.commodityTxt = new MySpecialTextField(this.cfg.getTextFields().element("commodity"), this);
		this.numberTxt = new MyTextField(this.cfg.getTextFields().element("number"));	
		this.add(this.commodityTxt);
		this.add(this.numberTxt);
		// 初始化标签
		this.add(new MyLabel(this.cfg.getLabels().element("id")));
		this.add(new MyLabel(this.cfg.getLabels().element("name")));
		this.add(new MyLabel(this.cfg.getLabels().element("model")));
		this.add(new MyLabel(this.cfg.getLabels().element("number")));
		this.add(new MyLabel(this.cfg.getLabels().element("tip")));
		this.currentId = new MyLabel(this.cfg.getLabels().element("currentid"));
		this.currentName = new MyLabel(this.cfg.getLabels().element("currentname"));
		this.currentModel = new MyLabel(this.cfg.getLabels().element("currentmodel"));
		this.add(this.currentId);
		this.add(this.currentName);
		this.add(this.currentModel);

	}

	/**
	 * 初始化按钮
	 */
	private void initButtons(){
		// 添加商品按钮
		this.add = new MyButton(this.cfg.getButtons().element("add"));
		this.add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(commodityTxt.getText()!=null){
					addCommodityVO = vomap.get(commodityTxt.getText());
					if(addCommodityVO!=null){
						currentId.setText(addCommodityVO.id);
						currentName.setText(addCommodityVO.name);
						currentModel.setText(addCommodityVO.model);
					}else{
						MyOptionPane.showMessageDialog(frame, "请重新选择商品！");
					}
				}
			}
		});
		this.add(this.add);
		// 提交按钮
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认添加该商品？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					try{
					int num = Integer.parseInt(numberTxt.getText());
					String info = commodityTxt.getText();
					// 创建赠品信息
					addCommodity(info,num);
					AddCommodityDialog.this.dispose();
					}catch(NumberFormatException ex){
						MyOptionPane.showMessageDialog(frame, "请正确输入数据！","错误提示",
								MyOptionPane.ERROR_MESSAGE);
					}
				}
			}	
		});
		this.add(this.commit);
		// 取消按钮
		this.cancel = new MyButton(this.cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认取消？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					AddCommodityDialog.this.dispose();
				}
			}
			
		});
		this.add(this.cancel);
	}
	
	/**
	 * 添加商品信息
	 * @param key 搜索关键字
	 * @param num 商品数量
	 */
	protected void addCommodity(String key, int num) {
		addCommodityVO = this.vomap.get(key);
		if(addCommodityVO!=null){
			this.commodityLineItemVO = new CommodityLineItemVO(addCommodityVO.id, addCommodityVO.name,
				addCommodityVO.model, num);
			this.panel.addCommodityLineItem(this.commodityLineItemVO);
		}else{
			MyOptionPane.showMessageDialog(frame, "请选择商品信息！");
		}
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.controller.fuzzyFind(keyword);
		if(list!=null){
			ArrayList<String> result = new ArrayList<String>();
			for(int i=0; i<list.size(); ++i){
				String str = list.get(i).name+"-"+list.get(i).model;
				result.add(list.get(i).name+"-"+list.get(i).model);
				vomap.put(str, list.get(i));
			}
			return result;
		}
		return null;
	}
}
