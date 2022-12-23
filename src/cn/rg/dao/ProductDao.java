package cn.rg.dao;

import cn.rg.domain.PageBean;
import cn.rg.domain.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 查找最热的商品
     * @return
     */
    List<Product> findHot();

    /**
     * 查找最新的商品
     * @return
     */
    List<Product> findNew();
    /**
     * 通过pid查询商品详细信息
     * @param pid
     * @return
     */

    Product getById(String pid);

    /**
     * 通过pb,cid查询该页的数据.
     * 分页查询
     * @param pb
     * @param cid
     * @return
     */
    List<Product> findByPage(PageBean<Product> pb, String cid);

    /**
     * 根据cid获取总记录数
     * @param cid
     * @return
     */
    int getTotalRecord(String cid);

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    /**
     * 添加商品
     * @param p
     */
    void save(Product p);

    /**
     * 查找商品通过pid
     * @param pid
     * @return
     */
    Product findOneById(String pid);

    /**
     * 更新商品
     * @param p
     */
    void updateProduct(Product p);

    /**
     * 删除商品
     * @param pid
     */
    void delOne(String pid);
}
