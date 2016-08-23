<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String jd=request.getParameter("jd");
    String wd=request.getParameter("wd");
    request.setAttribute("jd", jd);
    request.setAttribute("wd", wd);
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

var point =new BMap.Point(<%=jd%>, <%=wd%>);
var geoc = new BMap.Geocoder();
geoc.getLocation(point, function(rs){
	var addComp = rs.addressComponents;
	 document.getElementById("ss").innerHTML=addComp.province;
	 document.getElementById("cs").innerHTML=addComp.city;
	 document.getElementById("dd").innerHTML=addComp.district+addComp.street+addComp.streetNumber;
});
</script>
<title>设定打卡地点</title>
</head>
<body>
<form action="getLo" method="post">
<div class="weui_cells">
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>经度</p>
    </div>
    <div class="weui_cell_ft">
    <input type="hidden" name="jd" value="<%=jd %>">
      <%=jd %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>纬度</p>
    </div>
    <div class="weui_cell_ft">
    <input type="hidden" name="wd" value="<%=wd %>">
      <%=wd %>
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>省份</p>
    </div>
    <div class="weui_cell_ft" id="ss">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>城市</p>
    </div>
    <div class="weui_cell_ft" id="cs">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_bd weui_cell_primary">
      <p>地点</p>
    </div>
    <div class="weui_cell_ft" id="dd">
    </div>
  </div>
   <div class="weui_cell">
    <div class="weui_cell_hd"><label for="" class="weui_label">考勤时间</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" type="time" value="" placeholder="" name="time">
    </div>
  </div>
</div>
<button type="submit" class="weui_btn weui_btn_primary">按钮</button>
</form>
</body>
</html>