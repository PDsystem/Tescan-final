package com.example.springboot.back.client.web.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    // /* rowNum */
   // private int row_num;

   /* COR_REG_NO 사업자등록번호(not null) */
    private String corRegNo;

    /* CUS_NO 고객번호(not null) */
    private String cusNo;

    /* NAME 고객명(not null) */
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
           
    /* NOTE 비고 */
    private String note;
 
    /*  SEMAIL 골벵이 뒤의 메일 주소*/
    private String semail;

    /*  MOD_DATE 등록일자*/
    private LocalDate modDate;

    /*  MOD_ID 등록ID */
    private String modId;

    /*  REG_DATE 등록일자*/
    private LocalDate regDate;

    /*  REG_ID 등록ID */
    private String regId;
        
    /*  PHONE 휴대폰번호 */
    private String phone;

    /*  ENGINEER 담당자 */
    private String engineer;
}
