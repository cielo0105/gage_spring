package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ChatDao;
import com.ssafy.happyhouse.model.dao.DealDao;
import com.ssafy.happyhouse.model.dto.DealDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService{
	private final DealDao dao;
	private final ChatDao chatDao;

	@Override
	public int regist(DealDto dealDto) {
		return dao.registDeal(dealDto);
	}

	@Override
	public List<DealDto> getList(double ha, double qa, double oa, double pa) {
		return dao.getList(ha, qa, oa, pa);
	}

	@Override
	public DealDto getDeal(int id) {
		int result = chatDao.getDealId(id);
		return dao.getDeal(result);
	}
}
