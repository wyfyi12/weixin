<%@page import="java.util.Date"%>
<%@page import="util.kqinfointime"%>
<%@page import="java.util.HashMap"%>
<%@page import="util.gettime"%>
<%@page import="util.wx"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="util.mpaylist"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
JSONArray payja=new JSONArray();
String date="";
String m=request.getParameter("m");
if(m.equals("1")){
	date=gettime.getmonth();
}else{
	date=request.getParameter("date");
}
wx wx=new wx();
int xs=1;
HashMap<String, HashMap<String, String>> kqinfo=new HashMap<String, HashMap<String, String>>();
try{
	 kqinfo=kqinfointime.getkqinfo(date);
     xs=0;
}catch(Exception e){
	 xs=1;
}
String lastdate=gettime.getLastDay(date, 0, -1);
String tourl="http://weixin.njnantu.com:8080/kqlist.jsp?m=0&date="+lastdate;
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>考勤记录</title>
</head>
<body>
<h2 class="weui_msg_title"><%=date %>考勤概况</h2>
<div class="weui_cells weui_cells_access">
<%
if(xs==0){
for(String alias:kqinfo.keySet()){
	String kqno=kqinfo.get(alias).get("kqno");
	String fail=kqinfo.get(alias).get("kqfail");	
	 String url="http://weixin.njnantu.com:8080/searchkq?date="+date+"&userlist="+" "+alias;
%>
  <a class="weui_cell" href="<%=url%>">
    <div class="weui_cell_bd weui_cell_primary">
      <p><%=alias %></p>
    </div>
    <div class="weui_cell_ft">共计考勤：<%=kqno %>次，迟到：<%=fail %>次</div>
  </a>
  <%
}}
%>
</div>
<a href="<%=tourl %>" class="weui_btn weui_btn_primary">上个月概况</a>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>