package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import util.getip;
import util.wx;

public class match extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String department="";
		String mobile="";
		String email="";
		String weixinid="";
		wx wx=new wx();
		String access="";
		try {
			access = wx.getAcceptKey();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String code=req.getParameter("code");
		getip gi=new getip();
		String ip=gi.getIpAddr(req);
		String userinfo=wx.getcode(access, code,"1");
		net.sf.json.JSONObject job =wx.getJSONObjectfromString(userinfo);
		String userid=job.getString("UserId");
		String userint=wx.getUserInfo(access, userid);
		String name=wx.getJSONObjectfromString(userint).getString("name");
		try{
		 department=wx.getJSONObjectfromString(userint).getString("department");
		 department=department.replace("[", "");
		 department=department.replace("]", "");
		 department=wx.searchparty(access, department);
		 JSONArray jap=wx.getJSONArrayfromJSONObject(department, "department");
		 String party= jap.getString(0);
		 net.sf.json.JSONObject jobp=wx.getJSONObjectfromString(party);
		 department=jobp.getString("name");
		}catch(Exception e){
			 department="null";
		 }
		try{
		 mobile=wx.getJSONObjectfromString(userint).getString("mobile");}catch(Exception e){
			 mobile="null";
		 }
		try{
		 email=wx.getJSONObjectfromString(userint).getString("email");}catch(Exception e){
			 email="null";
		 }
		try{
			weixinid=wx.getJSONObjectfromString(userint).getString("weixinid");}catch(Exception e){
				weixinid="null";
			 }
		 
		String gender=wx.getJSONObjectfromString(userint).getString("gender");
		if(gender.equals("1")){
			gender="男";
		}else{
			gender="女";
		}
		String userin="用户id:"+userid+"姓名:"+name+"部门:"+department+"手机:"+mobile+"性别:"+gender+"邮箱:"+email+"微信号:"+weixinid+"ip:"+ip;
		net.sf.json.JSONObject job1=new net.sf.json.JSONObject();
		job1.element("touser", userid);
		job1.element("msgtype", "text");
		job1.element("agentid", 1);
		net.sf.json.JSONObject job2=new net.sf.json.JSONObject();
		job2.element("content", userin);
		job1.element("text", job2);
		wx.sendtext(job1, access);
	}

}
