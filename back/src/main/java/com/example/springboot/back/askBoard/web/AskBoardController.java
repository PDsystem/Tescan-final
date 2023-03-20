package com.example.springboot.back.askBoard.web;

import java.util.List;

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

    @GetMapping("/askBoard/List")
    public List<AskBoard> askBoardList(){
        System.out.println("=============================================");
        List<AskBoard> askBoard = askBoardRepository.findAll();
        System.out.println(askBoard);
        return askBoard;
    }

    @PostMapping("/askBoard")
    public void create(@RequestBody AskBoardDto AskBoardDto) {
        System.out.println("@@@@@@@@@@@@@@@@@@@");
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

