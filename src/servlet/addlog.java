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

import dao.addpaydao;
import dao.paylogdao;

public class addlog extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
    	String no=request.getParameter("no");
    	String userid=request.getParameter("userid");
    	HashMap<String, String> pay=new HashMap<>();
		try {
			pay = addpaydao.queryplByno(no);
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			pay.put("time", time);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	pay.put("userlist", userid);
    	pay.put("no",no);
    	try {
			String rs=paylogdao.insertpaylog(pay);
			response.sendRedirect("rs.jsp?rs="+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
