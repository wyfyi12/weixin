package test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;

import net.sf.json.JSONObject;
import util.MD5Util;
import util.XmlImpl;
import util.parsexmlcd;
import util.wx;

public class test4 {
	public String getpid(String userid,String ip) throws Exception{
		String pid="";
		wx w=new wx();
		String access=w.getAcceptKey();
        access=access.replace(" ", "");
        JSONObject job=new JSONObject();
        job.element("userid", userid);
		String openid=w.getopenid(job, access);
		JSONObject job1=w.getJSONObjectfromString(openid);
		openid=job1.getString("openid");
		XmlImpl dd=new XmlImpl();
        String str="D:/grade.xml";
        dd.init();
        String order=w.getdate();
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
        parameters.put("appid", "wx62b4232a976268cb");  
        parameters.put("mch_id", "1358691002");
        parameters.put("device_info", "WEB"); 
        parameters.put("body", "JSAPI支付测试");  
        parameters.put("nonce_str","Wm3WZYTPz0wzccnW"); 
        parameters.put("total_fee","1");
        parameters.put("spbill_create_ip",ip);
        parameters.put("out_trade_no",order+"51");
        parameters.put("notify_url","http://weixin.njnantu.com:8080/getnotice");
        parameters.put("trade_type","JSAPI");
        parameters.put("openid",openid);
        String characterEncoding = "UTF-8"; 
        String mySign = MD5Util.createSign(characterEncoding,parameters);
        HashMap<String, String> data=new HashMap<>();
        data.put("appid", "wx62b4232a976268cb");
        data.put("nonce_str", "Wm3WZYTPz0wzccnW");
        data.put("body", "JSAPI支付测试");
        data.put("mch_id", "1358691002");
        data.put("sign", mySign);
        data.put("device_info", "WEB");
        data.put("out_trade_no", order+"51");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", ip);
        data.put("notify_url", "http://weixin.njnantu.com:8080/getnotice");
        data.put("trade_type", "JSAPI");
        data.put("openid", openid);
         dd.createXml(str,data); 
         String rs=dd.getXmlString(str);
         System.out.println(rs);
         String rs1=w.getpid(rs, access);
         System.out.println(rs1);
		Map<String,String> a=parsexmlcd.doXMLParse(rs1);
		pid=a.get("prepay_id");
		return pid;
		
	}
	
}
