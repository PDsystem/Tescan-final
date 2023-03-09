package com.example.springboot.back.salesOppo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private final SalesOppoRepository salesOppoRepository;

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
    }
}