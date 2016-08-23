package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import org.apache.log4j.Logger;


//用于本地数据库操作
public class localdao {
	private static Properties prop = new Properties();
	private static Logger logger = Logger.getLogger(localdao.class);
	private static String url = "";
	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Statement st;
	static String dbuser = "";
	static String mysqlpassword = "";
	public static void getConnection() {
		try {
			localdao.readprop();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbuser, mysqlpassword);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void readprop() throws IOException {
		 String path = localdao.class.getResource("/").getPath();
		 String websiteURL = (path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") + "wx.properties").replaceFirst("/", "");
		InputStream ip = new FileInputStream(websiteURL);
		prop.load(ip);
		url = "jdbc:mysql://localhost:3306/" + prop.getProperty("sqlname") + "?characterEncoding=utf8";
		dbuser = prop.getProperty("dbuser");
		mysqlpassword = prop.getProperty("dbpassword");
		ip.close();
	}

	public static int insertuser(HashMap<String, String> usermap) throws Exception {
		localdao.getConnection();
		int result = 0;
		try {
			st = (Statement) conn.createStatement();
			String sql = "insert into localuser(alias,time,distance,DeviceId,status) values('" + usermap.get("alias")
					+ "','" + usermap.get("time") + "','" + usermap.get("distance") + "','" + usermap.get("DeviceId")
					+ "','" + usermap.get("status") + "')";
			result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("Alias") + "添加失败");
			} else {
				System.out.println(usermap.get("Alias") + "添加成功");
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		localdao.conn.close();
		return result;
	}

	public static ArrayList<HashMap<String, String>> queryById(String alias) throws SQLException {
		
		ArrayList<HashMap<String, String>> userlog = new ArrayList<>();
		try {
			localdao.getConnection();
			String sql = "select * from	localuser where alias='" + alias + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> userinfo = new HashMap<String, String>();
				String time = rs.getString("time");
				time = rs.getString("time");
				String distance = rs.getString("distance");
				String DeviceId = rs.getString("DeviceId");
				String status = rs.getString("status");
				userinfo.put("alias", alias);
				userinfo.put("time", time);
				userinfo.put("distance", distance);
				userinfo.put("DeviceId", DeviceId);
				userinfo.put("status", status);
				userlog.add(userinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		localdao.conn.close();
		return userlog;
	}

	public static ArrayList<HashMap<String, String>> querykqbydate(String date, String alias) throws SQLException {
		localdao.getConnection();
		ArrayList<HashMap<String, String>> userlog = new ArrayList<>();
		try {
			String sql = "select * from	localuser where time like '" + date + "%' and alias ='" + alias + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> userinfo = new HashMap<String, String>();
				String time = rs.getString("time");
				String DeviceId = rs.getString("DeviceId");
				String status = rs.getString("status");
				String distance = rs.getString("distance");
				userinfo.put("alias", alias);
				userinfo.put("time", time);
				userinfo.put("DeviceId", DeviceId);
				userinfo.put("status", status);
				userinfo.put("distance", distance);
				userlog.add(userinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		localdao.conn.close();
		return userlog;
	}

	public static HashSet<String> queryuserkqbytime(String date) throws SQLException {
		localdao.getConnection();
		HashSet<String> userlog = new HashSet<>();
		try {
			String sql = "select alias from	localuser where time like '" + date + "%'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String alias = rs.getString("alias");
				userlog.add(alias);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		localdao.conn.close();
		return userlog;
	}

	
}
