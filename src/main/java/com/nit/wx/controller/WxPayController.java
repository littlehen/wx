package com.nit.wx.controller;


import com.nit.wx.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class WxPayController {

    @Autowired
    PayService payService;


    @RequestMapping("/wxPay")
    public void wxPay(HttpServletRequest request, HttpServletResponse response){
        payService.wxPay(request,response);
    }
}
