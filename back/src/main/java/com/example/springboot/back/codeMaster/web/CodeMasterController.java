package com.example.springboot.back.codeMaster.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import com.example.springboot.back.codeMaster.entity.CodeMaster;
import com.example.springboot.back.codeMaster.web.dtos.CodeMasterDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class CodeMasterController {
    private final CodeMasterService codeMasterService;
  
    @PostMapping("/codeMaster/codeMasterList")
    public Page<CodeMaster>  boardList(Pageable pageable,@RequestBody Map<String,Object> params) { 
        int page=0;
        if(params.get("page")!=null){
            page=(int) params.get("page");
        }
         System.out.println(params);
         String searchKeyword="";
         if(params.get("searchKeyword")!=null){
            searchKeyword=(String) params.get("searchKeyword");
         }
         
        String searchType="TOTAL";
        if(params.get("searchType")!=null){
            searchType=(String) params.get("searchType");
        }
        
        
         Page<CodeMaster> list=codeMasterService.boardSearchList(searchKeyword,searchType, pageable,page);

         
       
        return list; 
    }
    
    @PostMapping("/codeMaster")
    public String create(@RequestBody CodeMasterDto codeMasterDto) {
        // String result= codeMasterService.codemax();
        // System.out.println(result);
        // System.out.println(codeMasterDto.getClass_code());
        codeMasterDto.setClass_code(codeMasterDto.getClass_code());
        System.out.println(codeMasterDto);
        codeMasterService.create(codeMasterDto);
        return null;
    }
}
