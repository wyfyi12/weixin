package Interface;

import java.util.HashMap;

import util.XmlImpl;

public interface XmlInterface {
	 
    /** 
    * 建立XML文档 
    * @param fileName 文件全路径名称 
    */
    public XmlImpl createXml(String fileName,HashMap<String,String> data); 
    /** 
    * 解析XML文档 
    * @param fileName 文件全路径名称 
    */
    public String parserXml(String fileName); 
}
