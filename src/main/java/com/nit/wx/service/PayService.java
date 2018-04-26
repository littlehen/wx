package com.nit.wx.service;


import com.nit.wx.dao.PayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class PayService {

    @Autowired
    PayDao payDao;


    public void wxPay(HttpServletRequest request, HttpServletResponse response){

    }
}
