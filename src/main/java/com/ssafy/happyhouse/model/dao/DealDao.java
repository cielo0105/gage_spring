package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.DealDto;

@Mapper
public interface DealDao {
	int registDeal(DealDto dealDto);

	List<DealDto> getList(double ha, double qa, double oa, double pa);

	DealDto getDeal(int id);
}
