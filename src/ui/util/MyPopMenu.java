package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class MyPopMenu extends JPopupMenu {

	private JMenuItem add;

	private JMenuItem del;

	private JMenuItem upd;

	public MyPopMenu(final BasicOperation panel) {
		add = new JMenuItem("添加");
		upd = new JMenuItem("修改");
		del = new JMenuItem("删除");
		this.add(add);
		this.addSeparator();
		this.add(upd);
		this.addSeparator();
		this.add(del);
		this.del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.delete();
			}
		});
		this.upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showUpdDialog();
			}
		});
		this.add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showAddDialog();
			}
		});
		this.setVisible(false);
	}

	public void enableItem(boolean isable){
		this.del.setEnabled(isable);
		this.upd.setEnabled(isable);
	}
}
