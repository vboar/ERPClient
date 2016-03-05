/**
 *时间处理通用类
 * @author oneoneO
 * @data 2014/11/20
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	/**
	 * 获得当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=df.format(new Date());
		return time;
	}

	/**
	 * 获得当前年份
	 * @return
	 */
	public static String getCurrentYear(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy");
		String year=df.format(new Date());
		return year;
	}

	/**
	 * 获得当前月份
	 * @return
	 */
	public static String getCurrentMonth(){
		SimpleDateFormat df=new SimpleDateFormat("MM");
		String month=df.format(new Date());
		return month;
	}

	/**
	 * 获得当前日
	 * @return
	 */
	public static String getCurrentDay(){
		SimpleDateFormat df=new SimpleDateFormat("dd");
		String date=df.format(new Date());
		return date;
	}

	/**
	 * 改良起始日期
	 * @param time1
	 * @return
	 */
	//若传入的time1为null或者""，返回“1970/1/1 0:0:0”
	public static String jdugeTime1(String time1){

		if(time1!=null&&!time1.equals("")){
			return time1;
		}else{
			return "1970/01/01 00:00:00";
		}

	}

	/**
	 * 改良截止日期
	 * @param time2
	 * @return
	 */
	//若传入的time2为null或者""，返回现在的时间
	public static String jdugeTime2(String time2){

		if(time2!=null&&!time2.equals("")){
			return time2+" 23:59:59";
		}else{
			return getCurrentTime();
		}

	}
}
