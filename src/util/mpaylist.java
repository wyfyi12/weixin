package util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import dao.addpaydao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class mpaylist {
	public static JSONArray get() throws ServletException, IOException {
	
	JSONArray payja=new JSONArray();
	try {
		ArrayList<HashMap<String, String>> paylist=addpaydao.queryallpaylist();
		for(int i=0;i<paylist.size();i++){
			HashMap<String, String> payinfo=paylist.get(i);
			JSONObject payjob=new JSONObject();
			for(String key:payinfo.keySet()){
				payjob.element(key, payinfo.get(key));
			}
			payja.add(payjob);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return payja;
	}
}
