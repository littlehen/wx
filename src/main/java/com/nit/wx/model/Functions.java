package com.nit.wx.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "functions")
public class Functions {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fid;

    @Column(name = "userid")
    private int userId;

    @Column(name = "plzhushou")
    private int plZhushou;

    @Column(name = "bcfpl")
    private int bcfPl;

    private int yp;

    private int jx;

    private int hd;

    private int sy;

    private String yz;
    
    private int isfinish;

    public int getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(int isfinish) {
		this.isfinish = isfinish;
	}

	public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    private Date update;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlZhushou() {
        return plZhushou;
    }

    public void setPlZhushou(int plZhushou) {
        this.plZhushou = plZhushou;
    }

    public int getBcfPl() {
        return bcfPl;
    }

    public void setBcfPl(int bcfPl) {
        this.bcfPl = bcfPl;
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public int getJx() {
        return jx;
    }

    public void setJx(int jx) {
        this.jx = jx;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public int getSy() {
        return sy;
    }

    public void setSy(int sy) {
        this.sy = sy;
    }

    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }
}
