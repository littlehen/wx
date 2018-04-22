package com.nit.wx.model;


import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "weibo")
public class Weibo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Weiboid")
    private int weiboId;

    @Column(name = "Userid")
    private Integer userId;

    @Column(name = "Username")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Ismain")
    private int isMain;

    @Column(name = "Isfinish")
    private int isFinish;

    @Column(name = "Fuhaonumber")
    private int fuhaoNumber;

    public int getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(int weiboId) {
        this.weiboId = weiboId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public int getFuhaoNumber() {
        return fuhaoNumber;
    }

    public void setFuhaoNumber(int fuhaoNumber) {
        this.fuhaoNumber = fuhaoNumber;
    }
}
