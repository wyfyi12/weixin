<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="net.sf.json.JSONObject"%>
     <%@ page import="util.wx"%>
    <%@ page import="util.getsig"%>
    <% 
   wx wx=new wx();
   String access=wx.getAcceptKey();
   String ticket=wx.getticket(access);
    String userid=request.getParameter("userid");
    String pname=request.getParameter("pname");
    pname=new String(pname.getBytes("ISO-8859-1"),"UTF-8");
    String no=request.getParameter("no");
    String logno=request.getParameter("logno");
    String date=request.getParameter("date");
    String time=request.getParameter("time");
    String userlist=request.getParameter("userlist");
    String text=request.getParameter("text");
    text=new String(text.getBytes("ISO-8859-1"),"UTF-8");
    String mnum=request.getParameter("mnum");
    double money=Double.parseDouble(mnum)*0.01;
    String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
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
    appId: 'wx62b4232a976268cb', 
    timestamp: timestamp,
    nonceStr: 'Wm3WZYTPz0wzccnW', 
    signature: '<%=signature%>',
    jsApiList: ['getLocation','openLocation','showAllNonBaseMenuItem','stopRecord','playVoice','translateVoice','onMenuShareAppMessage','scanQRCode','chooseWXPay','openEnterpriseContact','openEnterpriseChat','getNetworkType'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.error(function(res){
	//alert(res);
	// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});
wx.ready(function(){
});
</script>
<title>缴费记录详情</title>
</head>
<body>
<div class="weui_cells">
<div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>缴费记录编号</p>
    </div>
    <div class="weui_cell_ft">
      <%=logno %>
    </div>
  </div>
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
      <p>缴费时间</p>
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
  
  <button onclick="wx.closeWindow();"  class="weui_btn weui_btn_primary">确定</button>
  
</div>

<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>