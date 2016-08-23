package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.addpaydao;
import util.addporder;
import util.getip;

/**
 * 
 * @author -_-
 *
 */
public class paymoney extends HttpServlet{
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
	    	addporder ap=new addporder();
	    	String mnum=request.getParameter("mnum");
	    	String no=request.getParameter("no");
	    	String userid=request.getParameter("userid");
	    	HashMap<String, String> pay=new HashMap<>();
			try {
				pay = addpaydao.queryplByno(no);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	String pname=pay.get("pname");
	    	getip gi=new getip();
	    	String ip=gi.getIpAddr(request);
	    	try {
				String pid=ap.getpid(userid,ip,mnum,pname);
				response.sendRedirect("pay.jsp?pid="+pid+"&no="+no+"&userid="+userid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
