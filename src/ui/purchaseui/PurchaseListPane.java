package ui.purchaseui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.TablePanel;
import vo.PurchaseVO;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class PurchaseListPane extends TablePanel{

	private String[] columnNames;

	private static int COLUMN_NUM = 12;

	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PurchaseVO> list;

	private PurchaseBLService purchaseCtrl;
	
	public PurchaseListPane(TableConfig cfg) {
		super(cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initTable() {
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}
	
	public void showFindTable(String time1, String time2) {
		// TODO Auto-generated method stub
		
	}

	public void updateData(){
		this.initData();
		this.dtm.setDataVector(data, columnNames);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
		this.updateUI();
	}

}
