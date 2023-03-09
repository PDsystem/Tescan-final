package com.example.springboot.back.sales.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.back.sales.entity.Sales;
import com.example.springboot.back.sales.entity.SalesRepository;
import com.example.springboot.back.sales.web.dtos.SalesDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SalesService {

    @Autowired
    private final SalesRepository salesRepository;

    //목록 가져오기

    public List<SalesDto> getSalesList(){
        List<Sales> salesEntities = salesRepository.findAll();
        List<SalesDto> dtos = new ArrayList<>();

        for (Sales entity : salesEntities) {
            SalesDto dto = SalesDto.builder()
                    .visit_no(entity.getVisit_no())
                    .emp_no(entity.getEmp_no())
                    .cor_reg_no(entity.getCor_reg_no())
                    .visit_purpose(entity.getVisit_purpose())
                    .visit_contents(entity.getVisit_contents())
                    .visit_date(entity.getVisit_date())
                    .note(entity.getNote())
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }

    // 방문내역 가져오기
    public SalesDto getSales(String id) {
        Sales entity = salesRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return SalesDto.builder()
                .visit_no(entity.getVisit_no())
                .emp_no(entity.getEmp_no())
                .cor_reg_no(entity.getCor_reg_no())
                .visit_purpose(entity.getVisit_purpose())
                .visit_contents(entity.getVisit_contents())
                .visit_date(entity.getVisit_date())
                .note(entity.getNote())
                .build();
    }

}
