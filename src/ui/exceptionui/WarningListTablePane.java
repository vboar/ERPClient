package ui.exceptionui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
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

	private static int COLUMN_NUM = 3;
	
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
		this.columnNames = cfg.getColumnName();
		this.initData();
		this.dtm = new DefaultTableModel(this.data,this.columnNames){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		this.table = new MyTable(this.dtm,this.getWidth());
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setRowSorter(null);
		this.updateWidth();
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
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
	}

	/**
	 * 显示查找结果
	 * @param time1
	 * @param time2
	 */
	public void showFindTable(String time1, String time2) {
		list = controller.show(time1, time2);
		this.showFindData(list);
	}
	
	public void showFindData(ArrayList<WarningVO> list){		
		this.data = new Object[list.size()][COLUMN_NUM];
		for (int i = 0; i < list.size(); ++i) {
			WarningVO temp = list.get(i);
			this.createRow(data[i], temp);
		}
		this.dtm.setDataVector(data, columnNames);
		this.updateWidth();
		if (list.size() == 0) {
			MyOptionPane.showMessageDialog(WarningListTablePane.this,
					"抱歉，未找到相关记录！");
		}
	}
	
	public void updateWidth(){
		FrameUtil.setTableColumnWidth(this.table, this.getWidth(), 100);
        this.table.getColumnModel().getColumn(0).setMinWidth(160);
        this.table.getColumnModel().getColumn(1).setMinWidth(160);
        this.table.getColumnModel().getColumn(2).setMinWidth(460);
		this.table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer(){
			@Override
			 public Component getTableCellRendererComponent(JTable table, Object value,
                     boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setToolTipText(FrameUtil.autoLineFeed(value.toString()));
				return this;
			}
		});
        this.updateUI();
	}

}
