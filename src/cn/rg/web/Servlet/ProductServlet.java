package cn.rg.web.Servlet;

import cn.rg.domain.PageBean;
import cn.rg.domain.Product;
import cn.rg.service.ProductService;
import cn.rg.service.impl.ProductServiceImpl;
import cn.rg.utils.BeanFactory;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {
    private ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
    /**
     * 首页信息展示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.查询最新商品和热门商品
        List <Product> hotList =  ps.findHot();
        List<Product> newList =  ps.findNew();
        //将两个list都放入到request中
        req.setAttribute("hList",hotList);
        req.setAttribute("nList",newList);
        req.getRequestDispatcher("/jsp/index.jsp").forward(req,resp);
    }

    /**
     * 通过pid显示商品详细信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        //2.调用service获取单个商品,
        Product pro = ps.getById(pid);
        //3.将product存到request中
        req.setAttribute("product",pro);
        req.getRequestDispatcher("/jsp/product_info.jsp").forward(req,resp);
    }

    /**
     * 对每个种类进行分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        } catch (NumberFormatException e) {

        }
        int pageSize = 12;
        String cid = req.getParameter("cid");
        //cid = cid.substring(0,cid.lastIndexOf("."));//因为cid经过redis后是1.0这种形式,所以得这样处理才能在数据库中查找到数据

        //cid = String.valueOf((int)Double.parseDouble(cid));//将Double类型的字符串,转成int类型的字符串
        //2.调用service 分页查询商品
         PageBean<Product> bean = ps.findByPage(pageNumber,pageSize,cid);
        System.out.println(bean);

        //3.将PageBean放入request中,请求转发product_list.jsp

        req.setAttribute("pb",bean);

        req.getRequestDispatcher("/jsp/product_list.jsp").forward(req,resp);

    }
}
