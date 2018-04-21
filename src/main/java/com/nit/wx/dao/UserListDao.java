package com.nit.wx.dao;

import com.nit.wx.model.UserList;
import com.nit.wx.model.UserListId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserListDao extends JpaRepository<UserList,UserListId> {


    UserList findByOpenid(String openId);
}
