<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="util.gettime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    String userid=request.getParameter("userid");
    String devname=request.getParameter("dname");
    devname=new String(devname.getBytes("ISO-8859-1"),"UTF-8");
    String devid=request.getParameter("did");
    String time=request.getParameter("time");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>设备信息</title>
</head>
<body>
<div class="weui_cells">
<div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>用户id</p>
    </div>
    <div class="weui_cell_ft">
      <%=userid %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>设备id</p>
    </div>
    <div class="weui_cell_ft">
    </div>
  </div>
   <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p> <%=devid %></p>
    </div>
    <div class="weui_cell_ft">
     
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>设备名称</p>
    </div>
    <div class="weui_cell_ft">
      <%=devname %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>绑定时间</p>
    </div>
    <div class="weui_cell_ft">
      <%=time %>
    </div>
  </div>
  
 
  
</div>

<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>