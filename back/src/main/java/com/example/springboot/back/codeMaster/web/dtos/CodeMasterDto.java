package com.example.springboot.back.codeMaster.web.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeMasterDto  implements Serializable  {
    private String class_code;
    private String class_name;
    private String use_yn;
    private String main_table;
    private String main_column;
    private String class_desc;
    private LocalDateTime regist_time;
    private String regist_id;
    private LocalDateTime modify_time;
    private String modify_id;
   
}
