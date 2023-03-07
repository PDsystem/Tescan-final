package com.example.springboot.back.ts_emp.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<TS_EmpDto> empList(@PageableDefault(size = 12) Pageable pageable) {

        return ts_EmpService.getEmpList(pageable);
    }

    // emp 한 건 조회하기
    @GetMapping("/emp/{emp_no}")
    public TS_EmpDto getEmp(@PathVariable String emp_no) {

        return ts_EmpService.getEmp(emp_no);
    }
}
