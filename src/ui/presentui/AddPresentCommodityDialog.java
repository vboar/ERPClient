package ui.presentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.MyTextField;
import vo.CommodityVO;
import vo.PresentLineItemVO;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddPresentCommodityDialog extends JDialog implements FuzzySearch{

	private MySpecialTextField commodityTxt;
	
	private MyTextField numberTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private MyLabel id;
	
	private MyLabel name;
	
	private MyLabel model;
	
	private DialogConfig cfg;
	
	private CommodityVO addCommodityVO;
	
	private HashMap<String, CommodityVO> vomap;
	
	private CommodityBLService controller;
	
	public AddPresentCommodityDialog(CommodityBLService controller){
		this.controller = controller;
		this.cfg = ERPConfig.getADDPRESENTCOMMODITY_DIALOG_CONFIG();
		this.vomap = new HashMap<String,CommodityVO>();
		this.initComponent();
	}

	private void initComponent() {
		this.commodityTxt = new MySpecialTextField(this.cfg.getTextFields().element("commodity"), this);
		this.numberTxt = new MyTextField(this.cfg.getTextFields().element("number"));
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认添加该赠品？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					try{
					int num = Integer.parseInt(numberTxt.getText());
					addCommodity(commodityTxt.getText(),num);
					}catch(NumberFormatException ex){
						MyOptionPane.showMessageDialog(null, "请正确输入数据！","错误提示",
								MyOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		this.cancel = new MyButton(this.cfg.getButtons().element("cancel"));
		this.id = new MyLabel(this.cfg.getLabels().element("id"));
		this.name = new MyLabel(this.cfg.getLabels().element("name"));
		this.model = new MyLabel(this.cfg.getLabels().element("model"));
		this.add(this.id);
		this.add(this.name);
		this.add(this.model);
		this.add(this.commit);
		this.add(this.cancel);
		this.add(this.commodityTxt);
		this.add(this.numberTxt);
	}

	protected PresentLineItemVO addCommodity(String key, int num) {
		addCommodityVO = vomap.get(key);
		if(addCommodityVO!=null){
			PresentLineItemVO vo = new PresentLineItemVO(addCommodityVO.id, addCommodityVO.name,
				addCommodityVO.model, num);
			return vo;
		}
		return null;
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.controller.fuzzyFind(keyword);
		ArrayList<String> result = new ArrayList<String>();
		vomap.clear();
		for(int i=0; i<list.size(); ++i){
			String str = list.get(i).name+"-"+list.get(i).model;
			result.add(list.get(i).name+"-"+list.get(i).model);
			vomap.put(str, list.get(i));
		}
		return result;
	}

}
