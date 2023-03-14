package com.example.springboot.back.client.web;



import com.example.springboot.back.client.entity.Client;
import com.example.springboot.back.client.entity.ClientRepository;
import com.example.springboot.back.client.web.dtos.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.springboot.back.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {

    @Autowired
    private final ClientRepository repository;

    /**
     * 목록 가져오기
     */
    public Header<List<ClientDto>> getClientList(Pageable pageable) {
        // Page<Client> entities = repository.selectByClient(pageable);
        Page<Client> entities = repository.findAllByOrderByCorRegNoDesc(pageable);

        List<ClientDto> dtos = new ArrayList<>();
        for (Client entity : entities) {
            ClientDto dto = ClientDto.builder()
                    // .row_num(entity.getRow_num())
                    .corRegNo(entity.getCorRegNo())
                    .cusNo(entity.getCusNo())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .dept(entity.getDept())
                    .position(entity.getPosition())
                    .zip(entity.getZip())
                    .address1(entity.getAddress1())
                    .address2(entity.getAddress2())
                    .email(entity.getEmail())
                    .tel(entity.getTel())
                    .note(entity.getNote())
                    .tel(entity.getTel())
                    .semail(entity.getSemail())
                    .phone(entity.getPhone())
                    .engineer(entity.getEngineer())
                    .build();

            dtos.add(dto);
        }
        // log.info("3");
        Pagination pagination = new Pagination(
            (int) entities.getTotalElements()
            , pageable.getPageNumber() + 1
            , pageable.getPageSize()
            , 10
         );
        //  log.info("4");
        return Header.OK(dtos, pagination);
    }

    /**
     * 검색한 목록 받아오기
     */
    public Header<List<ClientDto>> searchClientList(Pageable pageable, String searchKeyword) {
        // // Page<Client> entities = repository.selectByClient(pageable);
        // Page<Client> entities = repository.findAllByOrderByCorRegNoDesc(pageable);

        // List<ClientDto> dtos = new ArrayList<>();
        // log.info("2");
        // for (Client entity : entities) {
        //     ClientDto dto = ClientDto.builder()
        //             // .row_num(entity.getRow_num())
        //             .corRegNo(entity.getCorRegNo())
        //             .cusNo(entity.getCusNo())
        //             .name(entity.getName())
        //             .email(entity.getEmail())
        //             .dept(entity.getDept())
        //             .position(entity.getPosition())
        //             .zip(entity.getZip())
        //             .address1(entity.getAddress1())
        //             .address2(entity.getAddress2())
        //             .email(entity.getEmail())
        //             .tel(entity.getTel())
        //             .note(entity.getNote())
        //             .tel(entity.getTel())
        //             .semail(entity.getSemail())
        //             .phone(entity.getPhone())
        //             .engineer(entity.getEngineer())
        //             .build();

        //     dtos.add(dto);
        // }
        // log.info("3");
        // Pagination pagination = new Pagination(
        //     (int) entities.getTotalElements()
        //     , pageable.getPageNumber() + 1
        //     , pageable.getPageSize()
        //     , 10
        //  );
        // //  log.info("4");
        // return Header.OK(dtos, pagination);
        return null;
    }
    
}
