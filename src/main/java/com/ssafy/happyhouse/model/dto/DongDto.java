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
public class DongDto {
	private String sido;
	private String gu;
	private String dong;
	private String lat;
	private String lng;
}

