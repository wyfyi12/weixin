package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.localdao;
import net.sf.json.JSONObject;
import util.wx;

public class distance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		wx wx = new wx();
		String access="";
		try {
			access = wx.getAcceptKey();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String code = req.getParameter("code");
		String rsd = wx.getcode(access, code, "1");
		String DeviceId = wx.getJSONObjectfromString(rsd).getString("DeviceId");
		String ticket = (String) req.getParameter("ticket");
		JSONObject job = new JSONObject();
		job.element("ticket", ticket);
		String rs = wx.yydistance(job, access);
		JSONObject rsj = wx.getJSONObjectfromString(rs);
		JSONObject data = wx.getJSONObjectfromString(rsj.getString("data"));
		JSONObject beacon = wx.getJSONObjectfromString(data.getString("beacon_info"));
		try {
			String userid = data.getString("userid");
			String distance = beacon.getString("distance");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			HashMap<String, String> usermap = new HashMap<>();
			usermap.put("alias", userid);
			usermap.put("distance", distance);
			usermap.put("time", time);
			usermap.put("DeviceId", DeviceId);
			localdao.getConnection();
			double dis = 0;
			String disu = usermap.get("distance");
			dis = Double.parseDouble(disu);
			if (dis < 5) {
				localdao.insertuser(usermap);
				localdao.conn.close();
				resp.sendRedirect("yyy.jsp?userid=" + userid + "&distance=" + distance + "&time=" + time + "&DeviceId="
						+ DeviceId);
			} else {
				resp.sendRedirect("yyy2.jsp?userid=" + userid + "&distance=" + distance + "&time=" + time + "&DeviceId="
						+ DeviceId);
			}
		} catch (Exception e) {
			String opid = data.getString("openid");
			String distance = beacon.getString("distance");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			HashMap<String, String> usermap = new HashMap<>();
			usermap.put("alias", opid);
			usermap.put("distance", distance);
			usermap.put("time", time);
			usermap.put("DeviceId", DeviceId);
			try {
				double dis = 0;
				String diso = usermap.get("distance");
				dis = Double.parseDouble(diso);
				if (dis < 5) {
					localdao.insertuser(usermap);
					resp.sendRedirect("yyy.jsp?userid=" + opid + "&distance=" + distance + "&time=" + time
							+ "&DeviceId=" + DeviceId);
				} else {
					resp.sendRedirect("yyy2.jsp?userid=" + opid + "&distance=" + distance + "&time=" + time + "&DeviceId="
							+ DeviceId);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
