package cn.rg.service;

import cn.rg.domain.Order;
import cn.rg.domain.PageBean;

import java.util.List;

public interface OrderService {
    /**
     * 提交订单
     * @param order
     */
    void save(Order order);

    /**
     * 查看我的订单
     * @param pageNumber
     * @param pageSize
     * @param uid
     * @return
     */
    PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid);

    /**
     * 获得订单详情
     * @param oid
     * @return
     */
    Order getById(String oid);

    /**
     * 更新订单
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 查询订单状态
     * @param state
     * @return
     */
    List<Order> findAllByState(String state);
}
