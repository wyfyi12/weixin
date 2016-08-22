<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.gettime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="net.sf.json.JSONObject"%>
    <%@ page import="util.wx"%>
    <%@ page import="util.getsig"%>
    <%
    wx wx=new wx();
    ArrayList<HashMap<String, String>> kq=wx.getkq();
	String access=wx.getAcceptKey();
    String ticket=wx.getticket(access);
    String url=request.getRequestURL().toString();
    String timestamp=wx.getdate();
    getsig gs=new getsig();  
    String signature=gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
    String jd=kq.get(0).get("jd");
    String wd=kq.get(0).get("wd");
    String time=kq.get(0).get("time");
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DCcU0IzKpdVeut0Y0bAgdDHQah0atIbL"></script>
<script type="text/javascript">
var timestamp = <%=timestamp%>;
var point =new BMap.Point(<%=jd%>, <%=wd%>);
var geoc = new BMap.Geocoder();
geoc.getLocation(point, function(rs){
	var addComp = rs.addressComponents;
	 document.getElementById("wz").innerHTML=addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
});
//alert(location.href.split('#')[0]);
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
<title>查看打卡设定</title>
</head>
<body>
<div class="weui_cells">
<div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>考勤位置</p>
    </div>
    <div class="weui_cell_ft" id="wz">
    </div>
  </div>
 <div class="weui_cell">
  <div class="weui_cell_bd weui_cell_primary">
      <p>考勤时间</p>
    </div>
    <div class="weui_cell_ft" id="wz">
    <%=time%>
    </div>
    </div>
</div>
</body>
</html>