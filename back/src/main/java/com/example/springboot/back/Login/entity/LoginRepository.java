package com.example.springboot.back.Login.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,String> {
    Login findByContentIdAndContentPw(String contentId,String contentPw);
}

