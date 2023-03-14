package com.example.springboot.back.client.web;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.springboot.back.client.web.dtos.ClientDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/client/list")
    public Header<List<ClientDto>> clientList(@PageableDefault(sort = {"corRegNo"}) Pageable pageable,
                                                String searchKeyword,HttpServletRequest request) { 

        HttpSession session = request.getSession();
        // System.out.print(pageable.getPageNumber());
        Header<List<ClientDto>> serviceList = null;
        log.info("1");
        if(searchKeyword == null){
            serviceList = clientService.getClientList(pageable);
        }else{
            serviceList = clientService.searchClientList(pageable,searchKeyword);
        }
        return clientService.getClientList(pageable); 
    }
    
}
