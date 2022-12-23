<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>支付结果页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body >
	<%@include file="head.jsp"%>

	<div style="height: 800px;">
		<h2 style="text-align: center;">
			恭喜你,你的订单已支付成功!等待发货~~~~~~
		</h2>

		<div style="text-align: center; font-size: 25px; height: 300px;padding-top: 50px">

			<h3>支付结果如下</h3>
			支付状态:成功<br/>
			支付金额:${sessionScope.order.total}元<br/>
			交易单号:${sessionScope.resMap.transaction_id}<br/>
			支付时间:${sessionScope.resMap.time_end}<br/>
		</div>


		<div>
			<div class="container-fluid">
				<%@include file="footer.jsp"%>
			</div>
		</div>
		<%--长时间不操作自动跳转--%>
		<%--<script>--%>
		<%--	var time = document.getElementById('time');--%>
		<%--	var second = 600;--%>

		<%--	function showTime() {--%>
		<%--		second--;--%>
		<%--		if (second <= 0) {--%>
		<%--			location.href = "${pageContext.request.contextPath}/jsp/index.jsp";--%>
		<%--		}--%>
		<%--		time.innerHTML = second;--%>
		<%--	}--%>
		<%--	setInterval(showTime, 1000);--%>
		<%--</script>--%>
	</div>

	</body>


</html>