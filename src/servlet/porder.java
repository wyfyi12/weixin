package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.test4;
import util.getip;
import util.wx;

/**
 * 生成订单号
 * @author -_-
 *
 */
public class porder extends HttpServlet{
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
	    	test4 t4=new test4();
	    	getip gi=new getip();
	    	String ip=gi.getIpAddr(request);
	    	wx wx=new wx();
			String access="";
			try {
				access = wx.getAcceptKey();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String code=request.getParameter("code");
			String userinfo=wx.getcode(access, code,"1");
			net.sf.json.JSONObject job =wx.getJSONObjectfromString(userinfo);
			String userid=job.getString("UserId");
	    	try {
				String pid=t4.getpid(userid,ip);
				response.sendRedirect("pay.jsp?pid="+pid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
