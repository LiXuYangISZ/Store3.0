package cn.rg.dao;

import cn.rg.domain.Order;
import cn.rg.domain.OrderItem;
import cn.rg.domain.PageBean;

import java.util.List;

public interface OrderDao {
    /**
     *向orders表中插入一条数据
     * @param order
     */
    void save(Order order);

    /**
     *向orderitem中插入数据
     * @param oi
     */
    void saveItem(OrderItem oi);

    /**
     * 查询订单总条数  用于分页
     * @param uid
     * @return
     */
    int getTotalRecord(String uid);

    /**
     * 查询当页分页数据
     * @param pb
     * @param uid
     * @return
     */
    List<Order> findMyOrdersByPage(PageBean<Order> pb, String uid);

    /**
     * 获取订单详情
     * @param oid
     * @return
     */
    Order getById(String oid);

    /**
     * 修改订单
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 查询所有订单
     * @param state
     * @return
     */
    List<Order> findAllByState(String state);
}
