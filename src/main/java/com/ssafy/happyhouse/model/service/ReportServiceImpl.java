package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ReportDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private ReportDao dao;
	@Override
	public List<String> getLocalPeopleRank(String code) {
		return dao.getLocalPeopleRank(code);
	}

}
