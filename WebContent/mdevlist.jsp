<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.localdao"%>
<%@page import="util.wx"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="util.mpaylist"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
localdao.getConnection();
int xs=1;
ArrayList<HashMap<String, String>> dl=new ArrayList<HashMap<String, String>>();
try{
 dl=localdao.queryalldev();
 System.out.print(dl);
 xs=0;
}catch(Exception e){
	e.printStackTrace();
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
<title>设备列表</title>
</head>
<body>
<div class="weui_cells weui_cells_access">
<%
if(xs==0){
for(int i=0;i<dl.size();i++){
	HashMap<String, String> devinfo=dl.get(i);
	String userid=devinfo.get("userid");
	String dname=devinfo.get("devname");
	dname=java.net.URLEncoder.encode(dname,"utf-8");
	String did=devinfo.get("DeviceId");
	String url="/devinfo.jsp?dname="+dname+"&time="+devinfo.get("time")+"&did="+did+"&userid="+userid;
%>
  <a class="weui_cell" href="<%=url%>">
    <div class="weui_cell_bd weui_cell_primary">
      <p><%=devinfo.get("devname")%></p>
    </div>
    <div class="weui_cell_ft"><%=devinfo.get("time")%></div>
  </a>
  <%
}}
%>
</div>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>