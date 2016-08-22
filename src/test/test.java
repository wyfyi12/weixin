package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import atg.taglib.json.util.JSONArray;
import net.sf.json.JSONObject;

import util.wx;

public class test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		wx wx=new wx();
		String access=wx.getAcceptKey();
		System.out.println(access);
		access=access.replace(" ", "");
//		ArrayList<String> users=new ArrayList<String>();
//		users.add("test2");
//		users.add("test3");
		JSONObject job=new JSONObject();
		JSONObject job1=new JSONObject();
		job1.element("type", "view");
		job1.element("name", "支付测试");
		job1.element("url", "http://weixin.njnantu.com:8080/porder.jsp");
		JSONObject jobt=new JSONObject();
		jobt.element("type", "view");
		jobt.element("name", "测试");
		jobt.element("url", "http://weixin.njnantu.com:8080/testwxjs.jsp");
		JSONObject job2=new JSONObject();
		job2.element("type", "view");
		job2.element("name", "验证");
		job2.element("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx62b4232a976268cb&redirect_uri=http%3a%2f%2fweixin.njnantu.com%3a8080%2fmatch&response_type=code&scope=snsapi_base#wechat_redirect");
		JSONArray ja1=new JSONArray();
		ja1.add(job1);
		ja1.add(job2);
		ja1.add(jobt);
		JSONArray ja2=new JSONArray();
		JSONObject job3=new JSONObject();
		job3.element("type", "view");
		job3.element("name", "邮箱");
		job3.element("url", "http://exmail.qq.com/login");
		ja2.add(job3);
		
		JSONObject job4=new JSONObject();
		job4.element("sub_button", ja1);
		job4.element("name", "常用");
		ja2.add(job4);
		//job.element("useridlist",users );
		job.element("button", ja2);
		
//		job.element("email", "yingweida@txmail.xyz");
//		String rs1=wx.douser( job, access);
//		System.out.println(rs1);
//		job.element("userid", "test2");
//		job.element("name", "yisi");
//		job.element("department", "[1]");
//		job.element("email", "wuyf@njnantu.com");
//		String rs2=wx.upuser(job, access);
//		System.out.println(rs2);
//		String rs=wx.getUserInfo(access, "wuyf");
//		System.out.println(rs);
//		String rs6=wx.getparty(access, "1", "1", "0");
//		System.out.println(rs6);
//		String rs7=wx.getpartyinfo(access, "1", "1", "0");
//		System.out.println(rs7);
//		String rs4=wx.delUser(access, "test2");
//		System.out.println(rs4);
//		String rs5=wx.delusers(job, access);
//		System.out.println(rs5);
//		String rs8=wx.upparty(job, access);
//		System.out.println(rs8);
//		String rs9=wx.delparty(access, "3");
//		System.out.println(rs9);
//		String rs10=wx.searchparty(access, "1");
//		System.out.println(rs10);
//		String rs11=wx.createtag(job, access);
//		System.out.println(rs11);
//		String rs11=wx.setapp(job, access);
//		System.out.println(rs11);
		String rs11=wx.addmenu(job, access,"1");
		System.out.println(rs11);
	}

}
