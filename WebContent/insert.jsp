<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
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
<title>配置设定</title>
</head>
<body>
<header class='demos-header'>
      <h1 class="demos-title">企业号配置设定</h1>
    </header>
    <form action="insert" method="post" >
	<div class="weui_cells weui_cells_form" >
	
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">wxid</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="pname" name="wxid" placeholder="请输入企业号CorpID" >
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">wxSecret</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mnum" name="wxSecret" placeholder="请输入企业号Secret" >
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">mch_id</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mnum" name="mch_id" placeholder="请输入商户号" >
    </div>
  </div>
 <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">sToken</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mnum" name="sToken" placeholder="请输入应用回调Token" >
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">sEncodingAESKey</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" id="mnum" name="sEncodingAESKey" placeholder="请输入应用回调EncodingAESKey" >
    </div>
  </div>
<button class="weui_btn weui_btn_primary" type="submit" >提交</button>
</div>
</form>
<script src="/jquery-weui/dist/lib/jquery-2.1.4.js"></script>
<script src="/jquery-weui/dist/js/jquery-weui.js"></script>
  
</body>

</html>