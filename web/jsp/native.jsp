<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>微信扫码支付</title>
<script type="text/javascript" src="../js/jquery.1.8.3.min.js"></script>
<script type="text/javascript" src="../js/qrcode.js"></script>
</head>
<body>
	<img src="${pageContext.request.contextPath}/img/logo2.png" id="img" style="display:none"/>
	<div style="text-align:center" id="qrcode"></div>
	<div style="text-align:center" id="qrtext"></div>
</body>
</html>
<script>
	var out_trade_no;  //订单号
	//往servlet发送异步请求下订单, 并获得订单信息
	$.post("${pageContext.request.contextPath}/NativeServlet", {}, function(
			data) {  //data存储的就是订单信息
		var url = data.code_url; //支付URL
		out_trade_no = data.out_trade_no; //订单号

		$('#qrcode').makeQRCode({
			render : 'image', //渲染模式,取值:canvas / image / div    
			ecLevel : 'H', //容错级别, 取值: L:7% / M:15% / Q:25% / H:30% 
			minVersion : 6, //二维码密度, 取值: 0-10
			fill : '#666', //二维码颜色
			background : '#fff', //二维码背景颜色
			text : url, //扫码时获取到的值
			size : 300, //二维码大小,单位是像素
			radius : 20, //圆滑度,取值50以内
			quiet : 4, //二维码边框
			mode:4, //显示模式, 取值: 不显示LOGO:0 / 文字且占整行:1 / 文字居中:2 / 图片且占整行:3 / 图片居中:4
			mSize:15, //logo大小
			label:'传智播客', //logo文字
			fontname:'微软雅黑', //logo字体名
			fontcolor:'green', //logo字体颜色
			image:$("#img")[0] //设置图片,要把mode改成4
		});
		$("#qrtext").html("请打开手机微信进行扫描");

		getPayStatus(); //生成二维码后就开始检测支付状态
	});

	//检测支付状态
	function getPayStatus() {
		//往servlet发送异步请求检测并获得支付状态
		$.post("${pageContext.request.contextPath}/PayStatusCheckServlet",
			{
				"out_trade_no" : out_trade_no  //订单号
			},
			function(data) {
				//SUCCESS表示支付成功
				if (data == "SUCCESS") {
					location.href = "${pageContext.request.contextPath}/jsp/notify2.jsp";
				} else {
					$("#qrcode")
							.html(
									"<button onclick='javascript:location.reload();'>支付失败,点击刷新,重新支付</button>");
					$("#qrtext").html("");
				}
			});
	}
</script>
