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

// import java.time.LocalDateTime;
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
                        .reg_date(entity.getRegDate())
                        .reg_id(entity.getRegId())
                        .mod_date(entity.getModDate())
                        .mod_id(entity.getModId())
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
    //  /**
    //  * 단건 가져오기
    //  */
    // public RestDto getRest(Integer restNo) {     

    //     Restaurant entity = repository.findById(restNo).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));
    //     return RestDto.builder()
    //             .rest_no(entity.getRestNo())
    //             .rest_name(entity.getRestName())
    //             .division_no(entity.getDivisionNo())
    //             .price(entity.getPrice())
    //             .address1(entity.getAddress1())
    //             .address2(entity.getAddress2())
    //             .zip(entity.getZip())
    //             .reg_date(entity.getRegDate())
    //             .reg_id(entity.getRegId())
    //             .mod_date(entity.getModDate())
    //             .mod_id(entity.getModId())
    //             .note(entity.getNote())
    //             .build();
  
    // }
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
                .reg_date(entity.getRegDate())
                .reg_id(entity.getRegId())
                .mod_date(entity.getModDate())
                .mod_id(entity.getModId())
                .note(entity.getNote())
                .build();
  
    }
}

