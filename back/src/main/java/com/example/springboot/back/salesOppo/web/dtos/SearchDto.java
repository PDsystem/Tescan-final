package com.example.springboot.back.salesOppo.web.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDto implements Serializable {
    //서치
	private String empSearch;
    private String corSearch;
    private String startDate;
    private String endDate;
}
