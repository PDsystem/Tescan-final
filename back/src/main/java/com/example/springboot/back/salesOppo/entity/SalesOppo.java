package com.example.springboot.back.salesOppo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
// import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(SalesOppoPK.class)
public class SalesOppo implements Serializable {
    
	@Id
	@Column(name = "empno", nullable = false, unique = true)
	private String empno; // 사원번호

	@Id
	@Column(name = "cor_reg_no", nullable = false, unique = true)
	private String corRegNo; // 사업자등록번호

	@Id
	@Column(name = "reg_date", nullable = false, unique = true)
	private LocalDateTime regDate; // 등록일

	private String equipNo; // 장비번호
	private String projectName; // 프로젝트명
	private String conditionCode; // 현황분류코드
	private int possibility; // 가능성
	private String retireDate; //예상종료일
	private int salesForecast;// 예상 금액
	private String endDate; // 완료일자
	private int conPrice; // 계약금액
	private int conPriceDol; // 계약금액 달러
	private String reason; // 사유
	private String note; // 비고
	
	// private Date dateStart;
	// private Date dateEnd;
	// private String name_emp_no; // 담당자(사원번호)
	// private String name_cor_reg_no; // 거래처(사업자등록번호)
	// private int rownum;	//페이징
}
