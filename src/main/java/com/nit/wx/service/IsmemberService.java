package com.nit.wx.service;


import com.nit.wx.dao.IsmemberDao;
import com.nit.wx.dao.UserListDao;
import com.nit.wx.model.Ismember;
import com.nit.wx.model.UserList;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IsmemberService {

    @Autowired
    UserListDao userListDao;

    public void findUserState(String openID){
        UserList userList = new UserList();
        userList = userListDao.findByOpenid(openID);
        Date date = new Date();
        Date memberEndtime = userList.getMemberEndtime();

        if (date.after(memberEndtime)){
            GoEasy goEasy = new GoEasy("BC-4882229bc1044eca9423455b60766994");
            goEasy.publish("wx_channel","true");
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
        channel: 'demo_channel',
        onMessage: function(message){
     alert('收到：'+message.content);
     }
     });



java端
 GoEasy goEasy = new GoEasy("BC-4882229bc1044eca9423455b60766994");
 goEasy.publish("","允许登录");
 */
}
