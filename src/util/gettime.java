package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class gettime {
	public static String getday(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(new Date());
		return time;
	}
	public   static String getmonth(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String time=df.format(new Date());
		return time;
	}
	public   static String getnowtime(){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String time=df.format(new Date());
		return time;
	}
	public   static String getnowdatetime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(new Date());
		return time;
	}
	public static String getLastDay(String dateStr,int addYear, int addMonth) throws Exception {  
        try {  
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");  
        java.util.Date sourceDate = sdf.parse(dateStr);  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sourceDate);  
        cal.add(Calendar.YEAR,addYear);  
        cal.add(Calendar.MONTH, addMonth);  
        java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM");  
        String dateTmp = returnSdf.format(cal.getTime());  
        return dateTmp;  
        } catch (Exception e) {  
        e.printStackTrace();  
        throw new Exception(e.getMessage());  
        }  
        } 
}
