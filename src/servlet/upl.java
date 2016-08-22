package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.localdao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.wx;

/**
 * 显示用户付款列表
 * @author -_-
 *
 */
public class upl extends HttpServlet{
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
		String userid=job.getString("UserId");
		try {
		JSONArray payja=new JSONArray();
			localdao.getConnection();
			ArrayList<HashMap<String, String>> upl=localdao.queryallupl(userid);
			for(int i=0;i<upl.size();i++){
				HashMap<String, String> payinfo=upl.get(i);
				JSONObject payjob=new JSONObject();
				for(String key:payinfo.keySet()){
					String value=payinfo.get(key);
					if(key.equals("pname")||key.equals("text")){
						value=java.net.URLEncoder.encode(value,"utf-8");
					}
					payjob.element(key, value);
				}
				int no=Integer.parseInt(payjob.getString("no"));
				String userlist=localdao.querypaylog(no);
				if(userlist.contains(userid)){
				}else{
				payja.add(payjob);
				}
			}
			JSONObject jobupl=new JSONObject();
			jobupl.element("upl", payja);
			req.setAttribute("upl", jobupl.toString());
			localdao.conn.close();
			req.getRequestDispatcher("/upaylist.jsp?userid="+userid).forward(req,resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			req.setAttribute("upl", "");
			req.getRequestDispatcher("/upaylist.jsp?userid="+userid).forward(req,resp);
			e.printStackTrace();
		}
	}	
}
