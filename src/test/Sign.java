package test;

import java.util.Properties;

import util.getdata;
import util.wx;

import java.io.FileInputStream;
import java.io.InputStream;

public class Sign {
    public static void main(String[] args) throws Exception {
    	getdata gd=new getdata();
    	getdata.getconn();
    	String wxid=gd.getwxid();
    	String wxkey=gd.getwxSecret();
    	wx wx=new wx();
    	String access=wx.getAcceptKey();
 	System.out.println(wxid+","+wxkey+","+access);
    }
}
