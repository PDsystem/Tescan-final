package com.example.springboot.back.askBoard.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AskBoardRepository extends JpaRepository<AskBoard,String>{
    // List<AskBoard> findByContentNo(String contentNo);

    // @Query(value = "select * from TS_CONTENT c where c.contentNo= :contentNo" ,nativeQuery = true)
    // List<AskBoard> findspl(Pageable pageable,@Param(value="contentNo") String contentNo);

    @Query(value = "select * from TS_CONTENT where content_no like %:searchKeyword%" ,nativeQuery = true)
    Page<AskBoard> findByContentNoContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);

    // @Query(value = "select * from TS_CONTENT c where c.contentId like %:searchKeyword%" ,nativeQuery = true)
    Page<AskBoard> findByContentIdContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);

    // @Query(value = "select * from TS_CONTENT c where c.contents like %:searchKeyword%" ,nativeQuery = true)
    Page<AskBoard> findByContentsContaining(@Param(value="searchKeyword") String searchKeyword, Pageable pageable);
}