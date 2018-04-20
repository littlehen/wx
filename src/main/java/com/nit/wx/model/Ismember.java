package com.nit.wx.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @Description 判断是否为会员，让c#客户端登录
 * @author 吴佶津
 * @date 2018年4月19日
 */
@Entity
public class Ismember {

	@Id
	private String openid;
	
	private String state; //为1是是会员，允许登录；0不是会员，不允许登录

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
