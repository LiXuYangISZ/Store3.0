package cn.rg.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
//@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        String uri = request.getRequestURI();
        if(!uri.endsWith("html")&&!uri.endsWith("css")&&!uri.endsWith(("js"))){
            request.setCharacterEncoding("utf-8");
            //处理响应乱码
            response.setContentType("text/html;charset=utf-8");
        }


        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
