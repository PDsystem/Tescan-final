package com.example.springboot.back.ts_emp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springboot.back.ts_emp.entity.TS_Emp;
import com.example.springboot.back.ts_emp.entity.TS_EmpRepository;
import com.example.springboot.back.ts_emp.web.dtos.TS_EmpDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TS_EmpService {

    // DI
    private final TS_EmpRepository ts_EmpRepository;

    // emp 목록 조회하기
    public List<TS_EmpDto> getEmpList(Pageable pageable) {
        Page<TS_Emp> empsEntity = ts_EmpRepository.findAll(pageable);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < empsEntity.getTotalPages(); i++) {
            pageNumbers.add(i);
        }

        System.out.println("getTotalPages : " + empsEntity.getTotalPages());
        System.out.println("getNumber : " + empsEntity.getNumber());
        System.out.println("getNumberOfElements : " + empsEntity.getNumberOfElements());
        System.out.println("getTotalElements : " + empsEntity.getTotalElements());

        List<TS_EmpDto> ts_EmpDto = new ArrayList<>();
        for (TS_Emp empEntity : empsEntity) {
            ts_EmpDto.add(TS_EmpDto.builder()
                    .emp_no(empEntity.getEmp_no())
                    .name(empEntity.getName())
                    .position_code(empEntity.getPosition_code())
                    .dept_code(empEntity.getDept_code())
                    .tel(empEntity.getTel())
                    .prev(empsEntity.getNumber() - 1)
                    .next(empsEntity.getNumber() + 1)
                    .pageNumbers(pageNumbers)
                    .build());
        }
        return ts_EmpDto;
    }

    // emp 한 건 조회하기
    public TS_EmpDto getEmp(String emp_no) {
        Optional<TS_Emp> empOp = ts_EmpRepository.findById(emp_no);
        if (empOp.isPresent()) {
            TS_Emp empEntity = empOp.get();

            return TS_EmpDto.builder()
                    .emp_no(empEntity.getEmp_no())
                    .id(empEntity.getId())
                    .name(empEntity.getName())
                    .eng_name(empEntity.getEng_name())
                    .tel(empEntity.getTel())
                    .dept_code(empEntity.getDept_code())
                    .phone(empEntity.getPhone())
                    .position_code(empEntity.getPosition_code())
                    .sex(empEntity.getSex())
                    .reg_no(empEntity.getReg_no())
                    .email(empEntity.getEmail())
                    .semail(empEntity.getSemail())
                    .zip(empEntity.getZip())
                    .address_1(empEntity.getAddress_1())
                    .address_2(empEntity.getAddress_2())
                    .mil_yn(empEntity.getMil_yn())
                    .join_code(empEntity.getJoin_code())
                    .salary(empEntity.getSalary())
                    .join_date(empEntity.getJoin_date())
                    .retire_date(empEntity.getRetire_date())
                    .image(empEntity.getImage())
                    .password(empEntity.getPassword())
                    .note(empEntity.getNote())
                    .build();
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다");
        }
    }
}
