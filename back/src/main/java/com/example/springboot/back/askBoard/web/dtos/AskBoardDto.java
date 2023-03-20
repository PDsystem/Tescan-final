package com.example.springboot.back.askBoard.web.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AskBoardDto implements Serializable {
    private String content_no;
    private String content_title;
    private String contents;
    private String content_id;
    private String content_pw;
    private String content_date;
    private String disclosure;
}
