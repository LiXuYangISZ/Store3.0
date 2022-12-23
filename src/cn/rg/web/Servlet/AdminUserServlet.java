package cn.rg.web.Servlet;

import cn.rg.domain.User;
import cn.rg.service.UserService;
import cn.rg.utils.BeanFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/adminUser/*")
public class AdminUserServlet extends BaseServlet {
    UserService us = (UserService) BeanFactory.getBean("UserService");

    /**
     * 查找所有用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查找所有用户
        List <User> list =  us.findAll();
        req.setAttribute("list",list);
        //转发是从web下的,所有要从这里开始写跳转的位置.
        req.getRequestDispatcher("/admin/user/list.jsp").forward(req,resp);
    }
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        //查找用户
        User user = us.findOne(uid);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/admin/user/edit.jsp").forward(req,resp);
    }

    /**
     * 修改用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取数据
        Map <String, String[]> map = req.getParameterMap();
        System.out.println(map);
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service修改用户
        us.update(user);
        //调账到显示页面
        findAll(req,resp);

    }

    /**
     * 删除用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        //调用service进行删除
        us.delUser(uid);
        //跳转到显示页面
        findAll(req,resp);
    }
}
