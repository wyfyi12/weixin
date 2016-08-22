<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="util.wx"%>
<%@ page import="com.SHA1"%>
<%@ page import="util.getsig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%
    wx wx=new wx();
    String access=wx.getAcceptKey();
    String ticket=wx.getticket(access);
	getsig gs=new getsig();    
    String timestamp=wx.getdate();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="jweixin-1.1.0.js"></script>
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">

<script type="text/javascript">
var timestamp = <%=timestamp%>;
var localId='';
//alert(location.href.split('#')[0]);
function where(){
	wx.getLocation({
	    type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	        wx.openLocation({
	    	    latitude: res.latitude, // 纬度，浮点数，范围为90 ~ -90
	    	    longitude: res.longitude , // 经度，浮点数，范围为180 ~ -180。
	    	    name: '当前位置', // 位置名
	    	    address: '日常工作地点', // 地址详情说明
	    	    scale: 10, // 地图缩放级别,整形值,范围从1~28。默认为最大
	    	    infoUrl: 'www.baidu.com' // 在查看位置界面底部显示的超链接,可点击跳转
	    	});
	    }
	});
}
function distence(){
	wx.getLocation({
	    type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	        var user = document.createElement('input');
	        window.location.href="http://weixin.njnantu.com:8080/kq?wd="+latitude+"&jd="+longitude;
            
	    }
	});
}
function startrecord(){
	wx.startRecord();
}
function stoprecord(){
	wx.stopRecord({
	    success: function (res) {
	         localId = res.localId;
	        
	    }
	
	});
}
function playrecord(){
	wx.playVoice({
	    localId: localId // 需要播放的音频的本地ID，由stopRecord接口获得
	});
}

function checkrecord(){
	wx.translateVoice({
		   localId: localId, // 需要识别的音频的本地Id，由录音相关接口获得
		    isShowProgressTips: 1, // 默认为1，显示进度提示
		    success: function (res) {
		        alert(res.translateResult); // 语音识别的结果
		    }
		});
}
function saoyisao(){
	wx.scanQRCode({
	    desc: 'scanQRCode desc',
	    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
	    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
	    success: function (res) {
	    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	}
	});
}
<%String signature=gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", "http://weixin.njnantu.com:8080/testwxjs.jsp");

%>

wx.config({
    debug: true, 
    appId: 'wx62b4232a976268cb', 
    timestamp: timestamp,
    nonceStr: 'Wm3WZYTPz0wzccnW', 
    signature: '<%=signature%>',
    jsApiList: ['getLocation','openLocation','showAllNonBaseMenuItem','stopRecord','playVoice','translateVoice','onMenuShareAppMessage','scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	
	
	
});

wx.error(function(res){
	alert(res);
	
	// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});
wx.checkJsApi({
    jsApiList: ['openEnterpriseChat'], 
    success: function(res) {
    	alert(res);
    }});

</script>
<title>Insert title here</title>
 
</head>
<body>
<div class="weui_grids">
  <a href="javascript:;" class="weui_grid js_grid" data-id="button" onclick="where()">
    <div class="weui_grid_icon" >
      <img src="./images/icon_nav_button.png" alt="">
    </div>
    <p class="weui_grid_label">
      	地图
    </p>
  </a>
  <a href="javascript:;" class="weui_grid js_grid" data-id="cell" onclick="startrecord()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_cell.png" alt="">
    </div>
    <p class="weui_grid_label">
     	 开始录音
    </p>
  </a>
  <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="stoprecord()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
                停止录音
    </p>
  </a>
  <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="playrecord()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
                播放录音
    </p>
  </a>
   <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="checkrecord()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
                翻译录音
    </p>
  </a>
   <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="saoyisao()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
                扫一扫
    </p>
  </a>
  <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="location='adduser.jsp'">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
                添加成员
    </p>
  </a>
  <a href="javascript:;" class="weui_grid js_grid" data-id="toast" onclick="distence()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
               考勤
    </p>
  </a>
  <a href="map.jsp" class="weui_grid js_grid" data-id="toast" onclick="distence()">
    <div class="weui_grid_icon">
      <img src="./images/icon_nav_toast.png" alt="">
    </div>
    <p class="weui_grid_label">
              百度map
    </p>
  </a>
</div>
</body>
<script src="jquery-weui-build/dist/lib/jquery-2.1.4.js"></script>
<script src="jquery-weui-build/dist/js/jquery-weui.js"></script> 
</html>