package ui.commodityui.commodityui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTreeTable;

import vo.CategoryCommodityVO;
import vo.CategoryVO;
import vo.CommodityVO;

public class TreeTableTest {

	public static void main(String[] args) {

		ArrayList<CategoryCommodityVO> list = new ArrayList<CategoryCommodityVO>();
		list.add(new CategoryCommodityVO("0001",new CategoryVO("0001","a",10),null));
		list.add(new CategoryCommodityVO("0001-0001",null,
				new CommodityVO("0001-0001","aa","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10))));
		list.add(new CategoryCommodityVO("0001-0002",null,
				new CommodityVO("0001-0002","bb","s01",10,5,10,5,10,0,false,new CategoryVO("0001","a",10))));
		list.add(new CategoryCommodityVO("0002",new CategoryVO("0002","b",10),null));
		
		CommodityTreeTableModel treeTableModel = new CommodityTreeTableModel(list);
		JXTreeTable treeTable = new JXTreeTable( treeTableModel );
		
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(10, 10, 700, 500);	
		jsp.getViewport().add(treeTable);
		
		JFrame frame = new JFrame();
		frame.add(jsp);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
