package ui.conditionui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.TablePanel;
import vo.SaleDetailsVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleDetailsTablePane extends TablePanel{
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<SaleDetailsVO> list;
	
	private SaleDetailsBLService controller;

	public SaleDetailsTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getSaleDetailsBLService();
	}

	@Override
	protected void initTable() {
		// TODO Auto-generated method stub
		
	}

	
}
