package com.nit.wx.service;


import com.nit.wx.dao.IsmemberDao;
import com.nit.wx.dao.PayDao;
import com.nit.wx.dao.UserListDao;
import com.nit.wx.model.Ismember;
import com.nit.wx.model.Pay;
import com.nit.wx.model.UserList;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class IsmemberService {

    @Autowired
    UserListDao userListDao;

    @Autowired
    PayDao payDao;

    public void findUserState(String openID){
        UserList userList = new UserList();
        userList = userListDao.findByOpenid(openID);
        Date date = new Date();
        Date memberEndtime = userList.getMemberEndtime();
        Pay pay = new Pay();
        pay = payDao.findByUserId(userList.getUserid());


        //当前时间的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        date = calendar.getTime();
        GoEasy goEasy = new GoEasy("BC-4882229bc1044eca9423455b60766994");
        if (date.before(memberEndtime) && pay.getType()==1){
            goEasy.publish("wx_channel","true");
        }else {
            goEasy.publish("wx_channel","false");
        }
    }
/**
 *
 * 客户端
 *<script type="text/javascript" src="http(s)://cdn.goeasy.io/goeasy.js"></script>
 * var goEasy = new GoEasy({
     appkey: 'BC-4882229bc1044eca9423455b60766994'
     });
      goEasy.subscribe({
        channel: 'wx_channel',
        onMessage: function(message){
             alert('收到：'+message.content);
     }
     });



java端
 GoEasy goEasy = new GoEasy("BC-4882229bc1044eca9423455b60766994");
 goEasy.publish("","允许登录");
 */
}
