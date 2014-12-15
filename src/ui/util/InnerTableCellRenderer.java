package ui.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class InnerTableCellRenderer extends JPanel implements TableCellRenderer {

	public InnerTableCellRenderer() {
		super();
		this.setLayout(new BorderLayout());
		setMaximumSize(new Dimension(200, 80));
		setVisible(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof JScrollPane) {
			this.setLayout(new BorderLayout());
			setMaximumSize(new Dimension(200, 80));
			add((JScrollPane) value);
			setVisible(true);
			repaint();
		}

		return this;
	}
//
//	public static void main(String[] args) {
//
//		String[] columnNames = { "名称及规格", "零数" };
//		JTable tableA;
//		Object[][] values = new Object[4][2];
//		values[0][0] = "a";
//		values[0][1] = "b";
//		values[1][0] = "c";
//		values[1][1] = "d";
//		DefaultTableModel model = new DefaultTableModel(values, columnNames);
//		tableA = new JTable(model);
//		JScrollPane jsp = new JScrollPane(tableA);
//		jsp.setPreferredSize(new Dimension(200, 50));
//
//		final JTable tableB;
//		Object[][] value = new Object[1][2];
//		value[0][0] = jsp;
//		value[0][1] = "b";
//		DefaultTableModel model3 = new DefaultTableModel(value, columnNames);
//		tableB = new JTable(model3);
//		tableB.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
//		tableB.getColumnModel().getColumn(0)
//				.setCellRenderer(new InnerTableCellRenderer());
//		tableB.getColumnModel().getColumn(0)
//				.setCellEditor(new InnerTableCellEditor(tableB));
//		tableB.setRowHeight(70);
//		JScrollPane scrollPane = new JScrollPane(tableB);
//
//		JFrame frame = new JFrame();
//		frame.setSize(400, 300);
//		frame.add(scrollPane);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(3);
//
//	}

}
