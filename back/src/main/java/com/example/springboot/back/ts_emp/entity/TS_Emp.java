package com.example.springboot.back.ts_emp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TS_Emp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String empno; // 사번

    private String id; // 아이디
    private String name; // 이름
    private String eng_name; // 영문성명
    private String tel; // 전화번호
    private String dept_code; // 부서명
    private String phone; // 휴대폰번호
    private String position_code; // 직위구분코드
    private String sex; // 성별
    private String reg_no; // 주민등록번호
    private String email; // 이메일(앞)
    private String semail; // 이메일(뒤)
    private String zip; // 우편번호?
    private String address_1; // 주소1
    private String address_2; // 주소2(상세주소)
    private String mil_yn; // 군필여부
    private String join_code; // 입사구분코드
    private Integer salary; // 연봉
    private String join_date; // 입사일자
    private String retire_date; // 퇴사일자
    private String image; // 사진
    private String password; // 비밀번호
    private String note; // 비고
}
