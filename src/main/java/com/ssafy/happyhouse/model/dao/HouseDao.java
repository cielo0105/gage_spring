package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GuInfoDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.MonthlyGuInfoDto;

@Mapper
public interface HouseDao {
	DongCodeDto dongCode(DongCodeDto dongCode);

	List<HouseInfoDto> searchByDongCode(long dongCode);

	List<HouseInfoDto> searchByAptNo(long aptNo);
	
	List<GuInfoDto> getGuInfoList(long code);
	List<MonthlyGuInfoDto> getMonthlyGuInfoList(long code);
}
