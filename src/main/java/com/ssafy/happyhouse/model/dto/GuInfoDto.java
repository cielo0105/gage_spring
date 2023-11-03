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
public class GuInfoDto implements Comparable<GuInfoDto> {
	private long gugunCode;
	private String sidoName;
	private String gugunName;
	private int dealCnt;
	private int dealAmountAvg;
	@Override
	public int compareTo(GuInfoDto o) {
        // dealAmountAvg를 기준으로 내림차순 정렬
        return Integer.compare(o.dealAmountAvg, this.dealAmountAvg);
	}
}
