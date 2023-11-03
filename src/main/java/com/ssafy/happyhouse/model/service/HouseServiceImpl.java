package com.ssafy.happyhouse.model.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GuInfoDto;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.MonthlyGuInfoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService{
	
	private final HouseDao dao;
	
	@Override
	public List<HouseInfoDto> searchByDongCode(long dongCode) {
		return dao.searchByDongCode(dongCode);
	}

	@Override
	public List<HouseDealDto> searchByAptNo(long aptNo) {
		return dao.searchByAptNo(aptNo);
	}

	@Override
	public DongCodeDto dongCode(DongCodeDto dongCode) {
		return dao.dongCode(dongCode);
	}

	@Override
	public List<GuInfoDto> getGuInfoList(long code) {
		List<GuInfoDto> list = dao.getGuInfoList(code);
		Collections.sort(list);
		
		return list;
	}

	@Override
	public List<MonthlyGuInfoDto> getMonthlyGuInfoList(long code) {
		return dao.getMonthlyGuInfoList(code);
	}

}
