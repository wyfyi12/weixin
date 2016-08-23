package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.paylogdao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class mpayloglist extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> loglist=new ArrayList<>();
		JSONArray logja=new JSONArray();
		try {
			 loglist=paylogdao.queryallpaylog();
			 for(int i=0;i<loglist.size();i++){
				 HashMap<String, String> loginfo=loglist.get(i);
				 JSONObject logjob=new JSONObject();
				 for(String key:loginfo.keySet()){
						String value=loginfo.get(key);
						if(key.equals("pname")||key.equals("text")){
							value=java.net.URLEncoder.encode(value,"utf-8");
						}
						logjob.element(key, value);
					}
					logja.add(logjob);
			 }
			 JSONObject jobupl=new JSONObject();
				jobupl.element("paylog", logja);
				req.setAttribute("paylog", jobupl.toString());
				req.getRequestDispatcher("/paylog.jsp?userid=0").forward(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("paylog", "");
			req.getRequestDispatcher("/paylog.jsp?userid=0").forward(req,resp);
			
		}
		
	}
}
