package ui.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicComboPopup;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyFindComboBox extends MyComboBox {

	FindTextListener listener;

	public MyFindComboBox(Element ele, FindTextListener listener) {
		super(ele);
		this.listener = listener;
		this.setOpaque(true);
		this.setEditable(true);
		this.setAutoscrolls(true);
		this.init();
	}

	public void addItems(ArrayList<String> strs) {
		this.removeAllItems();
		for (int i = 0; i < strs.size(); ++i) {
			this.addItem(strs.get(i));
		}
		this.setPopupVisible(true);
	}

	public void init() {
		this.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch == KeyEvent.CHAR_UNDEFINED) {
					return;
				} else {
					String str = MyFindComboBox.this.getEditor().getItem()
							.toString();
					listener.showFindResult(str);
					MyFindComboBox.this.getEditor().setItem(str);
				}
			}

		});
	}

	public void firePopupMenuWillBecomeVisible() {
		// Pop *UP*


		BasicComboPopup popup = (BasicComboPopup) getUI().getAccessibleChild(
				this, 0);
		popup.setOpaque(true);
//		popup.setAutoscrolls(true);
//		int height = popup.getHeight();
//		int width = popup.getWidth();
//		System.out.println("before: " + height + " " + width);
//		System.out.println("pre: " + popup.getPreferredSize().height);
//		if (height < popup.getPreferredSize().height) {
//			height = popup.getPreferredSize().height;
//		}
//		popup.setPopupSize(popup.getPreferredSize().width, height);
//		System.out.println("after: " + popup.getHeight());
//		System.out.println("----------------------");
		super.firePopupMenuWillBecomeVisible();
	}
}
