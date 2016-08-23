package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件中的信息
 * @author -_-
 *
 */
public class getdata {
	static Properties prop = new Properties();
	static String path = getdata.class.getResource("/").getPath();
	static String websiteURL = (path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") + "wx.properties").replaceFirst("/", "");
	public static void getconn() throws Exception{
    	 InputStream ip = new FileInputStream(websiteURL);
 		 prop.load(ip);
 		 ip.close();
	}
	public String getwxid(){
		String 	wxid = prop.getProperty("wxid");
		return wxid;
	}
	public String getmch_id(){
		String 	wxid = prop.getProperty("mch_id");
		return wxid;
	}
	public String getwxSecret(){
		String 	wxSecret = prop.getProperty("wxSecret");
		return wxSecret;
	}
	public String getsToken(){
		String 	wxSecret = prop.getProperty("sToken");
		return wxSecret;
	}
	public String getsEncodingAESKey(){
		String 	wxSecret = prop.getProperty("sEncodingAESKey");
		return wxSecret;
	}
}
