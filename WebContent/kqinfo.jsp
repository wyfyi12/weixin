<%@page import="dao.localdao"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page import="util.wx"%>
<%@ page import="util.getsig"%>
<%
	wx wx = new wx();
	String access = wx.getAcceptKey();
	String ticket = wx.getticket(access);
	String userid = request.getParameter("userid");
	String time = request.getParameter("time");
	String date = request.getParameter("date");
	String DeviceId = request.getParameter("DeviceId");
	String status = request.getParameter("status");
	localdao.getConnection();
	HashMap<String, String> devinfo=new HashMap<String, String>();
	String devname="";
	devinfo = localdao.querydevById(DeviceId);
	if(devinfo.size()==0){
		 devname="未登记设备";
	 }else{
		 devname = devinfo.get("devname"); 
	 }
	localdao.conn.close();
	String distance = request.getParameter("distance");
	String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI() + "?"
			+ request.getQueryString();
	String timestamp = wx.getdate();
	getsig gs = new getsig();
	String signature = gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
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
				jsApiList : [ 'getLocation', 'openLocation',
						'showAllNonBaseMenuItem', 'stopRecord', 'playVoice',
						'translateVoice', 'onMenuShareAppMessage',
						'scanQRCode', 'chooseWXPay', 'openEnterpriseContact',
						'openEnterpriseChat', 'getNetworkType' ]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
	wx.error(function(res) {
		//alert(res);
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	wx.ready(function() {
	});
</script>
<title>考勤记录详情</title>
</head>
<body>
	<div class="weui_cells">

		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>用户名</p>
			</div>
			<div class="weui_cell_ft">
				<%=userid%>
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>考勤日期</p>
			</div>
			<div class="weui_cell_ft">
				<%=date%>
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>考勤时间</p>
			</div>
			<div class="weui_cell_ft">
				<%=time%>
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>设备</p>
			</div>
			<div class="weui_cell_ft">
				<%=devname%>
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>与公司距离</p>
			</div>
			<div class="weui_cell_ft">
				<%=distance%>
			</div>
		</div>
		<div class="weui_cell">
			<div class="weui_cell_bd weui_cell_primary">
				<p>考勤状态</p>
			</div>
			<div class="weui_cell_ft">
				<%=status%>
			</div>
		</div>
		<button onclick="wx.closeWindow();" class="weui_btn weui_btn_primary">确定</button>
	</div>

	<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
	<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
</body>
</html>