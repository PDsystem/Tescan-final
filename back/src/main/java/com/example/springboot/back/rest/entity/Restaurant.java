package com.example.springboot.back.rest.entity;

import java.util.Date;

// import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
CREATE TABLE "TS_USER"."TS_GASAN_RESTAURANT" 
   (	"REST_NO" NUMBER(10,0) NOT NULL ENABLE, 
	"REST_NAME" VARCHAR2(20), 
	"DIVISION_NO" NUMBER(3,0) NOT NULL ENABLE, 
	"PRICE" NUMBER, 
	"ADDRESS1" VARCHAR2(255 CHAR), 
	"ADDRESS2" VARCHAR2(255 CHAR), 
	"ZIP" VARCHAR2(255 CHAR), 
	"REG_DATE" DATE, 
	"REG_ID" VARCHAR2(20), 
	"MOD_DATE" DATE, 
	"MOD_ID" VARCHAR2(20), 
	"NOTE" VARCHAR2(500), 
	 CONSTRAINT "TS_GASAN_RESTAURANT_PK" PRIMARY KEY ("REST_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TS_GASAN_RESTAURANT")
@Entity
public class Restaurant {

    /**REST_NO NUMBER(10,0) NOT NULL ENABLE 음식점번호 */
    @Id
    @GeneratedValue
    private Integer restNo;
    
    /** REST_NAME VARCHAR2(20) 상호명*/
	private String restName;
	
    /** "DIVISION_NO" NUMBER(3,0) NOT NULL ENABLE 구분번호 
        100 한식
        200 일식
        300 중식
        400 패스트푸드
        500 분식
        600 기타
     */
	private int divisionNo;

	/** PRICE NUMBER  가격*/
	private int price;
    
	/** "ADDRESS1" VARCHAR2(255 CHAR) 주소 */
    private String address1;

	/**	"ADDRESS2" VARCHAR2(255 CHAR) 상세 주소     */
    private String address2;

    /**  "ZIP" VARCHAR2(255 CHAR) 우편번호     */
    private String zip;
    
    /** "REG_DATE" DATE 등록일시  */
    private Date regDate;

    /** "REG_ID" VARCHAR2(20) 등록ID */
    private String regId;

    /**    "MOD_DATE" DATE 수정일시     */
    private Date modDate;

    /**    "MOD_ID" VARCHAR2(20) 수정ID  */
    private String modId;
    
    /** "NOTE" VARCHAR2(500) 비고 */
    private String note;
}
