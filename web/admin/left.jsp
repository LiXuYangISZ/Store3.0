<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','用户管理','','','mainFrame');

		d.add('010101','0101','展示用户','${pageContext.request.contextPath}/adminUser/findAll','','mainFrame');
        //d.add('010102','0101','修改用户','${pageContext.request.contextPath}/adminCategory/findAll','','mainFrame');

		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','展示分类','${pageContext.request.contextPath}/adminCategory/findAll','','mainFrame');
        d.add('010202','0102','添加分类','${pageContext.request.contextPath}/admin/category/add.jsp','','mainFrame');
       // d.add('010203','0102','修改分类','${pageContext.request.contextPath}/adminCategory/update','','mainFrame');
        d.add('0103','01','商品管理');
		d.add('010301','0103','商品管理','${pageContext.request.contextPath}/adminProduct/findAll','','mainFrame');
        d.add('010302','0103','添加商品','${pageContext.request.contextPath}/adminProduct/addUI','','mainFrame');
		d.add('0104','01','订单管理');
		d.add('010401','0104','订单列表','${pageContext.request.contextPath}/adminOrder/findAllByState','','mainFrame');
        d.add('010402','0104','未付款订单','${pageContext.request.contextPath}/adminOrder/findAllByState?state=0','','mainFrame');
        d.add('010403','0104','已付款订单','${pageContext.request.contextPath}/adminOrder/findAllByState?state=1','','mainFrame');
        d.add('010404','0104','已发货订单','${pageContext.request.contextPath}/adminOrder/findAllByState?state=2','','mainFrame');
        d.add('010405','0104','已完成订单','${pageContext.request.contextPath}/adminOrder/findAllByState?state=3','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
