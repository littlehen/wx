package com.nit.wx.dao;

import com.nit.wx.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserListDao extends JpaRepository<UserList,Integer> {


    UserList findByOpenid(String openId);


    @Query(nativeQuery = true,value = "SELECT MAX(UserId) FROM user_list")
    int findMax();
}
