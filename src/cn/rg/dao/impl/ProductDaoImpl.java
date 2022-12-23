package cn.rg.dao.impl;

import cn.rg.constant.Constant;
import cn.rg.dao.ProductDao;
import cn.rg.domain.PageBean;
import cn.rg.domain.Product;
import cn.rg.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List <Product> findHot() {
        String sql = "SELECT * FROM product WHERE is_hot = ? AND pflag = ? ORDER BY pdate DESC LIMIT 9 ";
        return template.query(sql,new BeanPropertyRowMapper <Product>(Product.class), Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
    }

    @Override
    public List <Product> findNew() {
        String sql = "SELECT * FROM product WHERE  pflag = ? ORDER BY pdate DESC LIMIT 9 ";
        return template.query(sql,new BeanPropertyRowMapper <Product>(Product.class),Constant.PRODUCT_IS_UP);
    }
    /**
     * 通过pid查询商品详细信息
     * @param pid
     * @return
     */
    @Override
    public Product getById(String pid) {
        String sql = "select * from product where pid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper <Product>(Product.class),pid);
    }

    /**
     * 分页查询
     * @param pb
     * @param cid
     * @return
     */
    @Override
    public List <Product> findByPage(PageBean <Product> pb, String cid) {
        String sql = "select * from product where cid = ? and pflag = ? order by pdate desc limit ?,? ";
        return template.query(sql,new BeanPropertyRowMapper <Product>(Product.class),cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
    }

    /**
     *
     * @param cid
     * @return
     */
    @Override
    public int getTotalRecord(String cid) {
        String sql = "select count(*) from product where cid = ?";
        int total = 0;
        try {
            total = template.queryForObject(sql,Integer.class,cid);
        } catch (DataAccessException e) {

        }

        return total;
    }

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List <Product> findAll() {
        String sql = "SELECT * FROM product ";
        return template.query(sql,new BeanPropertyRowMapper <Product>(Product.class));
    }

    /**
     * 添加商品
     * @param p
     */
    @Override
    public void save(Product p) {
        String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(p.getPimage());
        template.update(sql, p.getPid(), p.getPname(), p.getMarket_price(), p.getShop_price(), p.getPimage(), p.getPdate(), p.getIs_hot(), p.getPdesc(), p.getPflag(), p.getCategory().getCid());
    }

    /**
     * 查找商品
     * @param pid
     * @return
     */
    @Override
    public Product findOneById(String pid) {
        String sql = "select * from product where pid = ? ";
        Product product = null;
        try {
            product = template.queryForObject(sql,new BeanPropertyRowMapper <Product>(Product.class),pid);
        } catch (DataAccessException e) {

        }
        return product;
    }

    /**
     * 更新商品
     * @param p
     */
    @Override
    public void updateProduct(Product p) {
        if(p.getPimage()=="null"){
            String sql = "UPDATE product SET pname = ?,market_price = ?,shop_price= ?,is_hot = ?,pdesc = ?,cid = ? WHERE pid = ?";
            template.update(sql, p.getPname(), p.getMarket_price(), p.getShop_price(), p.getIs_hot(), p.getPdesc(), p.getCategory().getCid(), p.getPid());
        }else{
            String sql = "UPDATE product SET pname = ?,market_price = ?,shop_price= ?,pimage = ?,is_hot = ?,pdesc = ?,cid = ? WHERE pid = ?";
            template.update(sql, p.getPname(), p.getMarket_price(), p.getShop_price(), p.getPimage(), p.getIs_hot(), p.getPdesc(), p.getCategory().getCid(), p.getPid());
        }

    }

    /**
     * 删除商品
     * @param pid
     */
    @Override
    public void delOne(String pid) {
        String sql = "UPDATE product SET pflag = ? WHERE pid = ? ";
        template.update(sql,Constant.PRODUCT_IS_HOT,pid);
    }
}
