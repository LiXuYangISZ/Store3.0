package cn.rg.web.Servlet;

import cn.rg.domain.Category;
import cn.rg.service.CategoryService;
import cn.rg.service.impl.CategoryServiceImpl;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminCategory/*")
public class AdminCategoryServlet extends BaseServlet {
    private CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");

    /**
     * 查找所有的分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <Category> list = cs.findList();
        //将返回值放入到request中,请求转发
        req.setAttribute("list",list);
        req.getRequestDispatcher("/admin/category/list.jsp").forward(req,resp);
    }

    /**
     * 添加分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category c = new Category();
        c.setCid(UUIDUtils.getId());
        c.setCname(req.getParameter("cname"));
        cs.save(c);
        //跳转到展示页面
        findAll(req,resp);
    }

    /**
     * 修改分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先获得cid,cname并进行封装
        String cid = req.getParameter("cid");
        String cname = req.getParameter("cname");
        Category category = new Category(cid,cname);
        //2.调用service方法进行更新  记得更新redis
        cs.update(category);

        // 必须携带信息然后返回 最后重定向到list.jsp
       findAll(req,resp);
    }

    /**
     * 查找某一个分类
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cid
        String cid = req.getParameter("cid");
        //去数据库里查询 category
        Category category = cs.findOne(cid);

        //将category存入request,并跳转到edit页面.
        req.setAttribute("category",category);
        req.getRequestDispatcher("/admin/category/edit.jsp").forward(req,resp);
    }
}
