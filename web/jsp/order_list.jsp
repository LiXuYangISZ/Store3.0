<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
		<script>
			function confirmGetGoods(oid) {
				if(confirm("您确认收货了吗?")){
					alert(oid);
					location.href = "${pageContext.request.contextPath}/order/updateState?oid="+oid+"";
				}
			}
		</script>
	</head>

	<body>

			<%@include file="head.jsp"%>


		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">

						<c:forEach items="${pb.data}" var="order">


							<tbody>
								<tr class="success">
									<th colspan="2">订单编号:${order.oid} </th>
									<c:if test="${order.state==0}">
										<th colspan="1">订单状态:<a href="${pageContext.request.contextPath}/order/getById?oid=${order.oid}"><input type="button" value="去付款"></a> </th>
									</c:if>
									<c:if test="${order.state==1}">
										<th colspan="1">订单状态:已付款 </th>
									</c:if>
									<c:if test="${order.state==2}">
										<th colspan="1">订单状态:
												<%--<a href="${pageContext.request.contextPath}/order/updateState?oid=${order.oid}">确定收货</a>--%>
											<input type="button" value="确认收货" onclick="confirmGetGoods('${order.oid}')">
										</th>

									</c:if>
									<c:if test="${order.state==3}">
										<th colspan="1">订单状态:已完成 </th>
									</c:if>


									<th colspan="2">总金额:${order.total}
									</th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${order.items}" var="oi">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${oi.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank"> ${oi.product.pname}</a>
										</td>
										<td width="20%">
											￥${oi.product.shop_price}
										</td>
										<td width="10%">
											${oi.count}
										</td>
										<td width="15%">
											<span class="subtotal">￥${oi.subtotal}</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<%@include file="order_list_page.jsp"%>
		</div>

			<%@include file="footer.jsp"%>
	</body>

</html>