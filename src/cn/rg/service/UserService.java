package cn.rg.service;

import cn.rg.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     *用户激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 查找一个用户
     * @param uid
     * @return
     */
    User findOne(String uid);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param uid
     */
    void delUser(String uid);
}
