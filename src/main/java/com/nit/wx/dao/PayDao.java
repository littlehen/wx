package com.nit.wx.dao;

import com.nit.wx.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayDao extends JpaRepository<Pay,Integer> {

    Pay findByUserId(int userId);
}
