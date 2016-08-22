package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.localdao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用于搜索指定日期用户的考勤记录
 * @author -_-
 *
 */
public class searchkq extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
    	String date=request.getParameter("date");
    	String userid=request.getParameter("userlist");
    	String[] user=userid.split(" ");
    	userid=user[1];
    	localdao.getConnection();
    	ArrayList<HashMap<String, String>> pay=new ArrayList<>();
		try {
			pay = localdao.querykqbydate(date, userid);
			JSONArray logja=new JSONArray();
				 for(int i=0;i<pay.size();i++){
					 HashMap<String, String> loginfo=pay.get(i);
					 JSONObject logjob=new JSONObject();
					 for(String key:loginfo.keySet()){
							String value=loginfo.get(key);
							logjob.element(key, value);
						}
						logja.add(logjob);
				 }
				 JSONObject jobupl=new JSONObject();
					jobupl.element("kqlog", logja);
					request.setAttribute("kqlog", jobupl.toString());
					localdao.conn.close();
					request.getRequestDispatcher("/maukqlog.jsp").forward(request,response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
}
