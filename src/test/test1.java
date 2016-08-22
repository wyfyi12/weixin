package test;

import java.io.IOException;

import atg.taglib.json.util.JSONArray;
import net.sf.json.JSONObject;
import util.wx;

public class test1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JSONObject job=new JSONObject();
		wx wx=new wx();
		String access=wx.getAcceptKey();
		System.out.println(access);
		access=access.replace(" ", "");
		job.element("touser", "wuyf");
		job.element("toparty", "1");
		job.element("msgtype", "text");
		job.element("agentid", "1");
		JSONObject job1=new JSONObject();
		job1.element("content", "http://weixin.njnantu.com:8080/pay.jsp");
		job.element("text", job1);
		String rs=wx.sendtext(job, access);
		System.out.println(rs);
	}

}
