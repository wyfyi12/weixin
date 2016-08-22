<%@page import="util.wx"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="util.mpaylist"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
JSONArray payja=new JSONArray();
String userid=request.getParameter("userid");
wx wx=new wx();
int xs=1;
try{
    String upl=(String)request.getAttribute("upl");
    payja=wx.getJSONArrayfromJSONObject(upl, "upl");
     xs=0;
}catch(Exception e){
	 xs=1;
}
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>缴费列表</title>
</head>
<body>
<div class="weui_cells weui_cells_access">
<%
if(xs==0){
for(int i=0;i<payja.size();i++){
	JSONObject payjob=wx.getJSONObject(payja, i);
	String pname=payjob.getString("pname");
	String upname=java.net.URLDecoder.decode(pname,"utf-8");
	String text=payjob.getString("text");
	text=new String(text.getBytes("ISO-8859-1"),"UTF-8");
	String url="payinfo.jsp?pname="+pname+"&date="+payjob.getString("date")+"&mnum="+payjob.getString("mnum")+"&text="+text+"&time="+payjob.getString("time")+"&userlist="+payjob.getString("userlist")+"&no="+payjob.getString("no")+"&us=1"+"&userid="+userid;
%>
  <a class="weui_cell" href="<%=url%>">
    <div class="weui_cell_bd weui_cell_primary">
      <p><%=upname%></p>
    </div>
    <div class="weui_cell_ft"><%=payjob.getString("date")%></div>
  </a>
  <%
}}
%>
</div>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>