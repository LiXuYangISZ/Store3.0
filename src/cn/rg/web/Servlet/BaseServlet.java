package cn.rg.web.Servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取方法的分发
        //1.获取方法的名称
        String uri = req.getRequestURI();
        System.out.println("请求的uri:"+uri);

        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf("/")+1);
        System.out.println("方法名称"+methodName);
        System.out.println(this);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //由于最后Servlet处理完要么是转发,药模式进行重定向,而转发的次数较多,所以我们可以对其进行抽取.
            //String path = (String) method.invoke(this, req, resp);
            // 判断返回值是否为空,如果不为空则统一进行请求转发.
//            if(path!=null){
//                req.getRequestDispatcher(path).forward(req,resp);
//            }
            method.invoke(this,req,resp);//执行该方法.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
    //因为在Servlet中经常使用到序列化,所有我们可以对  这些重复写的序列化代码 这两个方法进行抽取.

    /**
     * 直接将传入的对象序列化为JSON,并写会客户端.
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为JSON字符串,并返回
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
