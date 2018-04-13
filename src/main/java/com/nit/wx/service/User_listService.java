package com.nit.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.wx.dao.User_listDao;

@Service
public class User_listService {

	@Autowired
	User_listDao user_liatDao;
}
