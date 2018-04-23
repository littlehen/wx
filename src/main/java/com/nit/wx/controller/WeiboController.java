package com.nit.wx.controller;


import com.nit.wx.service.WeiboService;
import net.sf.json.JSONObject;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeiboController {


    @Autowired
    WeiboService weiboService;

    @RequestMapping("/deleteWeiCount")
    public void daleteWeiCount(String weiboId,String openId){
        weiboService.daleteWeiCount(weiboId,openId);
    }


    @RequestMapping("/searchFu")
    public Map<String, Object> searchFu(String openid){
        return weiboService.searchFu(openid);
    }

    @RequestMapping("/editWeibo")
    public void editWeibo(String weiboId,String userName,String Password){
        System.out.println(userName+"====="+Password+"==========="+weiboId);
        weiboService.editWeibo(weiboId,userName,Password);
    }
 
    @RequestMapping("/addWeiboFu")
    public void addWeiboFu(String userName,String Password,String openId){
        System.out.println(userName+"====="+Password+"==========="+openId);
        weiboService.addWeiboFu(userName,Password,openId);
    }


}
