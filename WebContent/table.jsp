<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String tabletype=(String)request.getAttribute("tabletype");
    String tablewidth=(String)request.getAttribute("tablewidth");
    String tableheight=(String)request.getAttribute("tableheight");
    String datajob=(String)request.getAttribute("datajob");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>邦富缴费统计表</title>
<script type="text/javascript" src="fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript">
  FusionCharts.ready(function(){
    var revenueChart = new FusionCharts({
        "type": "<%=tabletype %>",
        "renderAt": "chartContainer",
        "width": "<%=tablewidth %>",
        "height": "<%=tableheight %>",
        "dataFormat": "json",
        "dataSource": <%=datajob%>
  });
revenueChart.render();
})
</script>
</head>
<body>
  <div id="chartContainer"></div>
</body>
</html>