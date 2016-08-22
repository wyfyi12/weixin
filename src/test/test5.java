package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dao.localdao;
import util.getdata;
import util.gettime;
import util.setdata;

public class test5 {
	public static void main(String[] args) throws Exception {
		getdata gd=new getdata();
		gd.getconn();
		String ss=gd.getwxid();
		System.out.println(ss);
		setdata.writeProperties("test", "222");
		
	}
}
