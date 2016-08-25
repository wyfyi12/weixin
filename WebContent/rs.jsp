<%@page import="util.getdata"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="net.sf.json.JSONObject"%>
    <%@ page import="util.wx"%>
    <%@ page import="util.getsig"%>
    <%
    String rs=request.getParameter("rs");
     rs=new String(rs.getBytes("ISO-8859-1"),"UTF-8");
    wx wx=new wx();
    String access=wx.getAcceptKey();
    String ticket=wx.getticket(access);
    getdata.getconn();
    getdata gd=new getdata();
    String domain=getdata.getdomain();
    String wxid=gd.getwxid();
    String url="http://"+domain+"/rs.jsp?rs="+java.net.URLEncoder.encode(rs,"utf-8");
    String timestamp=wx.getdate();
    getsig gs=new getsig();  
    String signature=gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
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
<script type="text/javascript">
var timestamp = <%=timestamp%>;
wx.config({
    debug: false, 
    appId: '<%=wxid%>', 
    timestamp: timestamp,
    nonceStr: 'Wm3WZYTPz0wzccnW', 
    signature: '<%=signature%>',
    jsApiList: ['getLocation','openLocation','showAllNonBaseMenuItem','stopRecord','playVoice','translateVoice','onMenuShareAppMessage','scanQRCode','chooseWXPay','openEnterpriseContact','openEnterpriseChat','getNetworkType'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.error(function(res){
	alert(res);
	// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});
wx.ready(function(){
});
</script>
<title>操作结果</title>
</head>
<body>
<div class="weui_msg">
  <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
  <div class="weui_text_area">
    <h2 class="weui_msg_title">操作结果</h2>
    <p class="weui_msg_desc"><%=rs%></p>
  </div>
  <div class="weui_opr_area">
    <p class="weui_btn_area">
      <button onclick="wx.closeWindow();"  class="weui_btn weui_btn_primary">确定</button>
      <button onclick="location.href='javascript:history.go(-1);'" class="weui_btn weui_btn_default">取消</button>
    </p>
  </div>
 
</div>
</body>
<script src="jquery-weui-build/dist/lib/jquery-2.1.4.js"></script>
<script src="jquery-weui-build/dist/js/jquery-weui.js"></script> 
</html>