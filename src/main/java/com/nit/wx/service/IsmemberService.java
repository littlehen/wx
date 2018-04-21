package com.nit.wx.service;


import com.nit.wx.dao.IsmemberDao;
import com.nit.wx.model.Ismember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IsmemberService {

    @Autowired
    IsmemberDao ismemberDao;

    public boolean findUserState(String openID){
        Ismember ismember = new Ismember();

        ismember = ismemberDao.findOne(openID);

        if(ismember != null){
            if("会员".equals(ismember.getState()))
                return true;
        }
        return false;
    }
}
