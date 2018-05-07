package com.nit.wx.dao;

import com.nit.wx.model.Weibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface WeiboDao extends JpaRepository<Weibo,Integer> {

    List<Weibo> findByUserIdAndFuhaoNumber(Integer userId,Integer fuhaoNumber);


    List<Weibo> findByUserId(Integer userId);


    @Query(nativeQuery = true,value = "SELECT * FROM weibo WHERE userid = ?1 AND ismain = ?2")
    List<Weibo> findByUserIdAndFuhaoNumber(Integer userId,int fuhao);

    @Query(nativeQuery = true,value = "SELECT * from weibo WHERE ismain = ?1 and UserId = ?2")
    Weibo findByFuhaoNumberAndUserId(Integer fuhaoNumber,Integer userId);


    Weibo findByUserIdAndUserName(Integer userId,String userName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "DELETE FROM weibo WHERE WeiboId = ?1")
    void abc(Integer weiboId);

}
