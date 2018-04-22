package com.nit.wx.service;


import com.nit.wx.dao.UserListDao;
import com.nit.wx.dao.WeiboDao;
import com.nit.wx.model.UserList;
import com.nit.wx.model.Weibo;
import net.sf.json.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeiboService {

    @Autowired
    WeiboDao weiboDao;

    @Autowired
    UserListDao userListDao;


    //删除辅助号
    public void daleteWeiCount(String userId,String userName){
        List<Weibo> weibos = new ArrayList<>();
        weibos = weiboDao.findByUserId(Integer.parseInt(userId));
        for (int i = 0; i < weibos.size();i++){
            Weibo weibo = weibos.get(i);
            if (weibo.getFuhaoNumber() != 0){
                System.out.println(weibo.getFuhaoNumber()+"=========================");
                weibo.setFuhaoNumber( weibo.getFuhaoNumber() - 1);
                System.out.println(weibo.getFuhaoNumber()+"=========================");
                weiboDao.save(weibo);
            }
            if (userName.equals(weibo.getUserName())){
                System.out.println(weibo.getUserName() + "==================");
                weiboDao.delete(weibo);
                System.out.println(weibo.getUserName() + "==================");
            }
        }

    }

    //辅助号列表
    public JSONObject searchFu(String pageSize, String offset , String openid){
    	JSONObject jsonObject = new JSONObject();
        UserList user = userListDao.findByOpenid(openid);
        List<Weibo> weiboList = weiboDao.findByUserIdAndFuhaoNumber(user.getUserid(),0);
	    List<Weibo> li = new LinkedList<Weibo>();
	    int off = Integer.parseInt(offset);
        int pagesi = Integer.parseInt(pageSize);
	    if(off*pagesi >= weiboList.size()) {
	    	if(weiboList.size()-pagesi*(off-1) > 0) {
		    	 for(int i = 0; i < weiboList.size()-pagesi*(off-1); i++) {
				    	li.add(weiboList.get(pagesi*(off-1)+i));
				    }
	    	}else {
	    		for(int i = 0; i < weiboList.size(); i++) {
			    	li.add(weiboList.get(pagesi*(off-1)+i));
			    }
	    	}
	    }else {
	    	for(int i = 0; i < pagesi; i++) {
		    	li.add(weiboList.get(pagesi*(off-1)+i));
		    }
			    
	    }
	    jsonObject.put("rows",li); //内容
	    jsonObject.put("total",weiboList.size());
        return jsonObject;
    }

    //编辑辅助号
    public void editWeibo(String weiboId,String userName,String Password){
        Weibo weibo = weiboDao.findOne(Integer.parseInt(weiboId));
        weibo.setUserName(userName);
        weibo.setPassword(Password);
        weiboDao.save(weibo);
    }


    public void addWeiboFu(String weiboId,String userName,String Password,String openId){
        UserList user = userListDao.findByOpenid(openId);
        int userId = user.getUserid();
        Weibo weibo = new Weibo();
        weibo.setPassword(Password);
        weibo.setUserName(userName);
        weibo.setUserId(userId);
        weiboDao.save(weibo);
        weibo = weiboDao.findByFuhaoNumberAndUserId(0,userId);
        System.out.println(weibo.getFuhaoNumber()+"======================================");
        weibo.setFuhaoNumber(weibo.getFuhaoNumber() - 1);
        System.out.println(weibo.getFuhaoNumber()+"======================================");
        weiboDao.save(weibo);
    }
}
