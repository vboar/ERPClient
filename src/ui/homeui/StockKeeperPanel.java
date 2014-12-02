/**
 * 库存管理人员
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import ui.commodityui.categoryui.CategoryPanel;
import ui.commodityui.commodityui.CommodityPanel;
import ui.exceptionui.ExceptionPanel;
import ui.messageui.MessagePanel;
import ui.presentui.PresentPanel;
import ui.stockui.stockcheck.StockCheckPanel;
import ui.stockui.stockinfo.StockInfoPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class StockKeeperPanel extends MyMainPanel {

	private MyButton categoryManageBtn;

	private MyButton commodityManageBtn;
	
	private MyButton presentBtn;
	
	private MyButton stockBtn;
	
	private MyButton stockCheckBtn;
	
	private MyButton exceptionBtn;
	
	private CommodityPanel commodityPanel;
	
	private CategoryPanel categoryPanel;
	
	private PresentPanel presentPanel;
	
	private StockInfoPanel stockInfoPanel;

	private StockCheckPanel stockCheckPanel;

	private ExceptionPanel exceptionPanel;

	private MessagePanel messagePanel;
	
	public StockKeeperPanel(JFrame frame){
		super(frame);
	}

	@Override
	public void initComponent() {
		super.initComponent();

		this.categoryManageBtn = new MyButton(pcfg.getButtons().element("category"));
		this.categoryManageBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				showCategory();
			}
		});
		this.commodityManageBtn = new MyButton(pcfg.getButtons().element("commodity"));
		this.commodityManageBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showCommodity();
			}
			
		});
		this.presentBtn = new MyButton(pcfg.getButtons().element("present"));
		this.presentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showPresent();
			}
		});
		this.stockBtn = new MyButton(pcfg.getButtons().element("stock"));
		this.stockBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showStockInfo();
			}

		});
		this.stockCheckBtn = new MyButton(pcfg.getButtons().element("stockcheck"));
		stockCheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showStockCheck();
			}
		});
		this.exceptionBtn = new MyButton(pcfg.getButtons().element("exception"));
		exceptionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showException();
			}
		});
		this.add(this.categoryManageBtn);
		this.add(this.commodityManageBtn);
		this.add(this.presentBtn);
		this.add(this.stockBtn);
		this.add(this.stockCheckBtn);
		this.add(this.exceptionBtn);

	}

	public void showCategory() {
		removeAllPanel();
		categoryPanel = new CategoryPanel(frame);
		this.add(categoryPanel);
		repaint();
	}

	public void showCommodity() {
		removeAllPanel();
		commodityPanel = new CommodityPanel(frame);
		add(commodityPanel);
		repaint();
	}

	public void showPresent() {
		removeAllPanel();
		presentPanel = new PresentPanel(frame);
		add(presentPanel);
		repaint();
	}

	public void showStockInfo() {
		removeAllPanel();
		stockInfoPanel = new StockInfoPanel(frame);
		add(stockInfoPanel);
		repaint();
	}

	public void showStockCheck() {
		removeAllPanel();
		stockCheckPanel = new StockCheckPanel(frame);
		add(stockCheckPanel);
		repaint();
	}

	public void showException() {
		removeAllPanel();
		exceptionPanel = new ExceptionPanel(frame);
		add(exceptionPanel);
		repaint();
	}

	@Override
	public void showMesssage() {
		removeAllPanel();
		messagePanel = new MessagePanel(frame);
		add(messagePanel);
		repaint();
	}

	private void removeAllPanel() {
		if(categoryPanel != null) remove(categoryPanel); categoryPanel = null;
		if(commodityPanel != null) remove(commodityPanel); commodityPanel = null;
		if(presentPanel != null) remove(presentPanel); presentPanel = null;
		if(stockInfoPanel != null) remove(stockInfoPanel); stockInfoPanel = null;
		if(stockCheckPanel != null) remove(stockCheckPanel); stockCheckPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
		if(exceptionPanel != null) remove(exceptionPanel); exceptionPanel = null;
	}
	
}
