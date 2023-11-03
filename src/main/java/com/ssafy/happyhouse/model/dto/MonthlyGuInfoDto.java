package com.ssafy.happyhouse.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyGuInfoDto {
	private long gugunCode;
	private int dealYear;
	private int dealMonth;
	private String sidoName;
	private String gugunName;
	private int dealCnt;
	private int dealAmountAvg;
}
