package com.lele.springbootstudy.entity;

import java.io.Serializable;
import java.util.Date;

public class UserTab implements Serializable {
    /** 主键id */
    private Integer id;

    /** 用户名称 */
    private String userName;

    /** 年龄 */
    private Integer age;

    /** 创建时间 */
    private Date createAt;

    /** 修改时间 */
    private Date modifyAt;

    /** 密码 */
    private String password;

    /** 用户唯一id */
    private String userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}