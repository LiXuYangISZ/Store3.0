package cn.rg.web.Servlet;

import cn.rg.domain.Cart;
import cn.rg.domain.CartItem;
import cn.rg.domain.Product;
import cn.rg.service.ProductService;
import cn.rg.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {

    private ProductService ps= (ProductService) BeanFactory.getBean("ProductService");
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取pid,count
        String pid = req.getParameter("pid");
        int count = Integer.parseInt(req.getParameter("quantity"));
        Product product = ps.getById(pid);
        //封装cartItem
        CartItem cartItem = new CartItem(product, count);
        //获取购物车
        Cart cart = getCart(req);
        cart.addToCart(cartItem);
        System.out.println("ahahhha");
        //重定向
        resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");

    }


    public Cart getCart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart==null){//如果cart为null,则添加一个
           cart = new Cart();
           request.getSession().setAttribute("cart",cart);
        }
        return cart;
    }

    /**
     * 从购物车中删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        //进行删除
        getCart(req).removeFromCart(pid);
        //跳转到cart页面
        resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCart(req).clearCart();
        resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");
    }
}
