package ui.exceptionui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.ExceptionVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import config.TableConfig;

/**
 * 报溢报损单列表面板
 * 
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class ExceptionListTablePane extends TablePanel {

	private String[] columnName;

	private static int COLUMN_NUM = 3;

	private Object[][] data;

	private DefaultTableModel dtm;

	private ArrayList<ExceptionVO> list;

	private ExceptionBLService controller;

	/**
	 * 构造函数
	 * 
	 * @param cfg
	 *            面板配置对象
	 * @param isloss
	 *            true为报损单，false为报溢单
	 */
	public ExceptionListTablePane(TableConfig cfg, boolean isloss) {
		super(cfg);
		if (isloss) {
			this.controller = ControllerFactoryImpl.getInstance()
					.getLossController();
		} else {
			this.controller = ControllerFactoryImpl.getInstance()
					.getOverflowController();
		}
		// 初始化表格
		this.initTable();
		// 初始化其他组件
		this.initComponent();
	}

	/**
	 * 初始化表格
	 */
	@Override
	protected void initTable() {
		this.columnName = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data, this.columnName) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.table = new MyTable(this.dtm, this.getWidth());
		// 设置不可排序
		this.table.setRowSorter(null);
		this.updateWidth();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		this.list = controller.show(null, null);
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			ExceptionVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
	}

	/**
	 * 添加一行信息
	 * 
	 * @param row
	 * @param vo
	 * @return
	 */
	private Object[] createRow(Object[] row, ExceptionVO vo) {
		row[0] = vo.id;
		row[1] = vo.time;
		row[2] = vo.list;
		return row;
	}

	/**
	 * 更新数据
	 */
	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnName);
		this.updateWidth();
	}

	/**
	 * 显示查找到的信息
	 * 
	 * @param time1
	 * @param time2
	 */
	public void showFindTable(String time1, String time2) {
		list = controller.show(time1, time2);
		this.showFindData(list);
	}
	
	public void showFindData(ArrayList<ExceptionVO> list){
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			ExceptionVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnName);
		this.updateWidth();
		if (list.size() == 0) {
			MyOptionPane.showMessageDialog(ExceptionListTablePane.this,
					"抱歉，未找到相关记录！");
		}
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 100);
        this.table.getColumnModel().getColumn(0).setMinWidth(160);
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(460);
        this.updateUI();
	}

}
