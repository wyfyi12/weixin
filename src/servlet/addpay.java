package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.localdao;

public class addpay extends HttpServlet{
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
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=df.format(new Date());
			String pname=request.getParameter("pname");
			String mnum=request.getParameter("mnum");
			String date=request.getParameter("date");
			String text=request.getParameter("text");
			String userlist=request.getParameter("userlist");
			HashMap<String, String> job=new HashMap<>();
			job.put("pname", pname);
			job.put("mnum", mnum);
			job.put("date", date);
			job.put("userlist", userlist);
			job.put("text", text);
			job.put("time", time);
			localdao.getConnection();
			try {
				String rs=localdao.insertaddpay(job);
				rs = java.net.URLEncoder.encode(rs,"utf-8");
				String url="rs.jsp?rs="+rs;
			response.sendRedirect(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String rs="出现未知错误";
				rs = java.net.URLEncoder.encode(rs,"utf-8");
				String url="rs.jsp?rs="+rs;
				response.sendRedirect(url);
			}
	    }
}
