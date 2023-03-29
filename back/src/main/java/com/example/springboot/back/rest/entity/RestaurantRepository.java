package com.example.springboot.back.rest.entity;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;


@EnableJpaRepositories
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query(value = "SELECT rest_no FROM(SELECT * FROM TS_GASAN_RESTAURANT ORDER BY rest_no DESC) WHERE ROWNUM = 1", nativeQuery = true)
    int selectCountId();
    
    // // @Query(value = "SELECT * FROM TS_CUSTOMER c where c.cor_reg_no like '%'+':keyword+'%' OR customer_name like '%'+':keyword+'%'order by c.cor_reg_no" ,nativeQuery = true)
    // // Page<Customer> findByCorRegNoOrCustomerNameContaining(Pageable pageable, @Param("keyword")String keyword);

    // Page<Customer> findByCorRegNoContaining(Pageable pageable, @Param("keyword")String keyword);

    // Page<Customer> findByCustomerNameContaining(Pageable pageable, @Param("keyword")String keyword);

    
}