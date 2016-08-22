<%@page import="util.wx"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="util.mpaylist"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
JSONArray payja=new JSONArray();
wx wx=new wx();
int xs=1;
try{
    String kql=(String)request.getAttribute("kqlog");
    payja=wx.getJSONArrayfromJSONObject(kql, "kqlog");
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
<title>考勤记录</title>
</head>
<body>
<div class="weui_cells weui_cells_access">
<%
if(xs==0){
for(int i=0;i<payja.size();i++){
	JSONObject payjob=wx.getJSONObject(payja, i);
	String date=payjob.getString("time");
	String[] d=date.split(" ");
	 date=d[0];
	 String time=d[1];
	 String userid=payjob.getString("alias");
	 String dev=payjob.getString("DeviceId");
	 String status=payjob.getString("status");
	 String distance=payjob.getString("distance");
	 String pic="";
	 if(status.equals("success")){
		 pic="/images/green.jpg";
	 }else{
		 pic="/images/red.jpg";
	 }
	 String url="kqinfo.jsp?userid="+userid+"&date="+date+"&time="+time+"&DeviceId="+dev+"&distance="+distance+"&status="+status;
%>
  <a class="weui_cell" href="<%=url %>">
    <div class="weui_cell_hd">
      <img src="<%=pic %>" alt="" style="width:20px;margin-right:5px;display:block">
    </div>
    <div class="weui_cell_bd weui_cell_primary">
      <p><%=userid %></p>
    </div>
    <div class="weui_cell_ft">
      <%=date %>
    </div>
  </a>
  <%
}}
%>
</div>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>