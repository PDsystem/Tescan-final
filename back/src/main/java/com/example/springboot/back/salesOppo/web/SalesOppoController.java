package com.example.springboot.back.salesOppo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.salesOppo.web.dtos.SalesOppoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SalesOppoController {
    private final SalesOppoService salesOppoService;

    // 목록 조회하기
    @GetMapping("/oppo/list")
    public List<SalesOppoDto> oppoList(HttpServletRequest request) { 
        // HttpSession session = request.getSession();
        // session.getAttribute(SessionConst.LOGIN_MEMBER);
        // System.out.println("=====================");
        // System.out.println(session.getAttribute("loginUser"));
       
        return salesOppoService.getSalesOppoList();
    }
}