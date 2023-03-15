package com.example.springboot.back.sales.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, String> { 
    Page<Sales> findByEmpnoContaining(String searchKeyword,Pageable pageable);
}