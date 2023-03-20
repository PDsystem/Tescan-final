package com.example.springboot.back.askBoard.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.Table;
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

    // /* rowNum */
    // private int idx;

       /* content_No 글번호 */
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    @Column(name = "contentNo")
    private String contentNo;
    
    /* content_title 제목(not null) */
    private String contentTitle;

    /* contents 게시글 내용*/
    private String contents;

    /* content id 내용*/
    private String contentId;

    /* content password 내용*/
    private String contentPw;

    /* content 내용*/
    private String contentDate;

    /* 공개여부*/
    private String disclosure;


}

