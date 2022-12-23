package cn.rg.service;

import cn.rg.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查找所有的分类
     * @return
     */
   String findAll();

    /**
     * 后台查询所有分类
     * @return
     */
    List<Category> findList();

    /**
     * 从redis中获取所有的分类
     * @return
     */
    String findAllFromRedis();

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
     * 编辑某一个分类
     * @param category
     */
    void update(Category category);
}
