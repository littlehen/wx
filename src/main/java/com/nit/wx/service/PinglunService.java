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

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (Integer.parseInt(user.getCmoney()) >= 100) {
            String[] content = contents.split("，");

            int maxContentId ;
            String contentId = "";
            //评论保存
            if (content.length != 0) {
                for (int i = 0; i < content.length; i++) {
                    Content content1 = new Content();
                    content1.setUserId(user.getUserid());
                    content1.setContent(content[i]);
                    contentDao.save(content1);
                    maxContentId = contentDao.findByContentId();
                    contentId += maxContentId;
                    if (i != content.length - 1)
                        contentId += "，";                   //注意在该程序中用到符号分割是逗号都是中文逗号

                }
            }
            //添加主账号
            Weibo weibo = new Weibo();
            List<Weibo> weibo1 = weiboDao.findByUserId(user.getUserid());
            weibo1.get(0).setIsFinish(0);
            weiboDao.save(weibo1.get(0));

            //添加评论
            Contentkey contentkey = new Contentkey();
            try {
                contentkey.setUserID(user.getUserid());
                contentkey.setZanNumber(Integer.parseInt(ZanNumber));
                contentkey.setContentId(contentId);
                contentkeyDao.save(contentkey);
            } catch (Exception e) {
                System.out.println(e);
            }


            //扣除积分
            user.setCmoney(Integer.parseInt(user.getCmoney()) - 100 + "");
            userListDao.save(user);
        }
        else
            System.out.println("积分不足，请充值！！！");

    }


    public Map<String,Object> baseInfo(String openId){
        Map<String,Object> map = new HashMap<>();
        UserList user = new UserList();
        System.out.println(openId+"=============================");
        user = userListDao.findByOpenid(openId);
        System.out.println(user.getUserid()+"=========================");
        Weibo weibo = weiboDao.findByFuhaoNumberAndUserId(0,user.getUserid());

        if ("".equals(weibo.getUserName())){
            map.put("state",false);
        }
        else {
            map.put("state",true);
            map.put("weibo",weibo);
        }
        return map;
    }



    public Map<String,Object> keyWord(String openId ,String keyword ,String FP , String Zh){
        Map<String,Object> map = new HashMap<>();
        UserList user = userListDao.findByOpenid(openId);
        int max = contentDao.findByContentId();
        if (Integer.parseInt(user.getCmoney()) >= 50){
            Contentkey contentkey = new Contentkey();
            contentkey.setUserID(user.getUserid());
            contentkey.setContentFu(1);
            contentkey.setContentId(max + 1 + "");
            contentkey.setKeyWord(keyword);
            contentkeyDao.save(contentkey);
            Content content = new Content();
            content.setUserId(user.getUserid());
            content.setContent(FP+"$"+Zh);
            contentDao.save(content);

            user.setCmoney(Integer.parseInt(user.getCmoney())-50 +"");
            map.put("YON",true);
        }else
            map.put("YON",false);
        return map;

    }


    public Map<String,Object> searchKeyword(String openid){
        Map<String,Object> map = new HashMap<>();
        UserList user = userListDao.findByOpenid(openid);
        List<Contentkey> contentkeys = contentkeyDao.findlist(user.getUserid());
        List<Contentkey> list = null;
        if (contentkeys.size() != 0){
            for (Contentkey contentkey : contentkeys){
                    Content content = contentDao.findByContentId(Integer.parseInt(contentkey.getContentId()));
                    String[] ls = content.getContent().split("$");
                    contentkey.setContentId(ls[0]);
                    contentkey.setContentFuId(ls[1]);
                    list.add(contentkey);
            }
        }
        if (list!=null)
            map.put("contentList",list);
        return map;
    }
}

