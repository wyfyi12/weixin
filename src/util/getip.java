package util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取客户端ip
 * @author -_-
 *
 */
public class getip {
	 public String getIpAddr(HttpServletRequest request) {
		    String ip = request.getHeader("x-forwarded-for");
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("Proxy-Client-IP");
		    }
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getRemoteAddr();
		    }
		    return ip;
		 }
}
