package com.example.springboot.back.customer.entity;

// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
// @Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    // @Query(value = "SELECT * FROM TS_CUSTOMER c where c.cor_reg_no like CON'%'+':keyword+'%' order by c.cor_reg_no" ,nativeQuery = true)
    Page<Customer> findByCorRegNoContaining(Pageable pageable, @Param("keyword")String keyword);

    
}