package cn.rg.service.impl;

import cn.rg.constant.Constant;
import cn.rg.dao.CategoryDao;
import cn.rg.domain.Category;
import cn.rg.service.CategoryService;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.JedisUtil;
import cn.rg.utils.JsonUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");

    /**
     * 前台查询所有分类
     * @return
     */
    @Override
    public String findAll() {
        List <Category> list = cd.findAll();
        //将list转换成json字符串
        if(list!=null&&list.size()>0){
            return JsonUtil.list2json(list);
        }
        return null;
    }

    /**
     * 后台查询所有分类
     * @return
     */
    @Override
    public List <Category> findList() {
        return cd.findAll();
    }

    /**
     * 从redis中获取所有分类
     * @return
     */
    //以下方法可以避免当redis为开启时,导航种类无法查出的情况.想比上面那个有了很大的提高.并且调用Dao方法,返回的是一个JSON字符串,从而可以避免直接从jedis中存取数据导致类型问题.
    @Override
    public String findAllFromRedis() {
        Jedis jedis = null;
        String value = null;
        try {
            try {
                //1.获取连接
                jedis= JedisUtil.getJedis();

                //1.2获取数据,判断数据是否为空

                value = jedis.get(Constant.STORE_CATEGORY_LIST);
                if(value!=null){
                    System.out.println("缓存中有数据");
                    return value;
                }
            } catch (Exception e) {

            }
            //2.如果redis中无数据,则从mysql数据库中获取
            value = findAll();
            //3.将value放入redis
            try {
                jedis.set(Constant.STORE_CATEGORY_LIST,value);
                System.out.println("已经将数据放入缓存中");
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JedisUtil.close(jedis);
        }


        return value;
    }

    /**
     * 添加分类
     * @param c
     */
    @Override
    public void save(Category c) {
        //1.调用dao完成添加
        cd.save(c);
        //2.更新redis
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            jedis.del(Constant.STORE_CATEGORY_LIST);
        } finally {
            JedisUtil.close(jedis);
        }
    }

    /**
     * 查找某一个分类
     * @param cid
     * @return
     */
    @Override
    public Category findOne(String cid) {
        Category category = cd.findOne(cid);
        return category;
    }

    /**
     * 编辑某一个分类
     * @param category
     */
    @Override
    public void update(Category category) {
        cd.update(category);
    }
}
