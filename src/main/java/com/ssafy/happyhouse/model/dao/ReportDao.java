package com.ssafy.happyhouse.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDao {
	Map<String, Double> getLocalPeopleRank(String code); // 인구 순위 구하기
}
