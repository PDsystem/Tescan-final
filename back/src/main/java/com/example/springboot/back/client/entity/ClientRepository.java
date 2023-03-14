package com.example.springboot.back.client.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client, String>  {

    Page<Client> findAllByOrderByCorRegNoDesc(Pageable pageable);

    // Page<Client> findByCorRegNoOrCusNoOrNameContaining(Pageable pageable);
    Page<Client> findByName(Pageable pageable, String searchKeyword);
    //     @Query(value="SELECT ROW_NUMBER() "+
    //                     "OVER(ORDER BY a.REG_ID, a.COR_REG_NO) row_num, a.* "+
    //                     "FROM ts_customer a ORDER BY a.MOD_DATE", nativeQuery = true)
    //     // @Query(value="SELECT * FROM (SELECT ROW_NUMBER() "+
    //     //                 "OVER(ORDER BY a.REG_ID, a.cor_reg_no) row_num, a.* "+
    //     //                 "FROM ts_customer a ORDER BY a.MOD_DATE, a.cor_reg_no)", nativeQuery = true)
    //     Page<Customer> selectByCustomer(Pageable pageable);
    //         // Page<Customer> findAllByOrderByRegnoDesc(Pageable pageable);
}
