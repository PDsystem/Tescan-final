package com.example.springboot.back.salesOppo.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.salesOppo.entity.SalesOppo;
import com.example.springboot.back.salesOppo.web.dtos.SalesOppoDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class SalesOppoController {
    private final SalesOppoService salesOppoService;

    // 목록 조회하기
    // @GetMapping("/oppo/list")
    // public List<SalesOppoDto> oppoList(HttpServletRequest request) { 
    //     // HttpSession session = request.getSession();
    //     // session.getAttribute(SessionConst.LOGIN_MEMBER);
    //     // System.out.println("=====================");
    //     // System.out.println(session.getAttribute("loginUser"));
       
    //     return salesOppoService.getSalesOppoList();
    // }

    @PostMapping("/Oppo/list")
    public Page<SalesOppo> salesOppoList(Model model,Pageable pageable,@RequestBody Map<String,Object> params) { 

        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
         
        String searchKeyword=(String) params.get("searchKeyword");

         Page<SalesOppo> list =null;
         if(searchKeyword ==null){
             list=salesOppoService.getSalesOppoList(pageable,page);
         }else{
             list=salesOppoService.salesOppoSearchList(searchKeyword, pageable,page);
         }       
        return list; 
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