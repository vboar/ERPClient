/**
 * 库存管理人员
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.commodityui.categoryui.CategoryPanel;
import ui.commodityui.commodityui.CommodityPanel;
import ui.presentui.PresentPanel;
import ui.stockui.stockinfo.StockInfoPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;

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
	
	public StockKeeperPanel(HomeUI frame){
		super(frame);
	}

	@Override
	public void initComponent() {
		super.initComponent();
		this.categoryManageBtn = new MyButton(pcfg.getButtons().element("category"));
		this.categoryManageBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(categoryPanel == null) {
					categoryPanel = new CategoryPanel(frame);
					add(categoryPanel);
				}
				if(commodityPanel != null) {
					remove(commodityPanel);
					commodityPanel = null;
				}
				if(stockInfoPanel != null) {
					remove(stockInfoPanel);
					stockInfoPanel = null;
				}
				repaint();
			}
		});
		this.commodityManageBtn = new MyButton(pcfg.getButtons().element("commodity"));
		this.commodityManageBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(commodityPanel == null) {
					commodityPanel = new CommodityPanel(frame);
					add(commodityPanel);
				}
				if(categoryPanel != null) {
					remove(categoryPanel);
					categoryPanel = null;
				}
				if(stockInfoPanel != null) {
					remove(stockInfoPanel);
					stockInfoPanel = null;
				}
				repaint();
				
			}
			
		});
		this.presentBtn = new MyButton(pcfg.getButtons().element("present"));
		this.presentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(presentPanel == null) {
					presentPanel = new PresentPanel();
					add(presentPanel);
				}
				if(categoryPanel != null) {
					remove(categoryPanel);
					categoryPanel = null;
				}
				if(stockInfoPanel != null) {
					remove(stockInfoPanel);
					stockInfoPanel = null;
				}
				if(commodityPanel != null) {
					remove(commodityPanel);
					commodityPanel = null;
				}
				repaint();
			}
		});
		this.stockBtn = new MyButton(pcfg.getButtons().element("stock"));
		this.stockBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stockInfoPanel == null) {
					stockInfoPanel = new StockInfoPanel();
					add(stockInfoPanel);
				}
				if(categoryPanel != null) {
					remove(categoryPanel);
					categoryPanel = null;
				}
				if(commodityPanel != null) {
					remove(commodityPanel);
					commodityPanel = null;
				}
				repaint();
			}
			
		});
		this.stockCheckBtn = new MyButton(pcfg.getButtons().element("stockcheck"));
		this.exceptionBtn = new MyButton(pcfg.getButtons().element("exception"));
		this.add(this.categoryManageBtn);
		this.add(this.commodityManageBtn);
		this.add(this.presentBtn);
		this.add(this.stockBtn);
		this.add(this.stockCheckBtn);
		this.add(this.exceptionBtn);

	}

	@Override
	public void showMesssage() {
		// TODO Auto-generated method stub
		
	}
	
}
