package com.nit.wx.model;


import javax.persistence.*;

@Entity
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Userid")
    private Integer userId;

    @Column(name = "Localcode")
    private String localCode;

    @Column(name = "Wechatpaycode")
    private String wechatPayCode;

    private String money;

    private int type;

    private String Body;

    private String Time;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getWechatPayCode() {
        return wechatPayCode;
    }

    public void setWechatPayCode(String wechatPayCode) {
        this.wechatPayCode = wechatPayCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
