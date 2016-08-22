package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 写入配置文件中的信息
 * @author -_-
 *
 */
public class setdata {
	static Properties prop = new Properties();
	static String path = setdata.class.getResource("/").getPath();
	static String websiteURL = (path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") + "wx.properties").replaceFirst("/", "");
	 public static void writeProperties(String keyname,String keyvalue) {       
	        try {
	        	System.out.println(websiteURL);
	        	InputStream fis = new FileInputStream(websiteURL);  
	            // 从输入流中读取属性列表（键和元素对）  
	            prop.load(fis);  
	            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
	            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	            OutputStream fos = new FileOutputStream(websiteURL);
	            prop.setProperty(keyname, keyvalue);
	            // 以适合使用 load 方法加载到 Properties 表中的格式，
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
	            prop.store(fos, "Update '" + keyname + "' value");
	        } catch (Exception e) {
	            System.err.println("属性文件更新错误");
	        }
	    }
}
