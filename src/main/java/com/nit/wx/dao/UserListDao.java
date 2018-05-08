package com.nit.wx.dao;

import com.nit.wx.model.UserList;
import com.nit.wx.model.UserListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserListDao extends JpaRepository<UserList,UserListId> {


    UserList findByOpenid(String openId);


    @Query(nativeQuery = true,value = "SELECT MAX(UserId) FROM user_list")
    int findMax();
}
