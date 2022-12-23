package cn.rg.web.Servlet;

import cn.rg.constant.Constant;
import cn.rg.dao.CategoryDao;
import cn.rg.domain.Category;
import cn.rg.domain.Product;
import cn.rg.service.CategoryService;
import cn.rg.service.ProductService;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.UUIDUtils;
import cn.rg.utils.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.jaxen.expr.iter.IterableAncestorOrSelfAxis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/adminProduct/*")
public class AdminProductServlet extends BaseServlet {
    ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
    /**
     * 查询所有产品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <Product> list =  ps.findAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/admin/product/list.jsp").forward(req,resp);
    }



    /**
     * 跳转到添加页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有分类
        CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
        List <Category> list = cs.findList();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/admin/product/add.jsp").forward(req,resp);
    }
    /**
     * 增加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //0.使用fileupload保存图片和将图片信息放入map中
            //0.1创建map存入商品的信息
            Map <String, Object> map = new HashMap <>();
            //0.2创建磁盘文件像工厂(设置临时文件的大小和位置)
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //0.3创建核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            //0.4解析request
            List<FileItem> list = upload.parseRequest(req);
                //0.5遍历list获取每一个文件项
            for (FileItem fi : list) {
                //0.6获取name属性值  这个是获得是表单域名 如name="username"
                String key = fi.getFieldName();
                //0.7判断是否是普通的上传组件
                if(fi.isFormField()){
                    //普通  getString() 得到普通 表单型的内容
                    map.put(key, fi.getString("utf-8"));
                }else{
                    //文件类型
                    //a.获取文件的名称  g/products/1.jpg
                    String name = fi.getName();

                    //b. 获取文件真实名称  1.jpg
                    String realName = UploadUtils.getRealName(name);

                    //c.获取文件随机名称  真实名称传递过去然后进行截取即可.
                    String uuidName = UploadUtils.getUUIDName(realName);
                    //d.获取随机目录
                    String dir = UploadUtils.getDir();
                    //e.获取文件内容(获取到输入流中)
                    InputStream is = fi.getInputStream();

                    //创建输出流
                    //获取products的真实路径
                    String productsPath = getServletContext().getRealPath("/products");
                    System.out.println(productsPath);
                    //创建随机目录  在productsPath中创建dir的文件夹
                    File dirFile = new File(productsPath, dir);
                    if(!dirFile.exists()){
                        dirFile.mkdirs();
                    }
                    // 创建输出流
                    FileOutputStream os = new FileOutputStream(new File(dirFile, uuidName));

                    //g.对拷流
                    IOUtils.copy(is, os);
                    //h.释放资源
                    os.close();
                    is.close();
                    //i.删除临时文件
                    fi.delete();

                    //j.将商品的路径放入到map  product/a/3/232132.jpg
                    map.put(key,"products"+dir+"/"+uuidName);


                }
            }
            //1.封装product其他属性(添加页面未设置的属性)
            Product p = new Product();
            //1.1手动设置pid
            map.put("pid", UUIDUtils.getId());
            //1.2手动设置pdate
            map.put("pdate", new Date());
            //1.3手动设置pfalg
            map.put("pflag", Constant.PRODUCT_IS_UP);
            //1.4使用beanutils封装
            BeanUtils.populate(p,map);
            //1.5手动设置category
            Category category = new Category();
            category.setCid((String) map.get("cid"));
            p.setCategory(category);
            //调用service保存
            ps.save(p);
            //重定向
            resp.sendRedirect(req.getContextPath()+"/adminProduct/findAll");

        } catch (Exception e) {
            e.printStackTrace();
        }


        
    }

    /**
     * 查找某一个商品通过pid
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        //查询商品
        Product product = ps.findOne(pid);
        req.setAttribute("product",product);
        //查询所有分类,保存到request中
        CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
        List <Category> list = cs.findList();
        req.setAttribute("list",list);
        //转发到显示页面
        req.getRequestDispatcher("/admin/product/edit.jsp").forward(req,resp);
    }

    /**
     * 更新商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //0.使用fileupload保存图片和将图片信息放入map中
            //0.1创建map存入商品的信息
            Map <String, Object> map = new HashMap <>();
            //0.2创建磁盘文件像工厂(设置临时文件的大小和位置)
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //0.3创建核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            //0.4解析request
            List<FileItem> list = upload.parseRequest(req);
            //0.5遍历list获取每一个文件项
            for (FileItem fi : list) {
                //0.6获取name属性值  这个是获得是表单域名 如name="username"
                String key = fi.getFieldName();
                //0.7判断是否是普通的上传组件
                if(fi.isFormField()){
                    //普通  getString() 得到普通 表单型的内容
                    map.put(key, fi.getString("utf-8"));
                }else{
                    //文件类型
                    //a.获取文件的名称  g/products/1.jpg
                    String name = fi.getName();

                    if(name!=""){
                        //b. 获取文件真实名称  1.jpg
                        String realName = UploadUtils.getRealName(name);

                        //c.获取文件随机名称  真实名称传递过去然后进行截取即可.
                        String uuidName = UploadUtils.getUUIDName(realName);
                        //d.获取随机目录
                        String dir = UploadUtils.getDir();
                        //e.获取文件内容(获取到输入流中)
                        InputStream is = fi.getInputStream();

                        //创建输出流
                        //获取products的真实路径
                        String productsPath = getServletContext().getRealPath("/products");
                        System.out.println(productsPath);
                        //创建随机目录  在productsPath中创建dir的文件夹
                        File dirFile = new File(productsPath, dir);
                        if(!dirFile.exists()){
                            dirFile.mkdirs();
                        }
                        // 创建输出流
                        FileOutputStream os = new FileOutputStream(new File(dirFile, uuidName));

                        //g.对拷流
                        IOUtils.copy(is, os);
                        //h.释放资源
                        os.close();
                        is.close();
                        //i.删除临时文件
                        fi.delete();

                        //j.将商品的路径放入到map  product/a/3/232132.jpg
                        map.put(key,"products"+dir+"/"+uuidName);
                    }else{
                        map.put(key,"null");
                    }


                }
            }
            //1.封装product其他属性(添加页面未设置的属性)
            Product p = new Product();
//            //1.1手动设置pid
//            map.put("pid", UUIDUtils.getId());
//            //1.2手动设置pdate
//            map.put("pdate", new Date());
            //1.3手动设置pfalg
           // map.put("pflag", Constant.PRODUCT_IS_UP);
            //1.4使用beanutils封装
            BeanUtils.populate(p,map);
            //1.5手动设置category
            Category category = new Category();
            category.setCid((String) map.get("cid"));
            p.setCategory(category);
            //调用service保存
            ps.updateProduct(p);
            //重定向
            resp.sendRedirect(req.getContextPath()+"/adminProduct/findAll");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        ps.delProduct(pid);
        resp.sendRedirect(req.getContextPath()+"/adminProduct/findAll");

    }
}
