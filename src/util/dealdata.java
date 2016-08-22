package util;

import java.util.ArrayList;
import java.util.HashMap;

import dao.localdao;

public class dealdata {
	public static String getmonthmoney(String time) throws Exception{
		String money="";
		double summoney=0;
		localdao.getConnection();
		ArrayList<HashMap<String, String>> paylog=localdao.querypaylogbytime(time);
		for(int i=0;i<paylog.size();i++){
			HashMap<String, String> payinfo=paylog.get(i);
			double paymoney=Double.parseDouble(payinfo.get("mnum"));
			summoney=summoney+paymoney;
		}
		money=summoney+"";
		localdao.conn.close();
		return money;
	} 
	
	public static HashMap<String, String> getyearmoney(String time) throws Exception{
		HashMap<String, String> yearmoney=new HashMap<>();
		for(int i=0;i<6;i++){
			double money=Double.parseDouble(dealdata.getmonthmoney(time));
			String timemoney=money*0.01+"";
			String[] month=time.split("-");
			yearmoney.put(month[1], timemoney);
			time=gettime.getLastDay(time, 0, -1);
		}
		return yearmoney;
	}
}
