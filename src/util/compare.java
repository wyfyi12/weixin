package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 比较日期先后
 * @author -_-
 *
 */
public class compare {
	public  String comparetime(String date1,String date2){
		String status="";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
		Date dt1 = df.parse(date1);
		Date dt2 = df.parse(date2);
		if (dt1.getTime() > dt2.getTime()) {
		status="fail";
		} else if (dt1.getTime() < dt2.getTime()) {
		status="success";
		} 
		} catch (Exception exception) {
		exception.printStackTrace();
		}
		return status;
	}
	public static double getinterval(String date1,String date2){
		double interval=0.00;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
		Date dt1 = df.parse(date1);
		Date dt2 = df.parse(date2);
		interval=dt1.getTime()-dt2.getTime();
		} catch (Exception exception) {
		exception.printStackTrace();
		}
		return interval;
	}
}
