package test;

import java.io.IOException;
import java.util.HashMap;

import util.XmlImpl;
import util.wx;

public class test2 {
	public static void main(String[] args) throws Exception {
		XmlImpl dd=new XmlImpl();
        String str="D:/grade.xml";
        dd.init();
        HashMap<String, String> data=new HashMap<>();
        data.put("appid", "wx2421b1c4370ec43b");
        data.put("attach", "支付测试");
        data.put("body", "JSAPI支付测试");
        data.put("mch_id", "10000100");
         dd.createXml(str,data); 
         String rs=dd.getXmlString(str);
        System.out.println(rs);
	
	
	}
}
