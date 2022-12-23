package cn.rg.dao;

import cn.rg.domain.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {
    /**
     * 查询所有分类
     * @return
     */
    List <Category> findAll();

    /**
     * 添加分类
     * @param c
     */
    void save(Category c);

    /**
     * 查找某一个分类
     * @param cid
     * @return
     */
    Category findOne(String cid);

    /**
     * 更新某一个分类
     * @param category
     */
    void update(Category category);
}
