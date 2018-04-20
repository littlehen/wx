package com.nit.wx.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @Description 第三方信息
 * @author 吴佶津
 * @date 2018年4月16日
 */
@Entity
public class Disanfanginfo {
	/**
	 * component_appid和component_appsecret就是第三方平台的AppID和AppSecret
	 */
	@Id
	private String componentappid;
	
	private String componentappsecret;
	
	private String componentaccesstoken;
	
	private String componentverifyticket;
	
	private String  outtimecactoken;
	
	private String preauthcode;
	
	private String authorizerrefreshtoken;
	
	public String getComponentappid() {
		return componentappid;
	}

	public void setComponentappid(String componentappid) {
		this.componentappid = componentappid;
	}

	private String authorizeraccesstoken;
	
	private String outtimeaactoken;

	public String getComponentappsecret() {
		return componentappsecret;
	}

	public void setComponentappsecret(String componentappsecret) {
		this.componentappsecret = componentappsecret;
	}



	public String getComponentaccesstoken() {
		return componentaccesstoken;
	}

	public void setComponentaccesstoken(String componentaccesstoken) {
		this.componentaccesstoken = componentaccesstoken;
	}



	public String getComponentverifyticket() {
		return componentverifyticket;
	}

	public void setComponentverifyticket(String componentverifyticket) {
		this.componentverifyticket = componentverifyticket;
	}

	public String getOuttimecactoken() {
		return outtimecactoken;
	}

	public void setOuttimecactoken(String outtimecactoken) {
		this.outtimecactoken = outtimecactoken;
	}


	public String getPreauthcode() {
		return preauthcode;
	}

	public void setPreauthcode(String preauthcode) {
		this.preauthcode = preauthcode;
	}

	public String getAuthorizerrefreshtoken() {
		return authorizerrefreshtoken;
	}

	public void setAuthorizerrefreshtoken(String authorizerrefreshtoken) {
		this.authorizerrefreshtoken = authorizerrefreshtoken;
	}

	public String getAuthorizeraccesstoken() {
		return authorizeraccesstoken;
	}

	public void setAuthorizeraccesstoken(String authorizeraccesstoken) {
		this.authorizeraccesstoken = authorizeraccesstoken;
	}

	public String getOuttimeaactoken() {
		return outtimeaactoken;
	}

	public void setOuttimeaactoken(String outtimeaactoken) {
		this.outtimeaactoken = outtimeaactoken;
	}
	

	
}
