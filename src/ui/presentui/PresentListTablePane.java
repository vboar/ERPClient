package ui.presentui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.PresentVO;
import businesslogicservice.presentblservice.PresentBLService;
import config.TableConfig;

@SuppressWarnings("serial")
public class PresentListTablePane extends TablePanel{

	private String[] columnName;
	
	private static int COLUMN_NUM = 6;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<PresentVO> list;
	
	public PresentListTablePane(TableConfig cfg, PresentBLService controller) {
		super(cfg);
		this.list = controller.show(null, null);
		this.initTable();
		this.initComponent();
	}

	protected void initTable() {
		this.columnName = cfg.getColumnName();
		this.data = new Object[list.size()][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnName);
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setRowSorter(null);
	}

}
