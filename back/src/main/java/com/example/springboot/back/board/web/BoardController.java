package com.example.springboot.back.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;

import com.example.springboot.back.board.entity.Board;
import com.example.springboot.back.board.web.dtos.BoardDto;
//import com.example.springboot.backend.session.SessionConst;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<BoardDto> boardList(HttpServletRequest request) { 
        HttpSession session = request.getSession();
        //session.getAttribute(SessionConst.LOGIN_MEMBER);
        System.out.println("=====================");
        System.out.println(session.getAttribute("loginUser"));
       
        return boardService.getBoardList(); }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
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

