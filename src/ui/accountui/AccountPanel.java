package ui.accountui;

import java.util.ArrayList;

import javax.swing.JPanel;

import ui.util.FuzzySearch;
import ui.util.MyButton;

@SuppressWarnings("serial")
public class AccountPanel extends JPanel implements FuzzySearch {
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton updateBtn;
	
	private MyButton findBtn;
	
	private MyButton showBtn;
	
	

	@Override
	public ArrayList<String> getFuzzyResult(String str) {
		return null;
	}

}
