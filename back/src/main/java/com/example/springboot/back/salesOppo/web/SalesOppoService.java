package com.example.springboot.back.salesOppo.web;

import java.time.LocalDateTime;
// import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.salesOppo.entity.SalesOppo;
import com.example.springboot.back.salesOppo.entity.SalesOppoPK;
import com.example.springboot.back.salesOppo.entity.SalesOppoRepository;
import com.example.springboot.back.salesOppo.web.dtos.SalesOppoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SalesOppoService {

    private final SalesOppoRepository salesOppoRepository;
    private final EntityManager em;

    public Page<SalesOppo> getSalesOppoList(Pageable pageable,int page) {
        return salesOppoRepository.findAll(PageRequest.of(page, 10));
    }

    public Page<SalesOppo> salesOppoEmpSearchList(String empSearch,Pageable pageable,int page ){
        return salesOppoRepository.findByEmpnoContaining(empSearch,PageRequest.of(page, 10));
    }

    public Page<SalesOppo> salesOppoCorSearchList(String corSearch,Pageable pageable,int page ){
        return salesOppoRepository.findByCorRegNoContaining(corSearch,PageRequest.of(page, 10));
    }

    // public Page<SalesOppo> salesOppoDateSearchList(Date dateStart, Date dateEnd,Pageable pageable,int page ){
    //     return salesOppoRepository.findByDateContaining(dateStart, dateEnd, PageRequest.of(page, 10));
    // }

    // public Page<SalesOppo> findByEmpCorContaining(String empSearch, String corSearch,Pageable pageable,int page ){
    //     return salesOppoRepository.findByEmpCorContaining(empSearch,corSearch,PageRequest.of(page, 10));
    // }

    // 리스트 가져오기
    @Cacheable
    public SalesOppoDto getSalesOppo(String empno, String corRegNo, LocalDateTime regDate) {

        SalesOppoPK idKey = new SalesOppoPK(empno, corRegNo, regDate);
        SalesOppo entity = salesOppoRepository.findById(idKey).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        
        return SalesOppoDto.builder()
            .empno(entity.getEmpno())
            .reg_date(entity.getRegDate())
            .equip_no(entity.getEquipNo())
            .cor_reg_no(entity.getCorRegNo())
            .project_name(entity.getProjectName())
            .condition_code(entity.getConditionCode())
            .possibility(entity.getPossibility())
            .retire_date(entity.getRetireDate())
            .sales_forecast(entity.getSalesForecast())
            .end_date(entity.getEndDate())
            .con_price(entity.getConPrice())
            .con_price_dol(entity.getConPriceDol())
            .reason(entity.getReason())
            .note(entity.getNote())
            // .name_emp_no(entity.getName_emp_no())
            // .name_cor_reg_no(entity.getName_cor_reg_no())
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
            .regDate(salesOppoDto.getReg_date())
            .equipNo(salesOppoDto.getEquip_no())
            .corRegNo(salesOppoDto.getCor_reg_no())
            .projectName(salesOppoDto.getProject_name())
            .conditionCode(salesOppoDto.getCondition_code())
            .possibility(salesOppoDto.getPossibility())
            .retireDate(retire_date_to_save)
            .salesForecast(salesOppoDto.getSales_forecast())
            .endDate(end_date_to_save)
            .conPrice(salesOppoDto.getCon_price())
            .conPriceDol(salesOppoDto.getCon_price_dol())
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

        SalesOppoPK idKey = new SalesOppoPK(salesOppoDto.getEmpno(), salesOppoDto.getCor_reg_no(), salesOppoDto.getReg_date());
        SalesOppo entity = salesOppoRepository.findById(idKey).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
            entity.setEmpno(salesOppoDto.getEmpno());
            entity.setRegDate(salesOppoDto.getReg_date());
            entity.setCorRegNo(salesOppoDto.getCor_reg_no());
            entity.setEquipNo(salesOppoDto.getEquip_no());
            entity.setProjectName(salesOppoDto.getProject_name());
            entity.setConditionCode(salesOppoDto.getCondition_code());
            entity.setPossibility(salesOppoDto.getPossibility());
            entity.setRetireDate(retire_date_to_save);
            entity.setSalesForecast(salesOppoDto.getSales_forecast());
            entity.setEndDate(end_date_to_save);
            entity.setConPrice(salesOppoDto.getCon_price());
            entity.setConPriceDol(salesOppoDto.getCon_price_dol());
            entity.setReason(salesOppoDto.getReason());
            entity.setNote(salesOppoDto.getNote());
    
            salesOppoRepository.save(entity);
    }

    //삭제
    public void delete(String empno, String corRegNo, String regDate) {
        SalesOppoPK idKey = new SalesOppoPK(empno, corRegNo, regDate);
        SalesOppo entity = salesOppoRepository.findById(idKey).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        
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
