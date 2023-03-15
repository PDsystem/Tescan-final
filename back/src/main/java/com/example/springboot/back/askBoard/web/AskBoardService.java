package com.example.springboot.back.askBoard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.back.askBoard.entity.AskBoard;
import com.example.springboot.back.askBoard.entity.AskBoardRepository;
import com.example.springboot.back.askBoard.web.dtos.AskBoardDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AskBoardService {

    @Autowired
    private final AskBoardRepository askBoardRepository;
    // private final AskBoardController AskBoardController;
    
    public AskBoardDto getBoard(String id) {  //데이터 가져오기 함수, 데이터 전송
        AskBoard entity = askBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return AskBoardDto.builder()
            .contentNo(entity.getContentNo())
            .contentTitle(entity.getContentTitle())
            .contents(entity.getContents())
            .contentId(entity.getContentId())
            .contentPw(entity.getContentPw())
            .contentDate(entity.getContentDate())
            .disclosure(entity.getDisclosure())
            .build();
        
    }

    public void delete(String id) {
        AskBoard entity = askBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        askBoardRepository.delete(entity);

    }

    

}
