package com.example.springboot.back.askBoard.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import com.example.springboot.back.askBoard.entity.AskBoard;
import com.example.springboot.back.askBoard.entity.AskBoardRepository;
import com.example.springboot.back.askBoard.web.dtos.AskBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AskBoardController {
    private final AskBoardRepository askBoardRepository;
    private final AskBoardService askBoardService;

    // 목록 가져오기
    @PostMapping("/askBoard/List")
    public Page<AskBoard> askBoardList(Pageable pageable, @RequestBody Map<String,Object> params) {
        int page=0;
        if(params.get("searchKeyword")!=null){
            page=(int) params.get("page");
        }

        String searchKeyword=(String) params.get("searchKeyword");
        Page<AskBoard> list = null;
        if(params.get("searchkeyWord")!=null){
            searchKeyword=(String) params.get("searchKeyword");
        }

        String searchType="TOTAL";
        if(params.get("searchType")!=null){
            searchType=(String) params.get("searchType");
        }

    } 

    @GetMapping("/askBoard/List")
    public List<AskBoard> askBoardList(){
        System.out.println("=============================================");
        List<AskBoard> askBoard = askBoardRepository.findAll();
        System.out.println(askBoard);
        return askBoard;
    }

    @PostMapping("/askBoard")
    public void create(@RequestBody AskBoardDto AskBoardDto) {
        System.out.println("######################");
        System.out.println(AskBoardDto);
        askBoardService.create(AskBoardDto);
    }

   @PatchMapping("/askBoard")
    public void update(@RequestBody AskBoardDto AskBoardDto) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // System.out.println(params);
        askBoardService.update(AskBoardDto);
    }

    @DeleteMapping("/askBoard/{id}")
    public void delete(@PathVariable String id) {
        askBoardService.delete(id);
    }
}

