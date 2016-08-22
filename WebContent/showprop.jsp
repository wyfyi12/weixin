<%@page import="util.getdata"%>
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
getdata.getconn();
getdata gd=new getdata();
String wxid=gd.getwxid();
String wxSecret=gd.getwxSecret();
String mch_id=gd.getmch_id();
String sToken=gd.getsToken();
String sEncodingAESKey=gd.getsEncodingAESKey();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>显示配置信息</title>
</head>
<body>
<h2 class="weui_msg_title">配置信息</h2>
<div class="weui_cells weui_cells_access">

 <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>wxid</p>
    </div>
    <div class="weui_cell_ft">
     <%=wxid %>
    </div>
  </div>
 <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>wxSecret</p>
    </div>
    <div class="weui_cell_ft">
     <%=wxSecret %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>mch_id</p>
    </div>
    <div class="weui_cell_ft">
     <%=mch_id %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>sToken</p>
    </div>
    <div class="weui_cell_ft">
     <%=sToken %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>sEncodingAESKey</p>
    </div>
    <div class="weui_cell_ft">
     <%=sEncodingAESKey %>
    </div>
  </div>
</div>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>