package edu.hncst.transactionManagement.entity;

import org.springframework.stereotype.Component;

/**
 * @author hncstXDD
 */
@Component
public class Admin {
    /**
     * 用户名
     * 密码
     */
    private String userName;
    private String password;

    /**
     * 构造 空参 and 有参
     * @param userName
     * @param password
     */
    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Admin(){}

    /**
     * get and set
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
