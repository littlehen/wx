package com.nit.wx.dao;

import com.nit.wx.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentDao extends JpaRepository<Content,Integer> {


    @Query(nativeQuery = true,value = "SELECT MAX(ContentId) contentID FROM content")
    int findByContentId();
}
