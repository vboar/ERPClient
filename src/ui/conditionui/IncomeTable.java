package ui.conditionui;

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
	
	private BusinessConditionVO vo;
	
	public IncomeTable(TableConfig cfg, BusinessConditionVO vo) {
		super(cfg);
		this.vo = vo;
	}

	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data, this.columnNames) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		this.table.setRowSorter(null);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 40);
	}

	private void initData() {
		this.data = new Object[1][COLUMN_NUM];
		data[0][0]=vo.saleIncome;
		data[0][1]=vo.commodityOverIncome;
		data[0][2]=vo.costAdjustIncome;
		data[0][3]=vo.voucherIncome;
		data[0][4]=vo.discount;
		data[0][5]=vo.incomeAfterDiscount;
	}

}
