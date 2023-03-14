package com.example.springboot.back.codeMaster.entity;
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
@Table(name="TS_CODE_MASTER")
@Entity
public class CodeMaster {
    @Id
    @Column(name = "class_code")
    private String classCode;

    private String className;
    private String useYn;
    private String mainTable;
    private String mainColumn;
    private String classDesc;
    private LocalDateTime registTime;
    private String registId;
    private LocalDateTime modifyTime;
    private String modifyId;


}
