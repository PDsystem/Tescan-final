package com.example.springboot.back.salesOppo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TS_SALES_INFO")
@Entity
public class SalesOppo implements Serializable {
    
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	//private int rownum;	//페이징
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String emp_no; // 사원번호
	private String reg_date; // 등록일
	private String equip_no; // 장비번호
	private String cor_reg_no; // 사업자등록번호
	private String project_name; // 프로젝트명
	private String condition_code; // 현황분류코드
	private int possibility; // 가능성
	private String retire_date; //예상종료일
	private int sales_forecast;// 예상 금액
	private String end_date; // 완료일자
	private int con_price; // 계약금액
	private int con_price_dol; // 계약금액 달러
	private String reason; // 사유
	private String note; // 비고
	private String name_emp_no; // 담당자(사원번호)
	private String name_cor_reg_no; // 거래처(사업자등록번호)
}
