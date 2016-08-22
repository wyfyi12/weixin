<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="servlet.kq"%>
<%@page import="util.gettime"%>
    <%@page import="net.sf.json.JSONObject"%>
    <%@ page import="util.wx"%>
    <%@ page import="util.getsig"%>
    <%
    wx wx=new wx();
	String access=wx.getAcceptKey();
	String code=request.getParameter("code");
	String userinfo=wx.getcode(access, code,"1");
	net.sf.json.JSONObject job =wx.getJSONObjectfromString(userinfo);
	String userid=job.getString("UserId");
	String DeviceId = wx.getJSONObjectfromString(userinfo).getString("DeviceId");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<script type="text/javascript" src="jweixin-1.1.0.js"></script>
<title>绑定新设备</title>
</head>
<body>
<form action="adddev" method="post">
<div class="weui_cells weui_cells_form">
 <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>用户账号</p>
    </div>
    <div class=""><%=userid%></div>
  </div>
 <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>设备id</p>
    </div>
    <div class=""></div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p><%=DeviceId%></p>
    </div>
    <div class=""></div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">设备名称</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" type="text" placeholder="请输入设备名称" name="devname">
    </div>
  </div>
 <input  type="hidden"  name="userid" value="<%=userid %>">
   <input  type="hidden"  name="devid" value="<%=DeviceId %>">
   <button type="submit" class="weui_btn weui_btn_primary">确定</button>
</div>
</form>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>