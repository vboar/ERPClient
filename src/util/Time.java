package util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
public static String getCurrentTime(){
	SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
	String time=df.format(new Date());
	return time;
}

public static String getCurrentYear(){
	SimpleDateFormat df=new SimpleDateFormat("yyyy");
	String year=df.format(new Date());
	return year;
}

public static String getCurrentMonth(){
	SimpleDateFormat df=new SimpleDateFormat("MM");
	String month=df.format(new Date());
	return month;
}

public static String getCurrentDay(){
	SimpleDateFormat df=new SimpleDateFormat("dd");
	String date=df.format(new Date());
	return date;
}
}
