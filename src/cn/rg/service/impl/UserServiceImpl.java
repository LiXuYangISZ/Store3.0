package cn.rg.service.impl;

import cn.rg.constant.Constant;
import cn.rg.dao.UserDao;
import cn.rg.dao.impl.UserDaoImpl;
import cn.rg.domain.User;
import cn.rg.service.UserService;
import cn.rg.utils.BeanFactory;
import cn.rg.utils.MailUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = (UserDao) BeanFactory.getBean("UserDao");
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        System.out.println(user);
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        System.out.println(u);
        if(u!=null){//用户已经存在,注册失败
            return false;
        }
        //2.保存用户信息

        userDao.save(user);
        //3.邮件激活 http://localhost:8080/Store3/user/active?code=
        String content = "恭喜"+user.getName()+":成为我们商城的一员,<a href= 'http://localhost:8080/Store3/user/active?code="+user.getCode()+"'>点击进行激活</a> .";
        MailUtils.sendMail(user.getEmail(),content,"账号激活邮件");
        return true;
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user!=null){
            //2.调用dao的修改激活状态的方法
            user.setState(Constant.USER_IS_ACTIVE);
            user.setCode(null);
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }

    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public List <User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(String uid) {
        return userDao.findOneById(uid);
    }

    /**
     * 修改用户
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }

    /**
     * 删除用户
     * @param uid
     */
    @Override
    public void delUser(String uid) {
        userDao.delUser(uid);
    }
}
