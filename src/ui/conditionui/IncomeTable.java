package ui.conditionui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.BusinessConditionVO;
import config.TableConfig;

@SuppressWarnings("serial")
public class IncomeTable extends TablePanel{

	private String[] columnNames;

	private static int COLUMN_NUM = 6;

	private Object[][] data;

	private DefaultTableModel dtm;
	
	public IncomeTable(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
		this.setRowHeaderWidth(0);
		this.setVisible(true);
	}

	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		this.table.setRowSorter(null);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.table.updateUI();
	}

	public void updateData(BusinessConditionVO vo) {
		this.data = new Object[1][COLUMN_NUM];
		data[0][0]=vo.saleIncome;
		data[0][1]=vo.commodityOverIncome;
		data[0][2]=vo.costAdjustIncome;
		data[0][3]=vo.voucherIncome;
		data[0][4]=vo.discount;
		data[0][5]=vo.incomeAfterDiscount;
		this.dtm.setDataVector(data, columnNames);
		this.table.updateUI();
	}

}
