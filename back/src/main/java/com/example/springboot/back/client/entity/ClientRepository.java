package com.example.springboot.back.client.entity;

// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.JpaRepository;

@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client, ClientKey>  {
    // @Query(value = "SELECT * FROM TS_CUSTOMER c where c.cor_reg_no like '%'+':keyword+'%' OR customer_name like '%'+':keyword+'%'order by c.cor_reg_no" ,nativeQuery = true)

    Page<Client> findByCorRegNoContaining(Pageable pageable, @Param("keyword")String keyword);

    Page<Client> findByNameContaining(Pageable pageable, @Param("keyword")String keyword);

    Page<Client> findByCusNoContaining(Pageable pageable, String searchKeyword);
}
