package com.example.springboot.back.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
     CREATE TABLE TS_USER.TS_CUSTOMER
    (
    COR_REG_NO     VARCHAR2(10 BYTE)              NOT NULL,
    CUSTOMER_NAME  VARCHAR2(100 BYTE)             NOT NULL,
    TYPECODE       VARCHAR2(1 BYTE),
    EMAIL          VARCHAR2(50 BYTE),
    REGION1        VARCHAR2(3 BYTE),
    REGION2        VARCHAR2(3 BYTE),
    FAX            VARCHAR2(11 BYTE),
    WEB            VARCHAR2(255 BYTE),
    ADDRESS1       VARCHAR2(255 BYTE),
    ADDRESS2       VARCHAR2(255 BYTE),
    HIGH_CUSTOMER  VARCHAR2(100 BYTE),
    NOTE           VARCHAR2(255 BYTE),
    TEL            VARCHAR2(11 BYTE),
    SEMAIL         VARCHAR2(50 BYTE),
    CONTENT_ID     VARCHAR2(255 CHAR),
    CONTENT_PW     VARCHAR2(255 CHAR)
    )
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TS_CUSTOMER")
@Entity
public class Customer{

    // /* rowNum */
    // private int row_num;

    /* cor_reg_no 사업자 등록번호(not null) */
    @Id
    @Column(name = "cor_reg_no",nullable = false, unique = true)
    private String corRegNo;
        
    // /* CUS_NO 고객번호(not null) */
    // private String cusNo;

    /* CUSTOMER_NAME 고객명(not null) */
    private String customerName;

    /* TYPECODE 타입 코드*/
    private String typeCode;

    /* EMAIL 이메일 */
    private String email;

    /* REGION1 */
    private String region1;
    
    /* REGION2 */
    private String region2;

    /* FAX 팩스 */
    private String fax;

    /* WEB 웹사이트*/
    private String web;

    /* ADDRESS1 주소 */
    private String address1;

    /* ADDRESS2 상세 주소 */
    private String address2;

    /* HIGH_CUSTOMER 상위 거래처 */
    private String high_customer;

    /* NOTE 비고 */
    private String note;

    /* TEL 전화번호 */
    private String tel; 

    /*  SEMAIL 골벵이 뒤의 메일 주소*/
    private String semail;

    /*  CONTENT_ID */
    private String content_id;

    /*  CONTENT_PW*/
    private String content_pw;
    
}