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
	private String addressDetail;
	private double area;
	private String recommend;
	private int floor;
	private int floorAll;
	private String desc;
	private String img;
	private int amount1;
	private int amount2;
	private Date createDate;
	private String lat;
	private String lon;
}