package com.nit.wx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nit.wx.service.User_listService;

@RestController
public class User_listController {

	@Autowired
	User_listService user_listService;
}
