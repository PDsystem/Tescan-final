package com.example.springboot.back.client.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * 
    CREATE TABLE TS_USER.TS_CLIENT
    (
    COR_REG_NO  VARCHAR2(10 BYTE)                 NOT NULL,
    CUS_NO      VARCHAR2(10 BYTE)                 NOT NULL,
    NAME        VARCHAR2(100 BYTE),
    DEPT        VARCHAR2(100 BYTE),
    POSITION    VARCHAR2(100 BYTE),
    ZIP         VARCHAR2(5 BYTE),
    ADDRESS1    VARCHAR2(255 BYTE),
    ADDRESS2    VARCHAR2(255 BYTE),
    EMAIL       VARCHAR2(50 BYTE),
    TEL         VARCHAR2(11 BYTE),
    PHONE       VARCHAR2(11 BYTE),
    ENGINEER    VARCHAR2(100 BYTE),
    NOTE        VARCHAR2(255 BYTE),
    SEMAIL      VARCHAR2(50 BYTE),
    MOD_DATE    DATE                              DEFAULT sysdate               NOT NULL,
    MOD_ID      VARCHAR2(255 BYTE)                DEFAULT sysdate               NOT NULL,
    REG_DATE    DATE                              DEFAULT sysdate               NOT NULL,
    REG_ID      VARCHAR2(255 BYTE)                DEFAULT sysdate               NOT NULL
    )
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TS_CLIENT")
@Entity
public class Client {

    // /* rowNum */
   // private int row_num;

   /* COR_REG_NO 사업자등록번호(not null) */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String corRegNo;

    /* CUS_NO 고객번호(not null) */
    private String cusNo;

    /* NAME 고객명 */
    private String name;

    /* DEPT 부서 */
    private String dept;

    /* POSITION 직급 */
    private String position;
    
    /* ZIP 우편번호*/
    private String zip;

    /* ADDRESS1 주소 */
    private String address1;

    /* ADDRESS2 상세 주소 */
    private String address2;    
        
    /* EMAIL 이메일 */
    private String email;
        
    /* TEL 전화번호 */
    private String tel; 
    
    /*  PHONE 휴대폰번호 */
    private String phone;

    /* NOTE 비고 */
    private String note;
 
    /*  SEMAIL 골벵이 뒤의 메일 주소*/
    private String semail;

    /*  ENGINEER 담당자 */
    private String engineer;


    /*  MOD_DATE 등록일자*/
    private LocalDate modDate;

    /*  MOD_ID 등록ID */
    private String modId;

    /*  REG_DATE 등록일자*/
    private LocalDate regDate;

    /*  REG_ID 등록ID */
    private String regId;
}
