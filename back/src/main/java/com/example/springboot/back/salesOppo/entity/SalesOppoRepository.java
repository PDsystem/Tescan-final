package com.example.springboot.back.salesOppo.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SalesOppoRepository extends JpaRepository<SalesOppo, SalesOppoPK> {
    List<SalesOppo> findByEmpno(String empno);

    Page<SalesOppo> findByEmpnoContaining(String empSearch,Pageable pageable);
    Page<SalesOppo> findByCorRegNoContaining(String corSearch,Pageable pageable);
    // Page<SalesOppo> findByDateContaining(Date dateStart, Date dateEnd,Pageable pageable);
    // Page<SalesOppo> findByEmpCorContaining(String empSearch, String corSearch,Pageable pageable);
}