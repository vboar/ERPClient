package ui.presentui;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PresentLineItemVO;
import config.TableConfig;

@SuppressWarnings("serial")
public class PresentTablePane extends TablePanel {

	private String[] columnName;
	
	private static int COLUMN_NUM = 4;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	public PresentTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
	}
	
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnName);
		this.table = new MyTable(this.dtm,this.getWidth());
	}
	
	public void addRow(PresentLineItemVO vo){
		Object[] newPresent= new Object[COLUMN_NUM];
		this.createRow(newPresent,vo);
		this.dtm.addRow(newPresent);
	}

	private void createRow(Object[] row, PresentLineItemVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.number;
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
}
