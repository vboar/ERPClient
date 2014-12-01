/**
 * 收款创建单据面板表格
 * @author vboar
 * @date 2014/12/01
 */

package ui.paymentui;

import businesslogicservice.accountblservice.AccountBLService;
import config.TableConfig;
import ui.util.MyTable;
import ui.util.TablePanel;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ReceiptTable extends TablePanel {

	private String[] columnNames;

	private static int COLUMN_NUM = 4;

	private Object[][] data;

	private DefaultTableModel dtm;

	private AccountBLService accountController;

	private CreateReceiptPanel panel;

	public ReceiptTable(TableConfig cfg, CreateReceiptPanel panel) {
		super(cfg);
		initTable();
		initComponent();
	}

	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
	}

}
