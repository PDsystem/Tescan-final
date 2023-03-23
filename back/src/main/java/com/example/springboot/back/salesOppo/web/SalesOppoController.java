package com.example.springboot.back.salesOppo.web;

import java.time.LocalDateTime;
// import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // 목록 조회
    @PostMapping("/Oppo/list")
    public Page<SalesOppo> salesOppoList(Model model, @PageableDefault(sort = {"regDate"}) Pageable pageable, @RequestBody Map<String,Object> params) { 
        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
         
        String empSearch=(String) params.get("empSearch");
        String corSearch=(String) params.get("corSearch");
        // Date dateStart=(Date) params.get("dateStart");
        // Date dateEnd=(Date) params.get("dateEnd");
            Page<SalesOppo> list =null;
            if(empSearch != null && corSearch == null){
                list=salesOppoService.salesOppoEmpSearchList(empSearch, pageable,page);
            }else if(empSearch == null && corSearch != null){
                list=salesOppoService.salesOppoCorSearchList(corSearch, pageable,page);
            // }else if(empSearch != null && corSearch == null && dateStart != null && dateEnd != null){
            //     list=salesOppoService.salesOppoDateSearchList(dateStart, dateEnd, pageable,page);
            // }else if(empSearch != null && corSearch != null){
            //     list=salesOppoService.findByEmpCorContaining(empSearch, corSearch, pageable,page);
            }else{
                list=salesOppoService.getSalesOppoList(pageable,page);
            }
        return list; 
    }

    @PostMapping("/SalesOppo/{empno}/{corRegNo}/{regDate}")
    public SalesOppoDto getSalesOppo(@PathVariable String empno, @PathVariable String corRegNo, @PathVariable LocalDateTime regDate) {

        return salesOppoService.getSalesOppo(empno, corRegNo, regDate);
    }

    //추가
    @PostMapping("/oppo/insert")
    public String create(@RequestBody SalesOppoDto salesOppoDto) {
        // String result= codeMasterService.codemax();
        // System.out.println(result);
        // System.out.println(codeMasterDto.getClass_code());
        salesOppoDto.setEmpno(salesOppoDto.getEmpno());
        System.out.println("추가");
        salesOppoService.create(salesOppoDto);
        return null;
    }

    //수정
    @PatchMapping("/oppo/")
    public void update(@RequestBody SalesOppoDto salesOppoDto) {
        System.out.println("수정");
        salesOppoService.update(salesOppoDto);
    }

    //삭제
    @DeleteMapping("/salesOppoDelete/{empno}/{corRegNo}/{regDate}")
    public void delete(@PathVariable String empno, @PathVariable String corRegNo, @PathVariable LocalDateTime regDate) {
        salesOppoService.delete(empno, corRegNo, regDate);
    }
}