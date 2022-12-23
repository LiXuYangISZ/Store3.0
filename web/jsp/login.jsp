<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}

 </style>
	<script src="./../js/jquery-1.11.3.min.js"></script>
	<script>
		/* 图片点击事件 */
		function changeCheckCode(){
			$("#checkCode").attr("src","${pageContext.request.contextPath}/checkCode?"+new Date().getTime());
		}
		function checkUsername() {
			//alert("hahhah");
			//1.获取用户名
			var username = $("#username").val();
			console.log()
			if(username==""){
				$("#username").css("border","1px solid red");
				$("#errorMsg").html("用户名不能为空!");
				return false;
			}else{
				$("#username").css("border","");
				$("#errorMsg").html("");
				return true;
			}
		}
		//校验密码
		function checkPassword() {
		//1.获取用户名
			let password = $("#password").val();
			if(password==""){
				$("#password").css("border","1px solid red");
				$("#errorMsg").html("密码不能为空!");
				return false;
			}else{
				$("#password").css("border","");
				$("#errorMsg").html("");
				return true;
			}
		}
		//校验验证码
		function checkCheckCode() {
			var checkCode = $("#code").val();
			//console.log(checkCode)
			if(checkCode==""){
				$("#code").css("border","1px solid red");
				$("#errorMsg").html("验证码不能为空!");
				return false;
			}else{
				$("#code").css("border","");
				$("#errorMsg").html("");
				return true;
			}
		}

		$(function(){
			//绑定表单提交事件
			$("#loginForm").submit(function () {
				if(checkUsername()&&checkPassword()&&checkCheckCode()){
					$.post("${pageContext.request.contextPath}/user/login",$(this).serialize(),function (data) {
						//alert("data:"+data);
						console.log(data);
						if(data.flag){
							location.href="${pageContext.request.contextPath}";
						}else{
							$("#errorMsg").html(data.errorMsg);
							changeCheckCode();
						}
					});
				}else{

					changeCheckCode();
				}
				return false;//阻止表单的跳转事件..
			})
			$("#username").blur(checkUsername);
			$("#password").blur(checkPassword);
			$("#code").blur(checkCheckCode);
		});

		//记住用户名
		$(function () {
			let username = "${cookie.saveUsername.value}";
			let password ="${cookie.savePassword.value}";
			$("#username").val(username);
			$("#password").val(password);
		})
	</script>
</head>
<body>
	
	
	
	
			<%@include file="head.jsp"%>

	
	
	
	
	
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN ${msg }

				<div>&nbsp;</div>
<form class="form-horizontal"  id="loginForm">

  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
    </div>
  </div>
   <div class="form-group">
    <label for="password" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password" >
    </div>
  </div>
   <div class="form-group">
        <label for="code" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="code" placeholder="请输入验证码" name="code">
    </div>
    <div class="col-sm-2">
      <img src="${pageContext.request.contextPath}/checkCode" onclick="changeCheckCode()" id="checkCode" />
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="saveName" value="ok"> 记住用户名
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
	<div class="form-group" style="text-align: center; color: red; font-size: 20px; font-weight: bold">
		<span id="errorMsg" ></span>
	</div>
</form>
</div>			
	</div>
</div>
</div>

            <%@include file="footer.jsp"%>
</body></html>