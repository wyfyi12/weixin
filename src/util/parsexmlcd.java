package util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class parsexmlcd {
	 @SuppressWarnings("unchecked")
	    public  static Object[] parseXmlToList2(String xml){   
	    	List<Map> argMapList = new ArrayList<Map>();
	    	Map retMap = new HashMap();
	    	try {
	    	StringReader read = new StringReader(xml);
	    	// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
	    	InputSource source = new InputSource(read);
	    	
	    	// 创建一个新的SAXBuilder
	    	SAXBuilder sb = new SAXBuilder();
	    	// 通过输入源构造一个Document
	    	Document doc = (Document) sb.build(source);
	    	Element root = doc.getRootElement();// 指向根节点
	    	List<Element> es = root.getChildren();
	    	if (es != null && es.size() != 0) {
	    	for (Element element : es) {
	    	retMap.put(element.getName(), element.getText());
	    	}
	    	}
	    	argMapList.add(retMap);
	    	} catch (Exception e) {
	    	e.printStackTrace();
	    	}
	    	return new Object[] { argMapList};
	    	}
	 public static Map<String,String> doXMLParse(String strxml) throws Exception {
		          if(null == strxml || "".equals(strxml)) {
		             return null;
		         }
		         Map<String,String> m = new HashMap<String,String>();
		         InputStream in = String2Inputstream(strxml);
		         SAXBuilder builder = new SAXBuilder();
		         Document doc = builder.build(in);
		         Element root = doc.getRootElement();
		        List list = root.getChildren();
		       Iterator it = list.iterator();
		        while(it.hasNext()) {
		            Element e = (Element) it.next();
		            String k = e.getName();
		            String v = "";
		             List children = e.getChildren();
		            if(children.isEmpty()) {
		               v = e.getTextNormalize();
		             } else {
		                 v = getChildrenText(children);
		             }
		             m.put(k, v);
		         }
		         
		         //关闭流
		         in.close();
		         return m;
		     }
		
	 public static InputStream String2Inputstream(String str) {
		          return new ByteArrayInputStream(str.getBytes());
		     }
		  /**
		       * 获取子结点的xml
		       * @param children
		       * @return String
		      */
		      public static String getChildrenText(List children) {
		          StringBuffer sb = new StringBuffer();
		          if(!children.isEmpty()) {
		              Iterator it = children.iterator();
		             while(it.hasNext()) {
		                 Element e = (Element) it.next();
		                 String name = e.getName();
		                 String value = e.getTextNormalize();
		                 List list = e.getChildren();
		                 sb.append("<" + name + ">");
		                 if(!list.isEmpty()) {
		                     sb.append(getChildrenText(list));
		                 }
		                 sb.append(value);
		                 sb.append("</" + name + ">");
		             }
		         }
		         
		         return sb.toString();
		     }
}
