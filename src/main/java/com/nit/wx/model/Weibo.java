package com.nit.wx.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Weibo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer weiboId;

    private Integer userId;

    private String username;

    private String password;

    private Integer isMain;

    @NotNull
    private Integer isFinish;


    @NotNull
    private Integer FUhaoNumber;

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getFUhaoNumber() {
        return FUhaoNumber;
    }

    public void setFUhaoNumber(Integer FUhaoNumber) {
        this.FUhaoNumber = FUhaoNumber;
    }
}