package cn.rg.dao;

import cn.rg.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 修改用户激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     *通过uid查询用户
     * @return
     * @param uid
     */
    User findOneById(String uid);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param uid
     */
    void delUser(String uid);
}
