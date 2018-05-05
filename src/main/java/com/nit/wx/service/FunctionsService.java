package com.nit.wx.service;


import com.nit.wx.dao.FunctionsDao;
import com.nit.wx.dao.PayDao;
import com.nit.wx.dao.UserListDao;
import com.nit.wx.model.Functions;
import com.nit.wx.model.Pay;
import com.nit.wx.model.UserList;
import com.nit.wx.util.CVTicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicScrollPaneUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FunctionsService {

    @Autowired
    FunctionsDao functionsDao;

    @Autowired
    UserListDao userDao;


    @Autowired
    PayDao payDao;


    public Map<String,Object> pinglunGongneng(String openId,String plzs,String bcfPl,String yp,String jx,String hd,String sy ,String yz){
    	System.out.println(openId+"123");
        Map<String,Object> map = new HashMap<>();
        UserList user = new UserList();
        user = userDao.findByOpenid(openId);
        System.out.println(user.getUserid());
        Functions functions = new Functions();
        Date date = new Date();
        Pay pay = new Pay();
        pay = payDao.findByUserId(user.getUserid());
        int money = Integer.parseInt(pay.getMoney());
        if (money >= 50) {
            functions.setUserId(user.getUserid());
            functions.setBcfPl(Integer.parseInt(bcfPl));
            functions.setPlZhushou(Integer.parseInt(plzs));
            functions.setHd(Integer.parseInt(hd));
            functions.setJx(Integer.parseInt(jx));
            functions.setSy(Integer.parseInt(sy));
            functions.setUpdate(date);
            functions.setYp(Integer.parseInt(yp));
            functions.setYz(yz);
            functionsDao.save(functions);
            money -= money;
            pay.setMoney(money+"");
            payDao.save(pay);
            map.put("state",true);
        }
        else
            map.put("state",false);
        return map;
    }


    public void payInfo(HttpServletRequest request , HttpServletResponse response) throws Exception{
        StringBuffer sb = new StringBuffer();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null)
            sb.append(line);
        String xml = sb.toString();
        Map<String,String> map = CVTicketUtil.readStringXmlOut(xml);
        if ("SUCCESS".equals(map.get("return_code"))){
            String openId = map.get("openid");
            UserList user = userDao.findByOpenid(openId);
            String WechatPayCode = map.get("out_trade_no");
            String time = map.get("time_end");
            String money = map.get("cash_fee");
            Pay pay = new Pay();
            pay.setMoney(money);
            pay.setTime(time);
            pay.setUserId(user.getUserid());
            pay.setWechatPayCode(WechatPayCode);
            payDao.save(pay);
            PrintWriter pw = response.getWriter();
            pw.write("success");
            pw.flush();
        }
    }
}
