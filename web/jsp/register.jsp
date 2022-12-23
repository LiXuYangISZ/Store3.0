<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
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
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
</head>
<script>
	/* 图片点击事件 */
	function changeCheckCode(){
		$("#checkCode").attr("src","${pageContext.request.contextPath}/checkCode?"+new Date().getTime());
	}
	//校检用户名
	function checkUsername(){
		let username = $("#username").val();
		//2.定义正则, 以字母开头,由字母数字下划线
		var reg_username =/^[a-zA-Z]\w{7,19}$/;
		var flag = reg_username.test(username);
		if(flag){
			$("#username").css("border","");
			return true;
		}else{
			$("#username").css("border","1px solid red");
			return false;
		}

	};
	//校检密码
	function checkPassword() {
		//1.获取用户名
		let password = $("#password").val();
		var reg_password = /^[a-zA-Z]\w{7,19}$/;
		var flag = reg_password.test(password);
		if(flag){
			$("#password").css("border","");
			return true;
		}else{
			$("#password").css("border","1px solid red");
			return false;
		}

	};
	//校检确认密码
	function checkConfirPassword() {
		//1.获取用户名
		let confirmPassword = $("#confirmpwd").val();
		let password = $("#password").val();
		if(confirmPassword==password){
			$("#confirmpwd").css("border","");
			return true;
		}else{
			$("#confirmpwd").css("border","1px solid red");
			return false;
		}

	};
	//校检邮箱
	function checkEmail(){
		var email = $("#email").val();
		var reg_email= /^[A-Za-z0-9._%-]+@([A-Za-z0-9-]+.)+[A-Za-z]{2,4}$/;
		var flag = reg_email.test(email);
		if(flag){
			$("#email").css("border","");
			return true;
		}else{
			$("#email").css("border","1px solid red");
			return false;
		}
	}
	// 校检姓名
	function checkName() {
		var name = $("#name").val();
		if(""!=name){
			$("#name").css("border","");
			return true;
		}else{
			$("#name").css("border","1px solid red");
			return false;
		}
	}

	//校检手机号
	function checkTelephone() {
		var telephone = $("#telephone").val();
		var reg_telephone = /^1[3|4|5|7|8]\d{9}$/;
		var flag = reg_telephone.test(telephone);
		if(flag){
			$("#telephone").css("border","");
			return true;
		}else{
			$("#telephone").css("border","1px solid red");
			return false;
		}
	}

	//出生日期
	function checkDate() {
		var date = $("#date").val();
		//alert(date);
		console.log(date);
		if(date!=null&&date!=""){
			$("#date").css("border","");
			return true;
		}else{
			$("#date").css("border","1px solid red");
			return false;
		}
	}

	//验证码进行校检
	function checkCheckCode() {
		 let $code = $("#code").val();
		 if($code!=null&&$code!=""){
			 $("#code").css("border","");
			 $("#info_msg").html("");
			 return true;
		 }else{
			 $("#code").css("border","1px solid red");
			 $("#info_msg").html("验证码不能为空!");
			 return false;
		 }
	}

	$(function(){
		//当一个组件失去焦点时,调用相应的校检方法
        $("#registerForm").submit(function () {
           // alert("哈哈哈");
            if(checkUsername()&&checkConfirPassword()&&checkPassword()&&checkEmail()&&checkName()&&checkDate()&&checkCheckCode()&&checkTelephone()){
                // alert("进行注册!");

                $.get("${pageContext.request.contextPath}/user/register",$(this).serialize(),function (data) {
                    // 验证码错误
					if(data.flag){
						//alert("跳转到成功界面");

<%--						<%--%>
<%--							request.setAttribute("msg","恭喜你，注册成功!请登录您的注册邮箱激活您的账号,激活后才能登录.");--%>
<%--							request.getRequestDispatcher("/jsp/msg.jsp").forward(request,response);--%>
<%--						%>--%>
						location.href = "register_msg.jsp";
					}else{
						$("#info_msg").html(data.errorMsg);
						changeCheckCode();
					}

                })

            }else{
				changeCheckCode();
			}
            //每次提交后都更新验证码

			//changeCheckCode();  这里不能这样写的原因在于,if语句没有执行完之前该语句就已经执行了,所以验证码两次对比会不正确..

            return false;//禁止进行跳转.
        })

		$("#username").blur(checkUsername);
		$("#password").blur(checkPassword);
		$("#confirmpwd").blur(checkConfirPassword);

		$("#email").blur(checkEmail);
		$("#name").blur(checkName);
		$("#date").blur(checkDate);
		$("#code").blur(checkCheckCode);
		$("#telephone").blur(checkTelephone);

	})



	


</script>
<body>
<%@include file="head.jsp"%>




<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	
	


	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>会员注册</font>USER REGISTER
		<form class="form-horizontal" style="margin-top:5px;" id="registerForm">
			<%--<input type="hidden" name="method" value="regist">--%>
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username" >

			    </div>
			  </div>
			   <div class="form-group">
			    <label for="password" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control"  placeholder="请输入密码" name="password" id="password">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" id="email" placeholder="Email" name="email">
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="name" placeholder="请输入姓名" name="name">
			    </div>
			  </div>
				<div class="form-group">
					<label for="telephone" class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="telephone" placeholder="请输入手机号" name="telephone">
					</div>
				</div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio1" value="男"> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
			</label>
			</div>
			  </div>		
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="date" class="form-control"  name="birthday" id="date">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="code" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" class="form-control" id="code" name="code" >
			      
			    </div>
			    <div class="col-sm-2">
			    <img src="${pageContext.request.contextPath}/checkCode" id="checkCode" onclick="changeCheckCode()"/>
			    </div>
			    
			  </div>
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="注册" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;" >
			    </div>
				  <div style="text-align: center; " >
					  <span style="text-align: center;color: red;font-size: 20px;font-weight: bold;" id="info_msg"></span>
				  </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>



<%@include file="footer.jsp"%>

</body></html>




