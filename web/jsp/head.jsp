<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<%--在进行动态包含时,一定要引入需要的一些js/Jquery文件.--%>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script>

        $(function () {
            //发送ajax请求,查询所有分类
            $.post("${pageContext.request.contextPath}/category/findAll",{},function (data) {
                console.log("哈哈哈"+data);
                //alert(data);
                for(let i = 0;i<data.length; i++){
                    console.log(data);
                    $("#c_ul").append("<li><a href='${pageContext.request.contextPath}/product/findByPage?pageNumber=1&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>");
                }
            })

        });
        // 欢迎信息
        $(function () {
            $.get("${pageContext.request.contextPath}/user/findOne",{},function (data) {
                console.log(data);
                //alert(data);
                if(data!=null){
                    var msg = "欢迎回来,"+data.name;
                    $("#welcome_msg").html(msg);
                    $("#login_li").css("display","none");


                    $("#register_li").css("display","none");

                }else{
                    // $("#cart_li").css("display","none");
                    // $("#order_li").css("display","none");
                    //$("#exit_li").css("display","none");
                }

            });
        });

</script>
<!--
    时间：2015-12-30
    描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo5.jpg" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <li id="welcome_msg"></li>
            <li id="login_li"><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
            <li id="register_li"><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
            <li id="order_li"><a href="${pageContext.request.contextPath}/order/findMyOrdersByPage?pageNumber=1">我的订单</a></li>
            <li id="cart_li"><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
            <li id="exit_li"><a href="javascript:location.href='${pageContext.request.contextPath}/user/exit'">退出</a></li>
        </ol>
    </div>
</div>
<!--
    时间：2015-12-30
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="c_ul">
                    <%--<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>--%>
                    <%--<li><a href="#">电脑办公</a></li>--%>
                    <%--<li><a href="#">电脑办公</a></li>--%>
                    <%--<li><a href="#">电脑办公</a></li>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>


