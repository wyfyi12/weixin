package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dao.kqdao;
import dao.ticketdao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class wx {
	public String accesstoken = "";
	public String Authorization = "";

	public String getdate() {
		int seconds = (int) (System.currentTimeMillis() / 1000);
		String times = Integer.toString(seconds);
		return times;
	}

	public ArrayList<HashMap<String, String>> getkq() throws Exception {
		ArrayList<HashMap<String, String>> kq = new ArrayList<>();
		kq = kqdao.querykq();
		return kq;
	}

	public String douser(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String getopenid(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String getpid(String data, String access) throws IOException {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsex(conn, data);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String setapp(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String yydistance(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/shakearound/getshakeinfo?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String addmenu(JSONObject usermap, String access, String agentid) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=" + access + "&agentid=" + agentid;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String createparty(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String upparty(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String sendtext(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String delparty(String access, String id) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + access + "&id=" + id;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}
	public String getticket(String access) throws Exception {
		wx wx = new wx();
		String jsapi_ticket=""; 
		String userInfo="";
		double result=0.00;
		HashMap<String, String> ticket=ticketdao.queryticketbyname("jsapi_ticket");
		String time=ticket.get("time");
		String now=gettime.getnowdatetime();
		if(time!=null){
			result=compare.getinterval(now, time);
		}else{
			result=7500000;
		}
		if(result<7200000){
			userInfo=ticket.get("keyinfo");
			JSONObject job = wx.getJSONObjectfromString(userInfo);
			jsapi_ticket = job.getString("ticket");
		}else{
		String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + access;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		JSONObject job = wx.getJSONObjectfromString(userInfo);
		jsapi_ticket = job.getString("ticket");
		HashMap<String, String> newticket =new HashMap<>();
		newticket.put("name", "jsapi_ticket");
		newticket.put("keyinfo", userInfo);
		newticket.put("time", now);
	    ticketdao.insertticket(newticket);
		}
		return jsapi_ticket ;
	}
	public String getgid(String access) throws Exception {
		String userInfo="";
		double result=0.00;
		HashMap<String, String> ticket=ticketdao.queryticketbyname("group_ticket");
		String time=ticket.get("time");
		String now=gettime.getnowdatetime();
		if(time!=null){
			result=compare.getinterval(now, time);
		}else{
			result=7500000;
		}
		if(result<7200000){
			userInfo=ticket.get("keyinfo");
		}else{
		String url = "https://qyapi.weixin.qq.com/cgi-bin/ticket/get?access_token=" + access + "&type=contact";
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		 userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		HashMap<String, String> newticket =new HashMap<>();
		newticket.put("name", "group_ticket");
		newticket.put("keyinfo", userInfo);
		newticket.put("time", now);
	    ticketdao.insertticket(newticket);
		}
		return userInfo;
	}

	public String getcode(String access, String code, String appid) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + access + "&code=" + code
				+ "&agentid=" + appid;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String searchparty(String access, String id) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + access + "&id=" + id;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String createtag(JSONObject tag, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, tag);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String upuser(JSONObject usermap, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, usermap);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	

	public String getUserInfo(String access, String userid) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + access + "&userid=" + userid;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String getparty(String access, String department_id, String fetch_child, String status) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + access + "&department_id"
				+ department_id + "&fetch_child=" + fetch_child + "&status=" + status;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String getalluser(String access) throws IOException {
		wx wx = new wx();
		String userlist = "";
		String rs = wx.getparty(access, "1", "1", "0");
		JSONArray ja = new JSONArray();
		ja = wx.getJSONArrayfromJSONObject(rs, "userlist");
		for (int i = 0; i < ja.size(); i++) {
			JSONObject job = ja.getJSONObject(i);
			String user = job.getString("userid");
			String rsu = wx.getUserInfo(access, user);
			String name = wx.getJSONObjectfromString(rsu).getString("name");
			userlist = userlist + name + " ";
		}
		return userlist;
	}

	public String getalluserid(String access) throws IOException {
		wx wx = new wx();
		String userlist = "";
		String rs = wx.getparty(access, "1", "1", "0");
		JSONArray ja = new JSONArray();
		ja = wx.getJSONArrayfromJSONObject(rs, "userlist");
		for (int i = 0; i < ja.size(); i++) {
			JSONObject job = ja.getJSONObject(i);
			String user = job.getString("userid");
			userlist = userlist + user + " ";
		}
		return userlist;
	}

	public String getpartyinfo(String access, String department_id, String fetch_child, String status)
			throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=" + access + "&department_id"
				+ department_id + "&fetch_child=" + fetch_child + "&status=" + status;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String delUser(String access, String userid) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + access + "&userid=" + userid;
		HttpURLConnection conn = getConnectiong(url);
		InputStream in = conn.getInputStream();
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String delusers(JSONObject users, String access) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=" + access;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponsej(conn, users);
		String result = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return result;
	}

	public String getUsersInfo(String accesstoken, String userid) throws IOException {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("userid", userid);
		dataMap.put("access_token", accesstoken);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponse(conn, dataMap);
		String userInfo = getStringFromStream(in);
		in.close();
		conn.disconnect();
		return userInfo;
	}

	public String getAcceptKey() throws Exception {
		getdata gd = new getdata();
		getdata.getconn();
		String wxid = gd.getwxid();
		String wxkey = gd.getwxSecret();
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("corpid", wxid);
		dataMap.put("corpsecret", wxkey);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("user-agent", "Fiddler");
		headMap.put("Charsert", "UTF-8");
		HttpURLConnection conn = getConnection(url);
		initConnection(conn, headMap);
		InputStream in = getHttpResponse(conn, dataMap);
		String accessKey = getAccessKeyFromStream(in);
		in.close();
		conn.disconnect();
		accessKey = accessKey.replaceAll(" ", "");
		return accessKey;
	}

	private String getAccessKeyFromStream(InputStream in) throws IOException {
		String returnedString = getStringFromStream(in);
		return getAccessKeyFromString(returnedString);
	}

	private String getAccessKeyFromString(String json) {
		json = json.substring(1, json.length() - 1);
		String accessKey = "";
		String[] jsonList = json.split(",");
		for (String jsonItem : jsonList) {
			String[] item = jsonItem.split(":");
			if (item[0].trim().equals("\"access_token\"")) {
				accessKey = accessKey + " " + item[1].trim().replace("\"", "");
			}
		}
		accesstoken = accessKey.trim();
		Authorization = "Bearer" + accessKey;
		return accessKey;
	}

	private String getStringFromStream(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		String temp;
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		in.close();
		return sb.toString();
	}

	private static HttpURLConnection getConnection(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		return conn;
	}

	private static HttpURLConnection getConnectiong(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		return conn;
	}

	private void initConnection(HttpURLConnection conn, Map<String, String> headMap) throws ProtocolException {
		Iterator<String> it = headMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = headMap.get(key);
			conn.setRequestProperty(key, value);
		}
	}

	public InputStream getHttpResponse(HttpURLConnection conn, Map<String, String> dataMap) throws IOException {
		String data = getData(dataMap);
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		osw.write(data);
		osw.flush();
		osw.close();
		return conn.getInputStream();
	}

	public InputStream getHttpResponsej(HttpURLConnection conn, JSONObject job) throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		osw.write(job.toString());
		osw.flush();
		osw.close();
		return conn.getInputStream();
	}

	public InputStream getHttpResponsex(HttpURLConnection conn, String job) throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		osw.write(job);
		osw.flush();
		osw.close();
		return conn.getInputStream();
	}

	public InputStream getHttpResponsegs(HttpURLConnection conn, Map<String, String> dataMap) throws IOException {
		String data = getData(dataMap);
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		osw.write(data);
		osw.flush();
		osw.close();
		return conn.getInputStream();
	}

	public InputStream getHttpResponseNew(HttpURLConnection conn, Map<String, Object> dataMap) throws IOException {
		String data = getDataNew(dataMap);
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		osw.write(data);
		osw.flush();
		osw.close();
		return conn.getInputStream();
	}

	private String getData(Map<String, String> paraMap) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = paraMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = paraMap.get(key);
			sb.append(key).append("=").append(value).append("&");
		}
		return sb.toString().substring(0, sb.length());
	}

	private String getDataNew(Map<String, Object> paraMap) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = paraMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = paraMap.get(key);
			sb.append(key).append("=").append(value).append("&");
		}
		return sb.toString().substring(0, sb.length());
	}

	public JSONObject getJSONObjectfromString(String str) {
		JSONObject myJsonObject = new JSONObject();
		myJsonObject = JSONObject.fromObject(str);
		return myJsonObject;
	}

	public JSONArray getJSONArrayfromJSONObject(String str, String str1) {
		JSONObject jsonObjSplit = new JSONObject();
		jsonObjSplit = JSONObject.fromObject(str);
		JSONArray ja = jsonObjSplit.getJSONArray(str1);
		return ja;
	}

	public JSONObject getJSONObject(JSONArray ja, int i) {
		JSONObject jo = (JSONObject) ja.get(i);
		return jo;
	}

}
