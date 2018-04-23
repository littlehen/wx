package com.nit.wx.controller;

import com.nit.wx.service.PinglunService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PinglunController {


    @Resource
    PinglunService pinglunService;


    @RequestMapping("/addPinglun")
    public void addPinglun(String openId,String weibocode,String weibopwd,String content,String ZanNumber){
    	pinglunService.addPinglun(openId,weibocode,weibopwd,content,ZanNumber);
    }
}
