package com.nit.wx.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_list")
@IdClass(UserListId.class)
public class UserList  implements Serializable{

    @Column(name = "UserId")
    private Integer userid;

    private String openid;

    private String cname;

    private int newMember;

    private int member;

    private Date memberEndtime;

    private Date resightTime;

    private String cmoney;

    @Column(name = "cleankey")
    private String cleanKey;

    private Date cleanStarttime;

    private Date cleanEndtime;

    public Integer getUserid() {
        return userid;
    }

    @Id
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    @Id
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getNewMember() {
        return newMember;
    }

    public void setNewMember(int newMember) {
        this.newMember = newMember;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public Date getMemberEndtime() {
        return memberEndtime;
    }

    public void setMemberEndtime(Date memberEndtime) {
        this.memberEndtime = memberEndtime;
    }

    public Date getResightTime() {
        return resightTime;
    }

    public void setResightTime(Date resightTime) {
        this.resightTime = resightTime;
    }

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
    }

    public String getCleanKey() {
        return cleanKey;
    }

    public void setCleanKey(String cleanKey) {
        this.cleanKey = cleanKey;
    }

    public Date getCleanStarttime() {
        return cleanStarttime;
    }

    public void setCleanStarttime(Date cleanStarttime) {
        this.cleanStarttime = cleanStarttime;
    }

    public Date getCleanEndtime() {
        return cleanEndtime;
    }

    public void setCleanEndtime(Date cleanEndtime) {
        this.cleanEndtime = cleanEndtime;
    }
}
