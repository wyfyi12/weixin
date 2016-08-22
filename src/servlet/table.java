package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.dealdata;
import util.gettime;


public class table extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
		String tabletype="line";
	    String tablewidth="350";
	    String tableheight="300";
	    JSONObject datajob=new JSONObject();
	    JSONArray dataja=new JSONArray();
	    JSONObject propjob=new JSONObject();
	    propjob.element("caption", "缴费统计");
	    propjob.element("xAxisName", "月份/月");
	    propjob.element("yAxisName", "金额/元");
	    propjob.element("theme", "fint");
	    String month=gettime.getmonth();
	    try {
			HashMap<String, String> moneyinfo=dealdata.getyearmoney(month);
			System.out.println(moneyinfo);
			 for(String key:moneyinfo.keySet()){
			    	JSONObject djob=new JSONObject();
			    	djob.element("label", key);
			    	djob.element("value", moneyinfo.get(key));
			    	dataja.add(djob);
			    }
			    datajob.element("chart", propjob);
			    datajob.element("data", dataja);
			    request.setAttribute("datajob", datajob.toString());
			    request.setAttribute("tabletype", tabletype);
			    request.setAttribute("tablewidth", tablewidth);
			    request.setAttribute("tableheight", tableheight);
			    request.getRequestDispatcher("table.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
    }
}
