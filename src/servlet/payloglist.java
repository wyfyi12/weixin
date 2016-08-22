package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.localdao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.wx;

public class payloglist extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		wx wx=new wx();
		String access="";
		try {
			access = wx.getAcceptKey();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String code=req.getParameter("code");
		String userinfo=wx.getcode(access, code,"1");
		net.sf.json.JSONObject job =wx.getJSONObjectfromString(userinfo);
		String userid="";
		HttpSession session = req.getSession();
		try{
		userid=job.getString("UserId");
		session.setAttribute("userid", userid);
		}catch(Exception e){
			userid=(String)session.getAttribute("userid");
		}
		localdao.getConnection();
		ArrayList<HashMap<String, String>> loglist=new ArrayList<>();
		JSONArray logja=new JSONArray();
		try {
			 loglist=localdao.querypaylogbyuserid(userid);
			 for(int i=0;i<loglist.size();i++){
				 HashMap<String, String> loginfo=loglist.get(i);
				 JSONObject logjob=new JSONObject();
				 for(String key:loginfo.keySet()){
						String value=loginfo.get(key);
						if(key.equals("pname")||key.equals("text")){
							value=java.net.URLEncoder.encode(value,"utf-8");
						}
						logjob.element(key, value);
					}
					logja.add(logjob);
			 }
			 JSONObject jobupl=new JSONObject();
				jobupl.element("paylog", logja);
				req.setAttribute("paylog", jobupl.toString());
				localdao.conn.close();
				req.getRequestDispatcher("/paylog.jsp?userid="+userid).forward(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.setAttribute("paylog", "");
			req.getRequestDispatcher("/paylog.jsp?userid="+userid).forward(req,resp);
			
		}
	}
}
