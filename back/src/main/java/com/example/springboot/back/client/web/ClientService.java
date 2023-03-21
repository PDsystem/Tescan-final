package com.example.springboot.back.client.web;

import com.example.springboot.back.client.entity.Client;
import com.example.springboot.back.client.entity.ClientRepository;
import com.example.springboot.back.client.entity.ClientOrganizationKey;
import com.example.springboot.back.client.web.dtos.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import com.example.springboot.back.model.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientService {

    @Autowired
    private final ClientRepository repository;
    private final EntityManager em;

    // 현재 날짜/시간
    Date now = new Date();

    // 포맷팅 정의
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

    // 포맷팅 적용
    String formatedNow = formatter.format(now);

    /**
     * 목록 가져오기
     */
    public Header<List<ClientDto>> getClientList(Pageable pageable,String searchKeyword,String searchType) {

        Page<Client> clientEntities = null;
        // ClientOrganizationKey idKey = new ClientOrganizationKey();
        // clientEntities = repository.findAll(pageable);
        if(searchKeyword==null || searchKeyword.isEmpty()){
            clientEntities = repository.findAll(pageable);
        }else{
            switch(searchType) {
                case "": 
                    // clientEntities = repository.findByCorRegNoOrClientNameContaining(pageable,searchKeyword); clientOrganizationKey
                    break;
                case "CORREGNO": 
                clientEntities = repository.findByCorRegNoContaining(pageable,searchKeyword);
                break;
                case "CUSNO": 
                    clientEntities = repository.findByCusNoContaining(pageable,searchKeyword);
                    break;
                case "CUSTOMERNAME": 
                    clientEntities = repository.findByNameContaining(pageable,searchKeyword);
                    break;
            }
        }

        List<ClientDto> dtos = new ArrayList<>();
        for (Client entity : clientEntities) {
            ClientDto dto = ClientDto.builder()
                    // .cor_reg_no(entity.getClientOrganizationKey().getCorRegNo())
                    // .cus_no(entity.getClientOrganizationKey().getCusNo())
                    .cor_reg_no(entity.getCorRegNo())
                    .cus_no(entity.getCusNo())
                    .name(entity.getName())
                    .dept(entity.getDept())
                    .position(entity.getPosition())
                    .zip(entity.getZip())
                    .address1(entity.getAddress1())
                    .address2(entity.getAddress2())
                    .email(entity.getEmail())
                    .tel(entity.getTel())
                    .phone(entity.getPhone())
                    .engineer(entity.getEngineer())
                    .note(entity.getNote())
                    .semail(entity.getSemail())
                    .mod_date(entity.getModDate())
                    .mod_id(entity.getModId())
                    .reg_date(entity.getRegDate())
                    .reg_id(entity.getRegId())
                    .build();
            dtos.add(dto);
        }
        Pagination pagination = new Pagination(
            (int) clientEntities.getTotalElements()
            , pageable.getPageNumber() + 1
            , pageable.getPageSize()
            , 10
            );
        return Header.OK(dtos, pagination);
    }
        /**
     * 단건 가져오기
     */
    public ClientDto getClient(String corRegNo,String cusNo) {
        
        ClientOrganizationKey idKey = new ClientOrganizationKey(corRegNo,cusNo);
        Client entity = repository.findById(idKey).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));

        return ClientDto.builder()
                        .cor_reg_no(entity.getCorRegNo())
                        .cus_no(entity.getCusNo())
                        .name(entity.getName())
                        .dept(entity.getDept())
                        .position(entity.getPosition())
                        .zip(entity.getZip())
                        .address1(entity.getAddress1())
                        .address2(entity.getAddress2())
                        .email(entity.getEmail())
                        .semail(entity.getSemail())
                        .tel(entity.getTel())
                        .phone(entity.getPhone())
                        .engineer(entity.getEngineer())
                        .reg_date(entity.getRegDate())
                        .reg_id(entity.getRegId())
                        .mod_date(entity.getModDate())
                        .mod_id(entity.getModId())
                        .note(entity.getNote())
                        .build();
    }

    /** 등록     */

    public void create(ClientDto clientDto) {    
    
        Client entity = Client.builder()
                        .corRegNo(clientDto.getCor_reg_no())
                        .cusNo(clientDto.getCus_no())
                        .name(clientDto.getName())
                        .dept(clientDto.getDept())
                        .position(clientDto.getPosition())
                        .zip(clientDto.getZip())
                        .address1(clientDto.getAddress1())
                        .address2(clientDto.getAddress2())
                        .email(clientDto.getEmail())
                        .semail(clientDto.getSemail())
                        .tel(clientDto.getTel())
                        .phone(clientDto.getPhone())
                        .engineer(clientDto.getEngineer())
                        .regDate(now)
                        .regId("Tescan")
                        .modDate(now)
                        .modId("Tescan")
                        .note(clientDto.getNote())
                        .build();
                em.persist(entity);
    }

    /**
     * 수정
     */
    public void update(ClientDto clientDto) {

        ClientOrganizationKey idKey = new ClientOrganizationKey(clientDto.getCor_reg_no(),clientDto.getCus_no());
        Client entity = repository.findById(idKey).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
            entity.setCorRegNo(clientDto.getCor_reg_no());
            entity.setCusNo(clientDto.getCus_no());
            entity.setName(clientDto.getName());
            entity.setDept(clientDto.getDept());
            entity.setPosition(clientDto.getPosition());
            entity.setZip(clientDto.getZip());
            entity.setAddress1(clientDto.getAddress1());
            entity.setAddress2(clientDto.getAddress2());
            entity.setEmail(clientDto.getEmail());
            entity.setTel(clientDto.getTel());
            entity.setPhone(clientDto.getPhone());
            entity.setEngineer(clientDto.getEngineer());
            entity.setNote(clientDto.getNote());
            entity.setSemail(clientDto.getSemail());
            entity.setModDate(now);
            entity.setModId("Tescan");
        
            repository.save(entity);
    
    }

    /** 삭제     */
    public void delete(String corRegNo,String cusNo) {

        ClientOrganizationKey idKey = new ClientOrganizationKey(corRegNo,cusNo);
        Client entity = repository.findById(idKey).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));

        repository.delete(entity);
    
    }
}
