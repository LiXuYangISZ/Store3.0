<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />

		<script src="${pageContext.request.contextPath}/js/jquery.1.8.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
		<script type="text/javascript">
			function showDetail(oid){
				//alert(oid);
				$.get("${pageContext.request.contextPath}/adminOrder/showDetail",{oid:oid},function (data) {
					var s = "<table width='100%' border='2' style='border-collapse: collapse; border: 1px solid grey; padding: 5px'>";
					s+="<tr><th>商品名称</th><th>购买数量</th><th>小计</th></tr>"
					$(data).each(function () {
						s+="<tr><td>"+this.product.pname+"</td><td>"+this.count+"</td><td>"+this.subtotal+"</td></tr>";
					})
					s+="</table>";
					layer.open({
						type:1,
						title:"订单号:"+oid,
						area:['520px','300px'],
						shadeClose:true,
						content:s
					})
				},"json");

				
			}
			function goFaHuo(oid) {
				if(confirm("确定为这个订单发货吗?")==true){
					//alert(oid);
					//alert("${pageContext.request.contextPath}/adminOrder/updateState?oid="+oid+"");
					location.href ="${pageContext.request.contextPath}/adminOrder/updateState?oid="+oid+"";
				}
			}

		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										订单金额
									</td>
									<td align="center" width="10%">
										收货人
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="50%">
										订单详情
									</td>
								</tr>
									<c:forEach var="order" items="${list}" varStatus="i">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${i.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${order.oid}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${order.total}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${order.name}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<c:if test="${order.state==0}">
													未付款
												</c:if>
												<c:if test="${order.state==1}">
													<input type="button" value="去发货" onclick="goFaHuo('${order.oid}')">
													<%--<a href="${pageContext.request.contextPath}/adminOrder/updateState?oid=${order.oid}" >去发货</a>--%>
												</c:if>
												<c:if test="${order.state==2}">
													待收货
												</c:if>
												<c:if test="${order.state==3}">
												已完成
												</c:if>
											
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input type="button" value="订单详情" onclick="showDetail('${order.oid}')"/>

											</td>
							
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<%--<td colspan="7">--%>
						<%--	第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页 --%>
						<%--	<s:if test="pageBean.page != 1">--%>
						<%--		<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=1">首页</a>|--%>
						<%--		<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|--%>
						<%--	</s:if>--%>
						<%--	<s:if test="pageBean.page != pageBean.totalPage">--%>
						<%--		<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|--%>
						<%--		<a href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|--%>
						<%--	</s:if>--%>
						<%--</td>--%>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

