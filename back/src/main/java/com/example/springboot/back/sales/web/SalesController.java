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
import com.example.springboot.back.sales.web.dtos.SalesDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class SalesController {
    private final SalesService salesService;

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

    @GetMapping("/sales/{id}")
    public SalesDto getSales(@PathVariable String id) {
        System.out.println("7777777777777777777777777777777777");
        return salesService.getSales(id);
    }
    @PostMapping("/sales")
    public Sales create(@RequestBody SalesDto salesDto) {
        return salesService.create(salesDto);
    }
    @PatchMapping("/sales")
    public Sales update(@RequestBody SalesDto salesDto) {
        return salesService.update(salesDto);
    }

    @DeleteMapping("/sales/{id}")
    public void delete(@PathVariable String id) {
        salesService.delete(id);
    }

}
