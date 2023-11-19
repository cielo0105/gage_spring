package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GuInfoDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.MonthlyGuInfoDto;

public interface GageService {
	List<String> getMainCategory(); // 대분류

	List<String> getMiddleCategory(String main);

	List<String> getSubCategory(String mid);

	List<String> getResult(String sub);
}
