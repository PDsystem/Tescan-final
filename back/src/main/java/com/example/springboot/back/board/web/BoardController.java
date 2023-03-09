package com.example.springboot.back.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.springboot.back.board.entity.Board;
import com.example.springboot.back.board.web.dtos.BoardDto;
//import com.example.springboot.backend.session.SessionConst;

//import java.util.List;




@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/list")
    public Page<Board>  boardList(Model model,Pageable pageable,@RequestBody Map<String,Object> params) { 

        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
         
        String searchKeyword=(String) params.get("searchKeyword");

         Page<Board> list =null;
         if(searchKeyword ==null){
             list=boardService.getBoardList(pageable,page);
         }else{
             list=boardService.boardSearchList(searchKeyword, pageable,page);
         }
        
       
        return list; 
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        System.out.println(id);
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public Board create(@RequestBody BoardDto boardDto) {
        return boardService.create(boardDto);
    }

    @PatchMapping("/board")
    public Board update(@RequestBody BoardDto boardDto) {
        return boardService.update(boardDto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}

