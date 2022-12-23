package cn.rg.domain;
/*
            uid` varchar(32) NOT NULL,
		  `username` varchar(20) DEFAULT NULL,
		  `password` varchar(20) DEFAULT NULL,

		  `name` varchar(20) DEFAULT NULL,
		  `email` varchar(30) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,

		  `birthday` date DEFAULT NULL,
		  `sex` varchar(10) DEFAULT NULL,
		  `state` int(11) DEFAULT NULL,

		  `code` varchar(64) DEFAULT NULL,
 */
public class User {
    private String uid;//用户id
    private String username;//用户名
    private String password;//密码
    private String name;//姓名
    private String email;//邮箱
    private String telephone;//手机号
    private String birthday;//生日
    private String sex;//性别
    private Integer state;//状态  如果已经激活则给与1,最初未激活则给与NULL.
    private String code;//激活码

    public User(String uid, String username, String password, String name, String email, String telephone, String birthday, String sex, Integer state, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.sex = sex;
        this.state = state;
        this.code = code;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }
}
