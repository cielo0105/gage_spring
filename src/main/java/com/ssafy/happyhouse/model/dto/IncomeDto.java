package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeDto {
	private String buldGbn;
	private String levelNoOut;
	private String metroNm;
	private int netOperIncome;
	private int netOperIncome2;
	private String regionCd;
	private String regionNm;
	private String researchDate;
	private String sectionNm;
	private String sidoNm;
}
