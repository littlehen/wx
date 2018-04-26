package com.nit.wx.service;


import com.nit.wx.dao.ContentDao;
import com.nit.wx.dao.ContentkeyDao;
import com.nit.wx.dao.UserListDao;
import com.nit.wx.dao.WeiboDao;
import com.nit.wx.model.Content;
import com.nit.wx.model.Contentkey;
import com.nit.wx.model.UserList;
import com.nit.wx.model.Weibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinglunService {

    @Autowired
    ContentDao contentDao;

    @Autowired
    ContentkeyDao contentkeyDao;

    @Autowired
    UserListDao userListDao;

    @Autowired
    WeiboDao weiboDao;


    public void addPinglun(String openId,String weibocode,String weibopwd,String contents,String ZanNumber){
        UserList user = userListDao.findByOpenid(openId);
        String[] content = contents.split("，");

        int maxContentId = contentDao.findByContentId();
        String contentId = "";
        //评论保存
        if (content.length!=0) {
            for (int i = 0; i < content.length;i++) {
                Content content1 = new Content();
                content1.setUserId(user.getUserid());
                content1.setContent(content[i]);
                maxContentId++;
                contentId += maxContentId ;
                if (i != content.length-1)
                    contentId += "，";                   //注意在该程序中用到符号分割是逗号都是中文逗号
                contentDao.save(content1);
            }
        }
        //添加主账号
        Weibo weibo = new Weibo();
        weibo.setUserName(weibocode);
        weibo.setPassword(weibopwd);
        weibo.setUserId(user.getUserid());
        weibo.setIsMain(1);
        weiboDao.save(weibo);

        //添加评论
        Contentkey contentkey =new Contentkey();
        contentkey.setUserID(user.getUserid());
        contentkey.setZanNumber(Integer.parseInt(ZanNumber));
        contentkey.setContentId(contentId);
        contentkeyDao.save(contentkey);
    }
}

