package com.example.springboot.back.askBoard.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AskBoardRepository extends JpaRepository<AskBoard,String>{
    // List<AskBoard> findByContentNo(String contentNo);

    Page<AskBoard> findByContentNoContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
    Page<AskBoard> findByContentIdContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
    Page<AskBoard> findByContentsContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
}