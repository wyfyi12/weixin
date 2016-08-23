package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

public class kqlog extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
		 wx wx=new wx();
			String access="";
			try {
				access = wx.getAcceptKey();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String code=request.getParameter("code");
			String userinfo=wx.getcode(access, code,"1");
			net.sf.json.JSONObject job =wx.getJSONObjectfromString(userinfo);
			String userid="";
			HttpSession session = request.getSession();
			try{
			userid=job.getString("UserId");
			session.setAttribute("userid", userid);
			}catch(Exception e){
				userid=(String)session.getAttribute("userid");
			}
			JSONArray ja=new JSONArray();
			try {
				ArrayList<HashMap<String, String>> userlog=localdao.queryById(userid);
				for(int i=0;i<userlog.size();i++){
					 HashMap<String, String> loginfo=userlog.get(i);
					 JSONObject logjob=new JSONObject();
					 for(String key:loginfo.keySet()){
							String value=loginfo.get(key);
							logjob.element(key, value);
						}
						ja.add(logjob);
				 }
				 JSONObject jobupl=new JSONObject();
					jobupl.element("kqlog", ja);
					request.setAttribute("kqlog", jobupl.toString());
					localdao.conn.close();
					request.getRequestDispatcher("/kqlog.jsp?userid="+userid).forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
