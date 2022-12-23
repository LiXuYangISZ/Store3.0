<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>WEB01</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
<div class="container-fluid">

	<%@include file="head.jsp"%>

	<div class="container-fluid" style="height: 500px; background-color: skyblue" >
		<div class="main_con"  >

			<h2 style="text-align: center; padding-top: 220px">恭喜你，注册成功!请登录您的注册邮箱激活您的账号,激活后才能登录.</h2>

			<%--<h2 style="text-align: center; padding-top: 220px">恭喜你，注册成功!请登录您的注册邮箱激活您的账号,激活后才能登录.</h2>--%>
		</div>
	</div>

</div>
<div class="container-fluid">
	<%@include file="footer.jsp"%>
</div>
</div>

</body>

</html>