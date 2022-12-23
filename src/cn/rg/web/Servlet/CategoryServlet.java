package cn.rg.web.Servlet;

import cn.rg.domain.Category;
import cn.rg.service.CategoryService;
import cn.rg.service.impl.CategoryServiceImpl;
import cn.rg.utils.BeanFactory;
import sun.awt.SunHints;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
    /**
     * 查询所有分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = null;
        try {
            //  获取数据
            response.setContentType("application/json;charset=utf-8");
            //从redis中获取列表
            json = categoryService.findAllFromRedis();
            //将字符串写到浏览器
            response.getWriter().println(json);
        } catch (IOException e) {

        }

        return json;
    }
}
