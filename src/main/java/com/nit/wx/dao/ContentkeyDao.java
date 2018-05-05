package com.nit.wx.dao;

import com.nit.wx.model.Contentkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentkeyDao extends JpaRepository<Contentkey,Integer> {


    Contentkey findByUserID(Integer userId);


    @Query(nativeQuery = true ,value = "SELECT * from contentkey WHERE userid = ?1 and keyword is NOT NULL and contentFu = 1 and isFinish = 0")
    List<Contentkey> findlist(Integer userId);
}
