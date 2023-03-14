package com.example.springboot.back.salesOppo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.salesOppo.entity.SalesOppo;
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

    @PostMapping("/oppo/insert")
    public String create(@RequestBody SalesOppoDto salesOppoDto) {
        // String result= codeMasterService.codemax();
        // System.out.println(result);
        // System.out.println(codeMasterDto.getClass_code());
        salesOppoDto.setEmpno(salesOppoDto.getEmpno());
        System.out.println(salesOppoDto);
        salesOppoService.create(salesOppoDto);
        return null;
    }
}