package ui.exceptionui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyTable;
import ui.util.TablePanel;
import vo.WarningVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.WarningBLService;
import config.TableConfig;

/**
 * 报警单列表面板
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class WarningListTablePane extends TablePanel {
	
	private String[] columnName;
	
	private static int COLUMN_NUM = 3;
	
	private Object[][] data;

	private DefaultTableModel dtm;
	
	private ArrayList<WarningVO> list;
	
	private WarningBLService controller;
	
	/**
	 * 构造函数
	 * @param cfg 配置对象
	 */
	public WarningListTablePane(TableConfig cfg) {
		super(cfg);
		this.controller = ControllerFactoryImpl.getInstance().getWarningController();
		this.initTable();
		this.initComponent();
	}

	/**
	 * 初始化表格
	 */
	@Override
	protected void initTable() {
		this.columnName = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnName){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setRowSorter(null);
		this.table.getColumnModel().getColumn(0).setMinWidth(160);
		this.table.getColumnModel().getColumn(1).setMinWidth(160);
		this.table.getColumnModel().getColumn(2).setMinWidth(500);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 100);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		this.list = controller.show(null, null);
		if(list!=null){
			this.data = new Object[list.size()][COLUMN_NUM];
			for(int i=0; i<list.size(); ++i){
				WarningVO temp = list.get(i);	
				this.createRow(data[i], temp);
			}
		}
	}
	
	/**
	 * 创建行
	 * @param row
	 * @param vo
	 * @return
	 */
	private Object[] createRow(Object[] row, WarningVO vo) {
		row[0]=vo.id;
		row[1]=vo.time;
		row[2]=vo.listToStr();
		return row;
	}
	
	/**
	 * 更新数据
	 */
	public void updateData() {
		this.initData();
		this.dtm.setDataVector(data, columnName);
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 100);
		this.updateUI();
	}

	/**
	 * 显示查找结果
	 * @param time1
	 * @param time2
	 */
	public void showFindTable(String time1, String time2) {
		list = controller.show(time1, time2);
		Vector<String> names = new Vector<String>(COLUMN_NUM);
		for(int i=0; i<COLUMN_NUM;++i){
			names.add(columnName[i]);
		}
		Vector<Object> table = new Vector<Object>(list.size());
		for(int i=0; i<list.size(); ++i){
			WarningVO vo = list.get(i);
			Vector<Object> row = new Vector<Object>(COLUMN_NUM);
			row.add(vo.id);
			row.add(vo.time);
			row.add(vo.listToStr());
			table.add(row);
		}
		this.dtm.setDataVector(table, names);
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 100);
		this.updateUI();
		if(list.size()==0){
			MyOptionPane.showMessageDialog(WarningListTablePane.this, "抱歉，未找到相关记录！");
		}
	}

}
