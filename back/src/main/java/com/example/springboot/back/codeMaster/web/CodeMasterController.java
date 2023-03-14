package com.example.springboot.back.codeMaster.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.springboot.back.codeMaster.entity.CodeMaster;
import com.example.springboot.back.codeMaster.entity.CodeMasterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class CodeMasterController {
    
    private final CodeMasterRepository codeMasterRepository;

    @PostMapping("/codeMaster/codeMasterList")
    public String codeMasterList(){
        List<CodeMaster> list =codeMasterRepository.findByClasscode("98");
        System.out.println(list);
        return "gd";
    }
}
