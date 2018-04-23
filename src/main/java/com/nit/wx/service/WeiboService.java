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
import java.util.*;

@Service
public class WeiboService {

    @Autowired
    WeiboDao weiboDao;

    @Autowired
    UserListDao userListDao;


    //删除辅助号
    public void daleteWeiCount(String weiboId,String openId){
        List<Weibo> weibos = new ArrayList<>();
        UserList user = userListDao.findByOpenid(openId);
        weibos = weiboDao.findByUserId(user.getUserid());
        for (int i = 0; i < weibos.size();i++){
            Weibo weibo = weibos.get(i);
            if (weibo.getFuhaoNumber() != 0){
                System.out.println(weibo.getFuhaoNumber()+"=========================");
                weibo.setFuhaoNumber( weibo.getFuhaoNumber() - 1);
                System.out.println(weibo.getFuhaoNumber()+"=========================");
                weiboDao.save(weibo);
            }
        }
        weiboDao.deleteByWeiboId(weiboId);
    }

    //编辑辅助号
    public void editWeibo(String weiboId,String userName,String Password){
        Weibo weibo = weiboDao.findOne(Integer.parseInt(weiboId));
        weibo.setUserName(userName);
        if (!"%11111111".equals(Password))
            weibo.setPassword(Password);
        weiboDao.save(weibo);
    }


    public void addWeiboFu(String userName,String Password,String openId){
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


    //辅助号列表
    public Map<String,Object> searchFu(String openId){
        Map<String,Object> map = new HashMap<>();
//        UserList user = userListDao.findByOpenid(openId);
//        List<Weibo> weibos = weiboDao.findByUserId(user.getUserid());
        List<Weibo> weiboList = weiboDao.findAll();
        map.put("number",weiboList.size());
        map.put("weiboList",weiboList);
        return map;
    }
}
