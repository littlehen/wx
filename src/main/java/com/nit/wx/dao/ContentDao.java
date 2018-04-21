package com.nit.wx.dao;

import com.nit.wx.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentDao extends JpaRepository<Content,Integer> {
}
