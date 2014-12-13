package ui.util;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 单元格中内置JSrollPane表格编辑器
 * @author JanelDQ
 *
 */
public class InnerTableCellEditor extends DefaultCellEditor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6546334664166791132L;

	private JPanel panel;
	
	private JScrollPane jsp;

	public InnerTableCellEditor(JTable table) {
		// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
		super(new JTextField());
		// 设置点击几次激活编辑。
		this.setClickCountToStart(1);
		this.jsp = new JScrollPane();
		this.initPanel();
	}

	private void initPanel() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
	}

	/**
	 * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
			this.jsp = (JScrollPane)value;
			this.panel.setSize(jsp.getWidth(),jsp.getHeight());	
			this.panel.add(jsp);
		
		return this.panel;
	}

	/**
	 * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
	 */
	@Override
	public Object getCellEditorValue() {
		return  this.jsp;
	}

}