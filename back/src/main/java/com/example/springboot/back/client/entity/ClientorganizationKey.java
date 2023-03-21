package com.example.springboot.back.client.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class ClientOrganizationKey implements Serializable{

    /** COR_REG_NO 사업자 등록 번호(not null) */    
    // @Id
    @Column(name = "cor_reg_no",nullable = false, unique = true)
    private String corRegNo;

    /* CUS_NO 고객번호(not null) */
    // @Id
    @Column(name = "cus_no",nullable = false, unique = true)
    private String cusNo;


    public ClientOrganizationKey(String corRegNo, String cusNo) {
		this.corRegNo = corRegNo;
		this.cusNo = cusNo;
	}
}
