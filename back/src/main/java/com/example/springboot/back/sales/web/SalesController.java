package com.example.springboot.back.sales.web;

// import java.util.List;
import java.util.Map;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.back.sales.entity.Sales;
import com.example.springboot.back.sales.entity.SalesRepository;
import com.example.springboot.back.sales.web.dtos.SalesDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class SalesController {
    private final SalesService salesService;
    private final SalesRepository salesRepository;

 @PostMapping("/sales/list")
    public Page<Sales>  salesList(Model model,Pageable pageable,@RequestBody Map<String,Object> params) { 

        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
         
        String searchKeyword=(String) params.get("searchKeyword");

         Page<Sales> list =null;
         if(searchKeyword ==null){
             list=salesService.getSalesList(pageable,page);
         }else{
             list=salesService.salesSearchList(searchKeyword, pageable,page);
         }
        
       
        return list; 
    }

    @PostMapping("/sales")
    public Sales create(@RequestBody SalesDto salesDto) {
        Boolean trueOrFalse=salesRepository.existsById(salesDto.getVisit_no());
        if(trueOrFalse == true){
            System.out.println("false로진입");
            salesService.update(salesDto);
           
        }else{
            salesDto.setVisit_no(salesDto.getVisit_no());
            salesService.create(salesDto);
        }
        
        
        System.out.println(salesDto);
        return null;
    }

    

    @DeleteMapping("/salesDelete/{id}")
    public int salesDelete(@PathVariable String id){
        System.out.println(id);
        int result=salesService.salesDelete(id);
        return result;
    }
}
