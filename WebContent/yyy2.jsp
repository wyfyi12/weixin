<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String userid=request.getParameter("userid");
      String distance=request.getParameter("distance");
      String time=request.getParameter("time");
      String DeviceId=request.getParameter("DeviceId");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>打卡失败</title>
</head>
<body>
<div class="weui_msg">
  <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
  <div class="weui_text_area">
    <h2 class="weui_msg_title">打卡失败</h2>
    <p class="weui_msg_desc">打卡成员：<%=userid %>;打卡时间：<%=time %>;打卡位置偏差：<%=distance %>;设备：<%=DeviceId %> 原因：打卡偏差过大 </p>
  </div>
  <div class="weui_opr_area">
    <p class="weui_btn_area">
      <a href="javascript:;" class="weui_btn weui_btn_primary">确定</a>
      <a href="javascript:;" class="weui_btn weui_btn_default">取消</a>
    </p>
  </div>
  <div class="weui_extra_area">
    <a href="">查看详情</a>
  </div>
</div>
</body>
</html>