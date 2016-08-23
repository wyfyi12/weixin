<%@page import="java.util.HashMap"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="util.wx"%>
<%@ page import="com.SHA1"%>
<%@ page import="util.getsig"%>
<%@ page import="util.MD5Util"%>
<%@ page import="java.util.SortedMap,
java.util.TreeMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	wx wx = new wx();
	String no = request.getParameter("no");
	String userid = request.getParameter("userid");
	String access = wx.getAcceptKey();
	String ticket = wx.getticket(access);
	getsig gs = new getsig();
	String timestamp = wx.getdate();
	String appId = "wx62b4232a976268cb";
	String nonceStr = "Wm3WZYTPz0wzccnW";
	String package1 = (String) request.getParameter("pid");
	package1 = "prepay_id=" + package1;
	SortedMap<Object, Object> data = new TreeMap<Object, Object>();
	data.put("appId", appId);
	data.put("timeStamp", timestamp);
	data.put("nonceStr", nonceStr);
	data.put("package", package1);
	data.put("signType", "MD5");
	String characterEncoding = "UTF-8";
	String mySign = MD5Util.createSign(characterEncoding, data);
	String url = "http://weixin.njnantu.com:8080/pay.jsp?pid=" + (String) request.getParameter("pid");
	String signature = gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jweixin-1.1.0.js"></script>
<script type="text/javascript">
var timestamp = <%=timestamp%>;

function onBridgeReady(){
	 WeixinJSBridge.invoke(
		       'getBrandWCPayRequest',{
		           "appId" : "<%=appId%>",     //公众号名称，由商户传入     
		           "timeStamp" :"<%=timestamp%>",         //时间戳，自1970年以来的秒数     
		           "nonceStr"  : "<%=nonceStr%>", //随机串     
		           "package"  : "<%=package1%>",     
		           "signType"  : "MD5",         //微信签名方式：     
		           "paySign" : "<%=mySign%>" //微信签名 
		       },
	       function(res){  
	    	   //alert(res.err_msg);
	    	   if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	    		   var url="./addlog?no=<%=no%>&userid=<%=userid%>";
				window.location.href = url;
			} // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
		});
	}
	if (typeof (WeixinJSBridge) == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
</script>
<title>交费进行中</title>

</head>
<body>

</body>
</html>