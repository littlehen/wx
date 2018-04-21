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
    public void addPinglun(String openId,String content,String ZanNumber,String keyWord,String contentFuId){

    }
}
