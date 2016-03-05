package ui.exceptionui;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import ui.util.MyTable;
import ui.util.TablePanel;
import vo.ExceptionLineItemVO;
import config.TableConfig;

/**
 * 报溢报损单内商品信息列表面板
 * @author JanelDQ
 *
 */
@SuppressWarnings("serial")
public class ExceptionTablePane extends TablePanel{
	
	private static int COLUMN_NUM = 6;

	/**
	 * 构造函数
	 * @param cfg
	 */
	public ExceptionTablePane(TableConfig cfg) {
		super(cfg);
		this.initTable();
		this.initComponent();
	}

	/**
	 * 初始化表格
	 */
	protected void initTable(){
		this.columnNames = cfg.getColumnName();
		this.data = new Object[0][COLUMN_NUM];
		this.dtm = new DefaultTableModel(this.data,this.columnNames);
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setRowSorter(null);
	}
	
	/**
	 * 添加行
	 * @param vo
	 */
	public void addRow(ExceptionLineItemVO vo){
		Object[] newrow= new Object[COLUMN_NUM];
		this.createRow(newrow,vo);
		this.dtm.addRow(newrow);
	}

	/**
	 * 创造行
	 * @param row
	 * @param vo
	 */
	private void createRow(Object[] row, ExceptionLineItemVO vo){
		row[0]=vo.id;
		row[1]=vo.name;
		row[2]=vo.model;
		row[3]=vo.systemNumber;
		row[4]=vo.actualNumber;
		row[5]=vo.actualNumber-vo.systemNumber;
	}
	
}
