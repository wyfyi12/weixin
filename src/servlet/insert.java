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
import util.setdata;

public class insert extends HttpServlet{
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
			String wxid=request.getParameter("wxid");
			String wxSecret=request.getParameter("wxSecret");
			String mch_id=request.getParameter("mch_id");
			String sToken=request.getParameter("sToken");
			String sEncodingAESKey=request.getParameter("sEncodingAESKey");
			if(wxid.length()>4){
				setdata.writeProperties("wxid", wxid);
			}
			if(wxSecret.length()>4){
				setdata.writeProperties("wxSecret", wxSecret);
			}
			if(mch_id.length()>4){
				setdata.writeProperties("mch_id", mch_id);
			}
			if(sToken.length()>4){
				setdata.writeProperties("sToken", sToken);
			}
			if(sEncodingAESKey.length()>4){
				setdata.writeProperties("sEncodingAESKey", sEncodingAESKey);
			}
			response.sendRedirect("showprop.jsp");
	    }
}
