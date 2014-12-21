package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vo.CommodityLineItemVO;
import config.ERPConfig;

@SuppressWarnings("serial")
public class AddTradeCommodityDialog extends AddCommodityDialog implements
		FuzzySearch {

	private MyTextField remarkTxt;
	private MyTextField priceTxt;

	private boolean isPurchase;

	public AddTradeCommodityDialog(AddCommodityLineItem panel, JFrame frame,
			boolean isPurchase) {
		super(panel, frame,true);
		this.isPurchase = isPurchase;
		this.cfg = ERPConfig.getADDSALECOMMODITY_DIALOG_CONFIG();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()
				+ this.cfg.getY());
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
	}

	/**
	 * 初始化组件
	 */
	protected void initComponent() {
		super.initComponent();
		// 初始化输入框
		this.priceTxt = new MyTextField(cfg.getTextFields().element("price"));
		this.remarkTxt = new MyTextField(cfg.getTextFields().element("remark"));
		this.add(remarkTxt);
		this.add(this.priceTxt);
		// 初始化标签
		this.add(new MyLabel(this.cfg.getLabels().element("price")));
		this.add(new MyLabel(this.cfg.getLabels().element("remark")));
	}

	/**
	 * 初始化按钮
	 */
	protected void initButtons() {
		super.initButtons();
		this.remove(commit);
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认添加该商品？",
						"确认提示", MyOptionPane.YES_NO_OPTION,
						MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					try {
						int num = Integer.parseInt(numberTxt.getText());
						double price = Double.parseDouble(priceTxt.getText());
						if (num <= 0 || price < 0) {
							throw new NumberFormatException();
						}
						if (!isPurchase) {
							if (num > addCommodityVO.number) {
								MyOptionPane.showMessageDialog(frame,
										"库存不足，该商品库存" + addCommodityVO.number
												+ "件。");
								return;
							}
						}
						String info = commodityTxt.getText();
						String remark = remarkTxt.getText();
						// 创建赠品信息
						addCommodity(info, num, price, remark);
						AddTradeCommodityDialog.this.dispose();
					} catch (NumberFormatException ex) {
						MyOptionPane.showMessageDialog(frame, "请输入合理数据！",
								"错误提示", MyOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		this.add(this.commit);
	}

	@Override
	protected void showCommodity() {
		super.showCommodity();
		priceTxt.setText(Double.toString(addCommodityVO.salePrice));
	}

	protected void addCommodity(String key, int num, double price, String remark) {
		addCommodityVO = this.vomap.get(key);
		if (addCommodityVO != null) {
			this.commodityLineItemVO = new CommodityLineItemVO(
					addCommodityVO.id, addCommodityVO.name,
					addCommodityVO.model, num, price, num * price, remark);
			this.panel.addCommodityLineItem(this.commodityLineItemVO);
		} else {
			MyOptionPane.showMessageDialog(frame, "请选择商品信息！");
		}
	}

}
