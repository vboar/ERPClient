package ui.userui;

import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import config.TableConfig;

@SuppressWarnings("serial")
public class UserTablePane extends TablePanel{

	private String[] columnName;
	
	private Object[][] data = {  
			{"Kathy",  "Snowboarding", new Integer(5), new Boolean(false)},  
			{"John", "Rowing", new Integer(3), new Boolean(true)},  
			{"Sue",  "Knitting", new Integer(2), new Boolean(false)},  
			{"Jane",  "Speed reading", new Integer(20), new Boolean(true)},  
			{"Joe",   "Pool", new Integer(10), new Boolean(false)}  
	};  
	
	private DefaultTableModel dtm;
	
	public UserTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
	}

	@Override
	protected void initTable(){
		this.columnName = cfg.getColumnName();
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm);
	}
}
