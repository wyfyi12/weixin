<%@page import="util.getdata"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="util.wx"%>
    <%@ page import="util.getsig"%>
    <%
    wx wx=new wx();
    String access=wx.getAcceptKey();
    String ticket=wx.getticket(access);
    getdata.getconn();
    String domain=getdata.getdomain();
    String url="http://"+domain+"/addpay.jsp";
    String timestamp=wx.getdate();
    String gip=wx.getgid(access);
    JSONObject gjob=wx.getJSONObjectfromString(gip);
    String gid=gjob.getString("group_id");
    String gticket=gjob.getString("ticket");
    getsig gs=new getsig();  
    String signature=gs.getSignature(ticket, timestamp, "Wm3WZYTPz0wzccnW", url);
    String gsignature=gs.getgSignature(gticket, timestamp, "Wm3WZYTPz0wzccnW", url);
    String alluser=wx.getalluser(access);
    String alluserid=wx.getalluserid(access);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/demo.css">
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
	alert(res);
	// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});

wx.ready(function(){
	var evalWXjsApi = function(jsApiFun) {
	    if (typeof WeixinJSBridge == "object" && typeof WeixinJSBridge.invoke == "function") {
	        jsApiFun();
	    } else {
	        document.attachEvent && document.attachEvent("WeixinJSBridgeReady", jsApiFun);
	        document.addEventListener && document.addEventListener("WeixinJSBridgeReady", jsApiFun);
	    }
	}
	                    
	document.querySelector('#openEnterpriseContact_invoke').onclick = function() {
	    evalWXjsApi(function() {
	        WeixinJSBridge.invoke("openEnterpriseContact", {
	            "groupId": "<%=gid%>",    // 必填，管理组权限验证步骤1返回的group_id
	            "timestamp": "<%=timestamp%>",    // 必填，管理组权限验证步骤2使用的时间戳
	            "nonceStr": "Wm3WZYTPz0wzccnW",    // 必填，管理组权限验证步骤2使用的随机字符串
	            "signature": "<%=gsignature%>",  // 必填，管理组权限验证步骤2生成的签名
	            "params" : {
	                'departmentIds' : [0],    // 非必填，可选部门ID列表（如果ID为0，表示可选管理组权限下所有部门）
	                'tagIds' : [0],    // 非必填，可选标签ID列表（如果ID为0，表示可选所有标签）
	                'userIds' : [],    // 非必填，可选用户ID列表
	                'mode' : 'multi',    // 必填，选择模式，single表示单选，multi表示多选
	                'type' : ['user'],    // 必填，选择限制类型，指定department、tag、user中的一个或者多个
	                'selectedDepartmentIds' : [],    // 非必填，已选部门ID列表
	                'selectedTagIds' : [],    // 非必填，已选标签ID列表
	                'selectedUserIds' : [],    // 非必填，已选用户ID列表
	            },
	        }, function(res) {
	            if (res.err_msg.indexOf('function_not_exist') > -1) {
	                alert('版本过低请升级');
	            } else if (res.err_msg.indexOf('openEnterpriseContact:fail') > -1) {
	            	alert(res.err_msg);
	                return;
	            }
	            var result = JSON.parse(res.result);    // 返回字符串，开发者需自行调用JSON.parse解析
	            var selectAll = result.selectAll;     // 是否全选（如果是，其余结果不再填充）
	            var obj = eval(result);
	            var value='';
	           if(!selectAll){
	            for(var i=0;i<obj.userList.length;i++){
	            	value=value+obj.userList[i].name+' ';
	           }}else{
	            	value='<%=alluser%>';
	            }
	            if(document.getElementById('user')==undefined){
	            var input = document.createElement('input');
	            input.setAttribute('class', 'weui_input');
	            input.setAttribute('type', 'text');
	            input.setAttribute('id', 'user');
	            input.setAttribute('value', value);
	            input.setAttribute('required', 'required');
	            document.getElementById('div1').appendChild(input);
	            }else{
	            	var input = document.getElementById('user');
	            	input.value=value;
	            }
	            var uvalue='';
	            if(!selectAll){
		            for(var i=0;i<obj.userList.length;i++){
		            	uvalue=value+obj.userList[i].id+' ';
		           }}else{
		            	uvalue='<%=alluserid%>';
		            }
	            var user = document.createElement('input');
	            user.setAttribute('id', 'userlist');
	            user.setAttribute('name', 'userlist');
	            user.setAttribute('type', 'hidden');
	            user.setAttribute('value', uvalue);
	            document.getElementById('div1').appendChild(user);
	           
	            if (!selectAll)
	            {
	                var selectedDepartmentList = result.departmentList;    // 已选的部门列表
	                for (var i = 0; i < selectedDepartmentList.length; i++) {
	                    var department = selectedDepartmentList[i];
	                    var departmentId = department.id;    // 已选的单个部门ID
	                    var departemntName = department.name;    // 已选的单个部门名称
	                }
	                var selectedTagList = result.tagList;    // 已选的标签列表
	                for (var i = 0; i < selectedTagList.length; i++) {
	                    var tag = selectedTagList[i];
	                    var tagId = tag.id;    // 已选的单个标签ID
	                    var tagName = tag.name;    // 已选的单个标签名称
	                }
	                var selectedUserList = result.userList;    // 已选的成员列表
	                for (var i = 0; i < selectedUserList.length; i++) {
	                    var user = selectedUserList[i];
	                    var userId = user.id;    // 已选的单个成员ID
	                    var userName = user.name;    // 已选的单个成员名称
	                }
	            }
	        })
	    });
	}
});
</script>
<title>新建缴费</title>
</head>
<body>
<header class='demos-header'>
      <h1 class="demos-title">新建缴费表单</h1>
    </header>
    <form action="addPay" method="post" >
	<div class="weui_cells weui_cells_form" >
	
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">项目名称</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="pname" name="pname" placeholder="请输入项目名称" required="required">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">收费金额</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mnum" name="mnum" placeholder="请输入收费金额" required="required">
    </div>
  </div>
  
  <div class="weui_cell">
    <div class="weui_cell_hd"><label for="" class="weui_label">截止日期</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" type="date" id="date" name="date" value="" required="required">
    </div>
  </div>
  <div class="weui_cell">
        <div class="weui_cell_hd"><label for="name" class="weui_label">选择缴费成员</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input"  id="openEnterpriseContact_invoke"   type="button" value="" required="required">
        </div>
      </div>
       <div class="weui_cell">
     <div class="weui_cell_bd weui_cell_primary"  id="div1" >
     
     </div></div>
  <div class="weui_cells_title">收费说明</div>
<div class="weui_cells weui_cells_form">
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <textarea class="weui_textarea" placeholder="请输入收费说明 " name="text" id="text" rows="3"></textarea>
      <div class="weui_textarea_counter"><span>0</span>/200</div>
    </div>
  </div>
</div>
<button class="weui_btn weui_btn_primary" type="submit" >提交</button>
</div>
</form>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
  
</body>

</html>