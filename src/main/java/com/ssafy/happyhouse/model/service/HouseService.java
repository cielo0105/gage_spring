package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GuInfoDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.MonthlyGuInfoDto;

public interface HouseService {
	List<HouseInfoDto> searchByDongCode(long dongCode);

	List<HouseInfoDto> searchByAptNo(long aptNo);

	DongCodeDto dongCode(DongCodeDto dongCode);

	List<GuInfoDto> getGuInfoList(long code);

	List<MonthlyGuInfoDto> getMonthlyGuInfoList(long code);

	long getDongCode(String dong);
}
