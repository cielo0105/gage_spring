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
public class DealDto {
	private int id;
	private String type;
	private String address;
	private String detailAddress;
	private double area;
	private String recommend;
	private int floor;
	private boolean elevator;
	private String desc;
	private Date createDate;
}