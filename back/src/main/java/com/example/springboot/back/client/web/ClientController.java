package com.example.springboot.back.client.web;

import lombok.RequiredArgsConstructor;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.springboot.back.client.web.dtos.ClientDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * client Controller(복합 키를 이용한 controller)
 * CRUD 실행
 */
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ClientController {
    
    private final ClientService clientService;

    /**
     * 거래처 아이디 리스트를 출력 또는 검색한 값에 대한 리스트 출력
     * 페이징 처리는 Header를 통해 실행
     * @param pageable
     * @param request
     * @param keyword
     * @param searchType
     * @return clientService.getClientList(pageable,keyword,searchType)
     */
    @GetMapping("/client/list")
    public Header<List<ClientDto>> clientList(@PageableDefault(sort = {"corRegNo"}) Pageable pageable
                                                    ,HttpServletRequest request,String keyword,String searchType) { 
        HttpSession session = request.getSession();
        // pageable = PageRequest.of(page, 10, Sort.by("contentDate").descending());
        // Pageable sortedByPriceDescNameAsc =
    // PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
        return clientService.getClientList(pageable,keyword,searchType); 
    }
    /**
     * 거래처 사업자 번호와 고객번호를 이용하여 단건 조회
     * @param corRegNo
     * @param cusNo
     * @return clientService.getClient(corRegNo,cusNo)
     */
    @PostMapping("/client/{corRegNo}/{cusNo}")
    public ClientDto getClient(@PathVariable String corRegNo,@PathVariable String cusNo) {

        return clientService.getClient(corRegNo,cusNo);
    }//corRegNo,cusNo는 컨트롤러 내에서만 같은 것을 부르면 됩니다.(다른것과 변수명이 다르다고 큰 차이가 생기진 않음)

    /** 새로운 사업자 번호를 등록한다.
     * 사업자 번호는 원래 수기로 입력 하기 때문에 전제 조건에 자동 추가에 해당하는 max가 포함 되어 있지 않다.
     * 하지만, 고객번호는 자동 생성을 할 수 있는 max+1이 들어가야 하지만 현재는 두가지 다 수기로 입력하게 되어 있다.
     * @param clientDto
     */
    @PostMapping("/client")
    public void create(@RequestBody ClientDto clientDto) {
        // System.out.println(clientDto.getCor_reg_no());
    
        // System.out.println(clientDto.getCus_no());
        clientService.create(clientDto);
    }

    /**
     * 단건 조회에 들어간 값을 이용하여 수정
     * @param clientDto
     */    
    @PatchMapping("/client")
    public void update(@RequestBody ClientDto clientDto) {
        clientService.update(clientDto);
    }
    /**
     * 거래처 사업자 번호와 고객번호를 이용하여 단건 조회를 이용한 삭제
     * @param corRegNo
     * @param cusNo
     */
    @DeleteMapping("/client/{corRegNo}/{cusNo}")
    public void delete(@PathVariable String corRegNo,@PathVariable String cusNo) { 
        clientService.delete(corRegNo,cusNo);
    }
}
