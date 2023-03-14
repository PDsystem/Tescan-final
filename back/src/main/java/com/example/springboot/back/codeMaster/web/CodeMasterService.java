package com.example.springboot.back.codeMaster.web;





import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.codeMaster.entity.CodeMaster;
import com.example.springboot.back.codeMaster.entity.CodeMasterRepository;
import com.example.springboot.back.codeMaster.web.dtos.CodeMasterDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CodeMasterService {
    
    
    private final CodeMasterRepository codeMasterRepository;
    private final EntityManager em;

    public Page<CodeMaster> getBoardList(Pageable pageable,int page) {
        return codeMasterRepository.findAll(PageRequest.of(page, 10));
    }
    
    public Page<CodeMaster> boardSearchList(String searchKeyword,String searchType,Pageable pageable,int page ){
        System.out.println(searchType);
        String key=searchKeyword;
        Page<CodeMaster> list=null;

            if(key=="TOTAL"){
                System.out.println("TOTAL진입");
                list=codeMasterRepository.findTotal(PageRequest.of(page, 10),searchKeyword);
            }else if (key =="CLASSCODE"){
                System.out.println("CLASS_CODE진입");
                list=codeMasterRepository.findCode(PageRequest.of(page, 10),searchKeyword);
            }else{
                System.out.println("CLASSName진입");
                list=codeMasterRepository.findName(PageRequest.of(page, 10),searchKeyword);
            }
    

        return list;
    }

    public String codemax() {
        System.out.println(codeMasterRepository.codeMax());
        return codeMasterRepository.codeMax();
    }
    @Transactional
    public void create(CodeMasterDto codeMasterDto) {
       // codeMasterDto.setRegist_time( LocalDate.now());
       codeMasterDto.setRegist_time(LocalDateTime.now());
       codeMasterDto.setRegist_id("hong");
        System.out.println(LocalDateTime.now());
        System.out.println("----------------------------");
        System.out.println(codeMasterDto.getClass_code());
        CodeMaster entity= CodeMaster.builder().
                        classCode(codeMasterDto.getClass_code()).
                        className(codeMasterDto.getClass_name()).
                        useYn(codeMasterDto.getUse_yn()).
                        mainTable(codeMasterDto.getMain_table()).
                        mainColumn(codeMasterDto.getMain_column()).
                        classDesc(codeMasterDto.getClass_desc()).
                        registTime(codeMasterDto.getRegist_time()).
                        registId(codeMasterDto.getRegist_id()).
                        modifyTime(codeMasterDto.getModify_time()).
                        modifyId(codeMasterDto.getModify_id()).
                        build();
                        em.persist(entity);
  

                        
    }

	

}
