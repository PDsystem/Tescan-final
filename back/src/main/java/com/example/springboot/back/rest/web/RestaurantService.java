package com.example.springboot.back.rest.web;

import com.example.springboot.back.rest.entity.Restaurant;
import com.example.springboot.back.rest.entity.RestaurantRepository;
import com.example.springboot.back.rest.web.dtos.RestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

import com.example.springboot.back.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository repository;
    private final EntityManager em;


    /**
     * 목록 가져오기
     */
    public Header<List<RestDto>> getRestList(Pageable pageable,String searchKeyword,String searchType) {

        Page<Restaurant> restEntities = null;
        if(searchKeyword==null || searchKeyword.isEmpty()){
            restEntities = repository.findAll(pageable);
        }else{
            // switch(searchType) {
            //     case "": 
            //         // restEntities = repository.findByCorRegNoOrRestNameContaining(pageable,searchKeyword);
            //         break;
            //     case "CORREGNO": 
            //         restEntities = repository.findByRestNoContaining(pageable,searchKeyword);
            //         break;
            //     case "CUSTOMERNAME": 
            //         restEntities = repository.findByRestNameContaining(pageable,searchKeyword);
            //         break;
        }
        
        List<RestDto> dtos = new ArrayList<>();
        for (Restaurant entity : restEntities) {
            RestDto dto = RestDto.builder()
                        .rest_no(entity.getRestNo())
                        .rest_name(entity.getRestName())
                        .division_no(entity.getDivisionNo())
                        .division(null)
                        .price(entity.getPrice())
                        .address1(entity.getAddress1())
                        .address2(entity.getAddress2())
                        .zip(entity.getZip())
                        .note(entity.getNote())
                        .build();
            dtos.add(dto);
        }
        Pagination pagination = new Pagination(
            (int) restEntities.getTotalElements()
            , pageable.getPageNumber() + 1
            , pageable.getPageSize()
            , 10
         );
        return Header.OK(dtos, pagination);
    }
     /**
     * 단건 가져오기
     */
    public RestDto getRest(Integer restNo) {     
        
        Restaurant entity = repository.findById(restNo).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));
        return RestDto.builder()
                .rest_no(entity.getRestNo())
                .rest_name(entity.getRestName())
                .division_no(entity.getDivisionNo())
                .price(entity.getPrice())
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .zip(entity.getZip())
                .note(entity.getNote())
                .build();  
    }
     /**
     * 랜덤 가져오기(Integer restNo)
     */
    public RestDto getRandomRest() {
        
        int restNoMax = repository.selectCountId();
        int restNo = (int)(Math.random()*restNoMax);
    
        Restaurant entity = repository.findById(restNo).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));

        return RestDto.builder()
                .rest_no(entity.getRestNo())
                .rest_name(entity.getRestName())
                .division_no(entity.getDivisionNo())
                .price(entity.getPrice())
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .zip(entity.getZip())
                .note(entity.getNote())
                .build();

    }
    /** 등록     */

    public void create(RestDto restDto) {
    
        // if(restDto==null){
            
        // }
        Restaurant entity = Restaurant.builder()
                .restNo(restDto.getRest_no())
                .restName(restDto.getRest_name())
                .divisionNo(restDto.getDivision_no())
                .price(restDto.getPrice())
                .address1(restDto.getAddress1())
                .address2(restDto.getAddress2())
                .zip(restDto.getZip())
                .modDate(Date.from(Instant.now()))
                .modId("Tescan")
                .regDate(Date.from(Instant.now()))
                .regId("Tescan")
                .note(restDto.getNote())
                .build();
                
                em.persist(entity);
    }

    /**
     * 수정
     */
    public void update(RestDto restDto) {
        Restaurant entity = repository.findById(restDto.getRest_no()).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));

            entity.setRestNo(restDto.getRest_no());
            entity.setRestName(restDto.getRest_name());
            entity.setDivisionNo(restDto.getDivision_no());
            entity.setPrice(restDto.getPrice());
            entity.setAddress1(restDto.getAddress1());
            entity.setAddress2(restDto.getAddress2());
            entity.setZip(restDto.getZip());
            entity.setModDate(Date.from(Instant.now()));
            entity.setModId("Tescan");
            entity.setNote(restDto.getNote());

            repository.save(entity);
    
    }
    /**
     * 삭제
     */
    public void delete(Integer id) {
        Restaurant entity = repository.findById(id).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
        repository.delete(entity);
    }
}

