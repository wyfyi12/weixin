package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.kqdao;


public class getlocation extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jd=req.getParameter("jd");
		String wd=req.getParameter("wd");
		String time=req.getParameter("time");
		HashMap<String, String> kq=new HashMap<>();
		kq.put("jd", jd);
		kq.put("wd", wd);
		kq.put("time", time);
		int rs=0;
		try {
			ArrayList<HashMap<String, String>> kqinfo=kqdao.querykq();
			if(kqinfo.get(0).size()>0){
				rs=kqdao.updatekq(kq);
			}else{
			 rs=kqdao.insertkq(kq);
			 }
			resp.sendRedirect("rs.jsp?rs="+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
