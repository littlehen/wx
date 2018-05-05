package com.nit.wx.controller;

import com.nit.wx.service.PinglunService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class PinglunController {


    @Resource
    PinglunService pinglunService;


    @RequestMapping("/addPinglun")
    public void addPinglun(String openId,String weibocode,String weibopwd,String content,String ZanNumber){
    	pinglunService.addPinglun(openId,weibocode,weibopwd,content,ZanNumber);
    }

    @RequestMapping("/baseInfo")
    public Map<String,Object>  baseInfo(String openId){
        return pinglunService.baseInfo(openId);
    }


    @RequestMapping("/keyWord")
    public Map<String, Object> keyWord(String openId ,String keyword ,String FP , String ZH){
        return pinglunService.keyWord(openId,keyword,FP,ZH);
    }
}
