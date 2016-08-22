<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="util.gettime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    String userid=request.getParameter("userid");
    String pname=request.getParameter("pname");
    pname=new String(pname.getBytes("ISO-8859-1"),"UTF-8");
    String no=request.getParameter("no");
    String date=request.getParameter("date");
    String time=request.getParameter("time");
    String userlist=request.getParameter("userlist");
    String text=request.getParameter("text");
    text=new String(text.getBytes("ISO-8859-1"),"UTF-8");
    String mnum=request.getParameter("mnum");
    double money=Double.parseDouble(mnum)*0.01;
    String now=gettime.gettime();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date bdate = df.parse(date);
    Date ndate = df.parse(now);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>缴费清单</title>
</head>
<body>
<div class="weui_cells">
<div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费编号</p>
    </div>
    <div class="weui_cell_ft">
      <%=no %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费名称</p>
    </div>
    <div class="weui_cell_ft">
      <%=pname %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费金额</p>
    </div>
    <div class="weui_cell_ft">
      <%=money %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>发布时间</p>
    </div>
    <div class="weui_cell_ft">
      <%=time %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>截止日期</p>
    </div>
    <div class="weui_cell_ft">
      <%=date %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费用户</p>
    </div>
    <div class="weui_cell_ft">
      <%=userlist %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费说明</p>
    </div>
    <div class="weui_cell_ft">
      <%=text %>
    </div>
  </div>
  <%try{
  if(request.getParameter("us").equals("1")&&ndate.getTime()<bdate.getTime()){ %>
  <a class="weui_btn weui_btn_primary" href="http://weixin.njnantu.com:8080/paymoney?no=<%=no %>&userid=<%=userid %>&mnum=<%=mnum %>" >确定</a>
  <%}}catch(Exception e){
	  
  } %>
</div>

<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>