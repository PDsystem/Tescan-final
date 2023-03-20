package com.example.springboot.back.salesOppo.web;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.salesOppo.entity.SalesOppo;
import com.example.springboot.back.salesOppo.entity.SalesOppoRepository;
import com.example.springboot.back.salesOppo.web.dtos.SalesOppoDto;

import lombok.RequiredArgsConstructor;

@EnableCaching
@RequiredArgsConstructor
@Service
public class SalesOppoService {

    private final SalesOppoRepository salesOppoRepository;
    private final EntityManager em;

    public Page<SalesOppo> getSalesOppoList(Pageable pageable,int page) {
        return salesOppoRepository.findAll(PageRequest.of(page, 10));
    }

    public Page<SalesOppo> salesOppoSearchList(String empSearch,Pageable pageable,int page ){
        return salesOppoRepository.findByEmpnoContaining(empSearch,PageRequest.of(page, 10));
    }

    // 리스트 가져오기
    @Cacheable
    public SalesOppoDto getSalesOppo(String id) {
        SalesOppo entity = salesOppoRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return SalesOppoDto.builder()
            .empno(entity.getEmpno())
            .reg_date(entity.getReg_date())
            .equip_no(entity.getEquip_no())
            .cor_reg_no(entity.getCor_reg_no())
            .project_name(entity.getProject_name())
            .condition_code(entity.getCondition_code())
            .possibility(entity.getPossibility())
            .retire_date(entity.getRetire_date())
            .sales_forecast(entity.getSales_forecast())
            .end_date(entity.getEnd_date())
            .con_price(entity.getCon_price())
            .con_price_dol(entity.getCon_price_dol())
            .reason(entity.getReason())
            .note(entity.getNote())
            .name_emp_no(entity.getName_emp_no())
            .name_cor_reg_no(entity.getName_cor_reg_no())
        .build();
    }

    //추가
    @Transactional
    public void create(SalesOppoDto salesOppoDto) {
       // codeMasterDto.setRegist_time(LocalDate.now());
        salesOppoDto.setReg_date(LocalDateTime.now());
        String retire_date_to_save = parseDateToSave(salesOppoDto.getRetire_date());
        String end_date_to_save = parseDateToSave(salesOppoDto.getEnd_date());
        SalesOppo entity= SalesOppo.builder()
            .empno(salesOppoDto.getEmpno())
            .reg_date(salesOppoDto.getReg_date())
            .equip_no(salesOppoDto.getEquip_no())
            .cor_reg_no(salesOppoDto.getCor_reg_no())
            .project_name(salesOppoDto.getProject_name())
            .condition_code(salesOppoDto.getCondition_code())
            .possibility(salesOppoDto.getPossibility())
            .retire_date(retire_date_to_save)
            .sales_forecast(salesOppoDto.getSales_forecast())
            .end_date(end_date_to_save)
            .con_price(salesOppoDto.getCon_price())
            .con_price_dol(salesOppoDto.getCon_price_dol())
            .reason(salesOppoDto.getReason())
            .note(salesOppoDto.getNote())
            .build();
        em.persist(entity);     
    }

    //수정
    public void update(SalesOppoDto salesOppoDto) {
        salesOppoDto.setReg_date(LocalDateTime.now());
        String retire_date_to_save = parseDateToSave(salesOppoDto.getRetire_date());
        String end_date_to_save = parseDateToSave(salesOppoDto.getEnd_date());
        SalesOppo entity = salesOppoRepository.findById(salesOppoDto.getEmpno()).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
            entity.setEmpno(salesOppoDto.getEmpno());
            entity.setReg_date(salesOppoDto.getReg_date());
            entity.setCor_reg_no(salesOppoDto.getCor_reg_no());
            entity.setEquip_no(salesOppoDto.getEquip_no());
            entity.setProject_name(salesOppoDto.getProject_name());
            entity.setCondition_code(salesOppoDto.getCondition_code());
            entity.setPossibility(salesOppoDto.getPossibility());
            entity.setRetire_date(retire_date_to_save);
            entity.setSales_forecast(salesOppoDto.getSales_forecast());
            entity.setEnd_date(end_date_to_save);
            entity.setCon_price(salesOppoDto.getCon_price());
            entity.setCon_price_dol(salesOppoDto.getCon_price_dol());
            entity.setReason(salesOppoDto.getReason());
            entity.setNote(salesOppoDto.getNote());
    
            salesOppoRepository.save(entity);
    }

    //삭제
    public void delete(String id) {
        SalesOppo entity = salesOppoRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        salesOppoRepository.delete(entity);
    }

    // yyyyMMdd 형태로 파싱해주는 함수
    public String parseDateToSave(String parsedDate) {
        if (parsedDate != null) {
            String originalYear = parsedDate.substring(0, 4);
            String originalMonth = parsedDate.substring(5, 7);
            String originalDay = parsedDate.substring(8, 10);
            String originalDate = originalYear + originalMonth + originalDay;

            return originalDate;
        } else {
            return null;
        }
    }

    public Page<SalesOppo> salesOppList(String searchKeyword, String searchType, Pageable pageable, int page) {
        return null;
    }
}
