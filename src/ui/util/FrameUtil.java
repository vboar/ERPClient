/**
 * 界面工具类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;

public class FrameUtil {

	private static Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static Dimension SCREEN_SIZE = TOOLKIT.getScreenSize();

	public static void setFrameCenter(JFrame frame,int windowUp){

		Dimension windowSize = frame.getSize();
		frame.setLocation((SCREEN_SIZE.width-windowSize.width)>>1, ((SCREEN_SIZE.height-windowSize.height)>>1)-windowUp);
	}
	
	public static void setTableColumnWidth(JTable table, int containerW, int padding){
		//计算表格总体宽度 getTable().
        int allwidth = table.getIntercellSpacing().width;
        for (int j = 0; j < table.getColumnCount(); j++) {
            //计算该列中最长的宽度
            int max = 0;
            //计算表头的宽度
            int headerwidth = table.getTableHeader().
              getDefaultRenderer().getTableCellRendererComponent(
                      table, table.getColumnModel().
              getColumn(j).getIdentifier(), false, false,
              -1, j).getPreferredSize().width;
            //列宽至少应为列头宽度
            max += headerwidth+2*padding;
            //设置列宽
            table.getColumnModel().
              getColumn(j).setPreferredWidth(max);
            //给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
            allwidth += max + table.getIntercellSpacing().width;
        }
        if (allwidth > containerW) {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
	}
	
	/**
	 * 将日期转为string
	 * @param date
	 * @return
	 */
	public static String getFormattedDate(Date date){
		if(date!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			return dateFormat.format(date);
		}
		return null;
	}
	
	public static Date getDateFormStr(String str){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String autoLineFeed(String str){
		String newStr = "<html>";
		String[] list = str.split(",");
		for(int i=0; i<list.length ; ++i){
			newStr = newStr + list[i] +"<br>";
		}
		newStr += "<html>";
		return newStr;
	}
}
