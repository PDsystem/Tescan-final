package com.example.springboot.back.sales.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TS_SALES_TASKLIST")
@Entity
public class Sales {
    @Id     
     //번호
     private String visit_no;

     //사원번호
     private String empno;

     //거래처번호
     private String cor_reg_no;

     //방문목적
     private String visit_purpose;

     //방문내용
     private String visit_contents;

     //방문날짜

     private LocalDateTime visit_date;

     //비고
     private String note;
}
