package cn.rg.dao.impl;

import cn.rg.dao.UserDao;
import cn.rg.domain.User;
import cn.rg.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        System.out.println("用户名:"+username);
        User user = null;
        String sql = "SELECT * FROM USER WHERE username = ? ";
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper <User>(User.class), username);
        } catch (Exception e) {
            e.printStackTrace();
            //e.printStackTrace();
        }
        return user;
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
                user.getSex(),user.getState(),user.getCode());
    }

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        String sql = "SELECT * FROM USER WHERE CODE = ? ";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper <User>(User.class), code);
        } catch (DataAccessException e) {

        }
        return user;
    }

    /**
     * 修改用户账号激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = "UPDATE USER SET state = ? WHERE uid = ? ";
        template.update(sql,user.getState(),user.getUid());
    }
    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM USER WHERE username = ? AND PASSWORD = ? ";
        User user =null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper <User>(User.class), username, password);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public List <User> findAll() {
        String sql = "select * from user";
        return template.query(sql,new BeanPropertyRowMapper <User>(User.class));
    }

    @Override
    public User findOneById(String uid) {
        String sql = "select * from user where uid = ?";
        User user = null;
        try {
            user = template.queryForObject(sql,new BeanPropertyRowMapper <User>(User.class),uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE USER SET username = ? ,PASSWORD = ?,NAME = ?,email = ?,telephone = ?,birthday = ? WHERE uid = ? ";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getUid());
    }

    /**
     * 删除用户
     * @param uid
     */
    @Override
    public void delUser(String uid) {
        String sql = "DELETE FROM USER WHERE uid = ? ";
        template.update(sql,uid);
    }
}
