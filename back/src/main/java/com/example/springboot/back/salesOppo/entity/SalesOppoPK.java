package com.example.springboot.back.salesOppo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class SalesOppoPK implements Serializable{
    // @Id
	@Column(name = "empno", nullable = false, unique = true)
	private String empno; // 사원번호

	// @Id
	@Column(name = "cor_reg_no", nullable = false, unique = true)
	private String corRegNo; // 사업자등록번호

	// @Id
	@Column(name = "reg_date", nullable = false, unique = true)
	private LocalDateTime regDate; // 등록일

    public SalesOppoPK(String empno, String corRegNo, LocalDateTime regDate) {
        this.empno = empno;
        this.corRegNo = corRegNo;
        this.regDate = regDate;
    }
}
