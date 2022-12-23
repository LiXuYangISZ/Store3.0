<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<html>
  <head>
    <title>支付结果</title>
  </head>
  <body>
  <h3 style="text-align: center;">
      <span id="time" >5</span>秒之后,自动跳转到登录页面
  </h3>


    <h3>支付结果</h3>
	支付状态:成功<br/>
	支付金额:${sessionScope.order.total}元<br/>
	交易单号:${sessionScope.resMap.transaction_id}<br/>
	支付时间:${sessionScope.resMap.time_end}<br/>
  </body>
  <script>
      var time = document.getElementById('time');
      var second = 5;

      function showTime() {
          second--;
          if (second <= 0) {
              location.href = "login.jsp";
          }
          time.innerHTML = second;
      }
      setInterval(showTime, 1000);
  </script>
</html>
