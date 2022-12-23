package cn.rg.web.Servlet;

import cn.rg.constant.Constant;
import cn.rg.domain.Order;
import cn.rg.domain.OrderItem;
import cn.rg.service.OrderService;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminOrder/*")
public class AdminOrderServlet extends BaseServlet {
    OrderService os = (OrderService) BeanFactory.getBean("OrderService");
    public void findAllByState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String state = req.getParameter("state");
        List <Order> list = os.findAllByState(state);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/admin/order/list.jsp").forward(req,resp);
    }

    /**
     * 展示详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String oid = req.getParameter("oid");
        Order order = os.getById(oid);
        if(order!=null){
            List <OrderItem> list = order.getItems();
            if(list!=null && list.size() > 0){
                JsonConfig config = JsonUtil.configJson(new String[]{"order", "pdate", "price"});
                resp.getWriter().println(JSONArray.fromObject(list,config));
            }
        }

    }

    public void updateState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        Order order = os.getById(oid);
        order.setState(Constant.ORDER_YIFAHUO);
        os.updateOrder(order);
        //挑转到
        resp.sendRedirect(req.getContextPath()+"/adminOrder/findAllByState?state=1");
    }

}
