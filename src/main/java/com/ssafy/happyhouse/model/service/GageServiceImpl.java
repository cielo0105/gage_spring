package com.ssafy.happyhouse.model.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.GageDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GageServiceImpl implements GageService{
	
	private final GageDao dao;

	@Override
	public List<String> getMainCategory() {
		return dao.getMainCategory(); 
	}

	@Override
	public List<String> getMiddleCategory(String main) {
		return dao.getMiddleCategory(main);
	}

	@Override
	public List<String> getSubCategory(String mid) {
		return dao.getSubCategory(mid);
	}

	@Override
	public List<String> getResult(String sub) {
		return dao.getResult(sub);
	}
	
	
}
