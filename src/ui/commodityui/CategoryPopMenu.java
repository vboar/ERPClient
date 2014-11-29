package ui.commodityui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class CategoryPopMenu extends JPopupMenu {

	private JMenuItem add;

	private JMenuItem del;

	private JMenuItem upd;

	public CategoryPopMenu(final CategoryTreePane panel) {
		add = new JMenuItem("添加子分类");
		upd = new JMenuItem("修改分类信息");
		del = new JMenuItem("删除该分类");
		this.add(add);
		this.addSeparator();
		this.add(upd);
		this.addSeparator();
		this.add(del);
		this.del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.deleteCategory();
			}
		});
		this.upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showUpdateCategoryDialog();
			}
		});
		this.add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showAddCategoryDialog();
			}
		});
		this.setVisible(false);
	}

	public void enableItem(boolean isable){
		this.del.setEnabled(isable);
		this.upd.setEnabled(isable);
	}
}
