package cn.rg.dao.impl;

import cn.rg.dao.CategoryDao;
import cn.rg.domain.Category;
import cn.rg.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List <Category> findAll() {
        String sql = "SELECT * FROM category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }

    /**
     * 添加分类
     * @param c
     */
    @Override
    public void save(Category c) {
        String sql = "INSERT INTO category VALUES(?,?)";
        template.update(sql,c.getCid(),c.getCname());
    }

    /**
     * 查找某一个分类
     * @param cid
     * @return
     */
    @Override
    public Category findOne(String cid) {
        String sql ="SELECT * FROM category WHERE cid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper <Category>(Category.class),cid);
    }

    /**
     * 更新某一个分类
     * @param category
     */
    @Override
    public void update(Category category) {
        String sql = "UPDATE category SET cname = ? WHERE cid = ? ";
        template.update(sql,category.getCname(),category.getCid());
    }

}
