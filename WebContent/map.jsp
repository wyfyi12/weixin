<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DCcU0IzKpdVeut0Y0bAgdDHQah0atIbL"></script>
	<title>定位</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(118.79922,32.02754);
	map.centerAndZoom(point,18);
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			map.addEventListener("click",function(e){
				var url='http://weixin.njnantu.com:8080/getlocation.jsp?jd='+e.point.lng+'&wd='+e.point.lat;
				 window.location.href=url; 
				alert(e.point.lng + "," + e.point.lat);
			});
		}
		else {
			alert('failed'+this.getStatus());
		} 
		// 定义一个控件类,即function
		function ZoomControl(){
		  // 默认停靠位置和偏移量
		  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		  this.defaultOffset = new BMap.Size(10, 10);
		}

		// 通过JavaScript的prototype属性继承于BMap.Control
		ZoomControl.prototype = new BMap.Control();

		// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
		// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
		ZoomControl.prototype.initialize = function(map){
		  // 创建一个DOM元素
		  var div = document.createElement("div");
		  // 添加文字说明
		  div.appendChild(document.createTextNode("设定当前位置为考勤地点"));
		  // 设置样式
		  div.style.cursor = "pointer";
		  div.style.border = "1px solid gray";
		  div.style.backgroundColor = "white";
		  var url='http://weixin.njnantu.com:8080/getlocation.jsp?jd='+r.point.lng+'&wd='+r.point.lat;
		  // 绑定事件
		  div.onclick = function(e){
			  window.location.href=url; 
		  }
		  // 添加DOM元素到地图中
		  map.getContainer().appendChild(div);
		  // 将DOM元素返回
		  return div;
		}
		// 创建控件
		var myZoomCtrl = new ZoomControl();
		// 添加到地图当中
		map.addControl(myZoomCtrl);
	},{enableHighAccuracy: true})
</script>
