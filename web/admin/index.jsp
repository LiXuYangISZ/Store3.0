<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />
 <script src="/js/jquery.1.8.3.min.js"></script>

<style type="text/css">
body {
  color: white;
}
</style>

</head>
<body style="background: #278296">
<center></center>
<form action="${pageContext.request.contextPath}/adminLoginServlet" method="post">
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    <td style="padding-left: 50px">
      <table>
      <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" id="username" /></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" id="password"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td><td><input type="submit" value="进入管理中心" class="button"  /></td>
      </tr>
      </table>

    </td>
  </tr>

  </table>
  <div style="text-align: center; font-weight: bold; color: #67b168;font-size: 20px">
    <%--<span>用户名或密码错误</span>--%>
   <c:if test="${ not empty msg}">
     <span>用户名或密码错误</span>
   </c:if>
  </div>
</form>
<script language="JavaScript">
<!--
  document.forms['theForm'].elements['username'].focus();
  
  /**
   * 检查表单输入的内容
   */
  function validate()
  {
    var validator = new Validator('theForm');
    validator.required('username', user_name_empty);
    //validator.required('password', password_empty);
    if (document.forms['theForm'].elements['captcha'])
    {
      validator.required('captcha', captcha_empty);
    }
    return validator.passed();
  }
  
//-->
</script>
</body>