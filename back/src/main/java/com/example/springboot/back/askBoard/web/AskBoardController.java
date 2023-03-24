package com.example.springboot.back.askBoard.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.askBoard.entity.AskBoard;
import com.example.springboot.back.askBoard.web.dtos.AskBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController 
    public class AskBoardController {
    private final AskBoardService askBoardService;

    // 게시판리스트 가져오기
    @PostMapping("/askBoard/List")
    public Page<AskBoard> askBoardList(Pageable pageable, @RequestBody Map<String,Object> params) {
        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
        // 검색어 분류코드(글번호, 아이디, 제목)
        String searchType= "";
        if(params.get("searchType")!=null) {
            searchType=(String) params.get("searchType");
        }

        // 게시판리스트에 있는 값을 검색 
        String searchKeyword=(String) params.get("searchKeyword");
        Page<AskBoard> askList = null;
        if(searchKeyword ==null){
            askList=askBoardService.getaskBoardList(pageable,page);
        }else{
            askList= askBoardService.askBoardSearchList(searchKeyword, pageable, page,searchType);
        }
        System.out.println(searchKeyword);
        System.out.println(page);
        System.out.println(searchType);

        return askList;
    } 
    
    // 게시글 추가
    @PostMapping("/askBoard")
    public void create(@RequestBody AskBoardDto AskBoardDto) {
        System.out.println("######################");
        System.out.println(AskBoardDto.getDisclosure());
        askBoardService.create(AskBoardDto);
    }

    //게시글 수정
   @PatchMapping("/askBoard")
    public void update(@RequestBody AskBoardDto AskBoardDto) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // System.out.println(params);
        askBoardService.update(AskBoardDto);
    }

    //게시글 삭제
    @DeleteMapping("/askBoard/{id}")
    public void delete(@PathVariable String id) {
        askBoardService.delete(id);
    }
}