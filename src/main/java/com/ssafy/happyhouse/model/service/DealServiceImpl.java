package com.ssafy.happyhouse.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.DealDao;
import com.ssafy.happyhouse.model.dto.DealDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService{
	private final DealDao dao;

	@Override
	public int regist(DealDto dealDto) {
		return dao.registDeal(dealDto);
	}
}
