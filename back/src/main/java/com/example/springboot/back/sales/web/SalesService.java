package com.example.springboot.back.sales.web;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.sales.entity.Sales;
import com.example.springboot.back.sales.entity.SalesRepository;
import com.example.springboot.back.sales.web.dtos.SalesDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final EntityManager em;
    
    //목록 가져오기

    // public List<SalesDto> getSalesList(){
    //     List<Sales> salesEntities = salesRepository.findAll();
    //     List<SalesDto> dtos = new ArrayList<>();

    //     for (Sales entity : salesEntities) {
    //         SalesDto dto = SalesDto.builder()
    //                 .visit_no(entity.getVisit_no())
    //                 .empno(entity.getEmpno())
    //                 .cor_reg_no(entity.getCor_reg_no())
    //                 .visit_purpose(entity.getVisit_purpose())
    //                 .visit_contents(entity.getVisit_contents())
    //                 .visit_date(entity.getVisit_date())
    //                 .note(entity.getNote())
    //                 .build();

    //         dtos.add(dto);
    //     }

    //     return dtos;
    // }

    public Page<Sales> getSalesList(Pageable pageable,int page) {

        return salesRepository.findAll(PageRequest.of(page, 10));
    }
    public Page<Sales> salesSearchList(String searchKeyword,Pageable pageable,int page ){
        return salesRepository.findByEmpnoContaining(searchKeyword,PageRequest.of(page, 10));
    }

    // 방문내역 가져오기
    public SalesDto getSales(String id) {
        Sales entity = salesRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return SalesDto.builder()
                .visit_no(entity.getVisit_no())
                .empno(entity.getEmpno())
                .cor_reg_no(entity.getCor_reg_no())
                .visit_purpose(entity.getVisit_purpose())
                .visit_contents(entity.getVisit_contents())
                .visit_date(entity.getVisit_date())
                .note(entity.getNote())
                .build();
    }

    //추가
    @Transactional
    public void create(SalesDto salesDto) {
        salesDto.setVisit_date(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println("----------------------------");
        System.out.println(salesDto.getEmpno());
        Sales entity= Sales.builder().
                        visit_no(salesDto.getVisit_no()).
                        empno(salesDto.getEmpno()).
                        cor_reg_no(salesDto.getCor_reg_no()).
                        visit_purpose(salesDto.getVisit_purpose()).
                        visit_contents(salesDto.getVisit_contents()).
                        visit_date(salesDto.getVisit_date()).
                        note(salesDto.getNote()).
                        build();
                        em.persist(entity);
    }

    public void update(SalesDto salesDto) {
        Sales entity = salesRepository.findById(salesDto.getVisit_no()).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
            entity.setVisit_purpose(salesDto.getVisit_purpose());
            entity.setVisit_contents(salesDto.getVisit_contents());
            entity.setNote(salesDto.getNote());
            salesRepository.save(entity);
    
    }

    // //수정
    // public void update(SalesDto salesDto) {
    //     System.out.println(salesDto.getEmpno());
    //     Sales entity= Sales.builder().
    //                         visit_purpose(salesDto.getVisit_purpose()).
    //                         visit_contents(salesDto.getVisit_contents()).
    //                         note(salesDto.getNote()).
    //                     build();
                        
    //                     salesRepository.save(entity);                   
    // }

    //삭제
    public int salesDelete(String id){
        
        salesRepository.deleteById(id);
        return 1;
    }

}
