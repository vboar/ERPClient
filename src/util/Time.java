package util;

import java.util.Calendar;

public class Time {
public static String getCurrentTime(){
	Calendar c=Calendar.getInstance();
	String year=String.valueOf(c.get(Calendar.YEAR));
	String month=String.valueOf(c.get(Calendar.MONTH));
	String date=String.valueOf(c.get(Calendar.DATE));
	String hour=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
	String minute=String.valueOf(c.get(Calendar.MINUTE));
	String second=String.valueOf(c.get(Calendar.SECOND));
	String time=year+"/"+month+"/"+date+"/"+hour+"/"+minute+"/"+second;
	
	return time;
}

public static String getCurrentYear(){
	Calendar c=Calendar.getInstance();
	String year=String.valueOf(c.get(Calendar.YEAR));
	return year;
}

public static String getCurrentMonth(){
	Calendar c=Calendar.getInstance();
	String month=String.valueOf(c.get(Calendar.MONTH));
	return month;
}

public static String getCurrentDay(){
	Calendar c=Calendar.getInstance();
	String date=String.valueOf(c.get(Calendar.DATE));
	return date;
}
}
