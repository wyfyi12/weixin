package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;
import util.wx;

public class addusers extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 try {
			doPost(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	 
	    /**
	     * 处理微信服务器发来的消息
	     */
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // TODO 消息的接收、处理、响应
	    	request.setCharacterEncoding("utf-8");
	    	wx wx=new wx();
			String access="";
			try {
				access = wx.getAcceptKey();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String userid=request.getParameter("userid");
			String username=request.getParameter("username");
			String userparty=request.getParameter("userparty");
			String mobile=request.getParameter("mobile");
			String gender=request.getParameter("gender");
			JSONObject job=new JSONObject();
			job.element("userid", userid);
			job.element("name", username);
			job.element("department", userparty);
			job.element("mobile", mobile);
			job.element("gender", gender);
			String rs=wx.douser(job, access);
			response.sendRedirect("/rs.jsp?rs="+rs);
	    }
}
