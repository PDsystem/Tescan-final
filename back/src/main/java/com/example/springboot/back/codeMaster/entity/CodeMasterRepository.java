package com.example.springboot.back.codeMaster.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeMasterRepository extends JpaRepository<CodeMaster,String>{
    
    List<CodeMaster> findByClassCode(String classCode);

    Page<CodeMaster> findByClassNameContaining(String searchKeyword, Pageable pageable);

    @Query(value = "select * from TS_CODE_MASTER c where c.class_code= :classCode" ,nativeQuery = true)
    List<CodeMaster> findspl(Pageable pageable,@Param(value="classCode") String classCode);

    @Query(value = "select * from TS_CODE_MASTER c where c.class_code like %:searchKeyword% or c.class_name like %:searchKeyword% ORDER BY REGIST_TIME DESC" ,nativeQuery = true)
    Page<CodeMaster> findTotal(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);

    @Query(value = "select * from TS_CODE_MASTER c where c.class_code like %:searchKeyword%" ,nativeQuery = true)
    Page<CodeMaster> findCode(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);

    @Query(value = "select * from TS_CODE_MASTER c where c.class_name like %:searchKeyword% ORDER BY REGIST_TIME DESC" ,nativeQuery = true)
	Page<CodeMaster> findName(Pageable pageable,@Param(value="searchKeyword") String searchKeyword);
    
    @Query(value = "select to_char( max(to_number(d.CLASS_CODE))+1) from TS_CODE_MASTER d " ,nativeQuery = true)
     String codeMax();

    @Transactional
    @Modifying
    @Query("update Login m set m.contentId= :#{#paramDeatail.contentNo} where m.contentNo= :#{#paramDeatail.contentNo}")
    public void update(@Param("paramDeatail")CodeMaster codeMaster);
}
