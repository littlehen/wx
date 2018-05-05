package com.nit.wx.controller;


import com.nit.wx.service.FunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FunctionsController {


    @Autowired
    FunctionsService functionsService;

    @RequestMapping("/Gongneng")
    public Map<String,Object> gongneng(String openId, String plzs, String bcfPl, String yp, String jx, String hd, String sy, String yz){
        return functionsService.pinglunGongneng(openId,plzs,bcfPl,yp,jx,hd,sy,yz);
    }

}
