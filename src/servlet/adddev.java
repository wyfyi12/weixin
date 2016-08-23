package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.devicedao;

public class adddev extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
		request.setCharacterEncoding("utf-8");
    	String userid=request.getParameter("userid");
    	String devid=request.getParameter("devid");
    	String devname=request.getParameter("devname");
    	HashMap<String, String> dev=new HashMap<>();
		try {
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			dev.put("time", time);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dev.put("userid", userid);
		dev.put("devid",devid);
		dev.put("devname",devname);
    	try {
			String rs=devicedao.insertdev(dev);
			rs = java.net.URLEncoder.encode(rs,"utf-8");
			response.sendRedirect("rs.jsp?rs="+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
