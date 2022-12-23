package cn.rg.web.Servlet;

import cn.rg.constant.Constant;
import cn.rg.domain.*;
import cn.rg.service.OrderService;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.PaymentUtil;
import cn.rg.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private  OrderService os = (OrderService) BeanFactory.getBean("OrderService");
    /**
     * 提交订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //-1从session中获取user
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            req.setAttribute("msg","请先登录!");
            req.getRequestDispatcher("/jsp/msg.jsp").forward(req,resp);
        }
        //0获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //1封装订单对象
        //1.1创建对象
        Order order = new Order();
        //1.2设置额oid
        order.setOid(UUIDUtils.getId());
        //1.3设置ordertime
        order.setOrdertime(new Date());
        //1.4设置total
        order.setTotal(cart.getTotal());
        //1.5设置 state
        order.setState(Constant.ORDER_WEIFUKUAN);
        //1.6设置user
        order.setUser(user);
        //1.7设置items(订单项列表) 遍历购物项列表
        for (CartItem ci : cart.getCartItems()) {
            //1.71封装成orderitem
            //a创建orderitem
            OrderItem oi = new OrderItem();

            //b.设置itemid uuid
            oi.setItemid(UUIDUtils.getId());
            //c设置count 从ci中获取
            oi.setCount(ci.getCount());
            //d设置subtotal
            oi.setSubtotal(ci.getSubtotal());
            //e.设置product
            oi.setProduct(ci.getProduct());
            //f.设置order
            oi.setOrder(order);

            //g将orderitem添加到items
            order.getItems().add(oi);
        }

        //2.调用orderservice完成保存

        os.save(order);

        //2.1清空购物车
        cart.clearCart();

        //3保存order数据,请求转发到order_info.jsp

        req.setAttribute("order",order);
        req.getRequestDispatcher("/jsp/order_info.jsp").forward(req,resp);

        //

    }

    /**
     * 通过分页来查看我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMyOrdersByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取pageNumber 设置pagesize,获取userid
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        int pageSize = 3;
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //未登录则给与提示
            req.setAttribute("msg","请先登录,再查看我的订单");
            req.getRequestDispatcher("/jsp/msg.jsp").forward(req,resp);
        }
        //2.调用service获取当前页所有数据PageBean
        PageBean<Order> bean = os.findMyOrdersByPage(pageNumber,pageSize,user.getUid());

        //3.将pageBean放入request域中,请求转发到order_list.jsp
        req.setAttribute("pb",bean);
        req.getRequestDispatcher("/jsp/order_list.jsp").forward(req,resp);
    }

    /**
     * 点击未付款,获得订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        Order order = os.getById(oid);
        req.getSession().setAttribute("order",order);
        req.setAttribute("order",order);
        req.getRequestDispatcher("/jsp/order_info.jsp").forward(req,resp);
    }

    /**
     * 在线支付
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取收获信息 获取oid 获取银行

        //2.调用service获取订单 修改收获人信息  更新订单

        //3.拼接给第三方的url

        //4.重定向
        //接受参数
        String address=request.getParameter("address");
        String username=request.getParameter("username");
        String telephone=request.getParameter("telephone");
        String oid=request.getParameter("oid");


        //通过id获取order

        Order order = os.getById(oid);

        order.setAddress(address);
        order.setName(username);
        order.setTelephone(telephone);

        //覆盖session中的order  后序如果支付成功则进行更新,否则不进行更新
        request.getSession().setAttribute("order",order);
        //重定向到支付页面.
        response.sendRedirect(request.getContextPath()+"/jsp/order.jsp");
    }
    //updateState


    public void updateState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        Order order = os.getById(oid);
        order.setState(Constant.ORDER_YIWANCHENG);
        os.updateOrder(order);
        //${pageContext.request.contextPath}/order/findMyOrdersByPage?pageNumber=1
        resp.sendRedirect(req.getContextPath()+"/order/findMyOrdersByPage?pageNumber=1");
    }
}
