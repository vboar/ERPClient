package ui.paymentui;

import config.TableConfig;
import ui.util.TablePanel;

<<<<<<< Updated upstream
=======
import javax.swing.table.DefaultTableModel;

>>>>>>> Stashed changes
/**
 * 创建现金费用单的表格
 * Created by Vboar on 2014/12/4.
 */
<<<<<<< Updated upstream
public class CashTable extends TablePanel{

	public CashTable(TableConfig cfg) {
		super(cfg);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initTable() {
		// TODO Auto-generated method stub
		
	}
=======
public class CashTable extends TablePanel {

    private String[] columnNames;

    private static int COLUMN_NUM = 4;

    private Object[][] data;

    private DefaultTableModel dtm;

    public CashTable(TableConfig cfg) {
        super(cfg);
    }

    @Override
    protected void initTable() {

    }
>>>>>>> Stashed changes
}
