package util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

/**
 * 生成订单号
 * @author -_-
 *
 */
public class addporder {
	/**
	 * 生成订单号
	 * @param userid
	 * @param ip
	 * @param mnum
	 * @param pname
	 * @return
	 * @throws Exception
	 */
	public String getpid(String userid,String ip,String mnum,String pname) throws Exception{
		String pid="";
		wx w=new wx();
		String access=w.getAcceptKey();
        JSONObject job=new JSONObject();
        job.element("userid", userid);
		String openid=w.getopenid(job, access);
		JSONObject job1=w.getJSONObjectfromString(openid);
		openid=job1.getString("openid");
		XmlImpl dd=new XmlImpl();
        String str="D:/grade.xml";
        dd.init();
        String order=w.getdate();
        getdata gd=new getdata();
        getdata.getconn();
        String wxid=gd.getwxid();
        String mch_id=gd.getmch_id();
        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
        parameters.put("appid", wxid);  
        parameters.put("mch_id", mch_id);
        parameters.put("device_info", "WEB"); 
        parameters.put("body", pname);  
        parameters.put("nonce_str","Wm3WZYTPz0wzccnW"); 
        parameters.put("total_fee",mnum);
        parameters.put("spbill_create_ip",ip);
        parameters.put("out_trade_no",order+"51");
        parameters.put("notify_url","http://weixin.njnantu.com:8080/getnotice");
        parameters.put("trade_type","JSAPI");
        parameters.put("openid",openid);
        String characterEncoding = "UTF-8"; 
        String mySign = MD5Util.createSign(characterEncoding,parameters);
        HashMap<String, String> data=new HashMap<>();
        data.put("appid", wxid);
        data.put("nonce_str", "Wm3WZYTPz0wzccnW");
        data.put("body", pname);
        data.put("mch_id", mch_id);
        data.put("sign", mySign);
        data.put("device_info", "WEB");
        data.put("out_trade_no", order+"51");
        data.put("total_fee", mnum);
        data.put("spbill_create_ip", ip);
        data.put("notify_url", "http://weixin.njnantu.com:8080/getnotice");
        data.put("trade_type", "JSAPI");
        data.put("openid", openid);
         dd.createXml(str,data); 
         String rs=XmlImpl.getXmlString(str);
         String rs1=w.getpid(rs, access);
		Map<String,String> a=parsexmlcd.doXMLParse(rs1);
		pid=a.get("prepay_id");
		return pid;
		
	}
}
