package com.nit.wx.dao;

import com.nit.wx.model.Weibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeiboDao extends JpaRepository<Weibo,Integer> {

    List<Weibo> findByUserIdAndFuhaoNumber(Integer userId,Integer fuhaoNumber);

    List<Weibo> findByUserId(Integer userId);

    @Query(nativeQuery = true,value = "SELECT * from weibo WHERE FUhaoNumber = ?1 and UserId = ?2")
    Weibo findByFuhaoNumberAndUserId(Integer fuhaoNumber,Integer userId);


    Weibo findByUserIdAndUserName(Integer userId,String userName);


    @Query(nativeQuery = true,value = "DELETE from weibo WHERE weiboId = ?1")
    void deleteByWeiboId(String weiboId);
}
