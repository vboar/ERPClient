package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import vo.CommodityLineItemVO;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddTradeCommodityDialog extends JDialog implements FuzzySearch{
	
	private MySpecialTextField commodityTxt;
	
	private MyTextField numberTxt;
	
	private MyTextField remarkTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private MyButton add;
	
	private MyLabel currentId;
	
	private MyLabel currentName;
	
	private MyLabel currentModel;
	
	private MyTextField priceTxt;
	
	private DialogConfig cfg;
	
	private CommodityVO addCommodityVO;
	
	private CommodityLineItemVO commodityLineItemVO;
	
	private AddCommodityLineItem panel;
	
	private JFrame frame;
	
	private HashMap<String, CommodityVO> vomap;
	
	private CommodityBLService controller;
	
	private boolean isPurchase;
	
	/**
	 * 构造函数
	 * @param panel 赠送单面板
	 * @param frame 主窗口
	 */
	public AddTradeCommodityDialog(AddCommodityLineItem panel,JFrame frame,boolean isPurchase){
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.isPurchase = isPurchase;
		this.panel = panel;
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		this.vomap = new HashMap<String,CommodityVO>();
		this.cfg = ERPConfig.getADDSALECOMMODITY_DIALOG_CONFIG();
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
		this.priceTxt = new MyTextField(cfg.getTextFields().element("price"));
		this.remarkTxt = new MyTextField(cfg.getTextFields().element("remark"));
		this.add(remarkTxt);
		this.add(this.priceTxt);
		this.add(this.commodityTxt);
		this.add(this.numberTxt);
		// 初始化标签
		this.add(new MyLabel(this.cfg.getLabels().element("id")));
		this.add(new MyLabel(this.cfg.getLabels().element("name")));
		this.add(new MyLabel(this.cfg.getLabels().element("model")));
		this.add(new MyLabel(this.cfg.getLabels().element("number")));
		this.add(new MyLabel(this.cfg.getLabels().element("price")));
		this.add(new MyLabel(this.cfg.getLabels().element("tip")));
		this.add(new MyLabel(this.cfg.getLabels().element("remark")));
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
						priceTxt.setText(Double.toString(addCommodityVO.salePrice));
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
					double price = Double.parseDouble(priceTxt.getText());
					if(num<=0||price<0){
						throw new NumberFormatException();
					}
					if(!isPurchase){
						if(num>addCommodityVO.number){
							MyOptionPane.showMessageDialog(frame, "库存不足，该商品库存"+addCommodityVO.number+"件。");
							return;
						}
					}
					String info = commodityTxt.getText();
					String remark = remarkTxt.getText();
					// 创建赠品信息
					addCommodity(info,num,price,remark);
					AddTradeCommodityDialog.this.dispose();
					}catch(NumberFormatException ex){
						MyOptionPane.showMessageDialog(frame, "请输入合理数据！","错误提示",
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
					AddTradeCommodityDialog.this.dispose();
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
	protected void addCommodity(String key, int num,double price,String remark) {
		addCommodityVO = this.vomap.get(key);
		if(addCommodityVO!=null){
			this.commodityLineItemVO = new CommodityLineItemVO(addCommodityVO.id, addCommodityVO.name,
				addCommodityVO.model, num, price,num*price,remark);
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
