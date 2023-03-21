package com.example.springboot.back.askBoard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import com.example.springboot.back.askBoard.entity.AskBoard;
import com.example.springboot.back.askBoard.entity.AskBoardRepository;
import com.example.springboot.back.askBoard.web.dtos.AskBoardDto;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
@Transactional 
public class AskBoardService {
    

    @Autowired
    private final AskBoardRepository askBoardRepository;
    private final EntityManager em;
    //private final AskBoardController AskBoardController;
    
    
    public AskBoardDto getBoard(String id) {  //데이터 가져오기 함수, 데이터 전송
        AskBoard entity = askBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return AskBoardDto.builder()
            .content_no(entity.getContentNo())
            .content_title(entity.getContentTitle())
            .contents(entity.getContents())
            .content_id(entity.getContentId())
            .content_pw(entity.getContentPw())
            .content_date(entity.getContentDate())
            .disclosure(entity.getDisclosure())
            .build();
        
    }
    //생성

    public void create(AskBoardDto askBoardDto) {

        AskBoard entity=AskBoard.builder().
                        contentNo(askBoardDto.getContent_no()).
                        contentTitle(askBoardDto.getContent_title()).
                        contents(askBoardDto.getContents()).
                        contentId(askBoardDto.getContent_id()).
                        contentPw(askBoardDto.getContent_pw()).
                        contentDate(askBoardDto.getContent_date()).
                        disclosure(askBoardDto.getDisclosure()).
                        build();
        em.persist(entity);
    }

    // public String askBoardMax() {
    //     System.out.println(askBoardRepository.askBoardMax());
    //     return askBoardRepository.askBoardMax();
    // }


    //수정
    public void update(AskBoardDto askBoardDto) {
        AskBoard entity = askBoardRepository.findById(askBoardDto.getContent_no()).orElseThrow(()-> new RuntimeException("게시물을 찾을 수 없습니다."));
        entity.setContentNo(askBoardDto.getContent_no());
        entity.setContentTitle(askBoardDto.getContent_title());
        entity.setContents(askBoardDto.getContents());
        entity.setContentId(askBoardDto.getContent_id());
        entity.setContentPw(askBoardDto.getContent_pw());
        entity.setContentDate(askBoardDto.getContent_date());
        entity.setDisclosure(askBoardDto.getDisclosure());

        askBoardRepository.save(entity);
    }

    // 삭제
    public void delete(String id) {
        AskBoard entity = askBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        askBoardRepository.delete(entity);

    }


}
