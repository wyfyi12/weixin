package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.kqdao;
import dao.localdao;
import util.compare;

/**
 * 生成考勤记录
 * @author -_-
 *
 */
public class kq extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static double EARTH_RADIUS = 6378.137;
	private static double rad(double d)
	{
	    return d * Math.PI / 180.0;
	}
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
	    double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lng1) - rad(lng2);
	    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	     Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	    s = s * EARTH_RADIUS;
	    s = Math.round(s * 10000) / 10000;
	    return s;
	}
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
    	String userid=request.getParameter("userid");
    	String time=request.getParameter("time");
    	String date=request.getParameter("date");
    	String dktime=date+" "+time;
    	String distance=request.getParameter("distance");
    	String DeviceId=request.getParameter("DeviceId");
    	HashMap<String, String> usermap = new HashMap<>();
		usermap.put("alias", userid);
		usermap.put("distance", distance);
		usermap.put("time", dktime);
		usermap.put("DeviceId", DeviceId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String kqdate=sdf.format(new Date());
		int rs=0;
		ArrayList<HashMap<String, String>> kq;
		try {
			kq = kqdao.querykq();
			String kqtime=kq.get(0).get("time");
			kqtime=kqdate+" "+kqtime;
			compare cp=new compare();
			String status=cp.comparetime(dktime, kqtime);
			usermap.put("status", status);
			 rs=localdao.insertuser(usermap);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	response.sendRedirect("rs.jsp?rs="+rs);
    }
}
