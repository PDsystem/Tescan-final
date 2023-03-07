package com.example.springboot.back.ts_emp.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.back.ts_emp.web.dtos.TS_EmpDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class TS_EmpController {
    
    // DI
    private final TS_EmpService ts_EmpService;

    // emp 목록 조회하기
    @GetMapping("/emp/list")
    public List<TS_EmpDto> empList(@PageableDefault(size = 12) Pageable pageable){

        return ts_EmpService.getEmpList(pageable);
    }
}
