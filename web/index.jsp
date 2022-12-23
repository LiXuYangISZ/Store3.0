<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%--默认--%>
<%--  <%--%>
<%--    response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");--%>
<%--  %>--%>
<%-- 因为数据要经过servlet处理,后渲染到页面.而且index路径和默认路径是一样的,所以用转发. --%>
  <jsp:forward page="product/index"></jsp:forward>
  </body>
</html>
