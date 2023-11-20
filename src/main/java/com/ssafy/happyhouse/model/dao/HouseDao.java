package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.DongDto;
import com.ssafy.happyhouse.model.dto.GuInfoDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.MonthlyGuInfoDto;

@Mapper
public interface HouseDao {
	DongDto dongCode(DongDto dongCode);

	List<HouseInfoDto> searchByDongCode(long dongCode);

	List<HouseInfoDto> searchByAptNo(long aptNo);
	
	List<GuInfoDto> getGuInfoList(long code);
	List<MonthlyGuInfoDto> getMonthlyGuInfoList(long code);

	long getDongCode(String dong);
}
