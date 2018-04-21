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


    public void addPinglun(String openId,String content,String ZanNumber,String keyWord,String contentFuId){
        UserList userList = userListDao.findByOpenid(openId);
        int userId = userList.getUserid();
        String[] text = content.split("，");
        int MaxId = contentDao.findByContentId();
        System.out.println(MaxId+"====================================");
        String contentId = "";
        for (int i = 0 ; i < text.length;i++){
            Content content1 = new Content();
            content1.setUserId(userId);
            content1.setContent(text[i]);
            MaxId++;
            contentId = MaxId + ",";
            contentDao.save(content1);
        }
        String contentFuIds = "";
        if("".equals(contentFuId) ){
            String[] weiboList = contentFuId.split("，");
            for(int i = 0 ; i < weiboList.length;i++) {
                Weibo weibo = new Weibo();
                weibo = weiboDao.findByUserIdAndUserName(userId,weiboList[i]);
                contentFuIds = weibo.getWeiboId()+"，";
            }
        }
        Contentkey contentkey = new Contentkey();
        contentkey.setUserID(userId);
        contentkey.setContentId(contentId);
        contentkey.setZanNumber(Integer.parseInt(ZanNumber));
        if(!"".equals(keyWord))
            contentkey.setKeyWord(keyWord);
        if (!"".equals(contentFuIds)) {
            contentkey.setContentFuId(contentFuIds);
            contentkey.setContentFu(1);
        }
        contentkeyDao.save(contentkey);

    }
}
