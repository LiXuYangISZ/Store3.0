package cn.rg.web.filter;

import cn.rg.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class PrivilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       //1.强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //逻辑
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("msg","请先登录!");
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request,response);
            return;
        }
        //放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
