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
    private String contentNo;
    private String contentTitle;
    private String contents;
    private String contentId;
    private String contentPw;
    private String contentDate;
    private String disclosure;
}
