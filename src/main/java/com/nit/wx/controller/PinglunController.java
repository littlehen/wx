package com.nit.wx.controller;

import com.nit.wx.service.PinglunService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PinglunController {


    @Resource
    PinglunService pinglunService;
}
