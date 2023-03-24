package com.example.springboot.back.askBoard.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TS_CONTENT")
@Entity
public class AskBoard implements Serializable {
    
    @Id
    @Column(name = "contentNo")
    private String contentNo;           //글번호
    private String contentTitle;        //제목
    private String contents;            //게시글 내용
    private String contentId;           //아이디
    private String contentPw;           //비밀번호
    private LocalDateTime contentDate;  //날짜  
    private String disclosure;          //공개여부
}