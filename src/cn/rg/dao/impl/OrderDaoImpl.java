package cn.rg.dao.impl;

import cn.rg.dao.OrderDao;
import cn.rg.domain.Order;
import cn.rg.domain.OrderItem;
import cn.rg.domain.PageBean;
import cn.rg.domain.Product;
import cn.rg.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     *向orders表中插入一条数据
     * @param order
     */
    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        template.update(sql, order.getOid(), order.getOrdertime(), order.getTotal()
                ,order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
    }
    /**
     *向orderitem中插入数据
     * @param oi
     */
    @Override
    public void saveItem(OrderItem oi) {
        String sql ="INSERT INTO orderitem VALUES(?,?,?,?,?)";
        template.update(sql, oi.getItemid(), oi.getCount(), oi.getSubtotal(), oi.getProduct().getPid(), oi.getOrder().getOid());
    }

    /**
     * 查询我的订单的条数,用于分页
     * @param uid
     * @return
     */
    @Override
    public int getTotalRecord(String uid) {
        String sql = "SELECT COUNT(*) FROM orders WHERE uid = ? ";
        return template.queryForObject(sql,Integer.class,uid);
    }

    /**
     * 查询该页的订单数据
     * @param pb
     * @param uid
     * @return
     */
    @Override
    public List <Order> findMyOrdersByPage(PageBean <Order> pb, String uid) {
        //查询 该页所有订单
        String sql = "SELECT * FROM orders WHERE uid = ? ORDER BY ordertime DESC  LIMIT ? ,? ";
        //List <Map <String, Object>> list = template.queryForList(sql, uid, pb.getStartIndex(), pb.getPageSize());
        List <Order> list = template.query(sql, new BeanPropertyRowMapper <Order>(Order.class), uid, pb.getStartIndex(), pb.getPageSize());
        //遍历订单集合,获取每一个订单,查询每个订单的订单项.
        for (Order order : list) {
            sql = "SELECT * FROM orderitem oi,product p WHERE oi.pid = p.pid AND oi.oid = ? ";
            //查询该订单的所有订单项
            List <Map <String, Object>> mapList = template.queryForList(sql, order.getOid());
            //遍历maplist ,获取每一个订单详情,封装成orderitem,并加入当前订单的订单项列表
            for (Map <String, Object> map : mapList) {
                //1.封装成orderitem
                //a创建orderitem对象
                OrderItem oi = new OrderItem();

                //b封装orderitem
                try {
                    BeanUtils.populate(oi,map);
                    //手动封装product
                    Product p = new Product();
                    BeanUtils.populate(p,map);
                    oi.setProduct(p);
                } catch (Exception e) {
                  e.printStackTrace();
                }

                //将orderitem放入order的订单项列表
                order.getItems().add(oi);// order是用list存订单项的,所以是add

            }
        }
        return list;
    }

    /**
     * 获得订单详情
     * @param oid
     * @return
     */
    @Override
    public Order getById(String oid) {
        String sql = "SELECT * FROM orders WHERE oid = ?";
        //查询订单基本信息
        Order order = template.queryForObject(sql, new BeanPropertyRowMapper <Order>(Order.class), oid);
        //查询订单项
        sql = "SELECT * FROM orderitem oi,product p WHERE oi.pid = p.pid AND oi.oid = ? ";
        //查询该订单的所有订单项
        List <Map <String, Object>> mapList = template.queryForList(sql, order.getOid());
        //遍历maplist ,获取每一个订单详情,封装成orderitem,并加入当前订单的订单项列表
        for (Map <String, Object> map : mapList) {
            //1.封装成orderitem
            //a创建orderitem对象
            OrderItem oi = new OrderItem();

            //b封装orderitem
            try {
                BeanUtils.populate(oi,map);
                //手动封装product
                Product p = new Product();
                BeanUtils.populate(p,map);
                oi.setProduct(p);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //将orderitem放入order的订单项列表
            order.getItems().add(oi);// order是用list存订单项的,所以是add

        }
        return order;
    }

    /**
     * 修改订单
     * @param order
     */
    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET state = ? ,address = ? ,NAME = ? ,telephone = ? WHERE oid = ? ";
        template.update(sql, order.getState(), order.getAddress(), order.getName(), order.getTelephone(),  order.getOid());
    }

    /**
     * 查询所有订单
     * @param state
     * @return
     */
    @Override
    public List <Order> findAllByState(String state) {
        String sql = "select * from orders";
        //判断state是否为空
        if(state==null||state.trim().length()==0){
            sql += " order by ordertime desc";
            return template.query(sql,new BeanPropertyRowMapper <Order>(Order.class));
        }
        sql += " where state = ? order by ordertime desc";
        return template.query(sql,new BeanPropertyRowMapper <Order>(Order.class),state);
    }
}
