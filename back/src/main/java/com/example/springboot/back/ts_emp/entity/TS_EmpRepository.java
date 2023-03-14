package com.example.springboot.back.ts_emp.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TS_EmpRepository extends JpaRepository<TS_Emp, String> {

  /* NullPointerException 해결 못하면 죽도 밥도 안됨 */
  // 얘는 된다
  @Query(value = "SELECT emp_no FROM(SELECT * FROM ts_emp ORDER BY emp_no DESC) WHERE ROWNUM = 1", nativeQuery = true)
  Long findLastUsedEmpno();

  @Query(value = "SELECT * FROM ts_emp ORDER BY emp_no", nativeQuery = true)
  Page<TS_Emp> findAllOrderBy(Pageable pageable);

  // 아니 이 자식 왜 안되냐고
  // @Query(value = "INSERT INTO ts_emp (emp_no, id, name, eng_name, tel, dept_code, phone, position_code, sex, reg_no, email, semail, zip, address_1, address_2, mil_yn, join_code, salary, join_date, retire_date, image, password, note) VALUES ((SELECT NVL(MAX(emp_no), 0)+1 FROM ts_emp), :id, :name, :eng_name, :tel, :dept_code, :phone, :position_code, :sex, :reg_no, :email, :semail, :zip, :address_1, :address_2, :mil_yn, :join_code, :salary, :join_date, :retire_date, :image, :password, :note)", nativeQuery = true)
  // void empSave(String id, String name, String eng_name, String tel, String dept_code, String phone, String position_code, String sex, String reg_no, String email, String semail, String zip, String address_1, String address_2, String mil_yn, String join_code, String salary, String join_date, String retire_date, String image, String password, String note);

  // 이것도 안되면 emp 추가 어떻게 해야하지
  // @Query(value = "INSERT INTO ts_emp(emp_no, id, name, eng_name, tel, dept_code, phone, position_code, sex, reg_no, email, semail, zip, address_1, address_2, mil_yn, join_code, salary, join_date, retire_date, image, password, note) VALUES(:emp_no, :id, :name, :eng_name, :tel, :dept_code, :phone, :position_code, :sex, :reg_no, :email, :semail, :zip, :address_1, :address_2, :mil_yn, :join_code, :salary, :join_date, :retire_date, :image, :password, :note", nativeQuery = true)
  // void insertEmp(String emp_no, String id, String eng_name, String tel, String dept_code, String phone, String position_code, String sex, String reg_no, String email, String semail, String zip, String address_1, String address_2, String mil_yn, String join_code, Integer salary, String join_date, String retire_date, String image, String password, String note);
  

  
}
