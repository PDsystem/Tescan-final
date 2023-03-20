package com.example.springboot.back.client.web.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SELECT 
 *         COR_REG_NO, CUS_NO, NAME, DEPT, 
 *          "POSITION", ZIP, ADDRESS1, ADDRESS2,
 *           EMAIL, TEL, PHONE, ENGINEER, NOTE, SEMAIL,
 *           MOD_DATE, MOD_ID, REG_DATE, REG_ID
 *   FROM TS_USER.TS_CLIENT;
 */

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    /** COR_REG_NO 사업자 등록 번호(not null) */
   private String cor_reg_no;

    /* CUS_NO 고객번호(not null) */
    private String cus_no;

    /** NAME 고객명*/
    private String name;

    /** DEPT 부서*/
    private String dept;

    /** "POSITION" 직책     */
    private String position;

    /** ZIP 우편번호     */
    private String zip;

    /* ADDRESS1 주소 */
    private String address1;

    /* ADDRESS2 상세 주소 */
    private String address2;

    /* EMAIL 이메일 */
    private String email;
    
    /* SEMAIL 이메일2 */
    private String semail;
    
    /** TEL 전화번호 */
     private String tel;
  
    /** PHONE 휴대폰 번호     */
    private String phone;

    /** ENGINEER 담당자    */
    private String engineer;
    
    /** REG_DATE 등록 일자*/
    private Date reg_date;
    
    /** REG_ID    등록ID*/
    private String reg_id;

    /** MOD_DATE 수정 일자    */
    private Date mod_date;
    
    /** MOD_ID 수정 ID   */
    private String mod_id;
    
    /* NOTE 비고 */
    private String note;

}
