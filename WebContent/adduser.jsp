<%@page import="java.util.HashMap"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="net.sf.json.JSONObject"%>
<%@ page import="util.wx"%>
<%@ page import="com.SHA1"%>
<%@ page import="util.getsig"%>
    <%
    wx wx=new wx();
    String access=wx.getAcceptKey();
    String rs=wx.searchparty(access, "1");
    JSONArray ja=wx.getJSONArrayfromJSONObject(rs, "department");
    HashMap<String ,String> party=new HashMap<String ,String>();
    for(int i=0;i<ja.size();i++){
    	JSONObject job=ja.getJSONObject(i);
    	String id=job.getString("id");
    	String name=job.getString("name");
    	party.put(id, name);
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="jquery-weui-build/dist/lib/weui.min.css">
<link rel="stylesheet" href="jquery-weui-build/dist/css/jquery-weui.css">
<title>Insert title here</title>
</head>
<body>
	<form action="addUs" method="post" >
	<div class="weui_cells weui_cells_form">
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">用户id</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="userid" name="userid" placeholder="请输入用户id">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">姓名</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="username" name="username" placeholder="请输入姓名">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">部门</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="userparty" name="userparty" placeholder="请输入部门">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">手机</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mobile" name="mobile" placeholder="请输入手机号码">
    </div>
  </div>
   <div class="weui_cell weui_cell_select">
    <div class="weui_cell_bd weui_cell_primary">
      <select class="weui_select" name="userparty" id="userparty" >
      <%for(String id: party.keySet()){ %>
        <option value="<%=id %>"><%=party.get(id)%></option>
        
        <% }%>
      </select>
    </div>
  </div>
  <div class="weui_cell weui_cell_select">
    <div class="weui_cell_bd weui_cell_primary">
      <select class="weui_select" name="gender" id="gender" >
        <option value="1">男</option>
        <option value="2">女</option>
        
      </select>
    </div>
  </div>
 
  
  
 
  

</div>
	<button href="javascript:;" class="weui_btn weui_btn_primary" type="submit" >提交</button>
	
	</form>
</body>
<script src="jquery-weui-build/dist/lib/jquery-2.1.4.js"></script>
<script src="jquery-weui-build/dist/js/jquery-weui.js"></script> 
</html>