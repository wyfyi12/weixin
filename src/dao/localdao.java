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
	private static String url = "jdbc:mysql://localhost:3306/weixin?characterEncoding=utf8";
	private static String user = "root";
	private static String password = "123456";
	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Statement st;
	static String dbuser = "";
	static String mysqlpassword = "";
	public static void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("错误故障为：" + e);
		}
	}
	public static void readprop() throws IOException {
		InputStream ip = new FileInputStream("wx.properties");
		prop.load(ip);
		url = "jdbc:mysql://localhost:3306/" + prop.getProperty("sqlname") + "?characterEncoding=utf8";
		dbuser = prop.getProperty("dbuser");
		mysqlpassword = prop.getProperty("dbpassword");
		ip.close();
	}

	public static void create() {
		try {
			String sql = "CREATE database localuser;";
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	public static void createtable() {
		try {
			String sql = "CREATE TABLE localuser (alias varchar(50) NOT NULL ,time varchar(50) NOT NULL,distance varchar(50) NOT NULL,DeviceId  varchar(50) NOT NULL,PRIMARY KEY (alias)) ENGINE=MyISAM  DEFAULT CHARSET=utf8;";
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
			logger.error("错误故障为：" + e);
		}
	}

	public static HashMap<String, Integer> searchtable() {
		HashMap<String, Integer> tablename = new HashMap<String, Integer>();
		tablename.put("localuser", 0);
		try {
			String sql = "show tables;";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String ename = rs.getString("Tables_in_jsnu");
				tablename.put(ename, 1);
			}
			st.close();
		} catch (Exception e) {
			logger.error("错误故障为：" + e);
		}
		return tablename;
	}

	public static int insertuser(HashMap<String, String> usermap) throws SQLException {
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
		return result;
	}

	public static int updatekq(HashMap<String, String> usermap) throws SQLException {
		int result = 0;
		try {
			st = (Statement) conn.createStatement();
			String sql = "update kq set jd=' " + usermap.get("jd") + "'," + "wd='" + usermap.get("wd") + "'," + "time='"
					+ usermap.get("time") + "'where no=1";
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
		return result;
	}

	public static int insertkq(HashMap<String, String> usermap) throws SQLException {
		int result = 0;
		try {
			st = (Statement) conn.createStatement();
			String sql = "insert into kq(jd,wd,time) values('" + usermap.get("jd") + "','" + usermap.get("wd") + "','"
					+ usermap.get("time") + "')";
			result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("time") + "添加失败");
			} else {
				System.out.println(usermap.get("time") + "添加成功");
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return result;
	}

	public static String insertaddpay(HashMap<String, String> usermap) throws SQLException {
		String rs = "";
		try {
			st = (Statement) conn.createStatement();
			String sql = "insert into addpay(pname,time,mnum,date,userlist,text) values('" + usermap.get("pname")
					+ "','" + usermap.get("time") + "','" + usermap.get("mnum") + "','" + usermap.get("date") + "','"
					+ usermap.get("userlist") + "','" + usermap.get("text") + "')";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("pname") + "添加失败");
				rs = usermap.get("pname") + "添加失败";
			} else {
				System.out.println(usermap.get("pname") + "添加成功");
				rs = usermap.get("pname") + "添加成功";
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return rs;
	}

	public static String insertpaylog(HashMap<String, String> usermap) throws SQLException {
		String rs = "";
		try {
			st = (Statement) conn.createStatement();
			String nos = usermap.get("no");
			int no = Integer.parseInt(nos);
			String mnums = usermap.get("mnum");
			int mnum = Integer.parseInt(mnums);
			String sql = "insert into paylog(pname,time,mnum,date,userlist,text,no) values('" + usermap.get("pname")
					+ "','" + usermap.get("time") + "','" + mnum + "','" + usermap.get("date") + "','"
					+ usermap.get("userlist") + "','" + usermap.get("text") + "','" + no + "')";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("pname") + "添加失败");
				rs = usermap.get("pname") + "添加失败";
			} else {
				System.out.println(usermap.get("pname") + "添加成功");
				rs = usermap.get("pname") + "添加成功";
			}
		} catch (SQLException e) {
			System.out.println("错误故障为：" + e);
		}
		st.close();
		return rs;
	}

	public static String insertdev(HashMap<String, String> usermap) throws SQLException {
		String rs = "";
		try {
			st = (Statement) conn.createStatement();
			String userid = usermap.get("userid");
			String devid = usermap.get("devid");
			String devname = usermap.get("devname");
			String time = usermap.get("time");
			String sql = "insert into device(userid,time,DeviceId,devname) values('" + userid + "','" + time + "','"
					+ devid + "','" + devname + "')";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("devname") + "添加失败");
				rs = usermap.get("devname") + "添加失败";
			} else {
				System.out.println(usermap.get("devname") + "添加成功");
				rs = usermap.get("devname") + "添加成功";
			}
		} catch (SQLException e) {
			System.out.println("错误故障为：" + e);
		}
		st.close();
		return rs;
	}

	public static ArrayList<HashMap<String, String>> queryById(String alias) throws SQLException {
		ArrayList<HashMap<String, String>> userlog = new ArrayList<>();
		try {
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
		return userlog;
	}

	public static ArrayList<HashMap<String, String>> querykq() throws SQLException {
		ArrayList<HashMap<String, String>> userlog = new ArrayList<>();
		try {
			String sql = "select * from	kq ";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> userinfo = new HashMap<String, String>();
				String time = rs.getString("time");
				time = rs.getString("time");
				String jd = rs.getString("jd");
				String wd = rs.getString("wd");
				userinfo.put("wd", wd);
				userinfo.put("time", time);
				userinfo.put("jd", jd);
				userlog.add(userinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return userlog;
	}

	public static ArrayList<HashMap<String, String>> querykqbydate(String date, String alias) throws SQLException {
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
		return userlog;
	}

	public static HashSet<String> queryuserkqbytime(String date) throws SQLException {
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
		return userlog;
	}

	public static HashMap<String, String> querydevById(String DeviceId) throws SQLException {
		HashMap<String, String> devinfo = new HashMap<String, String>();
		try {
			String sql = "select * from	device where DeviceId='" + DeviceId + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String time = rs.getString("time");
				String devname = rs.getString("devname");
				String userid = rs.getString("userid");
				devinfo.put("time", time);
				devinfo.put("devname", devname);
				devinfo.put("userid", userid);
				devinfo.put("DeviceId", DeviceId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		st.close();
		return devinfo;
	}

	public static HashMap<String, String> queryplByno(String no) throws SQLException {
		HashMap<String, String> userinfo = new HashMap<String, String>();
		try {
			String sql = "select * from	addpay where no='" + no + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String pname = rs.getString("pname");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String mnum = rs.getString("mnum");
				String text = rs.getString("text");
				userinfo.put("pname", pname);
				userinfo.put("date", date);
				userinfo.put("time", time);
				userinfo.put("mnum", mnum);
				userinfo.put("text", text);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return userinfo;
	}
	
	public static String insertticket(HashMap<String, String> usermap) throws SQLException {
		String rs = "";
		try {
			st = (Statement) conn.createStatement();
			String keyinfo = usermap.get("keyinfo");
			String name = usermap.get("name");
			String time = usermap.get("time");
			String sql = "insert into ticket(keyinfo,time,name) values('" + keyinfo + "','" + time + "','"
					+ name + "')";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("name") + "添加失败");
				rs = usermap.get("name") + "添加失败";
			} else {
				System.out.println(usermap.get("name") + "添加成功");
				rs = usermap.get("name") + "添加成功";
			}
		} catch (SQLException e) {
			System.out.println("错误故障为：" + e);
		}
		st.close();
		return rs;
	}
	public static HashMap<String, String> queryticketbyname(String name) throws SQLException {
		HashMap<String, String> userinfo = new HashMap<String, String>();
		try {
			String sql = "select * from	ticket where name='" + name + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String time = rs.getString("time");
				String keyinfo = rs.getString("keyinfo");
				userinfo.put("keyinfo", keyinfo);
				userinfo.put("time", time);
				userinfo.put("name", name);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return userinfo;
	}
	public static ArrayList<HashMap<String, String>> queryallpaylist() throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	addpay";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String pname = rs.getString("pname");
				String time = rs.getString("time");
				String mnum = rs.getString("mnum");
				String date = rs.getString("date");
				String userlist = rs.getString("userlist");
				String text = rs.getString("text");
				String no = rs.getString("no");
				payinfo.put("pname", pname);
				payinfo.put("time", time);
				payinfo.put("mnum", mnum);
				payinfo.put("date", date);
				payinfo.put("userlist", userlist);
				payinfo.put("text", text);
				payinfo.put("no", no);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}

	public static ArrayList<HashMap<String, String>> queryallupl(String uerid) throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	addpay";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String pname = rs.getString("pname");
				String time = rs.getString("time");
				String mnum = rs.getString("mnum");
				String date = rs.getString("date");
				String userlist = rs.getString("userlist");
				String text = rs.getString("text");
				String no = rs.getString("no");
				payinfo.put("pname", pname);
				payinfo.put("time", time);
				payinfo.put("mnum", mnum);
				payinfo.put("date", date);
				payinfo.put("userlist", userlist);
				payinfo.put("text", text);
				payinfo.put("no", no);
				if (userlist.contains(uerid)) {
					paylist.add(payinfo);
				}
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}

	public static String querypaylog(int no) throws SQLException {
		String userlist = "";
		try {
			String sql = "select * from	paylog where no='" + no + "'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String user = rs.getString("userlist");
				userlist = userlist + user;
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return userlist;
	}

	public static ArrayList<HashMap<String, String>> queryallpaylog() throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	paylog";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String pname = rs.getString("pname");
				String time = rs.getString("time");
				String mnum = rs.getString("mnum");
				String date = rs.getString("date");
				String userlist = rs.getString("userlist");
				String text = rs.getString("text");
				String no = rs.getString("no");
				String logno = rs.getString("logno");
				payinfo.put("pname", pname);
				payinfo.put("time", time);
				payinfo.put("mnum", mnum);
				payinfo.put("date", date);
				payinfo.put("userlist", userlist);
				payinfo.put("text", text);
				payinfo.put("no", no);
				payinfo.put("logno", logno);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}

	public static ArrayList<HashMap<String, String>> querypaylogbyuserid(String userid) throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	paylog where userlist='" + userid + "';";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String pname = rs.getString("pname");
				String time = rs.getString("time");
				String mnum = rs.getString("mnum");
				String date = rs.getString("date");
				String userlist = rs.getString("userlist");
				String text = rs.getString("text");
				String no = rs.getString("no");
				String logno = rs.getString("logno");
				payinfo.put("pname", pname);
				payinfo.put("time", time);
				payinfo.put("mnum", mnum);
				payinfo.put("date", date);
				payinfo.put("userlist", userlist);
				payinfo.put("text", text);
				payinfo.put("no", no);
				payinfo.put("logno", logno);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}
	
	public static ArrayList<HashMap<String, String>> querypaylogbytime(String time) throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	paylog where time like '" + time + "%'";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String pname = rs.getString("pname");
				String mnum = rs.getString("mnum");
				String date = rs.getString("date");
				String userlist = rs.getString("userlist");
				String text = rs.getString("text");
				String no = rs.getString("no");
				String logno = rs.getString("logno");
				payinfo.put("pname", pname);
				payinfo.put("time", time);
				payinfo.put("mnum", mnum);
				payinfo.put("date", date);
				payinfo.put("userlist", userlist);
				payinfo.put("text", text);
				payinfo.put("no", no);
				payinfo.put("logno", logno);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}

	public static ArrayList<HashMap<String, String>> querydevlogbyuid(String userid) throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	device where userid='" + userid + "';";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String DeviceId = rs.getString("DeviceId");
				String time = rs.getString("time");
				String devname = rs.getString("devname");
				payinfo.put("DeviceId", DeviceId);
				payinfo.put("time", time);
				payinfo.put("devname", devname);
				payinfo.put("userid", userid);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}

	public static ArrayList<HashMap<String, String>> queryalldev() throws SQLException {
		ArrayList<HashMap<String, String>> paylist = new ArrayList<>();
		try {
			String sql = "select * from	device";
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> payinfo = new HashMap<String, String>();
				String DeviceId = rs.getString("DeviceId");
				String time = rs.getString("time");
				String devname = rs.getString("devname");
				String userid = rs.getString("userid");
				payinfo.put("DeviceId", DeviceId);
				payinfo.put("time", time);
				payinfo.put("devname", devname);
				payinfo.put("userid", userid);
				paylist.add(payinfo);
			}
		} catch (SQLException e) {
			logger.error("错误故障为：" + e);
		}
		st.close();
		return paylist;
	}
}
