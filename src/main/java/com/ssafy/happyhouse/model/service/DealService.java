package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.DealDto;

public interface DealService {
	int regist(DealDto dealDto);

	List<DealDto> getList(double ha, double qa, double oa, double pa);
}
