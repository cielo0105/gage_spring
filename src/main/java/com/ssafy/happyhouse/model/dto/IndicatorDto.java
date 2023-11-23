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
public class IndicatorDto {
	private String year;
	private String dongCd;
	private String dongNm;
	private String changeCd;
	private String changeNm;
	private String ingAvg;
	private String closeAvg;
	private String sIngAvg;
	private String sCloseAvg;
}
