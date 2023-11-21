package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDao {
	List<String> getLocalPeopleRank(String code); // 인구 순위 구하기
}
