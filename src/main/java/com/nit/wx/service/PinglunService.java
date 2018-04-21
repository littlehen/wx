package com.nit.wx.service;


import com.nit.wx.dao.ContentDao;
import com.nit.wx.dao.ContentkeyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinglunService {

    @Autowired
    ContentDao contentDao;

    @Autowired
    ContentkeyDao contentkeyDao;
}
