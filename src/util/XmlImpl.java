package util;

import Interface.XmlInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlImpl implements XmlInterface{
    private Document document;
 
    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public XmlImpl createXml(String fileName,HashMap<String,String> data ) {
        Element root = this.document.createElement("xml"); 
        this.document.appendChild(root); 
       // Element employee = this.document.createElement("employee");
        for(String key:data.keySet()){
        Element name = this.document.createElement(key); 
        name.appendChild(this.document.createTextNode(data.get(key))); 
        root.appendChild(name); 
        }
        
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("生成XML文件成功!");
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
 
    public String parserXml(String fileName) {
    	String rs="";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                    	rs=rs+employeeMeta.item(k).getNodeName()
                                + ":" + employeeMeta.item(k).getTextContent();
                    }
                }
            }
            System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public static String getXmlString(String fileName) throws Exception{  
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
    	Document document = db.parse(fileName);
        TransformerFactory tf = TransformerFactory.newInstance();  
        try {  
            Transformer t = tf.newTransformer();  
            t.setOutputProperty(OutputKeys.ENCODING,"UTF-8");//解决中文问题，试过用GBK不行  
            t.setOutputProperty(OutputKeys.METHOD, "html");    
            t.setOutputProperty(OutputKeys.VERSION, "4.0");    
            t.setOutputProperty(OutputKeys.INDENT, "no");    
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            t.transform(new DOMSource(document), new StreamResult(bos));  
            return bos.toString();  
        } catch (TransformerConfigurationException e) {  
            e.printStackTrace();  
        } catch (TransformerException e) {  
            e.printStackTrace();  
        }  
        return "";  
    } 
   

}