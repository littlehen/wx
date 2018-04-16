package com.nit.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.wx.bean.DisanfangInfo;
import com.nit.wx.dao.DisanfangInfoDao;

@Service
public class DisanfangInfoService {
	@Autowired 
	DisanfangInfoDao disanfangInfoDao;
	
	public void savedisanfangInfo(String component_verify_ticket) {
		DisanfangInfo disanfangInfo = new DisanfangInfo();
		disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
		if(disanfangInfo == null ) {
			disanfangInfo.setComponent_appid("wx3d6a383a2aa2b1e2");
			disanfangInfo.setComponent_appsecret("R3L7ap7lTL37ZVvazV8266gV7L57Ll362F5vQZ67752");
			disanfangInfo.setComponent_verify_ticket(component_verify_ticket);
			System.out.println("component_verify_ticket存入成功1");
			disanfangInfoDao.save(disanfangInfo);
		}
		else {
			disanfangInfo.setComponent_verify_ticket(component_verify_ticket);
			System.out.println("component_verify_ticket存入成功2");
			disanfangInfoDao.save(disanfangInfo);
		}
		
	}
}
