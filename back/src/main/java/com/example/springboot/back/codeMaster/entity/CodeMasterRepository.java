package com.example.springboot.back.codeMaster.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;

public interface CodeMasterRepository extends JpaRepository<CodeMaster,String>{
    List<CodeMaster> findByClasscode(@Param("classcode") String classcode);
}
