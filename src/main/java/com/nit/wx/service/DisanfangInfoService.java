package com.nit.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.wx.dao.DisanfangInfoDao;
import com.nit.wx.model.Disanfanginfo;

@Service
public class DisanfangInfoService {
	@Autowired 
	DisanfangInfoDao disanfangInfoDao;
	
	//@SuppressWarnings("null")
	public void savedisanfangInfo(String component_verify_ticket) {
		System.out.println("33333333333");
		Disanfanginfo disanfangInfo = new Disanfanginfo();
		System.out.println("444444444");
		disanfangInfo = disanfangInfoDao.findOne("wx3d6a383a2aa2b1e2");
		System.out.println("555555555555");
		System.out.println(disanfangInfo.getComponentappid());
		
//		if(disanfangInfo == null ) {
//			disanfangInfo.setComponent_appid("wx3d6a383a2aa2b1e2");
//			disanfangInfo.setComponent_appsecret("R3L7ap7lTL37ZVvazV8266gV7L57Ll362F5vQZ67752");
//			disanfangInfo.setComponent_verify_ticket(component_verify_ticket);
//			System.out.println("component_verify_ticket存入成功1");
//			disanfangInfoDao.save(disanfangInfo);
//		}
//		else {
			disanfangInfo.setComponentverifyticket(component_verify_ticket);
			System.out.println("component_verify_ticket存入成功2");
			disanfangInfoDao.save(disanfangInfo);
//		}
		
	}
}
