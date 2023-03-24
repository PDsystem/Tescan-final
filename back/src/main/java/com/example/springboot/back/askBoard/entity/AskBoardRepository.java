package com.example.springboot.back.askBoard.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AskBoardRepository extends JpaRepository<AskBoard,String>{


    //select
    Page<AskBoard> findByContentNoContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
    Page<AskBoard> findByContentIdContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
    Page<AskBoard> findByContentTitleContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);

    @Query(value = "select * from TS_CONTENT ORDER BY CONTENT_DATE DESC",nativeQuery=true)
    Page<AskBoard> findAllOrderByContentDateDesc(Pageable pageable);
}