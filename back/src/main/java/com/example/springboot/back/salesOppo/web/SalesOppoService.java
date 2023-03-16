package com.example.springboot.back.salesOppo.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.salesOppo.entity.SalesOppo;
import com.example.springboot.back.salesOppo.entity.SalesOppoRepository;
import com.example.springboot.back.salesOppo.web.dtos.SalesOppoDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SalesOppoService {

    private final SalesOppoRepository salesOppoRepository;
    private final EntityManager em;

<<<<<<< HEAD
    public Page<SalesOppo> getSalesOppoList(Pageable pageable,int page) {
        return salesOppoRepository.findAll(PageRequest.of(page, 10));
    }

    public Page<SalesOppo> salesOppoSearchList(String empSearch, String corSearch,Pageable pageable,int page ){
        return salesOppoRepository.findByEmpnoContaining(empSearch,corSearch,PageRequest.of(page, 10));
    }

    // 리스트 가져오기
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
=======
    //리스트 조회
    public List<SalesOppoDto> getSalesOppoList() {
        List<SalesOppo> salesOppoEntities = salesOppoRepository.findAll();
            
            List<SalesOppoDto> dtos = new ArrayList<>();
            for (SalesOppo entity : salesOppoEntities) {
                SalesOppoDto dto = SalesOppoDto.builder()
                    // .rownum(entity.getRownum())
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
            dtos.add(dto);
            }
        return dtos;
>>>>>>> parent of 6f79b0e ( 0316 조진형 페이징 / 바인딩 완료)
    }

    //추가
    @Transactional
    public void create(SalesOppoDto salesOppoDto) {
       // codeMasterDto.setRegist_time(LocalDate.now());
        salesOppoDto.setReg_date(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println("----------------------------");
        System.out.println(salesOppoDto.getEmpno());
        String retire_date_to_save = parseDateToSave(salesOppoDto.getRetire_date());
        String end_date_to_save = parseDateToSave(salesOppoDto.getEnd_date());
        SalesOppo entity= SalesOppo.builder().
                        empno(salesOppoDto.getEmpno()).
                        reg_date(salesOppoDto.getReg_date()).
                        equip_no(salesOppoDto.getEquip_no()).
                        cor_reg_no(salesOppoDto.getCor_reg_no()).
                        project_name(salesOppoDto.getProject_name()).
                        condition_code(salesOppoDto.getCondition_code()).
                        possibility(salesOppoDto.getPossibility()).
                        retire_date(retire_date_to_save).
                        sales_forecast(salesOppoDto.getSales_forecast()).
                        end_date(end_date_to_save).
                        con_price(salesOppoDto.getCon_price()).
                        con_price_dol(salesOppoDto.getCon_price_dol()).
                        reason(salesOppoDto.getReason()).
                        note(salesOppoDto.getNote()).
                        build();
                        em.persist(entity);     
    }

    //삭제
    public int salesOppoDelete(String id){ 
        salesOppoRepository.deleteById(id);
        return 1;
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
