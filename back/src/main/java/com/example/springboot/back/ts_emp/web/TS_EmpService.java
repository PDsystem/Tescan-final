package com.example.springboot.back.ts_emp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.back.ts_emp.entity.TS_Emp;
import com.example.springboot.back.ts_emp.entity.TS_EmpRepository;
import com.example.springboot.back.ts_emp.web.dtos.TS_EmpDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TS_EmpService {

    // DI
    private final TS_EmpRepository ts_EmpRepository;
    private final EntityManager em;

    // emp 목록 조회하기
    public List<TS_EmpDto> getEmpList(Pageable pageable) {
        Page<TS_Emp> empsEntity = ts_EmpRepository.findAllOrderBy(pageable);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < empsEntity.getTotalPages(); i++) {
            pageNumbers.add(i);
        }

        // System.out.println("getTotalPages : " + empsEntity.getTotalPages());
        // System.out.println("getNumber : " + empsEntity.getNumber());
        // System.out.println("getNumberOfElements : " +
        // empsEntity.getNumberOfElements());
        // System.out.println("getTotalElements : " + empsEntity.getTotalElements());

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
        // 1. 사용자 있는지 확인하기
        Optional<TS_Emp> empOp = ts_EmpRepository.findById(emp_no);

        // 2. 있다면 entity로 만들기
        if (empOp.isPresent()) {
            TS_Emp empEntity = empOp.get();

            // 3. join_date, retire_date : yyyy-MM-dd 형태로 파싱하기
            String parsedJoinDate = parseDateToPrint(empEntity.getJoin_date());
            String parsedRetireDate = parseDateToPrint(empEntity.getRetire_date());

            // 4. entity에 데이터 넣기
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
                    .join_date(parsedJoinDate)
                    .retire_date(parsedRetireDate)
                    .image(empEntity.getImage())
                    .password(empEntity.getPassword())
                    .note(empEntity.getNote())
                    .build();
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다");
        }
    }

    // emp 추가
    @Transactional
    public void writeEmp(TS_EmpDto ts_EmpDto) {
        System.out.println("=============Emp 추가 서비스 시작이에용===========");

        /** 새로운 사번 만들기 */
        Long last_emp_no = ts_EmpRepository.findLastUsedEmpno();
        Long new_long_emp_no = last_emp_no + 1;
        String new_emp_no = Long.toString(new_long_emp_no);
        System.out.println("새로운 사번 만들어졌니? : " + new_emp_no);

        /** join_date, retire_date 저장 형태 변경 */
        String join_date_to_save = parseDateToSave(ts_EmpDto.getJoin_date());
        String retire_date_to_save = parseDateToSave(ts_EmpDto.getRetire_date());

        TS_Emp empEntity = TS_Emp.builder()
                .emp_no(new_emp_no)
                .id(ts_EmpDto.getId())
                .name(ts_EmpDto.getName())
                .eng_name(ts_EmpDto.getEng_name())
                .tel(ts_EmpDto.getTel())
                .dept_code(ts_EmpDto.getDept_code())
                .phone(ts_EmpDto.getPhone())
                .position_code(ts_EmpDto.getPosition_code())
                .sex(ts_EmpDto.getSex())
                .reg_no(ts_EmpDto.getReg_no())
                .email(ts_EmpDto.getEmail())
                .semail(ts_EmpDto.getSemail())
                .zip(ts_EmpDto.getZip())
                .address_1(ts_EmpDto.getAddress_1())
                .address_2(ts_EmpDto.getAddress_2())
                .mil_yn(ts_EmpDto.getMil_yn())
                .join_code(ts_EmpDto.getJoin_code())
                .salary(ts_EmpDto.getSalary())
                .join_date(join_date_to_save) // 230313 형태로 DB에 저장
                .retire_date(retire_date_to_save) // 230313 형태로 DB에 저장
                .image(ts_EmpDto.getImage())
                .password(ts_EmpDto.getPassword())
                .note(ts_EmpDto.getNote())
                .build();

                em.persist(empEntity);

        System.out.println("=============Emp 추가 서비스 끝이에용===========");
    }

    // join_date, retire_date를 yyyy-MM-dd 형태로 파싱해주는 함수
    // 요렇게 해야 인풋태그(type:date)에서 출력이 된다
    public String parseDateToPrint(String originalDate) {

        if (originalDate != null) {
            String year = originalDate.substring(0, 4);
            String month = originalDate.substring(4, 6);
            String day = originalDate.substring(6, 8);
            String parsedDate = year + "-" + month + "-" + day;

            return parsedDate;
        } else {
            return null;
        }
    }

    // join_date, retire_date를 yyyyMMdd 형태로 파싱해주는 함수
    // DB 안에는 이 형태로 저장해야 한다 (귀찮게시리)
    public String parseDateToSave(String parsedDate) {

        if (parsedDate != null) {
            String originalYear = parsedDate.substring(0, 4);
            String originalMonth = parsedDate.substring(5, 7);
            String originalDay = parsedDate.substring(8, 10);
            String originalDate = originalYear + originalMonth + originalDay;

            return originalDate;
        } else {
            return null;
        }
    }
}
