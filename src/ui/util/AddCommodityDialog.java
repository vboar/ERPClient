package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;

import vo.CommodityLineItemVO;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddCommodityDialog extends EditDialog implements FuzzySearch{
	
	protected MySpecialTextField commodityTxt;
	protected MyTextField numberTxt;
	
	protected MyButton commit;	
	protected MyButton cancel;	
	protected MyButton add;
	
	protected MyLabel currentId;	
	protected MyLabel currentName;
	protected MyLabel currentModel;
	
	protected DialogConfig cfg;
	
	protected boolean hasCommodity = false;
	protected CommodityVO addCommodityVO;	
	protected CommodityLineItemVO commodityLineItemVO;
	protected HashMap<String, CommodityVO> vomap;	
	
	protected AddCommodityLineItem panel;	

	protected CommodityBLService controller;
	
	/**
	 * 构造函数
	 * @param panel 赠送单面板
	 * @param frame 主窗口
	 */
	public AddCommodityDialog(AddCommodityLineItem panel,JFrame frame){
		super(frame);
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

	public AddCommodityDialog(AddCommodityLineItem panel,JFrame frame,boolean ismore){
		super(frame);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.panel = panel;
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		this.vomap = new HashMap<String,CommodityVO>();
	}
	
	/**
	 * 初始化组件
	 */
	protected void initComponent() {
		// 初始化按钮
		this.initButtons();
		// 初始化输入框
		this.commodityTxt = new MySpecialTextField(this.cfg.getTextFields().element("commodity"), this);
		this.commodityTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					showCommodity();
				}
			}		
		});
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
	protected void initButtons(){
		// 添加商品按钮
		this.add = new MyButton(this.cfg.getButtons().element("add"));
		this.add.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				showCommodity();
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
					if(num>addCommodityVO.number){
						MyOptionPane.showMessageDialog(frame, "库存不足，该商品库存仅余"+
					addCommodityVO.number+"件");
						return;
					}
					// 创建赠品信息
					addCommodity(num);
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
	
	protected void showCommodity(){
		if(commodityTxt.getText()!=null){
			addCommodityVO = vomap.get(commodityTxt.getText());
			if(addCommodityVO!=null){
				currentId.setText(addCommodityVO.id);
				currentName.setText(addCommodityVO.name);
				currentModel.setText(addCommodityVO.model);
				hasCommodity = true;
			}else{
				MyOptionPane.showMessageDialog(frame, "请重新选择商品！");
			}
		}
	}
	
	/**
	 * 添加商品信息
	 * @param key 搜索关键字
	 * @param num 商品数量
	 */
	protected void addCommodity(int num) {
		if(hasCommodity){
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
