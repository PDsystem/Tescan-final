package com.example.springboot.back.Login.entity;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login,String> {
    Login findByContentIdAndContentPw(String contentId,String contentPw);

    @Transactional
    @Modifying
    @Query("update Login m set m.contentId= :#{#paramDeatail.contentNo} where m.contentNo= :#{#paramDeatail.contentNo}")
    public void update(@Param("paramDeatail")Login login);


   
}

