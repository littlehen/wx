package com.nit.wx.controller;


import com.nit.wx.service.WeiboService;
import net.sf.json.JSONObject;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeiboController {


    @Autowired
    WeiboService weiboService;

    @RequestMapping("/daleteWeiCount")
    public void daleteWeiCount(String userId,String userName){
        weiboService.daleteWeiCount(userId,userName);
    }


    @RequestMapping("/searchFu")
    public JSONObject searchFu(String pageSize,String offset ,String openid){
        return weiboService.searchFu(pageSize,offset,openid);
    }

    @RequestMapping("/editWeibo")
    public void editWeibo(String weiboId,String userName,String Password,String openId){
        weiboService.editWeibo(weiboId,userName,Password);
    }

    @RequestMapping("/addWeiboFu")
    public void addWeiboFu(String weiboId,String userName,String Password,String openId){

    }
}
