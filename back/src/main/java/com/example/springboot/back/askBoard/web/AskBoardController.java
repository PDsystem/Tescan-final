package com.example.springboot.back.askBoard.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.askBoard.entity.AskBoard;
import com.example.springboot.back.askBoard.entity.AskBoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AskBoardController {

    private final AskBoardRepository askBoardRepository;
    private final AskBoardService askBoardService;

    @GetMapping("/askBoard/List")
    public List<AskBoard>  askBoardList(){
        System.out.println("=============================================");
        List<AskBoard> askBoard = askBoardRepository.findAll();
        System.out.println(askBoard);
        return askBoard;
    }

    @DeleteMapping("/askBoard/{id}")
    public void delete(@PathVariable String id) {
        askBoardService.delete(id);
    }
}

