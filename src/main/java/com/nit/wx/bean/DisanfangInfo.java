package com.nit.wx.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @Description 第三方信息
 * @author 吴佶津
 * @date 2018年4月16日
 */
@Entity
public class DisanfangInfo {
	/**
	 * component_appid和component_appsecret就是第三方平台的AppID和AppSecret
	 */
	@Id
	private String component_appid;
	
	private String component_appsecret;
	
	private String component_access_token;
	
	private String component_verify_ticket;
	
	private String  outtime_cactoken;
	

	public String getOuttime_cactoken() {
		return outtime_cactoken;
	}

	public void setOuttime_cactoken(String outtime_cactoken) {
		this.outtime_cactoken = outtime_cactoken;
	}

	public String getComponent_verify_ticket() {
		return component_verify_ticket;
	}

	public void setComponent_verify_ticket(String component_verify_ticket) {
		this.component_verify_ticket = component_verify_ticket;
	}

	public String getComponent_appid() {
		return component_appid;
	}

	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}

	public String getComponent_appsecret() {
		return component_appsecret;
	}

	public void setComponent_appsecret(String component_appsecret) {
		this.component_appsecret = component_appsecret;
	}

	public String getComponent_access_token() {
		return component_access_token;
	}

	public void setComponent_access_token(String component_access_token) {
		this.component_access_token = component_access_token;
	}
	
	

}
