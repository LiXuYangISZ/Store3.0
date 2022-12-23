<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!--order_list页面的分页部分 -->
<div style="width:380px;margin:0 auto;margin-top:50px;">
    <ul class="pagination" style="text-align:center; margin-top:10px;">
        <%--判断是否第一页--%>
        <c:if test="${pb.pageNumber==1}">
            <li class="disabled"><a href="javascirpt:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        </c:if>
        <c:if test="${pb.pageNumber!=1}">
            <li ><a href="${pageContext.request.contextPath}/order/findMyOrdersByPage?pageNumber=${pb.pageNumber-1}&cid=${param.cid}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        </c:if>
        <%--显示所有页码--%>
        <c:forEach begin="1" end="${pb.totalPage}" var="i">
            <%--						判断是否是当前也--%>
            <c:if test="${pb.pageNumber==i}">
                <li class="active"><a href="javascript:void(0);">${i}</a></li>
            </c:if>
            <c:if test="${pb.pageNumber!=i}">
                <li ><a href="${pageContext.request.contextPath}/order/findMyOrdersByPage?pageNumber=${i}&cid=${param.cid}">${i}</a></li>
            </c:if>


        </c:forEach>
        <c:if test="${pb.pageNumber==pb.totalPage}">
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pb.pageNumber!=pb.totalPage}">
            <li >
                <a href="${pageContext.request.contextPath}/order/findMyOrdersByPage?pageNumber=${pb.pageNumber+1}&cid=${param.cid}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>



    </ul>
</div>
