package cn.rg.service.impl;

import cn.rg.dao.OrderDao;
import cn.rg.domain.Order;
import cn.rg.domain.OrderItem;
import cn.rg.domain.PageBean;
import cn.rg.service.OrderService;
import cn.rg.utils.BeanFactory;

import cn.rg.utils.JDBCUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
    /**
     * 提交订单
     * @param order
     */
    @Override
    public void save(Order order) {
        try {
            //1.开启事务
            JDBCUtils.startTransaction();

            //2.向orders中插入一条数据
            od.save(order);
            //3.向orderitem中插入n条数据
            for (OrderItem oi : order.getItems()) {
                od.saveItem(oi);
            }
            //4.事务提交
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
        //出现错误进行回滚.
            try {
                JDBCUtils.rollbackAndClose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 查看我的订单
     * @param pageNumber
     * @param pageSize
     * @param uid
     * @return
     */
    @Override
    public PageBean <Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) {
        //1.创建pageBean
        PageBean <Order> pb = new PageBean <Order>(pageNumber,pageSize);
        //2.查询总条数 设置
        int totalRecord = od.getTotalRecord(uid);
        pb.setTotalRecord(totalRecord);

        //3.查询当前页数据,设置
        List <Order> data = od.findMyOrdersByPage(pb,uid);
        pb.setData(data);
        return pb;
    }

    @Override
    public Order getById(String oid) {
        Order order = od.getById(oid);
        return order;
    }

    /**
     * 更新订单
     * @param order
     */
    @Override
    public void updateOrder(Order order) {
        od.updateOrder(order);
    }

    /**
     * 查询所有订单
     * @param state
     * @return
     */
    @Override
    public List <Order> findAllByState(String state) {
        return od.findAllByState(state);
    }
}
