package cn.rg.service.impl;

import cn.rg.dao.ProductDao;
import cn.rg.domain.PageBean;
import cn.rg.domain.Product;
import cn.rg.service.ProductService;
import cn.rg.utils.BeanFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    //采用IOC思想创建 接口对象
    private ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
    /**
     * 查找最热的商品
     * @return
     */
    @Override
    public List <Product> findHot() {

        return pd.findHot();
    }

    /**
     * 查找最新的商品
     * @return
     */
    @Override
    public List <Product> findNew() {
        return pd.findNew();
    }

    /**
     * 通过pid查询商品详细信息
     * @param pid
     * @return
     */
    @Override
    public Product getById(String pid) {
        return pd.getById(pid);
    }

    @Override
    public PageBean <Product> findByPage(int pageNumber, int pageSize, String cid) {
        //1.创建pagebean
        PageBean <Product> pb = new PageBean <Product>(pageNumber,pageSize);
        //2.设置当前页数据
        List<Product> data = pd.findByPage(pb,cid);
        pb.setData(data);;
        //3.设置总记录数
        int totalRecord = pd.getTotalRecord(cid);
        pb.setTotalRecord(totalRecord);

        return pb;
    }

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List <Product> findAll() {
        return pd.findAll();
    }

    /**
     * 添加商品
     * @param p
     */
    @Override
    public void save(Product p) {
        pd.save(p);
    }

    /**
     * 查找商品通过id
     * @param pid
     * @return
     */
    @Override
    public Product findOne(String pid) {
        return pd.findOneById(pid);
    }

    @Override
    public void updateProduct(Product p) {
        pd.updateProduct(p);
    }

    /**
     * 删除商品
     * @param pid
     */
    @Override
    public void delProduct(String pid) {
        pd.delOne(pid);
    }

}
