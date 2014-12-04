package ui.exceptionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextField;
import vo.CommodityVO;
import vo.ExceptionLineItemVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddExceptionLineItemDialog extends JDialog implements FuzzySearch{

	private MySpecialTextField commodityTxt;
	
	private MyTextField numberTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private MyButton add;
	
	private MyLabel currentId;
	
	private MyLabel currentName;
	
	private MyLabel currentModel;
	
	private CommodityVO addCommodityVO;
	
	private ExceptionLineItemVO exceptionLineItemVO;
	
	private CreateLossPanel panel;
	
	private HashMap<String, CommodityVO> vomap;
	
	private CommodityBLService commodityController;
	
	private DialogConfig cfg;
	
	public AddExceptionLineItemDialog(CreateLossPanel panel, JFrame frame) {
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.panel = panel;
		this.commodityController = ControllerFactoryImpl.getInstance().getCommodityController();
		this.vomap = new HashMap<String,CommodityVO>();
		this.cfg = ERPConfig.getADDPRESENTCOMMODITY_DIALOG_CONFIG();
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
		this.setVisible(true);
	}

	private void initComponent() {
		this.initButtons();
		this.commodityTxt = new MySpecialTextField(this.cfg.getTextFields().element("commodity"), this);
		this.numberTxt = new MyTextField(this.cfg.getTextFields().element("number"));	
		this.currentId = new MyLabel(this.cfg.getLabels().element("currentid"));
		this.currentName = new MyLabel(this.cfg.getLabels().element("currentname"));
		this.currentModel = new MyLabel(this.cfg.getLabels().element("currentmodel"));
		this.add(new MyLabel(this.cfg.getLabels().element("id")));
		this.add(new MyLabel(this.cfg.getLabels().element("name")));
		this.add(new MyLabel(this.cfg.getLabels().element("model")));
		this.add(new MyLabel(this.cfg.getLabels().element("number")));
		this.add(new MyLabel(this.cfg.getLabels().element("tip")));
		this.add(this.currentId);
		this.add(this.currentName);
		this.add(this.currentModel);
		this.add(this.commodityTxt);
		this.add(this.numberTxt);
	}

	private void initButtons() {
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
						MyOptionPane.showMessageDialog(null, "请重新选择商品！");
					}
				}
			}
		});
		this.add(this.add);
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认添加该商品？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					try{
					int num = Integer.parseInt(numberTxt.getText());
					String info = commodityTxt.getText();
					// 创建赠品信息
					addCommodity(info,num);
					AddExceptionLineItemDialog.this.dispose();
					}catch(NumberFormatException ex){
						MyOptionPane.showMessageDialog(null, "请正确输入数据！","错误提示",
								MyOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		this.add(this.commit);
		this.cancel = new MyButton(this.cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认取消？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					AddExceptionLineItemDialog.this.dispose();
				}
			}
			
		});
		this.add(this.cancel);
	}

	protected void addCommodity(String key, int num) {
		if(addCommodityVO!=null){
			this.exceptionLineItemVO = new ExceptionLineItemVO(addCommodityVO.id, addCommodityVO.name,
					addCommodityVO.model, addCommodityVO.number, 
					num);;
			this.panel.addCommodity(this.exceptionLineItemVO);
		}else{
			MyOptionPane.showMessageDialog(null, "请选择商品信息！");
		}
	}
	
	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.commodityController.fuzzyFind(keyword);
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
