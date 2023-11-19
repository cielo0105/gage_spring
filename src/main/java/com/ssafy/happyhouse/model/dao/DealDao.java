package com.ssafy.happyhouse.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.DealDto;

@Mapper
public interface DealDao {
	int registDeal(DealDto dealDto);
}
