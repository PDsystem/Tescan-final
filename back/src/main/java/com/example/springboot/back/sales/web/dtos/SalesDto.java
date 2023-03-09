package com.example.springboot.back.sales.web.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDto implements Serializable{
    
    //번호

    private String visit_no;

    //사원번호

    private String emp_no;

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
