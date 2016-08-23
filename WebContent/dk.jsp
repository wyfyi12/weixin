<%@page import="dao.kqdao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.kq"%>
<%@page import="util.gettime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	String DeviceId = job.getString("DeviceId");
    String ticket=wx.getticket(access);
    String url=request.getRequestURL().toString()+"?code="+code;
    String timestamp=wx.getdate();
    getsig gs=new getsig();  
    String signature=gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
    String date=gettime.getday();
    String time=gettime.getnowtime();
    String userint=wx.getUserInfo(access, userid);
	String name=wx.getJSONObjectfromString(userint).getString("name");
	ArrayList<HashMap<String,String>> kq=kqdao.querykq();
	String jd=kq.get(0).get("jd");
	double bzjd=Double.parseDouble(jd);
	String wd=kq.get(0).get("wd");
	double bzwd=Double.parseDouble(wd);
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<script type="text/javascript" src="jweixin-1.1.0.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DCcU0IzKpdVeut0Y0bAgdDHQah0atIbL"></script>
<script type="text/javascript">
var timestamp = <%=timestamp%>;
//alert(location.href.split('#')[0]);
function toRad(d) {  return d * Math.PI / 180; }
function getDisance(lat1, lng1, lat2, lng2) {
    var dis = 0;
    var radLat1 = toRad(lat1);
    var radLat2 = toRad(lat2);
    var deltaLat = radLat1 - radLat2;
    var deltaLng = toRad(lng1) - toRad(lng2);
    var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
    return dis * 6378137;
}
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
	
	wx.getLocation({
	    type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	        var convertor = new BMap.Convertor();
	        var pointArr = [];
	        var ggPoint=new BMap.Point(longitude,latitude);
	        var x_PI = 3.14159265358979324 * 3000.0 / 180.0;
	    	    var z = Math.sqrt(longitude * longitude + latitude * latitude) + 0.00002 * Math.sin(latitude * x_PI);
	    	    var theta = Math.atan2(latitude, longitude) + 0.000003 * Math.cos(longitude * x_PI);
	    	    var bd_lng = z * Math.cos(theta) + 0.0065;
	    	    var bd_lat = z * Math.sin(theta) + 0.006;
	    	    
	        var dis=getDisance(<%=bzwd%>, <%=bzjd%>, bd_lat, bd_lng);
	        dis=parseInt(dis);
	        document.getElementById("f1").innerHTML=dis;
	        if(dis<20){
	        	var url="http://weixin.njnantu.com:8080/kq?userid=<%=userid%>&time=<%=time%>&date=<%=date%>&DeviceId=<%=DeviceId%>&distance="+dis;
	        	var a = document.createElement('input');
	 	        a.setAttribute("class", "weui_btn weui_btn_primary");
	 	       a.setAttribute("value", "打卡");
	 	      a.setAttribute("type", "button");
	 	        a.setAttribute("onclick", "location.href='"+url+"'");
	 	        document.getElementById('div1').appendChild(a);
	 	      
	        }
	    }
	});
});
</script>
<title>考勤</title>
</head>
<body>
<div class="weui_cells">
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>用户</p>
    </div>
    <div class="weui_cell_ft">
      <%=name %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>日期</p>
    </div>
    <div class="weui_cell_ft">
     <%=date %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>时间</p>
    </div>
    <div class="weui_cell_ft">
       <%=time%>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>地点</p>
    </div>
    <div class="weui_cell_ft">
      您与公司相差：<font id="f1"></font>米
    </div>
  </div>
  <div id="div1">
  </div>
</div>
</body>
<script src="jquery-weui-build/dist/lib/jquery-2.1.4.js"></script>
<script src="jquery-weui-build/dist/js/jquery-weui.js"></script> 
</html>