package cn.rg.web.Servlet;

import cn.rg.constant.Constant;
import cn.rg.domain.ResultInfo;
import cn.rg.domain.User;
import cn.rg.service.UserService;
import cn.rg.service.impl.UserServiceImpl;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = (UserService) BeanFactory.getBean("UserService");
    //注册方法
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //校检验证码
        String checkCode = req.getParameter("code");

        HttpSession session = req.getSession();
        //Object aaa = session.getAttribute("CHECKCODE_SERVER");
        //System.out.println(aaa);

        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println("Servlet中后台生成的验证码:"+checkcode_server);
        //移除验证码  为了保证验证码只是用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //进行比较
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(checkCode)){
           // System.out.println("验证码错误!");;
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            writeValue(info,resp);//将结果返回给前台页面
            return;
        }
      //  System.out.println("验证码正确.."); // 正确就不存数据
        //验证码通过,则开始想数据库中存入数据
        //1.获取数据
        Map <String, String[]> map = req.getParameterMap();
       // System.out.println(map);
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            //手动封装 uid
            user.setUid(UUIDUtils.getId());
            //手动封装激活状态 state
            user.setState(Constant.USER_IS_NOT_ACTIVE);
            //手动封装激活码
            user.setCode(UUIDUtils.getCode());


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册

        boolean flag = userService.register(user);
        ResultInfo info = new ResultInfo();

        //4.响应结果
        if(flag){//注册成功
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名已存在,请更换用户名后重新注册!");
        }
        writeValue(info,resp);

    }

    /**
     * 邮箱激活
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String code = req.getParameter("code");
        if(code!=null){
            //2.调用service完成激活

           boolean flag = userService.active(code);
           String msg = null;
           //3.判断标记
            if(flag){
                //激活成功
                msg = "激活成功,请<a href='"+req.getContextPath()+"/jsp/login.jsp'>登录</a>";
            }else{//说明已经被激活过了,或者其它问题无法进行激活
                msg = "激活失败,请联系管理员!";
            }
            req.setAttribute("msg",msg);
            //转发是服务器内部的请求,所以不用写全部的路径,只需要写相对于web的路径即可.
            req.getRequestDispatcher("/jsp/msg.jsp").forward(req,resp);
        }
    }

    /**
     * 登录方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //校检验证码
        String checkCode = req.getParameter("code");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //移除验证码  为了保证验证码只是用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //进行比较
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(checkCode)){
            System.out.println("验证码错误!");;
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            writeValue(info,resp);//将结果返回给前台页面
            return;
        }
        //System.out.println("验证码正确.."); // 正确就不存数据

        Map <String, String[]> map = req.getParameterMap();
        //2.封装
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //查询

        User u = userService.login(user);
       // System.out.println(u);
        ResultInfo info = new ResultInfo();
        //4.判断u是否为空
       if(u==null){
                //用户名或密码错误
           info.setFlag(false);
           info.setErrorMsg("用户名或密码错误!");

        }
        //5.判断是否激活
        if(u!=null&&u.getState()==Constant.USER_IS_NOT_ACTIVE){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活,请先去激活再登录.");
        }
        //登录成功
        if(u!=null&&u.getState()==Constant.USER_IS_ACTIVE){
            info.setFlag(true);
            //存入用户信息,以便后期使用
            req.getSession().setAttribute("user",u);
            //记住用户名
            //判断是否勾选了记住用户名
            System.out.println("勾选情况"+req.getParameter("saveName"));
            if(Constant.SAVE_NAME.equals(req.getParameter("saveName"))){
                Cookie cookie = new Cookie("saveUsername", URLEncoder.encode(u.getUsername(), "utf-8"));
                Cookie cookie2 = new Cookie("savePassword", URLEncoder.encode(u.getPassword(), "utf-8"));
                cookie.setMaxAge(Integer.MAX_VALUE);
                cookie2.setMaxAge(Integer.MAX_VALUE);
                cookie.setPath(req.getContextPath()+"/");
                cookie2.setPath(req.getContextPath()+"/");
                resp.addCookie(cookie);
                resp.addCookie(cookie2);
            }
        }
        writeValue(info,resp);
    }

    /**
     * 寻找session中是否有用户的信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        writeValue(user,resp);
    }

    /**
     * 用户退出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getSession().invalidate();
       resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
    }
}
