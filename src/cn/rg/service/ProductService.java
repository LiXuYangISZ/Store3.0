package cn.rg.service;

import cn.rg.domain.PageBean;
import cn.rg.domain.Product;

import java.util.List;

public interface ProductService {
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
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @param cid
     * @return
     */
    PageBean<Product> findByPage(int pageNumber, int pageSize, String cid);

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
     * 查找商品通过id
     * @param pid
     * @return
     */
    Product findOne(String pid);

    /**
     * 更新商品信息
     * @param p
     */
    void updateProduct(Product p);

    /**
     * 删除商品
     * @param pid
     */
    void delProduct(String pid);
}
