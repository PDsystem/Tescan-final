package com.example.springboot.back.sales.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;


import com.example.springboot.back.sales.web.dtos.SalesDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class SalesController {
    private final SalesService salesService;

    @GetMapping("/sales/list")
    public List<SalesDto> salesList(HttpServletRequest request) { 
        HttpSession session = request.getSession();
        //session.getAttribute(SessionConst.LOGIN_MEMBER);
        System.out.println("1234567890");
        System.out.println(session.getAttribute("loginUser"));
       
        return salesService.getSalesList(); }

    @GetMapping("/sales/{id}")
    public SalesDto getSales(@PathVariable String id) {
        System.out.println("7777777777777777777777777777777777");
        return salesService.getSales(id);
    }

}
