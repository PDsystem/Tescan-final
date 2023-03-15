package com.example.springboot.back.customer.entity;

// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
// @Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    Page<Customer> findAllByOrderByCorRegNoDesc(Pageable pageable);

    
}