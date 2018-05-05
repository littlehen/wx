package com.nit.wx.dao;

import com.nit.wx.model.Contentkey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentkeyDao extends JpaRepository<Contentkey,Integer> {


    Contentkey findByUserID(Integer userId);
}
